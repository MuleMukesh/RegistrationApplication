package com.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		int id=Integer.parseInt(req.getParameter("idd"));
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","root");
			PreparedStatement pst=con.prepareStatement("delete from registration where id=?");
			pst.setInt(1,id);
			pst.executeUpdate();
			con.close();
			String msg="Record Deleted";
			res.sendRedirect("Login.html?msg="+msg);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
