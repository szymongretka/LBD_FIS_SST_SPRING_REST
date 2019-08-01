package pl.fis.szymon.gretka.services;

import java.util.List;

import pl.fis.szymon.gretka.entities.SpaceFleet;
import pl.fis.szymon.gretka.entities.Spaceship;


public interface SpaceshipService {
	
	public void initializeSpaceshipsAndSpaceFleet();
	public void save(String spacefleetName, Spaceship spaceship);
	public void delete(String spacefleetName, String spaceshipName);
	public SpaceFleet getSpaceFleet(String name);
	public Spaceship getByName(String name);
	public Spaceship getSpaceshipBySpacefleetName(String spaceFleetName, String spaceShipName);
	public List<Spaceship> getListOfSpaceships(String name);
	public List<Spaceship> sortList(String param1, String param2, List<Spaceship> list);
}
