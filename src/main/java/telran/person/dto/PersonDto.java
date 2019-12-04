package telran.person.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.person.model.Address;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, 
				include = JsonTypeInfo.As.PROPERTY,
				property = "type")
@JsonSubTypes({
	@Type(value = ChildDto.class, name = "child"),
	@Type(value = EmployeeDto.class, name = "employee")
})

public class PersonDto {
	int id;
	String name;
	String birthDate;
	Address address;

}
