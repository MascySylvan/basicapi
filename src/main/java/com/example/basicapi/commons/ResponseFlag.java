package com.example.basicapi.commons;

public enum ResponseFlag {

	F("FAIL") {
		@Override
		public String getMessage() {
			return "Transaction failed.";
		}

		@Override
		public String getMessage(String processRefNo) {
			return "Process Ref. No. [" + processRefNo + "]. Transaction failed.";
		}
	}, E("ERROR") {
		@Override
		public String getMessage() {
			return "Transaction failed due to server error.";
		}

		@Override
		public String getMessage(String processRefNo) {
			return "Process Ref. No. [" + processRefNo + "]. Transaction failed due to server error.";
		}
	}, S("SUCCESS") {
		@Override
		public String getMessage() {
			return "Transaction successful.";
		}

		@Override
		public String getMessage(String processRefNo) {
			return "Process Ref. No. [" + processRefNo + "]. Transaction successful."; 
		}
	};

	private ResponseFlag(String code) {
		this.code = code;
	}

	private String code;
	public abstract String getMessage();
	public abstract String getMessage(String processRefNo);
	
	public String toString() {
		return this.code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

}
