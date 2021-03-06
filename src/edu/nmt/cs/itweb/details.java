package edu.nmt.cs.itweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class details
 */
public class details extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public details() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type").toString();
		Integer id = Integer.parseInt(request.getParameter("id").toString());
		PrintWriter out = response.getWriter();
		try {
			if (type.equals("item")) {
				Item item = Item.getItem(id);
				out.print("Item name: "+item.getName());
			} else if (type.equals("quest")) {
				Quest quest = Quest.getQuest(id);
				out.print("Quest name: "+quest.getName());
			} else if (type.equals("character")) {
				GameCharacter character = GameCharacter.getCharacter(id);
				out.print("Character name: "+character.getName());
			}
		} catch (SQLException e) {
			out.print("The "+type+" id does not exist!");
		}
		session = request.getSession();
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
		} catch (SQLException e) {
			
		}
		if (type.equals("item")) {
			try {
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT * FROM item WHERE (`id`='" + id + "');");
				Item details = new Item();
				details.setId(id);
				details.setName(rs.getString("name"));
				details.setIcon(rs.getString("icon"));
				session.setAttribute("details",details);
				response.sendRedirect("details.jsp");
			} catch (SQLException e) {
				
			}
			
		}
	}
}
