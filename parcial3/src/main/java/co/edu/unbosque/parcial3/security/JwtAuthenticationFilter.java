package co.edu.unbosque.parcial3.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.edu.unbosque.parcial3.util.AESUtil;



/**
 * Filtro de autenticación JWT que intercepta las solicitudes HTTP. Valida los
 * tokens JWT en las solicitudes y establece la autenticación en el contexto de
 * seguridad. Se ejecuta una vez por cada solicitud.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	/** Utilidad para operaciones con tokens JWT. */
	private final JwtUtil jwtUtil;

	/** Servicio para cargar los detalles del usuario. */
	private final UserDetailsService userDetailsService;

	/**
	 * Constructor que inicializa las dependencias necesarias para el filtro.
	 *
	 * @param jwtUtil            Utilidad para operaciones con tokens JWT
	 * @param userDetailsService Servicio para cargar los detalles del usuario
	 */
	public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {

	    final String authorizationHeader = request.getHeader("Authorization");

	    String jwt = null;
	    String username = null;

	    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	        jwt = authorizationHeader.substring(7);
	        try {
	            String encryptedUsername = jwtUtil.extractUsername(jwt);
	            username = AESUtil.decrypt(encryptedUsername);
	        } catch (Exception e) {
	            logger.error("Error al desencriptar el username del token JWT", e);
	        }
	    }

	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        // 3. Cargamos el usuario desencriptado
	        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

	        // 4. Validamos el token con el usuario
	        if (jwtUtil.validateToken(jwt, userDetails)) {
	            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                    new UsernamePasswordAuthenticationToken(
	                            userDetails, null, userDetails.getAuthorities());

	            usernamePasswordAuthenticationToken
	                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	        }
	    }

	    filterChain.doFilter(request, response);
	}

}
