package co.edu.unbosque.proyectocalendar.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.proyectocalendar.dto.EventDTO;
import co.edu.unbosque.proyectocalendar.service.EventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/event")
@CrossOrigin(origins = { "*" })
@SecurityRequirement(name = "bearerAuth")
public class EventController {

	@Autowired
	private EventService eventServ;

	@PostMapping("/crearconjson")
	public ResponseEntity<String> crearConJson(@RequestBody EventDTO nuevoEvento) {
		int estado = eventServ.create(nuevoEvento);
		if (estado == 0) {
			return new ResponseEntity<>("Evento creado", HttpStatus.CREATED);
		} else if (estado == 2) {
			return new ResponseEntity<>("La fecha de fin debe ser posterior a la fecha de inicio",
					HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Error al crear evento", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping("/mostrartodo")
	public ResponseEntity<ArrayList<EventDTO>> mostrarTodo() {
		ArrayList<EventDTO> eventos = eventServ.findAll();
		if (eventos.isEmpty()) {
			return new ResponseEntity<>(eventos, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(eventos, HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping("/eliminarporid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		int status = eventServ.deteleById(id);
		if (status == 0) {
			return new ResponseEntity<>("Evento eliminado con éxito", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error al eliminar el evento. ID no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Long id, @RequestBody EventDTO nuevoEvento) {
		int estado = eventServ.actualizarById(id, nuevoEvento);
		if (estado == 0) {
			return new ResponseEntity<>("Evento actualizado con éxito", HttpStatus.CREATED);
		} else if (estado == 2) {
			return new ResponseEntity<>("La fecha de fin debe ser posterior a la fecha de inicio",
					HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Error al actualizar el evento. ID no encontrado o datos inválidos",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping("/mostrarnombre")
	public ResponseEntity<ArrayList<EventDTO>> mostrarNombre(@RequestParam String nombre) {
		ArrayList<EventDTO> eventos = eventServ.mostrarNombre(nombre);
		if (eventos.isEmpty()) {
			return new ResponseEntity<>(eventos, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventos, HttpStatus.ACCEPTED);
		}
	}
}
