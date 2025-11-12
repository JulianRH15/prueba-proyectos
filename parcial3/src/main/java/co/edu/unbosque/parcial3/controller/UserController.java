package co.edu.unbosque.parcial3.controller;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.parcial3.dto.UserDTO;
import co.edu.unbosque.parcial3.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "*" })
@SecurityRequirement(name = "bearerAuth")
public class UserController {
	@Autowired
	private UserService userServ;

	@PostMapping("/crearconjson")
	public ResponseEntity<String> crearConJson(@RequestBody UserDTO nuevoUsuario) {
		int estado = userServ.createUser(nuevoUsuario);
		switch (estado) {
		case 0:
			return new ResponseEntity<>("Usuario creado", HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("El nombre de usuario ya está en uso", HttpStatus.NOT_ACCEPTABLE);
		case 2:
			return new ResponseEntity<>("La contraseña no es segura", HttpStatus.NOT_ACCEPTABLE);
		case 3:
			return new ResponseEntity<>("El correo electrónico no es válido", HttpStatus.NOT_ACCEPTABLE);
		case 4:
			return new ResponseEntity<>("Las contraseñas no coinciden", HttpStatus.NOT_ACCEPTABLE);
		default:
			return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
