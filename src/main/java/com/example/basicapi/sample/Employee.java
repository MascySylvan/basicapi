package com.example.basicapi.sample;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import lombok.Data;

@Entity
@Table(name = Employee.ENTITY_NAME)
@Data
public class Employee implements java.io.Serializable {

	private static final long serialVersionUID = 7296202732672303479L;
	
	public static final String ENTITY_NAME = "Employee";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "salary")
	private BigDecimal salary;
	
	@Column(name = "hiredate")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date hiredate;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "delflg")
	private String delflg = "N";

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fullname=" + fullname + ", salary=" + salary
				+ ", hiredate=" + hiredate + "]";
	}
	
}
