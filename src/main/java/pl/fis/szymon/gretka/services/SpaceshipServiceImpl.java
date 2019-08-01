package pl.fis.szymon.gretka.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.fis.szymon.gretka.entities.SpaceFleet;
import pl.fis.szymon.gretka.entities.Spaceship;

@Service
public class SpaceshipServiceImpl implements SpaceshipService{

	private Map<String,List<Spaceship>> map = new HashMap<>();
	private SpaceFleet fisSpaceFleet = new SpaceFleet();
	private List<Spaceship> listOfFisSpaceships = new ArrayList<>(); 
	
	public SpaceshipServiceImpl() {
		initializeSpaceshipsAndSpaceFleet();
	}
	
	@Override
	public void initializeSpaceshipsAndSpaceFleet() {
		listOfFisSpaceships.add(new Spaceship("Battlestar Galactica", 2000, LocalTime.now()));
		listOfFisSpaceships.add(new Spaceship("Elysium", 8000, LocalTime.now()));
		listOfFisSpaceships.add(new Spaceship("Reapers", 3000, LocalTime.now()));
		listOfFisSpaceships.add(new Spaceship("USS Enterprise", 30000, LocalTime.now()));
		listOfFisSpaceships.add(new Spaceship("Spaceball One", 100000, LocalTime.now()));
		listOfFisSpaceships.add(new Spaceship("Millennium Falcon", 55, LocalTime.now()));
		listOfFisSpaceships.add(new Spaceship("Death Star", 5000, LocalTime.now()));
		listOfFisSpaceships.add(new Spaceship("Starkiller Base", 555, LocalTime.now()));
		listOfFisSpaceships.add(new Spaceship("The Fighter", 2000, LocalTime.now()));
		listOfFisSpaceships.add(new Spaceship("USCSS Prometheus", 8000, LocalTime.now()));
		
		fisSpaceFleet.setName("FIS");
		fisSpaceFleet.setList(listOfFisSpaceships);
		
		map.put(fisSpaceFleet.getName(), fisSpaceFleet.getList());
	}

	@Override
	public void save(String spacefleetName, Spaceship spaceship) {
		map.get(spacefleetName).add(spaceship);
		
	}

	@Override
	public void delete(String spacefleetName, String shipName) {
		Iterator<Spaceship> iterator = map.get(spacefleetName).iterator();
	    while (iterator.hasNext()) {
	    	Spaceship spaceship = iterator.next();
	        if (spaceship.getName().equals(shipName)) {
	            listOfFisSpaceships.remove(spaceship);
	        }
	    }
		
	}

	@Override
	public SpaceFleet getSpaceFleet(String name) {
		return new SpaceFleet(name, map.get(name));
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
	public List<Spaceship> getListOfSpaceships(String spacefleetName) {
		return map.get(spacefleetName);
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
