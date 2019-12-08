package telran.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.person.dao.PersonRepository;
import telran.person.dto.ChildDto;
import telran.person.dto.CityPopulationDto;
import telran.person.dto.EmployeeDto;
import telran.person.dto.PersonDto;
import telran.person.model.Child;
import telran.person.model.Employee;
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
		if (personDto instanceof ChildDto) {
			ChildDto childDto = (ChildDto) personDto;
			return new Child(childDto.getId(), childDto.getName(),
					LocalDate.parse(childDto.getBirthDate()),
					childDto.getAddress(), childDto.getKindergarten());
		}
		if (personDto instanceof EmployeeDto ) {
			EmployeeDto employeeDto = (EmployeeDto) personDto;
			return new Employee(employeeDto.getId(), employeeDto.getName(),
					LocalDate.parse(employeeDto.getBirthDate()), employeeDto.getAddress(),
					employeeDto.getCompany(), employeeDto.getSalary());
		}
		return Person.builder()
				.id(personDto.getId())
				.name(personDto.getName())
				.birthDate(LocalDate.parse(personDto.getBirthDate()))
				.address(personDto.getAddress())
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
		if (person instanceof Child) {
			Child child = (Child) person;
			return new ChildDto(child.getId(), child.getName(), 
					child.getBirthDate().toString(), child.getAddress(),
					child.getKindergarten());
		}
		if (person instanceof Employee) {
			Employee employee = (Employee) person;
			return new EmployeeDto(employee.getId(), employee.getName(),
					employee.getBirthDate().toString(), employee.getAddress(),
					employee.getCompany(), employee.getSalary());
		}
		return PersonDto.builder()
				.id(person.getId())
				.name(person.getName())
				.birthDate(person.getBirthDate().toString())
				.address(person.getAddress())
				.build();
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findPersonsByName(String name) {
		return personRepository.findByName(name)
				//.stream()
				.map(this::convertToPersonDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findPersonsByAges(int min, int max) {
		LocalDate from = LocalDate.now().minusYears(max);
		LocalDate to = LocalDate.now().minusYears(min);
		return personRepository.findByBirthDateBetween(from, to).stream()
				.map(this::convertToPersonDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findPersonsByCity(String city) {
		return personRepository.findByAddressCity(city)
				.stream()
				.map(this::convertToPersonDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findEmployeesBySalary(int min, int max) {
		return personRepository.findBySalaryBetween(min, max).stream()
				.map(this::convertToPersonDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findChildren() {
		return personRepository.findBy().stream()
				.map(this::convertToPersonDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<CityPopulationDto> getCityPopulation() {
		return personRepository.getCityPopulation();
	}

}
