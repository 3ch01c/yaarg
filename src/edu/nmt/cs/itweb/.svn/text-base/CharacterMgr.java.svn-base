package edu.nmt.cs.itweb;

import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class createChar
 */
public class CharacterMgr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CharacterMgr() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action"),
			message = "Character "+action.toLowerCase()+"d.",
			charId, charName, charClass, charIcon,
			charOwner = request.getSession().getAttribute("email").toString(),
			streetAddress = session.getAttribute("streetAddress").toString(),
			city = session.getAttribute("city").toString(),
			state = session.getAttribute("state").toString(),
			zip = session.getAttribute("zip").toString(),
			country = session.getAttribute("country").toString(),
			location = streetAddress+", "+city+", "+state+", "+zip+", "+country;
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			if (action.equals("Create")) {
				charName = request.getParameter("charName");
				charClass = request.getParameter("charClass");
				charIcon = "images/"+request.getParameter("charIcon");
				if (location.equals("")) location = "Socorro, NM";
				ResultSet rs = stmt.executeQuery("SELECT * FROM `class` WHERE `class`='" + charClass + "';");
				if(rs.next()) {
					String hp = rs.getString("hp_base");
					String energy = rs.getString("energy_base");
					stmt.executeUpdate("INSERT INTO `character` (`owner`,`class`,`name`,`type`,`hp`,`energy`,`strength`," +
							"`magic`,`dexterity`,`luck`,`hp_regen`,`current_hp`,`energy_regen`,`current_energy`," +
							"`armor`,`armor_pen`,`magic_res`,`magic_pen`,`base_phys_damage`,`base_magic_damage`," +
							"`attack_speed`,`hit_chance`,`crit_chance`,`evasion`,`icon_url`,`location`) VALUES ('"
							+ charOwner + "','"
							+ charClass + "','"
							+ charName + "','"
							+ "Character','"
							+ hp + "','"
							+ energy + "','"
							+ rs.getString("str_base") + "','"
							+ rs.getString("mag_base") + "','"
							+ rs.getString("dex_base") + "','"
							+ rs.getString("luck_base") + "','"
							+ rs.getString("hp_regen_base") + "','"
							+ hp + "','"
							+ rs.getString("energy_regen_base") + "','"
							+ energy + "','"
							+ rs.getString("armor_base") + "','"
							+ rs.getString("armor_pen_base") + "','"
							+ rs.getString("magic_res_base") + "','"
							+ rs.getString("magic_pen_base") + "','"
							+ rs.getString("phys_dmg_base") + "','"
							+ rs.getString("magic_dmg_base") + "','"
							+ rs.getString("att_spd_base") + "','"
							+ rs.getString("hit_base") + "','"
							+ rs.getString("crit_base") + "','"
							+ rs.getString("evasion_base") + "','"
							+ charIcon + "','"
							+ location + "');");
				}
                                ArrayList<GameCharacter> charlist = GameCharacter.listCharacters(charOwner);
                                GameCharacter character = charlist.get(charlist.size() - 1);
                                Integer charID = character.getId();                                
                                stmt.executeUpdate("INSERT INTO `char_skill` (`char_id`, `skill`) VALUES ('" + charID + "', 'Fire Blast');");
                                stmt.executeUpdate("INSERT INTO `char_skill` (`char_id`, `skill`) VALUES ('" + charID + "', 'Ice Blast');");
                                con.close();

			} else if (action.equals("Update")) {
                charClass = request.getParameter("charClass");				
				charId = request.getParameter("charId");
				charName = request.getParameter("charName");
				charIcon = request.getParameter("charIcon");
				String str = request.getParameter("strength"),
					mag = request.getParameter("magic"),
					dex = request.getParameter("dexterity"),
					luck = request.getParameter("luck"),
					points = request.getParameter("statpoints");
				
				System.out.println("Class: "+charClass+" ID: "+charId+ " Name: "+charName+" Icon: "+charIcon);
				System.out.println("Str: "+str+" Magic: "+mag+" Dex: "+dex+" Luck: "+luck+" Points: "+points);
				
				/*stmt.executeUpdate("UPDATE `character` SET name='" + charName + "', icon_url='images/" + charIcon +
                                                    "' WHERE id='" + charId + "';");*/
				stmt.executeUpdate("UPDATE `character` SET " +
						"name='" + charName + "', " +
                        "strength='" + str + "', " +
                        "magic='" + mag + "', " +
                        "dexterity='" + dex + "', " +
                        "luck='" + luck + "', " +
						"icon_url='images/" + charIcon + "', " +
                        "stat_points='" + points +
                        "' WHERE id='" + charId + "';");

			} else if (action.equals("Delete")) {
				charId = request.getParameter("charId");
				stmt.executeUpdate("DELETE FROM `character` WHERE id='" + charId +"';");
			}
			con.close();
		} catch (Exception e) {
			message = e.toString();
		}
		session.setAttribute("message", message);
		response.sendRedirect("userHome.jsp");
	}
}
