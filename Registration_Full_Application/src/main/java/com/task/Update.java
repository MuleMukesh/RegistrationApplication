package com.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Update extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		int id=Integer.parseInt(req.getParameter("idd"));
		String fname=req.getParameter("fname");
		String lname=req.getParameter("lname");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","root");
			PreparedStatement pst=con.prepareStatement("update registration set firstname=?,lastname=?,username=?,password=? where id=?");
			pst.setString(1,fname);
			pst.setString(2,lname);
			pst.setString(3,username);
			pst.setString(4,password);
			pst.setInt(5,id);
			pst.executeUpdate();
			con.close();
			String msg="Record updated";
			res.sendRedirect("Login.html?msg="+msg);
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
