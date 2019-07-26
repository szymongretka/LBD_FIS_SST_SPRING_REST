package pl.fis.szymon.gretka.entities;

import java.time.LocalTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Spaceship extends ResourceSupport {
	
	@NotBlank(message = "{name.notblank}")
	private String name;
	
	@NotNull(message = "{range.notnull}")
	@Min(value = 0L, message = "{range.positive}")
	private Integer speed;
	
	@PastOrPresent
	private LocalTime year_of_manufacturing;

	public Spaceship() {}

	public Spaceship(String name, Integer speed, LocalTime time) {
		super();
		this.name = name;
		this.speed = speed;
		this.year_of_manufacturing = time;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
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
		return "Spaceship [name=" + name + ", range=" + speed + "]";
	}
	
	
	
	
}
