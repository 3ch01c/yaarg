package edu.nmt.cs.itweb;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Used for adding, modifying, and deleting items from database.
 * Has additional functionality for generating random items.
 */
public class QuestMgr extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestMgr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            HttpSession session = request.getSession();
            String action = request.getParameter("action"), message = "Quest " + action.toLowerCase()+"d.", questId;
            String name=null, lore=null, type=null;
            int required_level=0, suggested_level=0, difficulty=0, reward_gold=0,reward_experience=0,
                    repeatable=0, req_pre_quest=0;

            if(action.equals("Create") || action.equals("Update"))
            {
                name = request.getParameter("questName").toString();
                lore = request.getParameter("questLore").toString();
                required_level = Integer.parseInt(request.getParameter("questRequired_level").toString());
                suggested_level = Integer.parseInt(request.getParameter("questSuggested_level").toString());
                difficulty = Integer.parseInt(request.getParameter("questDifficulty").toString());
                type = request.getParameter("questType").toString();
                reward_gold = Integer.parseInt(request.getParameter("questReward_gold").toString());
                reward_experience = Integer.parseInt(request.getParameter("questReward_experience").toString());
                repeatable = Integer.parseInt(request.getParameter("questRepeatable").toString());
                req_pre_quest = Integer.parseInt(request.getParameter("questReq_pre_quest").toString());
            }

		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			if (action.equals("Create")) {
                                stmt.executeUpdate("INSERT INTO `quest` (`name`,`lore`,`required_level`,`suggested_level`,`difficulty`,`type`,"
                                        +"`reward_gold`,`reward_experience`,`repeatable`,`req_pre_quest`)"
                                        +" VALUES('"+name+"','"+lore+"','"+required_level+"','"+suggested_level+"','"+difficulty+"','"
                                        +type+"','"+reward_gold+"','"+reward_experience+"','"+repeatable+"','"+req_pre_quest+"');");
			} else if (action.equals("Update")) {
				// TODO This section will need to be updated if more attributes are editable.
				questId = request.getParameter("questId");
                                stmt.executeUpdate("UPDATE `quest` SET name='"+name
                                        +"',lore='"+lore
                                        +"',required_level='"+required_level
                                        +"',suggested_level='"+suggested_level
                                        +"',difficulty='"+difficulty
                                        +"',type='"+type
                                        +"',reward_gold='"+reward_gold
                                        +"',reward_experience='"+reward_experience
                                        +"',repeatable='"+repeatable
                                        +"',req_pre_quest='"+req_pre_quest
                                        +"' WHERE id='"+questId+"';");
			} else if (action.equals("Delete")) {
                                questId = request.getParameter("questId");
				stmt.executeUpdate("DELETE FROM `quest` WHERE id='" + questId +"';");
			}
			con.close();
		} catch (Exception e) {
			message = e.toString();
                        out.println("<p> IT'S BROKEN! anyways...continuing....<br>"+message);
		}
		session.setAttribute("message", message);
		response.sendRedirect("userHome.jsp");
	}

}
