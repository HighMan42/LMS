package dto;

public class Books {
	
	private int id;
	private String name;
	private String author;
	private String availability;
	

	public Books(int id, String name, String author, String availability) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.availability = availability;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
}
