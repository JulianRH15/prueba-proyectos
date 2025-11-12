package co.edu.unbosque.proyectocalendar.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EventDTO {
	private Long id;
	private String title;
	private LocalDate startDate;
	private LocalTime startTime;
	private LocalDate endDate;
	private LocalTime endTime;
	private boolean allDay;
	private String description;
	private String color;
	private List<String> invitedUserEmail;
	private List<String> attachedFiles;
	private String nombre;

	public EventDTO() {
		// TODO Auto-generated constructor stub
	}

	public EventDTO(String title, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime,
			boolean allDay, String description, String color, List<String> invitedUserEmail, List<String> attachedFiles,
			String nombre) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.allDay = allDay;
		this.description = description;
		this.color = color;
		this.invitedUserEmail = invitedUserEmail;
		this.attachedFiles = attachedFiles;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getInvitedUserEmail() {
		return invitedUserEmail;
	}

	public void setInvitedUserEmail(List<String> invitedUserEmail) {
		this.invitedUserEmail = invitedUserEmail;
	}

	public List<String> getAttachedFiles() {
		return attachedFiles;
	}

	public void setAttachedFiles(List<String> attachedFiles) {
		this.attachedFiles = attachedFiles;
	}

	@Override
	public String toString() {
		return "EventDTO [id=" + id + ", title=" + title + ", startDate=" + startDate + ", startTime=" + startTime
				+ ", endDate=" + endDate + ", endTime=" + endTime + ", allDay=" + allDay + ", description="
				+ description + ", color=" + color + ", invitedUserEmail=" + invitedUserEmail + ", attachedFiles="
				+ attachedFiles + ", nombre=" + nombre + "]";
	}

}
