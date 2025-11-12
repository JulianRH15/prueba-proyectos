package co.edu.unbosque.parcial3.dto;



public class JokeDTO {
	private boolean error;
	private String category;
	private String type;
	private String joke;
	private String setup;
	private String delivery;
	private boolean nsfw;
	private boolean religious;
	private boolean political;
	private boolean racist;
	private boolean sexist;
	private boolean explicit;

	public JokeDTO() {
		// TODO Auto-generated constructor stub
	}

	public JokeDTO(boolean error, String category, String type, String joke, String setup, String delivery, boolean nsfw,
			boolean religious, boolean political, boolean racist, boolean sexist, boolean explicit) {
		super();
		this.error = error;
		this.category = category;
		this.type = type;
		this.joke = joke;
		this.setup = setup;
		this.delivery = delivery;
		this.nsfw = nsfw;
		this.religious = religious;
		this.political = political;
		this.racist = racist;
		this.sexist = sexist;
		this.explicit = explicit;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJoke() {
		return joke;
	}

	public void setJoke(String joke) {
		this.joke = joke;
	}

	public String getSetup() {
		return setup;
	}

	public void setSetup(String setup) {
		this.setup = setup;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public boolean isNsfw() {
		return nsfw;
	}

	public void setNsfw(boolean nsfw) {
		this.nsfw = nsfw;
	}

	public boolean isReligious() {
		return religious;
	}

	public void setReligious(boolean religious) {
		this.religious = religious;
	}

	public boolean isPolitical() {
		return political;
	}

	public void setPolitical(boolean political) {
		this.political = political;
	}

	public boolean isRacist() {
		return racist;
	}

	public void setRacist(boolean racist) {
		this.racist = racist;
	}

	public boolean isSexist() {
		return sexist;
	}

	public void setSexist(boolean sexist) {
		this.sexist = sexist;
	}

	public boolean isExplicit() {
		return explicit;
	}

	public void setExplicit(boolean explicit) {
		this.explicit = explicit;
	}

	@Override
	public String toString() {
		return "Joke [error=" + error + ", category=" + category + ", type=" + type + ", joke=" + joke + ", setup="
				+ setup + ", delivery=" + delivery + ", nsfw=" + nsfw + ", religious=" + religious + ", political="
				+ political + ", racist=" + racist + ", sexist=" + sexist + ", explicit=" + explicit + "]";
	}

}
