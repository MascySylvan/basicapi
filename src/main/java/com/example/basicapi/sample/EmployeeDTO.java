package com.example.basicapi.sample;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class EmployeeDTO {
	
	private String fullname;
	private BigDecimal salary;
	private Date hiredate;
	private String uuid;
	private String delflg;
	
	public EmployeeDTO(Employee employee) {
		if (employee != null) {
			this.setFullname(employee.getFullname() );
			this.setSalary(employee.getSalary() );
			this.setHiredate(employee.getHiredate() );
			this.setUuid(employee.getUuid() );
			this.setDelflg(employee.getDelflg() );
		}
	}
	
	public Employee toEntity() {
		Employee employee = new Employee();
		employee.setFullname(this.getFullname() );
		employee.setSalary(this.getSalary() );
		employee.setHiredate(this.getHiredate() );
		employee.setUuid(this.getUuid() );
		employee.setDelflg(this.getDelflg() );
		
		return employee;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [fullname=" + fullname + ", salary="
				+ salary + ", hiredate=" + hiredate + ", uuid=" + uuid + ", delflg=" + delflg + "]";
	}
	
}
