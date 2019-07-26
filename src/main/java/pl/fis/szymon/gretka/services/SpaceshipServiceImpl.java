package pl.fis.szymon.gretka.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.fis.szymon.gretka.entities.SpaceFleet;
import pl.fis.szymon.gretka.entities.Spaceship;

@Service
public class SpaceshipServiceImpl implements SpaceshipService{

	private List<Spaceship> listOfSpaceships = new ArrayList<>();
	private SpaceFleet fisSpaceFleet = new SpaceFleet();
	
	public SpaceshipServiceImpl() {
		initializeSpaceshipsAndSpaceFleet();
	}
	
	@Override
	public void initializeSpaceshipsAndSpaceFleet() {
		listOfSpaceships.add(new Spaceship("Battlestar Galactica", 2000, LocalTime.now()));
		listOfSpaceships.add(new Spaceship("Elysium", 8000, LocalTime.now()));
		listOfSpaceships.add(new Spaceship("Reapers", 3000, LocalTime.now()));
		listOfSpaceships.add(new Spaceship("USS Enterprise", 30000, LocalTime.now()));
		listOfSpaceships.add(new Spaceship("Spaceball One", 100000, LocalTime.now()));
		listOfSpaceships.add(new Spaceship("Millennium Falcon", 55, LocalTime.now()));
		listOfSpaceships.add(new Spaceship("Death Star", 5000, LocalTime.now()));
		listOfSpaceships.add(new Spaceship("Starkiller Base", 555, LocalTime.now()));
		listOfSpaceships.add(new Spaceship("The Fighter", 2000, LocalTime.now()));
		listOfSpaceships.add(new Spaceship("USCSS Prometheus", 8000, LocalTime.now()));
		
		fisSpaceFleet.setName("FIS");
		fisSpaceFleet.setList(listOfSpaceships);
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
		return fisSpaceFleet;
	}

	@Override
	public Spaceship getByName(String name) {
		Spaceship ship = fisSpaceFleet.getList().stream()
				  .filter(sh -> name.equals(sh.getName()))
				  .findAny()
				  .orElse(null);
		
		return ship;
	}

	@Override
	public List<Spaceship> getListOfSpaceships() {
		return listOfSpaceships;
	}

	@Override
	public List<Spaceship> sortList(String param1, String param2, List<Spaceship> list) {
		List<Spaceship> sortedList = list;
		
		if(param1.equals("name") && param2.equals("asc"))
			sortedList.sort(Comparator.comparing(Spaceship::getName));
		
		else if(param1.equals("name") && param2.equals("desc"))
			sortedList.sort(Comparator.comparing(Spaceship::getName).reversed());
		
		else if(param1.equals("speed") && param2.equals("asc"))
			sortedList.sort(Comparator.comparing(Spaceship::getSpeed));
		
		else if(param1.equals("speed") && param2.equals("desc"))
			sortedList.sort(Comparator.comparing(Spaceship::getSpeed).reversed());
		
		
		return sortedList;
	}

	@Override
	public Spaceship getSpaceshipBySpacefleetName(String spaceFleetName, String spaceShipName) {
		Spaceship ship = getByName(spaceShipName);
		if(fisSpaceFleet.getName().equals(spaceFleetName)) {
			return ship;
		}
		return null;
	}

}
