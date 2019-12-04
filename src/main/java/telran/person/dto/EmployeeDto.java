package telran.person.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.person.model.Address;

@NoArgsConstructor
@Setter
@Getter
public class EmployeeDto extends PersonDto{
	
	String company;
	int salary;
	public EmployeeDto(int id, String name, String birthDate, Address address, String company, int salary) {
		super(id, name, birthDate, address);
		this.company = company;
		this.salary = salary;
	}
	
	

}
