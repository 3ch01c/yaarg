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
public class ItemMgr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemMgr() {
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
            HttpSession session = request.getSession();
            String action = request.getParameter("action"), message = "Item " + action.toLowerCase()+"d.", itemId=null;
            String name=null, type=null, icon=null;
            int hp_bonus=0, hp_regen_bonus=0, mana_bonus=0, mana_regen_bonus=0, str_bonus=0, mag_bonus=0, dex_bonus=0,
                    luck_bonus=0, armor_pen_bonus=0, magic_pen_bonus=0, armor_bonus=0, magic_res_bonus=0;

            if(action.equals("Create") || action.equals("Update"))
            {
                name = request.getParameter("itemName").toString();
                type = request.getParameter("itemType").toString();
                icon = request.getParameter("itemIcon").toString();
                hp_bonus = Integer.parseInt(request.getParameter("itemHp_bonus"));
                hp_regen_bonus = Integer.parseInt(request.getParameter("itemHp_regen_bonus").toString());
                mana_bonus = Integer.parseInt(request.getParameter("itemMana_bonus").toString());
                mana_regen_bonus = Integer.parseInt(request.getParameter("itemMana_regen_bonus").toString());
                str_bonus = Integer.parseInt(request.getParameter("itemStr_bonus").toString());
                mag_bonus = Integer.parseInt(request.getParameter("itemMag_bonus").toString());
                dex_bonus = Integer.parseInt(request.getParameter("itemDex_bonus").toString());
                luck_bonus = Integer.parseInt(request.getParameter("itemLuck_bonus").toString());
                armor_pen_bonus = Integer.parseInt(request.getParameter("itemArmor_pen_bonus").toString());
                magic_pen_bonus = Integer.parseInt(request.getParameter("itemMagic_pen_bonus").toString());
                armor_bonus = Integer.parseInt(request.getParameter("itemArmor_bonus").toString());
                magic_res_bonus = Integer.parseInt(request.getParameter("itemMagic_res_bonus").toString());
            }
            
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			if (action.equals("Create")) {
                                stmt.executeUpdate("INSERT INTO `item` (`name`,`type`,`hp_bonus`,`hp_regen_bonus`,`mana_bonus`,"
                                        +"`mana_regen_bonus`,`str_bonus`,`mag_bonus`,`dex_bonus`,`luck_bonus`,`armor_pen_bonus`,"
                                        +"`magic_pen_bonus`,`armor_bonus`,`magic_res_bonus`,`icon`)" +
                                        " VALUES('"+name+"','"+type+"','"+hp_bonus+"','"+hp_regen_bonus+"','"+mana_bonus+"','"+
                                        mana_regen_bonus+"','"+str_bonus+"','"+mag_bonus+"','"+dex_bonus+"','"
                                        +luck_bonus+"','"+armor_pen_bonus +"','"+magic_pen_bonus+"','"+armor_bonus+"','"+magic_res_bonus+"','"
                                        +icon +"');");
			} else if (action.equals("Update")) {
				// TODO This section will need to be updated if more attributes are editable.
				itemId = request.getParameter("itemId");
                                stmt.executeUpdate("UPDATE `item` SET name='"+name
                                        +"',type='"+type
                                        +"',hp_bonus='"+hp_bonus
                                        +"',hp_regen_bonus='"+hp_regen_bonus
                                        +"',mana_bonus='"+mana_bonus
                                        +"',mana_regen_bonus='"+mana_regen_bonus
                                        +"',mag_bonus='"+mag_bonus
                                        +"',dex_bonus='"+dex_bonus
                                        +"',luck_bonus='"+luck_bonus
                                        +"',armor_pen_bonus='"+armor_pen_bonus
                                        +"',magic_pen_bonus='"+magic_pen_bonus
                                        +"',armor_bonus='"+armor_bonus
                                        +"',magic_res_bonus='"+magic_res_bonus
                                        +"',icon='"+icon+"' "+
                                        "WHERE id='"+itemId+"';");
			} else if (action.equals("Delete")) {
				itemId = request.getParameter("itemId");
				stmt.executeUpdate("DELETE FROM `item` WHERE id='" + itemId +"';");
			}
			con.close();
		} catch (Exception e) {
			message = e.toString();
		}
		session.setAttribute("message", message);
		response.sendRedirect("userHome.jsp");
	}

}
