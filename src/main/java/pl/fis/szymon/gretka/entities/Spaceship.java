package pl.fis.szymon.gretka.entities;

import java.time.LocalTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Spaceship {
	
	@NotBlank
	private String name;
	
	@NotNull
	@Min(value = 0L, message = "The value must be positive")
	private Integer range;
	
	@PastOrPresent
	private LocalTime year_of_manufacturing;

	public Spaceship() {}

	public Spaceship(String name, Integer range, LocalTime time) {
		super();
		this.name = name;
		this.range = range;
		this.year_of_manufacturing = time;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRange() {
		return range;
	}
	public void setRange(Integer range) {
		this.range = range;
	}
	
	@JsonProperty("year-of-manufacturing") 
	public LocalTime getTime() {
		return year_of_manufacturing;
	}

	public void setTime(LocalTime time) {
		this.year_of_manufacturing = time;
	}


	@Override
	public String toString() {
		return "Spaceship [name=" + name + ", range=" + range + "]";
	}
	
	
	
	
}
