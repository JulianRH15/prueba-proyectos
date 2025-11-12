package co.edu.unbosque.parcial3.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unbosque.parcial3.dto.UserDTO;
import co.edu.unbosque.parcial3.model.User;
import co.edu.unbosque.parcial3.repository.UserRepository;
import co.edu.unbosque.parcial3.util.AESUtil;


@Service
public class UserService {
	@Autowired
	private UserRepository uR;
	@Autowired
	private ModelMapper mM;

	@Autowired
	private PasswordEncoder pE;

	public int createUser(UserDTO data) {
		if (!esContraseñaSegura(data.getPassword())) {
			return 2;
		}
		if (!esCorreoValido(data.getEmail())) {
			return 3;
		}
		if (!data.getPassword().equals(data.getConfirmPassword())) {
			return 4;
		}
		User entity = mM.map(data, User.class);
		if (findUsernameAlreadyTaken(entity)) {
			return 1;
		}
		entity.setPassword(pE.encode(entity.getPassword()));
		if (data.getRole() != null) {
			entity.setRole(data.getRole());
		}
		entity.setUsername(AESUtil.encrypt(entity.getUsername()));
		entity.setEmail(AESUtil.encrypt(entity.getEmail()));
		uR.save(entity);
		return 0;
	}

	public boolean findUsernameAlreadyTaken(User newUser) {
		Optional<User> found = uR.findByUsername(newUser.getUsername());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean exist(Long id) {
		return uR.existsById(id);
	}
	
	public boolean findUsernameAlreadyTaken(String username) {
		Optional<User> found = uR.findByUsername(username);
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