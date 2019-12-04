package telran.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	Stream<Person> findByName(String name);

	List<Person> findByBirthDateBetween(LocalDate from, LocalDate to);
	
	List<Person> findByAddressCity(String city);

}
