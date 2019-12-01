package telran.person.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.person.dao.PersonRepository;
import telran.person.dto.PersonDto;
import telran.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonRepository personRepository;

	@Override
	@Transactional
	public boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		Person person = convertToPerson(personDto);
		personRepository.save(person);
		return true;
	}

	private Person convertToPerson(PersonDto personDto) {
		return Person.builder()
				.id(personDto.getId())
				.name(personDto.getName())
				.birthDate(LocalDate.parse(personDto.getBirthDate()))
				.build();
	}

	@Override
	public PersonDto findPersonById(int id) {
		Person person = personRepository.findById(id).orElse(null);
		if (person == null) {
			return null;
		}
		PersonDto personDto = convertToPersonDto(person);
		return personDto;
	}

	private PersonDto convertToPersonDto(Person person) {
		return PersonDto.builder()
				.id(person.getId())
				.name(person.getName())
				.birthDate(person.getBirthDate().toString())
				.build();
	}

	@Override
	public Iterable<PersonDto> findPersonsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<PersonDto> findPersonsByAges(int min, int max) {
		// TODO Auto-generated method stub
		return null;
	}

}
