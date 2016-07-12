package edu.nmt.cs.itweb;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Account
 */
public class AccountMgr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action"),
		message = "";
		if (!action.equals("Logout")) {
			String email = request.getParameter("email"),
			passwd = request.getParameter("passwd");
			if (email.equals("")) {
				message = "Invalid email address.";
			} else {
				try {
					Connection con = DBConnection.getConnection();
					Statement stmt = con.createStatement();
					if (action.equals("Create") || action.equals("Update")) {
						String pass2 = request.getParameter("pass2");
						if (!passwd.equals(pass2)) message = "Passwords don't match!";
						else {
							message = "Account " + action.toLowerCase() + "d.";
							String firstName = request.getParameter("firstName"),
							lastName = request.getParameter("lastName"),
							streetAddress = request.getParameter("streetAddress"),
							city = request.getParameter("city"),
							state = request.getParameter("state"),
							zip = request.getParameter("zip"),
							country = request.getParameter("country");
							if (firstName.equals("First name")) firstName = "";
							if (lastName.equals("Last name")) lastName = "";
							if (streetAddress.equals("Street address")) streetAddress = "";
							if (city.equals("City")) city = "";
							if (state.equals("State")) state = "";
							if (zip.equals("ZIP")) zip = "";
							if (country.equals("Country")) country = "";
							if (action.equals("Create")) {
								stmt.executeUpdate("INSERT INTO account (email,password,firstname,lastname,address,city,state,zip,country) VALUES ('"
										+email+"','"+passwd+"','"+firstName+"','"+lastName+"','"+streetAddress+"','"+city+"','"+state+"','"+zip+"','"+country+"');");
							} else if (action.equals("Update")) {
								String oldEmail = session.getAttribute("email").toString();
								stmt.executeUpdate("UPDATE account SET email='"+email
										+"', password='"+passwd
										+"', firstname='"+firstName
										+"', lastname='"+lastName
										+"', address='"+streetAddress
										+"', city='"+city
										+"', state='"+state
										+"', zip='"+zip
										+"', country='"+country
										+"' WHERE email='"+oldEmail+"';");
								session.setAttribute("firstName", firstName);
								session.setAttribute("lastName", lastName);
								session.setAttribute("streetAddress", streetAddress);
								session.setAttribute("city", city);
								session.setAttribute("state", state);
								session.setAttribute("zip", zip);
								session.setAttribute("country", country);
								session.setAttribute("email", email);
							}
						}
					} else if (action.equals("Login")) {
						ResultSet rs = stmt.executeQuery("SELECT * FROM account WHERE (email='" + email + "' AND password='" + passwd + "');");
						if (rs.next()) {
							session.setMaxInactiveInterval(60 * 60);
							session.setAttribute("firstName",
									rs.getString("firstname"));
							session.setAttribute("lastName",
									rs.getString("lastname"));
							session.setAttribute("email",
									rs.getString("email"));
							session.setAttribute("streetAddress",
									rs.getString("address"));
							session.setAttribute("city",
									rs.getString("city"));
							session.setAttribute("state",
									rs.getString("state"));
							session.setAttribute("zip",
									rs.getString("zip"));
							session.setAttribute("country",
									rs.getString("country"));
							session.setAttribute("message", "");
							session.setAttribute("userRole",
									rs.getString("role"));
							session.setAttribute("random", new SecureRandom());
						} else {
							message = "Username or password is incorrect.";
						}
					} else if (action.equals("Delete")) {
						stmt.executeUpdate("DELETE FROM `account` WHERE email='" + email +"';");
						session.invalidate();
					}
					con.close();
				} catch (Exception e) {
					message = e.toString();
				}
			}
			session.setAttribute("message", message);
		} else if (action.equals("Logout")) {
			session.invalidate();
		}
		response.sendRedirect("index.jsp");
	}
}
