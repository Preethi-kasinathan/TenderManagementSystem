package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import service.UserDAOInterface;
import utility.ConnectionManager;

public class UserDAO implements UserDAOInterface {

	@Override
	public int NewUser(User user) throws ClassNotFoundException, Exception {
		
		BufferedReader bfrd = new BufferedReader(new InputStreamReader(System.in));
		ConnectionManager cnmgr = new ConnectionManager();
		Connection con = cnmgr.getConnection();
		
		System.out.println("ENTER FIRSTNAME:");
		String fname = bfrd.readLine();
		System.out.println("ENTER LASTNAME:");
		String lname = bfrd.readLine();
		System.out.println("ENTER EMAIL ID:");
		String email = bfrd.readLine();
		System.out.println("ENTER PASSWORD:");
		String pass = bfrd.readLine();
		System.out.println("ENTER MOBILE NUMBER:");
		int mblno = Integer.parseInt(bfrd.readLine());
		
		user.setF_name(fname);
		user.setL_name(lname);
		user.setEmail_id(email);
		user.setPassword(pass);
		user.setMobile_no(mblno);
		
		// YOU HAVE TO CREATE THE TABLE IN YOUR SQL DEVELOPER
		// REPLACE THE TABLENAME INSTEAD OF PUT YOUR CREATED TABLE NAME
		
		/*CREATE TABLE USERLOGIN (REPLACE TABLENAME TO USERLOGIN)
		(
		  F_NAME VARCHAR(50),
		  L_NAME VARCHAR(50),
		  EMAIL_ID VARCHAR(50),
		  PASSWORD VARCHAR(50),
		  MOBILE_NO NUMBER(11)
		);*/
		
		String sql = "insert into TABLENAME VALUES(?,?,?,?,?)";
		PreparedStatement prestmt = con.prepareStatement(sql);
		prestmt.setString(1, user.getF_name());
		prestmt.setString(2, user.getL_name());
		prestmt.setString(3, user.getEmail_id());
		prestmt.setString(4, user.getPassword());
		prestmt.setInt(5, user.getMobile_no());
		ResultSet rs = prestmt.executeQuery();
		
		if(rs!=null)
		{
			System.out.println("USER REGISTERED SUCCESSFULLY");
			con.close();
		}
		else
		{
			System.out.println("Check Your Database");
			con.close();
		}
		
		return 0;
	}

	@Override
	public boolean Userlogin(User user) throws ClassNotFoundException, Exception {
		
		BufferedReader bfrd = new BufferedReader(new InputStreamReader(System.in));
		ConnectionManager cnmgr = new ConnectionManager();
		Connection con = cnmgr.getConnection();
		
		System.out.println("ENTER EMAIL ID:");
		String emlid = bfrd.readLine();
		System.out.println("ENTER PASSWORD:");
		String pswd = bfrd.readLine();
		
		user.setEmail_id(emlid);
		user.setPassword(pswd);
		
		// REPLACE TABLENAME TO USERLOGIN AFTER CREATING USERLOGIN TABLE.
		//ERROR SHOWN IN IF CONDITION STATEMENT IF U CREATE A USERLOGIN TABLE IT'LL AUTOMATICALLY GONE.
		
		String sql = "SELECT * FROM TABLENAME WHERE EMAIL_ID = ? AND PASSWORD = ?";
		PreparedStatement prstmt = con.prepareStatement(sql);
		prstmt.setString(3, user.getEmail_id());
		prstmt.setString(4, user.getPassword());
		ResultSet rs = prstmt.executeQuery();
		
		while(rs.next())
		{
			if(emlid.equals(user.getString) && pswd.equals(user.getString("password")))
			{
				con.close();
				System.out.println("LOGIN SUCCESSFULLY");
				// whatever you want any method to call you can create object and call a method in this space.
			}
			else
			{
				con.close();
				System.out.println("INVALID USER");
			}
		}
		
		
		return false;
	}

}
