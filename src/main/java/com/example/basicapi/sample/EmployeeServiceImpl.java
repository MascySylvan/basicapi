package com.example.basicapi.sample;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.basicapi.commons.ResponseFlag;
import com.example.basicapi.commons.ServiceResponse;
import com.example.basicapi.commons.StringUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional(rollbackOn = Exception.class)
	@Override
	public ServiceResponse<EmployeeDTO> create(String tranRefNo, EmployeeDTO employeeDTO) throws Throwable {
		ServiceResponse<EmployeeDTO> response = new ServiceResponse<EmployeeDTO>();
		
		Employee entity = employeeDTO.toEntity();
		entity.setUuid(StringUtil.generateUUID() );
		
		this.employeeDao.save(entity);

		Employee creObj = this.employeeDao.getByUuid(entity.getUuid() );
		
		if (creObj != null) {
			response.setData(new EmployeeDTO(creObj) );
		} else {
			response.setFlag(ResponseFlag.F);
			response.setMessage("Unsuccessful to Save!");
		}
		
		response.setTranRefNo(tranRefNo);

		return response;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public ServiceResponse<EmployeeDTO> delete(String tranRefNo, String uuid) throws Throwable {
		ServiceResponse<EmployeeDTO> response = new ServiceResponse<EmployeeDTO>();

		Employee entity = this.employeeDao.getByUuid(uuid);
		entity.setDelflg("Y");
		
		this.employeeDao.save(entity);
		
		response.setData(new EmployeeDTO(entity) );
		response.setTranRefNo(tranRefNo);
		
		return response;
	}

	@Override
	public ServiceResponse<EmployeeDTO> getByUuid(String tranRefNo, String uuid) throws Throwable {
		ServiceResponse<EmployeeDTO> response = new ServiceResponse<EmployeeDTO>();

		// Extract from Database.
		Employee employee = this.employeeDao.getByUuid(uuid);
		
		if (employee != null) {
			response.setData(new EmployeeDTO(employee) );
		}
		else {
			response.setFlag(ResponseFlag.F);
			response.setMessage("No matching records found.");
		}
				
		response.setTranRefNo(tranRefNo);
		
		return response;
	}
	
	@Override
	public ServiceResponse<List<EmployeeDTO> > getAllEmployee(String tranRefNo) throws Throwable {
		ServiceResponse<List<EmployeeDTO> > response = new ServiceResponse<List<EmployeeDTO> >();
		
		Iterable<Employee> iterable = this.employeeDao.getAllEmployee();
		
		if (iterable != null) {
			List<EmployeeDTO> resultList = new ArrayList<EmployeeDTO>();
			
			for (Employee d: iterable) {
				resultList.add(new EmployeeDTO(d) );
			}
			
			response.setData(resultList);
			
		} else {
			response.setFlag(ResponseFlag.F);
			response.setMessage("No matching records found.");
		}
		
		response.setTranRefNo(tranRefNo);
		
		return response;
	}
}
