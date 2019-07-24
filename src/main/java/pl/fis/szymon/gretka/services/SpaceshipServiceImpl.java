package pl.fis.szymon.gretka.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import pl.fis.szymon.gretka.entities.SpaceFleet;
import pl.fis.szymon.gretka.entities.Spaceship;

@Service
public class SpaceshipServiceImpl implements SpaceshipService{

	private List<Spaceship> listOfSpaceships = new ArrayList<>();
	private SpaceFleet spaceFleet = new SpaceFleet();
	
	public SpaceshipServiceImpl() {
		initializeSpaceshipsAndSpaceFleet();
	}
	
	@Override
	public void initializeSpaceshipsAndSpaceFleet() {
		listOfSpaceships.add(new Spaceship("venga", 2000, LocalTime.now()));
		listOfSpaceships.add(new Spaceship("goliath", 8000, LocalTime.now()));
		
		spaceFleet.setName("FIS Space Fleet");
		spaceFleet.setList(listOfSpaceships);
	}

	@Override
	public void save(Spaceship spaceship) {
		listOfSpaceships.add(spaceship);
		
	}

	@Override
	public void delete(String name) {
		Iterator<Spaceship> iterator = listOfSpaceships.iterator();
	    while (iterator.hasNext()) {
	    	Spaceship spaceship = iterator.next();
	        if (spaceship.getName().equals(name)) {
	            listOfSpaceships.remove(spaceship);
	        }
	    }
		
	}

	@Override
	public SpaceFleet getSpaceFleet() {
		return spaceFleet;
	}

	@Override
	public Spaceship getByName(String name) {
		Spaceship ship = spaceFleet.getList().stream()
				  .filter(sh -> name.equals(sh.getName()))
				  .findAny()
				  .orElse(null);
		
		return ship;
	}

	@Override
	public List<Spaceship> getListOfSpaceships() {
		return listOfSpaceships;
	}

}
