package com.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Insert extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		String fname=req.getParameter("fname");
		if(fname=="")
		{
			fname=null;
		}
		String lname=req.getParameter("lname");
		if(lname=="")
		{
			lname=null;
		}
		String username=req.getParameter("username");
		if(username=="")
		{
			username=null;
		}
		String password=req.getParameter("password");
		if(password=="")
		{
			password=null;
		}
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","root");
			PreparedStatement pst=con.prepareStatement("insert into registration(firstname,lastname,username,password) values(?,?,?,?)");
			pst.setString(1,fname);
			pst.setString(2,lname);
			pst.setString(3,username);
			pst.setString(4,password);
			pst.executeUpdate();
			System.out.println("Record inserted successfully");
			String msg="successfully registered";
			pw.println("Record inserted successfully");
			res.sendRedirect("Login.html?msg="+msg);
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			pw.println("Username already exists");
		}
	}
}
