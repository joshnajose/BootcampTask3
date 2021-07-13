package com.fis.app.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {
private static Connection con;

static
{
	try
	{
		String host = "localhost:3306";
	    String database = "electronicdevice";
	    String url = "jdbc:mysql://"+ host+"/"+database;

		Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,"root","Cloud@123$");
       
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
}

public static Connection getConnection()
{
	return con;
}

}