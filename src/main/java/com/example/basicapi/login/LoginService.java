package com.example.basicapi.login;

import com.example.basicapi.commons.ServiceResponse;

public interface LoginService {

	public ServiceResponse<LoginDTO> create(String tranRefNo, LoginDTO loginDTO) throws Throwable;

	public ServiceResponse<LoginDTO> update(String tranRefNo, LoginDTO loginDTO, String uuid) throws Throwable;
	
	public ServiceResponse<String> delete(String tranRefNo, String uuid) throws Throwable;
	
	public ServiceResponse<LoginDTO> getByUuid(String tranRefNo, String uuid) throws Throwable;
	
	public ServiceResponse<String> checkAccess(String tranRefNo, String username, String password) throws Throwable;
}
