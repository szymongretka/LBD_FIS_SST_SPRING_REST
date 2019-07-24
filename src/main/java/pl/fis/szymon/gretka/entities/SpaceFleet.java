package pl.fis.szymon.gretka.entities;

import java.io.Serializable;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;

public class SpaceFleet implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name;
	private List<Spaceship> ships;

	public SpaceFleet() {}
	
	public SpaceFleet(String name, List<Spaceship> ships) {
		this.name = name;
		this.ships = ships;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty(value="ships")
	public List<Spaceship> getList() {
		return ships;
	}
	public void setList(List<Spaceship> ships) {
		this.ships = ships;
	}

	@Override
	public String toString() {
		return "SpaceFleet [name=" + name + ", ships=" + ships + "]";
	}
	
	
	
}