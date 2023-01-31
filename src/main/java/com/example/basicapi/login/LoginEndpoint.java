package com.example.basicapi.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.basicapi.commons.ResponseFlag;
import com.example.basicapi.commons.ServiceResponse;
import com.example.basicapi.commons.StringUtil;

@RestController
@RequestMapping("/v0/login")
public class LoginEndpoint {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	ObjectMapper objMapper;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value=""
			  , method=RequestMethod.POST
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<String> create(HttpServletRequest req
			, @RequestBody(required=false) String jwtReq) throws Throwable {
		ServiceResponse<LoginDTO> response = new ServiceResponse<LoginDTO>();
		response.setTranRefNo(StringUtil.generateUUID() );
		System.out.println(jwtReq);
		LoginDTO dto = new ObjectMapper().readValue(StringUtil.decodeString(jwtReq), LoginDTO.class);
		
		ServiceResponse<LoginDTO> resObj = this.loginService.create(response.getTranRefNo(), dto);
		
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

	@CrossOrigin(origins = "*")
	@RequestMapping(value="/{uuid}"
			  , method=RequestMethod.PATCH
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<String> update(HttpServletRequest req
			, @RequestBody(required=false) String jwtReq
			, @PathVariable(name="uuid", required=true) String uuid) throws Throwable {
		ServiceResponse<LoginDTO> response = new ServiceResponse<LoginDTO>();
		response.setTranRefNo(StringUtil.generateUUID() );
		
		LoginDTO dto = new ObjectMapper().readValue(StringUtil.decodeString(jwtReq), LoginDTO.class);
		
		ServiceResponse<LoginDTO> resObj = this.loginService.update(response.getTranRefNo(), dto, uuid);
		
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

	@CrossOrigin(origins = "*")
	@RequestMapping(value="/{uuid}"
			  , method=RequestMethod.DELETE
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<String> delete(HttpServletRequest req
			, @PathVariable(name="uuid", required=true) String uuid) throws Throwable {
		ServiceResponse<String> response = new ServiceResponse<String>();
		response.setTranRefNo(StringUtil.generateUUID() );
		
		ServiceResponse<String> resObj = this.loginService.delete(response.getTranRefNo(), uuid);
		
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

	@CrossOrigin(origins = "*")
	@RequestMapping(value="/checkAccess"
			  , method=RequestMethod.GET
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<String> checkAccess(HttpServletRequest req
			, @RequestParam(name="logindto", required=false) String logindto) throws Throwable {
		ServiceResponse<String> response = new ServiceResponse<String>();
		response.setTranRefNo(StringUtil.generateUUID() );
		
		LoginDTO dto = new ObjectMapper().readValue(StringUtil.decodeString(logindto), LoginDTO.class);
		
		ServiceResponse<String> resObj = this.loginService.checkAccess(response.getTranRefNo(), dto.getUsername(), dto.getPassword() );
		
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

	@CrossOrigin(origins = "*")
	@RequestMapping(value="/{uuid}"
			  , method=RequestMethod.GET
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<String> getByUuid(HttpServletRequest req
			, @PathVariable(name="uuid", required=true) String uuid) throws Throwable {
		
		ServiceResponse<LoginDTO> response = new ServiceResponse<LoginDTO>();
		response.setTranRefNo(StringUtil.generateUUID() );
		
		ServiceResponse<LoginDTO> resObj = this.loginService.getByUuid(response.getTranRefNo(), uuid);
		
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

	@CrossOrigin(origins = "*")
	@RequestMapping(value="/template"
			  , method=RequestMethod.GET
			  , consumes="application/json"
			  , produces="application/json")
	public ResponseEntity<ServiceResponse<LoginDTO> > getTemplate(HttpServletRequest req) throws Exception {	
		ServiceResponse<LoginDTO> response = new ServiceResponse<LoginDTO>();
		response.setTranRefNo(StringUtil.generateUUID() );
		
		LoginDTO dto = new LoginDTO();
		dto.setUsername("TeraNinja");
		dto.setPassword("123123");
		//dto.setUuid(StringUtil.generateUUID() );
		//dto.setDelflg("N");
		
		response.setData(dto);
		
		return ResponseEntity.ok().body(response);
	} // End method
}
