package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.TenderRequest;
import model.Tender_Authority;
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
			if(rs!=null)
			{
				con.close();
				System.out.println("LOGIN SUCCESSFULLY");
				System.out.println();
				System.out.println("********** DETAILS OF ALL TENDERS **************");
				// CALL A METHOD ALL_TENDER_DETAILS();
				System.out.println();
				System.out.println(" DO YOU WANT TO GIVE A TENDER REQUEST ENTER YES OR NO ? ");
				String ys = "YES";
				String no = "NO";
				String ys_no = bfrd.readLine();
				
				if(ys_no.equals(ys))
				{
					TenderRequestDAO tndr_rqst = new TenderRequestDAO();
					tndr_rqst.tender_request();
				}
				else
				{
					System.out.println();
					System.out.println("THANK YOU FOR VISIT OUR SITE");
					System.out.println("******** VISIT AGAIN *******");
				}
				
			}
			else
			{
				con.close();
				System.out.println("INVALID USER");
			}
		}
		
		
		return false;
	}

	@Override
	public boolean Authoritylogin(Tender_Authority tndr_autry) throws ClassNotFoundException, Exception {
		
		BufferedReader bfrd = new BufferedReader(new InputStreamReader(System.in));
		ConnectionManager cmgr = new ConnectionManager();
		Connection con = cmgr.getConnection();
		
		System.out.println("ENTER USERNAME: ");
		String usrnm = bfrd.readLine();
		System.out.println("ENTER PASSWORD: ");
		String pswd = bfrd.readLine();
		
		tndr_autry.setUsername(usrnm);
		tndr_autry.setPassword(pswd);
		String sql = "select * from tender_authority where username = ? and password = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, tndr_autry.getUsername());
		stmt.setString(2, tndr_autry.getPassword());
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next())
		{
			if(usrnm.equals(rs.getString("USERNAME")) && pswd.equals(rs.getString("PASSWORD")))
			{
				UserDAO usrdao1 = new UserDAO();
				usrdao1.selectallrequest();
				//call sms() method.
			}
		}
		
		return false;
	}

	@Override
	public List<TenderRequest> selectallrequest() throws ClassNotFoundException, Exception {
		
		TenderRequestDAO tndrrqst = new TenderRequestDAO();
		List<TenderRequest> lsttndrrqst = new ArrayList<TenderRequest>();
		TenderRequest trqst = null;
		ConnectionManager cmgr = new ConnectionManager();
		Connection con = cmgr.getConnection();
		
		String sql = "select * from tender_request";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next())
		{
			trqst = new TenderRequest(null, null, null, null, null, null);
			System.out.println("NAME: " + rs.getString(1));
			System.out.println("TENDER NO: " + rs.getString(2));
			System.out.println("COMPANY NAME: " + rs.getString(3));
			System.out.println("MOBILE NUMBER: " + rs.getString(4));
			System.out.println("ESTIMATE AMOUNT: " + rs.getString(5));
			System.out.println("DATE: " + rs.getString(6));
			lsttndrrqst.add(trqst);
			
			//YOU CAN APPROVE USER THROUGH SMS API
			// CALL METHOD SMS();
		}
		
		
		
		return null;
	}
	
	

}
