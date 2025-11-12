package co.edu.unbosque.parcial3.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.parcial3.model.User;


public interface UserRepository extends CrudRepository<User, Long> {
	public Optional<User> findByUsername(String username);

}
