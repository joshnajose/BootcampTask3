package com.fis.app.exce;

public class NoDeviceFoundException extends Exception {
	private int id;
	public NoDeviceFoundException(int id)
	{
		this.id=id;
	}
	@Override
	public String toString() {
		return "Invalid Device id =" + id;
	}
	
}