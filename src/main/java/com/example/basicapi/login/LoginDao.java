package com.example.basicapi.login;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends CrudRepository<Login, Long> {

	// JPQL Query with parameter
	@Query("select a from Login a where a.uuid=:uuid "
			+ "and a.delflg='N'")
    public Login getByUuid(@Param("uuid") String id);

	// JPQL Query with parameter
	@Query("select a from Login a where a.username=:username "
			+ "and a.delflg='N'")
    public Login getByUsername(@Param("username") String username);
	
}
