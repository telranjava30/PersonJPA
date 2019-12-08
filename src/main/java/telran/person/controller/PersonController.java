package telran.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.person.dto.CityPopulationDto;
import telran.person.dto.PersonDto;
import telran.person.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@PostMapping
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}
	
	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable int id) {
		return personService.findPersonById(id);
	}
	
	@GetMapping("/name/{name}")
	public Iterable<PersonDto> findSameNamePersons(@PathVariable String name) {
		return personService.findPersonsByName(name);
	}
	
	@GetMapping("/ages/{min}/{max}")
	public Iterable<PersonDto> findPersonsByAges(@PathVariable int min, @PathVariable int max) {
		return personService.findPersonsByAges(min, max);
	}
	
	@GetMapping("/city/{city}")
	public Iterable<PersonDto> findByCity(@PathVariable String city) {
		return personService.findPersonsByCity(city);
	}
	
	@GetMapping("/salary/{min}/{max}")
	public Iterable<PersonDto> findEmployeesBySalary(@PathVariable int min, @PathVariable int max){
		return personService.findEmployeesBySalary(min, max);
	}
	
	@GetMapping("/children")
	public Iterable<PersonDto> findAllChildren(){
		return personService.findChildren();
	}
	
	@GetMapping("/city/population")
	public Iterable<CityPopulationDto> getCityPopulation() {
		return personService.getCityPopulation();
	}

}
