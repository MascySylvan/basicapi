package com.example.basicapi.login;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.basicapi.commons.ResponseFlag;
import com.example.basicapi.commons.ServiceResponse;
import com.example.basicapi.commons.StringUtil;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	@Transactional(rollbackOn = Exception.class)
	@Override
	public ServiceResponse<LoginDTO> create(String tranRefNo, LoginDTO loginDTO) throws Throwable {
		ServiceResponse<LoginDTO> response = new ServiceResponse<LoginDTO>();
		response.setTranRefNo(tranRefNo);
		
		Login tempEntity = this.loginDao.getByUsername(loginDTO.getUsername() );
		
		if (tempEntity != null) {
			response.setFlag(ResponseFlag.F);
			response.setMessage("Username [" + loginDTO.getUsername() + "] already exists!");
			
			return response;
		}
		
		Login entity = loginDTO.toEntity();
		entity.setUuid(StringUtil.generateUUID() );
		entity.setCretime(new Date() );
		entity.setDelflg("N");
		
		this.loginDao.save(entity);

		Login creObj = this.loginDao.getByUuid(entity.getUuid() );
		
		if (creObj != null) {
			response.setData(new LoginDTO(creObj) );
		} else {
			response.setFlag(ResponseFlag.F);
			response.setMessage("Unsuccessful to Save!");
		}
		
		return response;
	}
	
	@Transactional(rollbackOn = Exception.class)
	@Override
	public ServiceResponse<LoginDTO> update(String tranRefNo, LoginDTO loginDTO, String uuid) throws Throwable {
		ServiceResponse<LoginDTO> response = new ServiceResponse<LoginDTO>();
		response.setTranRefNo(tranRefNo);
		
		Login updObj = this.loginDao.getByUuid(uuid);
		
		if (updObj != null) {
			updObj.updateEntity(loginDTO);
			
			this.loginDao.save(updObj);
			
			response.setData(new LoginDTO(updObj) );
			
		} else {
			response.setFlag(ResponseFlag.F);
			response.setMessage("No matching record was found!");
		}

		return response;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public ServiceResponse<String> delete(String tranRefNo, String uuid) throws Throwable {
		ServiceResponse<String> response = new ServiceResponse<String>();

		Login entity = this.loginDao.getByUuid(uuid);
		entity.setDelflg("Y");
		
		this.loginDao.save(entity);
		
		response.setData(uuid);
		response.setTranRefNo(tranRefNo);
		
		return response;
	}

	@Override
	public ServiceResponse<LoginDTO> getByUuid(String tranRefNo, String uuid) throws Throwable {
		ServiceResponse<LoginDTO> response = new ServiceResponse<LoginDTO>();

		// Extract from Database.
		Login login = this.loginDao.getByUuid(uuid);
		
		if (login != null) {
			response.setData(new LoginDTO(login) );
		}
		else {
			response.setFlag(ResponseFlag.F);
			response.setMessage("No matching records found.");
		}
				
		response.setTranRefNo(tranRefNo);
		
		return response;
	}
	
	@Override
	public ServiceResponse<String> checkAccess(String tranRefNo, String username, String password) throws Throwable {
		ServiceResponse<String> response = new ServiceResponse<String>();
		response.setTranRefNo(tranRefNo);
		
		Login entity = this.loginDao.getByUsername(username);
		
		if (entity == null) {
			response.setFlag(ResponseFlag.F);
			response.setMessage("Login failed. User " + username + " does not exists!");
			
			return response;
		}
		
		LoginDTO entDto = new LoginDTO(entity);
		
		if (password != null) {
			if (password.trim().equalsIgnoreCase(entDto.getPassword() ) ) {
				response.setData(entDto.getUuid() );
			} else {
				response.setFlag(ResponseFlag.F);
				response.setMessage("Login failed. Incorrect Password for " + username);
			}
		} else {
			response.setFlag(ResponseFlag.F);
			response.setMessage("Login failed. Incorrect Password for " + username);
		}
		
		return response;
	}
}
