package com.fis.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fis.app.exce.NoDeviceFoundException;
import com.fis.app.model.ElectronicDevice;



public class ElectronicDeviceImpl implements IElectronicDeviceDAO{
	Connection con=null;
	String insertdevice="insert into electronicdevice.electronic values(?,?,?,?,?,?,?)";
	String displayallDevice="select * from electronicdevice.electronic";
	@Override
	public boolean addDevice(ElectronicDevice device) throws Exception {
		con=DatabaseUtil.getConnection();
		boolean isInserted=false;
		if(con!=null)
		{
			int deviceId=device.getDeviceId();
			String model=device.getBrandName();
			String type=device.getDeviceType();
			int cost=device.getCost();
			int starRating=device.getStarRatings();
			int power=device.getPower();
			String color=device.getColor();
			PreparedStatement ps = con.prepareStatement(insertdevice);
			ps.setInt(1, deviceId);
			ps.setString(2, model);
			ps.setString(3, type);
			ps.setInt(4, cost);
			ps.setInt(5, power);
			ps.setInt(6, starRating);
			ps.setString(7, color);
			
			int status=ps.executeUpdate();
			if(status>0)
				isInserted=true;
			
		}
		
		return isInserted;
	}

	@Override
	public List<ElectronicDevice> getAllDevices() throws Exception {
		Connection con=DatabaseUtil.getConnection();
		List<ElectronicDevice> edl=new ArrayList<>();
		if(con!=null)
		{
			
			PreparedStatement ps=con.prepareStatement(displayallDevice);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ElectronicDevice ed=new ElectronicDevice();
				ed.setBrandName(rs.getString("brandName"));
				ed.setDeviceId(rs.getInt(1));
				ed.setDeviceType(rs.getString("deviceType"));
				ed.setCost(rs.getInt(4));
				ed.setPower(rs.getInt(5));
				ed.setStarRatings(rs.getInt(6));
				ed.setColor(rs.getString("color"));
				
				edl.add(ed);
			}
		}
		return edl;
	}

	@Override
	public int changeDevicePrice(int deviceId, int newPrice) throws NoDeviceFoundException,SQLException {
		Connection con=DatabaseUtil.getConnection();
		String updatePriceQuery="update electronicdevice set cost=? where deviceId=?";
		ElectronicDevice ed= new ElectronicDevice();
		int rs=0;
		if(con!=null)
		{
			PreparedStatement ps=con.prepareStatement(updatePriceQuery);
			ps.setInt(1, newPrice);
			ps.setInt(2, deviceId);
			boolean isFound=false;
			
			 rs=ps.executeUpdate();
			
			if(rs==0)
			{
				throw new NoDeviceFoundException(deviceId);
			}
			
		}
		return rs;
	}

	@Override
	public int changeDeviceRating(int deviceId, int newRating) throws NoDeviceFoundException, SQLException {
		Connection con=DatabaseUtil.getConnection();
		String updateRatingQuery="update electronicdevice set starRatings=? where deviceId=?";
		int rs=0;
		if(con!=null)
		{
			PreparedStatement ps=con.prepareStatement(updateRatingQuery);
			ps.setInt(1, newRating);
			ps.setInt(2, deviceId);
			
			rs=ps.executeUpdate();
			
			if(rs<=0)
			{
				throw new NoDeviceFoundException(deviceId);
			}
			
		}
		return rs;
	}

	@Override
	public boolean deleteDevice(int deviceId) throws NoDeviceFoundException,SQLException {
		Connection con=DatabaseUtil.getConnection();
		String deleteDeviceQuery="delete from electronicdevice where deviceId=?";
		boolean isDelete=false;
		if(con!=null)
		{
			PreparedStatement ps=con.prepareStatement(deleteDeviceQuery);
			ps.setInt(1, deviceId);
			int i=ps.executeUpdate();
			if(i>0)
				isDelete=true;
			else
				throw new NoDeviceFoundException(deviceId);
			
		}
		return isDelete;
	}

	@Override
	public List<ElectronicDevice> geDevicesBasedOnBrandNameAndType(String brandName, String type) throws SQLException {
		Connection con=DatabaseUtil.getConnection();
		String BrandTypeQuery="select * from electronicdevice where brandName=? and deviceType=?";
		List<ElectronicDevice> edl=new ArrayList<>();
		if(con!=null)
		{
			
			PreparedStatement ps=con.prepareStatement(BrandTypeQuery);
			ps.setString(1,brandName);
			ps.setString(2, type);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ElectronicDevice ed=new ElectronicDevice();
				ed.setBrandName(rs.getString("brandName"));
				ed.setDeviceId(rs.getInt(1));
				ed.setDeviceType(rs.getString("deviceType"));
				ed.setCost(rs.getInt(4));
				ed.setPower(rs.getInt(5));
				ed.setStarRatings(rs.getInt(6));
				ed.setColor(rs.getString("color"));
				
				edl.add(ed);
			}
		}
		return edl;
	}

	@Override
	public int countDeviceType(String type) throws SQLException {
		Connection con=DatabaseUtil.getConnection();
		String countDeviceQuery="select count(*) from electronicdevice where deviceType=?";
		List<ElectronicDevice> edl=new ArrayList<>();
		int count=0;
		if(con!=null)
		{
			
			PreparedStatement ps=con.prepareStatement(countDeviceQuery);
			ps.setString(1, type);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				count=rs.getInt(1);
			}
		}
		return count;
	}

	@Override
	public int getSumofPriceBasedOnType(String type) throws SQLException, NoDeviceFoundException {
		Connection con=DatabaseUtil.getConnection();
		String sumofPriceQuery="select sum(cost) from electronicdevice where deviceType=?";
		List<ElectronicDevice> edl=new ArrayList<>();
		int sum=0;
		if(con!=null)
		{
			
			PreparedStatement ps=con.prepareStatement(sumofPriceQuery);
			ps.setString(1, type);
			ResultSet rs=ps.executeQuery();
			boolean isFound=false;
			while(rs.next())
			{
				isFound=true;
				sum=rs.getInt(1);
			}
			if(!isFound)
			{
				throw new NoDeviceFoundException(0);
			}
		}
		return sum;
	}

	@Override
	public List<ElectronicDevice> getDeviceBasedOnPriceRangeAndType(int range1, int range2, String type) throws SQLException,NoDeviceFoundException {
		Connection con=DatabaseUtil.getConnection();
		String query="select * from electronicdevice where deviceType=?";
		List<ElectronicDevice> edl=new ArrayList<>();
		if(con!=null)
		{
			
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, type);
			ResultSet rs=ps.executeQuery();
			boolean isFound=false;
			while(rs.next())
			{
				isFound=true;
				ElectronicDevice ed=new ElectronicDevice();
				ed.setBrandName(rs.getString("brandName"));
				ed.setDeviceId(rs.getInt(1));
				ed.setDeviceType(rs.getString("deviceType"));
				ed.setCost(rs.getInt(4));
				ed.setPower(rs.getInt(5));
				ed.setStarRatings(rs.getInt(6));
				ed.setColor(rs.getString("color"));
				
				edl.add(ed);
			}
			if(!isFound)throw new NoDeviceFoundException(0);
			else
			{
				edl=edl.stream().filter(emp1->{
					return emp1.getCost()>=range1 && emp1.getCost()<=range2;
				}).collect(Collectors.toList());
			}
		}
		return edl;
	}
	
	

}