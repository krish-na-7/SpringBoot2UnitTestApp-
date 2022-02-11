package in.nareshit.raghu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Employee {
	 
	@Id
	@GeneratedValue
	private Integer empId;
	private String empName;
	private Double empSal;
}
