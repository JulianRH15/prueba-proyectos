package co.edu.unbosque.proyectocalendar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectocalendar.dto.EventDTO;
import co.edu.unbosque.proyectocalendar.model.Event;
import co.edu.unbosque.proyectocalendar.repository.EventRepository;
import co.edu.unbosque.proyectocalendar.util.AESUtil;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private ModelMapper modelMapper;

	public int create(EventDTO data) {
		Event entity = modelMapper.map(data, Event.class);

		// Validar que la fecha de fin sea después de la fecha de inicio
		if (entity.getEndDate() != null && entity.getStartDate() != null
				&& !entity.getEndDate().isAfter(entity.getStartDate())) {
			return 2; // Código para fechas inválidas
		}

		try {
			entity.setTitle(AESUtil.encrypt(entity.getTitle()));
			entity.setDescription(AESUtil.encrypt(entity.getDescription()));
			entity.setColor(AESUtil.encrypt(entity.getColor()));

			List<String> emailsEncriptados = new ArrayList<>();
			for (String email : entity.getInvitedUserEmail()) {
				emailsEncriptados.add(AESUtil.encrypt(email));
			}
			entity.setInvitedUserEmail(emailsEncriptados);

			List<String> filesEncriptadas = new ArrayList<>();
			for (String file : entity.getAttachedFiles()) {
				filesEncriptadas.add(AESUtil.encrypt(file));
			}
			entity.setAttachedFiles(filesEncriptadas);

			eventRepo.save(entity);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	public ArrayList<EventDTO> findAll() {
		ArrayList<Event> entityList = (ArrayList<Event>) eventRepo.findAll();
		ArrayList<EventDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			EventDTO dto = modelMapper.map(entity, EventDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	public int deteleById(Long id) {
		Optional<Event> found = eventRepo.findById(id);
		if (found.isPresent()) {
			eventRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	public int actualizarById(Long id, EventDTO data) {
		Optional<Event> found = eventRepo.findById(id);
		if (found.isPresent()) {

			// Validar fechas
			if (data.getEndDate() != null && data.getStartDate() != null
					&& !data.getEndDate().isAfter(data.getStartDate())) {
				return 2; // Código para fechas inválidas
			}

			Event entity = found.get();
			entity.setTitle(AESUtil.encrypt(data.getTitle()));
			entity.setDescription(AESUtil.encrypt(data.getDescription()));
			entity.setColor(AESUtil.encrypt(data.getColor()));

			List<String> emailsEncriptados = new ArrayList<>();
			for (String email : data.getInvitedUserEmail()) {
				emailsEncriptados.add(AESUtil.encrypt(email));
			}
			entity.setInvitedUserEmail(emailsEncriptados);

			List<String> filesEncriptados = new ArrayList<>();
			for (String file : data.getAttachedFiles()) {
				filesEncriptados.add(AESUtil.encrypt(file));
			}
			entity.setAttachedFiles(filesEncriptados);

			entity.setNombre(AESUtil.encrypt(data.getNombre()));
			entity.setStartDate(data.getStartDate());
			entity.setEndDate(data.getEndDate());

			eventRepo.save(entity);
			return 0;
		} else {
			return 1;
		}
	}

	public ArrayList<EventDTO> mostrarNombre(String nombre) {
		ArrayList<Event> entityList = (ArrayList<Event>) eventRepo.findByNombre(nombre);
		ArrayList<EventDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			EventDTO dto = modelMapper.map(entity, EventDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

}
