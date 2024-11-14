package miranda.theo.dto;

import java.util.Date;

public class ComputerDto {
	private Long id;
	private int serialNumber;
	private String model;
	private Date releaseDate;
	
	
	private String status;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public int getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model= model;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate= releaseDate;
	}	
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		if ("available".equals(status) || "borrowed".equals(status)) {
	        this.status = status;
	    }
	}
}
