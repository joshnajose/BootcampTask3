package com.fis.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.fis.app.exce.NoDeviceFoundException;
import com.fis.app.model.ElectronicDevice;

public interface IElectronicDeviceDAO {

	
	public boolean addDevice(ElectronicDevice device)throws Exception;
	public List<ElectronicDevice> getAllDevices() throws Exception;
	
	public int changeDevicePrice(int deviceId,int newPrice)throws NoDeviceFoundException, SQLException;
	public int changeDeviceRating(int deviceId,int newRating)throws NoDeviceFoundException, SQLException;
	
	public boolean deleteDevice(int deviceId)throws NoDeviceFoundException, SQLException;
	
	public List<ElectronicDevice> geDevicesBasedOnBrandNameAndType(String brandName,String type) throws SQLException;
	public int countDeviceType(String type) throws SQLException;
	public int getSumofPriceBasedOnType(String type) throws SQLException, NoDeviceFoundException;
	public List<ElectronicDevice> getDeviceBasedOnPriceRangeAndType(int range1,int range2,String type) throws SQLException, NoDeviceFoundException;
	
	
	
}