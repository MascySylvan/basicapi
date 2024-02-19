package com.example.basicapi.sample;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.basicapi.commons.ResponseFlag;
import com.example.basicapi.commons.ServiceResponse;
import com.example.basicapi.commons.StringUtil;

@RestController
@RequestMapping("/v0/employee")
public class EmployeeEndpoint {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	ObjectMapper objMapper;
	
	@RequestMapping(value=""
			  , method=RequestMethod.POST
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<ServiceResponse<EmployeeDTO> > create(HttpServletRequest req
			, @RequestBody(required=false) String jwtReq) throws Throwable {
		ServiceResponse<EmployeeDTO> response = new ServiceResponse<EmployeeDTO>();
		response.setTranRefNo(StringUtil.generateUUID() );
		
		EmployeeDTO dto = new ObjectMapper().readValue(StringUtil.decodeString(jwtReq), EmployeeDTO.class);
		
		ServiceResponse<EmployeeDTO> resObj = this.employeeService.create(response.getTranRefNo(), dto);
		
		if (ResponseFlag.F.equals(resObj.getFlag() ) ) {
			response.setFlag(resObj.getFlag() );
			response.setMessage(resObj.getMessage() );
			response.setTranRefNo(resObj.getTranRefNo() );
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			
		} else if (ResponseFlag.E.equals(resObj.getFlag() ) ) {
			response.setFlag(resObj.getFlag() );
			response.setMessage(resObj.getMessage() );
			response.setTranRefNo(resObj.getTranRefNo() );
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

		response.setData(resObj.getData() );
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	} // End method*/
	
	@RequestMapping(value="/{uuid}"
			  , method=RequestMethod.PATCH
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<String> update(HttpServletRequest req
			, @RequestBody(required=false) String jwtReq
			, @PathVariable(name="uuid", required=true) String uuid) throws Throwable {
		ServiceResponse<EmployeeDTO> response = new ServiceResponse<EmployeeDTO>();
		response.setTranRefNo(StringUtil.generateUUID() );
		
		EmployeeDTO dto = new ObjectMapper().readValue(StringUtil.decodeString(jwtReq), EmployeeDTO.class);
		
		ServiceResponse<EmployeeDTO> resObj = this.employeeService.update(response.getTranRefNo(), dto, uuid);
		
		if (ResponseFlag.F.equals(resObj.getFlag() ) ) {
			response.setFlag(resObj.getFlag() );
			response.setMessage(resObj.getMessage() );
			response.setTranRefNo(resObj.getTranRefNo() );
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectMapper().writeValueAsString(response) );
			
		} else if (ResponseFlag.E.equals(resObj.getFlag() ) ) {
			response.setFlag(resObj.getFlag() );
			response.setMessage(resObj.getMessage() );
			response.setTranRefNo(resObj.getTranRefNo() );
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ObjectMapper().writeValueAsString(response) );
		}

		response.setData(resObj.getData() );
		
		return ResponseEntity.status(HttpStatus.CREATED).body(StringUtil.encodeString(new ObjectMapper().writeValueAsString(response) ) );
	} // End method*/
	
	@RequestMapping(value="/{uuid}"
			  , method=RequestMethod.DELETE
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<String> delete(HttpServletRequest req
			, @PathVariable(name="uuid", required=true) String uuid) throws Throwable {
		ServiceResponse<String> response = new ServiceResponse<String>();
		response.setTranRefNo(StringUtil.generateUUID() );
		
		ServiceResponse<String> resObj = this.employeeService.delete(response.getTranRefNo(), uuid);
		
		if (ResponseFlag.F.equals(resObj.getFlag() ) ) {
			response.setFlag(resObj.getFlag() );
			response.setMessage(resObj.getMessage() );
			response.setTranRefNo(resObj.getTranRefNo() );
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectMapper().writeValueAsString(response) );
			
		} else if (ResponseFlag.E.equals(resObj.getFlag() ) ) {
			response.setFlag(resObj.getFlag() );
			response.setMessage(resObj.getMessage() );
			response.setTranRefNo(resObj.getTranRefNo() );
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ObjectMapper().writeValueAsString(response) );
		}

		response.setData(resObj.getData() );
		
		return ResponseEntity.status(HttpStatus.CREATED).body(StringUtil.encodeString(new ObjectMapper().writeValueAsString(response) ) );
	} // End method*/
	
