package pl.fis.szymon.gretka.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import pl.fis.szymon.gretka.entities.SpaceFleet;
import pl.fis.szymon.gretka.entities.Spaceship;
import pl.fis.szymon.gretka.exceptions.ResourceNotFound;
import pl.fis.szymon.gretka.services.SpaceshipService;

@RestController
@RequestMapping("/api")
public class SpaceshipController {

	private final SpaceshipService spaceshipService;

	public SpaceshipController(SpaceshipService spaceshipService) {
		this.spaceshipService = spaceshipService;
	}

	
	@ApiOperation(value = "Is Unique", notes = "Get spaceship")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Request Accepted"),
            @ApiResponse(code = 404, message = "Not Found") })
	@GetMapping("v4/space-fleet/{name}")
	public ResponseEntity<Spaceship> getSpaceshipByNameV4(@PathVariable (value = "name") String name) {
		
		Spaceship ship = spaceshipService.getByName(name);
		
		if(ship == null) {
			throw new ResourceNotFound("resource " + name + " has not been found!");
		}
		
		return ResponseEntity.ok()
			      .cacheControl(CacheControl.maxAge(5, TimeUnit.MINUTES))
			      .body(ship);
		
		//return ship;
	}
	
	@ApiOperation(value = "Is Unique", notes = "Get spaceships")
	@GetMapping(path = "v4/space-fleet", produces = "application/json")
	public ResponseEntity<SpaceFleet> getSpaceFleetV4() {
		
		return ResponseEntity.ok()
			      .cacheControl(CacheControl.maxAge(5, TimeUnit.MINUTES))
			      .body(spaceshipService.getSpaceFleet());
		
		//return spaceshipService.getSpaceFleet();
	}
	
	@ApiOperation(value = "Is Unique", notes = "Post spaceship")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Request Accepted"),
            @ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping(path = "v4/space-fleet", consumes = "application/json", produces = "application/json")
	public void createSpaceshipInSpaceFleetV4(@RequestBody @Valid Spaceship spaceship) { 
		spaceshipService.save(spaceship);
	}
	
	@GetMapping("v4/space-fleet/ships")
	public List<Spaceship> getListOfSpaceShipsV4(@RequestParam(value = "sortByDescription", defaultValue = "name") String param1, 
			@RequestParam(value = "sort", defaultValue = "asc") String param2){
		List<Spaceship> sortedShips = spaceshipService.sortList(param1, param2, 
				spaceshipService.getListOfSpaceships());
	
		return sortedShips;
	}
	

}
