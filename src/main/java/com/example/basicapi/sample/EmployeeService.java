package com.example.basicapi.sample;

import java.util.List;

import com.example.basicapi.commons.ServiceResponse;

public interface EmployeeService {

	public ServiceResponse<EmployeeDTO> create(String tranRefNo, EmployeeDTO employeeDTO) throws Throwable;

	public ServiceResponse<EmployeeDTO> update(String tranRefNo, EmployeeDTO employeeDTO, String uuid) throws Throwable;
	
	public ServiceResponse<String> delete(String tranRefNo, String uuid) throws Throwable;
	
	public ServiceResponse<EmployeeDTO> getByUuid(String tranRefNo, String uuid) throws Throwable;
	
	public ServiceResponse<List<EmployeeDTO> > getAllEmployee(String tranRefNo) throws Throwable;
}
