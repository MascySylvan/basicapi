package com.example.basicapi.commons;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse<E> {

	private E data;
	private ResponseFlag flag = ResponseFlag.S;
	private String message = ResponseFlag.S.getMessage();
	private String tranRefNo;
	
	public ServiceResponse(){
	}
	
	public ServiceResponse(E data) {
		this.setData(data);
	}
	
	public ServiceResponse(String tranRefNo, E data) {
		this.setData(data);
		this.setTranRefNo(tranRefNo);
	}
	
	public ServiceResponse(String tranRefNo, ResponseFlag flag, String message) {
		this.setFlag(flag);
		this.setMessage(message);
		this.setTranRefNo(tranRefNo);
	}
	
	public ServiceResponse(String tranRefNo, ResponseFlag flag, E data) {
		this.setData(data);
		this.setFlag(flag);
		this.setTranRefNo(tranRefNo);
	}
	
	public ServiceResponse(String tranRefNo, ResponseFlag flag, E data, String message) {
		this.setData(data);
		this.setFlag(flag);
		this.setMessage(message);
		this.setTranRefNo(tranRefNo);
	}
	
	public String getTranRefNo() {
		return tranRefNo;
	}

	public void setTranRefNo(String tranRefNo) {
		if (StringUtil.isNullOrEmpty(tranRefNo) == false) {
			this.tranRefNo = tranRefNo;	
		}
	}

	public ResponseFlag getFlag() {
		return flag;
	}

	public void setFlag(ResponseFlag responseStatus) {
		this.flag = responseStatus;
		
		if (responseStatus != null) {
			this.setMessage(responseStatus.getMessage() );
		}
	}
	
	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;

		if (data == null) {
			return;
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonIgnore
	public void appendToMessage(String message) {
		if (this.message != null) {
			this.message += " " + message;
		} else {
			this.message = message;
		}
	}

	@JsonIgnore
	public void appendToMessage(Throwable e) {
		if (e != null) {

			StringWriter sw = new StringWriter();

			e.printStackTrace(new PrintWriter(sw));

			if (this.message != null) {

				this.message += ". " + sw.toString();
			} else {
				this.message = sw.toString();
			}
		}
	}

	@Override
	public String toString() {
		return "ServiceResponse [data=" + data + ", flag=" + flag + ", message=" + message + ", tranRefNo=" + tranRefNo
				+ "]";
	}
	
}
