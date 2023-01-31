package com.example.basicapi.sample;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {
	
	// JPQL Query with pagination
	@Query("select a from Employee a "
			+ "where a.delflg='N' "
			+ "order by a.id")
	public List<Employee> getAllEmployee();

	// JPQL Query with parameter
	@Query("select a from Employee a where a.uuid=:uuid "
			+ "and a.delflg='N'")
    public Employee getByUuid(@Param("uuid") String id);
	
}
