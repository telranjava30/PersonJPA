package telran.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
