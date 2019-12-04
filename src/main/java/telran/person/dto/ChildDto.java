package telran.person.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.person.model.Address;

@NoArgsConstructor
@Getter
@Setter
public class ChildDto extends PersonDto{
	
	String kindergarten;

	public ChildDto(int id, String name, String birthDate, Address address, String kindergarten) {
		super(id, name, birthDate, address);
		this.kindergarten = kindergarten;
	}
	
	

}
