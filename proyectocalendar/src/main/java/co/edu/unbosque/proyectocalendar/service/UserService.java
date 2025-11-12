package co.edu.unbosque.proyectocalendar.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectocalendar.dto.UserDTO;
import co.edu.unbosque.proyectocalendar.model.User;
import co.edu.unbosque.proyectocalendar.repository.UserRepository;
import co.edu.unbosque.proyectocalendar.util.AESUtil;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean exist(Long id) {
		return userRepo.existsById(id);
	}

	public int create(UserDTO data) {
		if (!esContraseñaSegura(data.getPassword())) {
			return 2;
		}
		if (!esCorreoValido(data.getEmail())) {
			return 3;
		}
		if (!data.getPassword().equals(data.getConfirmPassword())) {
			return 4;
		}
		User entity = modelMapper.map(data, User.class);

		if (findUsernameAlreadyTaken(entity)) {
			return 1;
		}

		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		if (data.getRole() != null) {
			entity.setRole(data.getRole());
		}

		entity.setUsername(AESUtil.encrypt(entity.getUsername()));
		entity.setEmail(AESUtil.encrypt(entity.getEmail()));
		userRepo.save(entity);
		return 0;
	}

	public boolean findUsernameAlreadyTaken(User newUser) {
		Optional<User> found = userRepo.findByUsername(newUser.getUsername());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Verifica si un nombre de usuario ya está en uso.
	 *
	 * @param username Nombre de usuario a verificar
	 * @return true si el nombre de usuario ya está en uso, false en caso contrario
	 */
	public boolean findUsernameAlreadyTaken(String username) {
		Optional<User> found = userRepo.findByUsername(username);
		return found.isPresent();
	}

	public boolean esContraseñaSegura(String password) {
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?#])[A-Za-z\\d@$!%*?#]{12,}$";
		return password.matches(regex);
	}

	public boolean esCorreoValido(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		return email.matches(regex);
	}
}