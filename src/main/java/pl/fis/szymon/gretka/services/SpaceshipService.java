package pl.fis.szymon.gretka.services;

import java.util.List;

import pl.fis.szymon.gretka.entities.SpaceFleet;
import pl.fis.szymon.gretka.entities.Spaceship;


public interface SpaceshipService {
	
	public void initializeSpaceshipsAndSpaceFleet();
	public void save(Spaceship spaceship);
	public void delete(String name);
	public SpaceFleet getSpaceFleet();
	public Spaceship getByName(String name);
	public List<Spaceship> getListOfSpaceships();
}
