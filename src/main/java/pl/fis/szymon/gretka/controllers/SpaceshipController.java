package pl.fis.szymon.gretka.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import pl.fis.szymon.gretka.entities.SpaceFleet;
import pl.fis.szymon.gretka.entities.Spaceship;
import pl.fis.szymon.gretka.services.SpaceshipService;

@RestController
@RequestMapping("/api")
public class SpaceshipController {

	private final SpaceshipService spaceshipService;

	public SpaceshipController(SpaceshipService spaceshipService) {
		this.spaceshipService = spaceshipService;
	}

	
	
	@GetMapping("v4/space-fleet/{name}")
	public Spaceship getSpaceshipByNameV4(@PathVariable (value = "name") String name) {
		
		Spaceship ship = spaceshipService.getByName(name);
		
		/*if(ship == null) {
			throw new ResourceNotFound("resource " + name + " has not been found!");
		}*/
		
		return ship;
	}
	
	
	@GetMapping("v4/space-fleet")
	public SpaceFleet getSpaceFleetV4() {
		return spaceshipService.getSpaceFleet();
	}
	
	@PostMapping("v4/space-fleet")
	public void createSpaceshipInSpaceFleetV4(@Valid Spaceship spaceship) {
		spaceshipService.save(spaceship);
	}

}