	@RequestMapping(value=""
			  , method=RequestMethod.GET
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<String> getAllEmployee(HttpServletRequest req) throws Throwable {

		ServiceResponse<List<EmployeeDTO> > response = new ServiceResponse<List<EmployeeDTO> >();
		response.setTranRefNo(StringUtil.generateUUID() );

		ServiceResponse<List<EmployeeDTO> > resObj = this.employeeService.getAllEmployee(response.getTranRefNo() );
		
		if (ResponseFlag.F.equals(resObj.getFlag() ) ) {
			response.setFlag(resObj.getFlag() );
			response.setMessage(resObj.getMessage() );
			response.setTranRefNo(resObj.getTranRefNo() );
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectMapper().writeValueAsString(response) );
			
		} else if (ResponseFlag.E.equals(resObj.getFlag() ) ) {
			response.setFlag(resObj.getFlag() );
			response.setMessage(resObj.getMessage() );
			response.setTranRefNo(resObj.getTranRefNo() );
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ObjectMapper().writeValueAsString(response) );
		}
		
		
		
		response.setData(resObj.getData() );
		
		return ResponseEntity.ok().body(StringUtil.encodeString(new ObjectMapper().writeValueAsString(response) ) );
	} // End method
	
	@RequestMapping(value="/{uuid}"
			  , method=RequestMethod.GET
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<String> getByUuid(HttpServletRequest req
			, @PathVariable(name="uuid", required=true) String uuid) throws Throwable {
		
		ServiceResponse<EmployeeDTO> response = new ServiceResponse<EmployeeDTO>();
		response.setTranRefNo(StringUtil.generateUUID() );
		
		ServiceResponse<EmployeeDTO> resObj = this.employeeService.getByUuid(response.getTranRefNo(), uuid);
		
		if (ResponseFlag.F.equals(resObj.getFlag() ) ) {
			response.setFlag(resObj.getFlag() );
			response.setMessage(resObj.getMessage() );
			response.setTranRefNo(resObj.getTranRefNo() );
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectMapper().writeValueAsString(response) );
			
		} else if (ResponseFlag.E.equals(resObj.getFlag() ) ) {
			response.setFlag(resObj.getFlag() );
			response.setMessage(resObj.getMessage() );
			response.setTranRefNo(resObj.getTranRefNo() );
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ObjectMapper().writeValueAsString(response) );
		}
		
		response.setData(resObj.getData() );
		
		return ResponseEntity.ok().body(StringUtil.encodeString(new ObjectMapper().writeValueAsString(response) ) );
	}

	@RequestMapping(value="/reqToEncode"
			  , method=RequestMethod.POST
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<String> encodeJson(HttpServletRequest req
			, @RequestBody(required=false) String jwtReq) throws Exception {		
		return ResponseEntity.ok().body(StringUtil.encodeString(jwtReq) );
	} // End method

	@RequestMapping(value="/reqToDecode"
			  , method=RequestMethod.POST
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<String> decodeJson(HttpServletRequest req
			, @RequestBody(required=false) String jwtReq) throws Exception {		
		return ResponseEntity.ok().body(StringUtil.decodeString(jwtReq) );
	} // End method

	@RequestMapping(value="/template"
			  , method=RequestMethod.GET
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<ServiceResponse<EmployeeDTO> > getTemplate(HttpServletRequest req) throws Exception {	
		ServiceResponse<EmployeeDTO> response = new ServiceResponse<EmployeeDTO>();
		response.setTranRefNo(StringUtil.generateUUID() );
		
		EmployeeDTO dto = new EmployeeDTO();
		dto.setFullname("Mascy Sylvan");
		dto.setSalary(new BigDecimal("100000") );
		dto.setHiredate(new Date() );
		//dto.setUuid(StringUtil.generateUUID() );
		//dto.setDelflg("N");
		
		response.setData(dto);
		
		return ResponseEntity.ok().body(response);
	} // End method
}
