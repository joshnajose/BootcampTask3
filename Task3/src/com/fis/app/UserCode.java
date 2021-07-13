package com.fis.app;

import java.util.ArrayList;
import java.util.List;

import com.fis.app.dao.ElectronicDeviceImpl;
import com.fis.app.dao.IElectronicDeviceDAO;
import com.fis.app.model.ElectronicDevice;

public class UserCode {
	public static void main(String[] args)  {
		try {
			IElectronicDeviceDAO edao=new ElectronicDeviceImpl();
			
	/*ElectronicDevice ed1=new ElectronicDevice("Laptop",21, "Dell", 76680, 1400, 78, "Brown");
	ElectronicDevice ed2=new ElectronicDevice("Computer",22, "HP", 44680, 3400, 68, "Black");
	ElectronicDevice ed3=new ElectronicDevice("Laptop",23, "HP", 45680, 1400, 56, "White");
	ElectronicDevice ed4=new ElectronicDevice("MobilePhone",24, "Redmi", 38680, 2560, 98, "Pink");
	
	ElectronicDevice ed6=new ElectronicDevice("Laptop",26, "Asus", 55680, 3400, 86, "Gray");
		IElectronicDeviceDAO edao=new ElectronicDeviceImpl();
		
		boolean status=edao.addDevice(ed1);
		status=edao.addDevice(ed2);
		status=edao.addDevice(ed3);
		status=edao.addDevice(ed4);
		
		status=edao.addDevice(ed6);
		if(status)
		{
			System.out.println("Inserted");
		}
		else
		{
			System.out.println("not inserted");
		}*/
			ElectronicDevice ed5=new ElectronicDevice("MobilePhone",25, "Lenovo", 28680, 2650, 68, "Golden");
			//ElectronicDevice ed6=new ElectronicDevice("MobilePhone",12, "Redmi", 20080, 2550, 88, "Orange");
			boolean status=edao.addDevice(ed5);
			//status=edao.addDevice(ed6);
			
			System.out.println("\n================All Devices============");
			List<ElectronicDevice> edl=new ArrayList<>();
			edl=edao.getAllDevices();
			edl.stream().forEach((edc)->System.out.println(edc));
			
			System.out.println("\n===============Update Price============");
			ElectronicDevice ed1=new ElectronicDevice();
			int x3=edao.changeDevicePrice(21, 87903);
			if(x3>0) System.out.println("Updated record");
			
			System.out.println("\n===============Update Ratings============");			
			x3=edao.changeDeviceRating(22, 90);
			if(x3>0) System.out.println("Updated record ");
			
			System.out.println("\n===============delete Device============");			
			boolean x=edao.deleteDevice(25);
			if(x) System.out.println("Record deleted");
			
			
			System.out.println("\n==============Get device based on brand and type===========");
			List<ElectronicDevice> edl3=new ArrayList<>();
			edl3=edao.geDevicesBasedOnBrandNameAndType("Dell", "Laptop");
			edl3.stream().forEach((edc)->System.out.println(edc));
			
			System.out.println("\n===============Count devices============");			
			int x1=edao.countDeviceType("Computer");
			if(x1>0) System.out.println("Count is "+x1);
			
			System.out.println("\n===============Sum of Price Based On Type============");			
		    x1=edao.getSumofPriceBasedOnType("Computer");
			if(x1>0) System.out.println("Sum is "+x1);
			
			System.out.println("\n==============Get device based on price range and type===========");
			List<ElectronicDevice> edl4=new ArrayList<>();
			edl4=edao.getDeviceBasedOnPriceRangeAndType(10000, 80000, "Laptop");
			edl4.stream().forEach((edc)->System.out.println(edc));
		}
		catch(Exception e)
		{
			System.out.println("Exception is: "+e);
		}
		
	}
}
