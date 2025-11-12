package co.edu.unbosque.proyectocalendar.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.proyectocalendar.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event> findByNombre(String nombre);

}
