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
public class NpcMgr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private GameCharacter NPC;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NpcMgr() {
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
            String action = request.getParameter("action"), message = "NPC " + action.toLowerCase()+"d.", itemId;
            String name=null, type="NPC", icon_url=null, owner="dev@null.com",
                location = "801 Leroy Pl., Socorro, NM, 87801, United States of America", char_class=null,
                status="Normal", npcId=null;
            int level=0, exp=0, required_xp=0,gold=0, hp=0, energy=0,
                strength=0, magic=0, dexterity=0, luck=0,
                current_hp=0, hp_regen=0, current_energy=0, energy_regen=0,
                armor=0, armor_pen=0, magic_res=0, magic_pen=0,
                base_phys_damage=0, base_magic_damage=0,
                attack_speed=0,
                head=0, body=0, shoulders=0, legs=0, hands=0,
                feet=0, accessory=0, mainhand=0, offhand=0;
            float hit_chance=0, crit_chance=0, evasion=0;

            if(action.equals("Create") || action.equals("Update"))
            {
                name = request.getParameter("npcName").toString();
                char_class = request.getParameter("npcChar_class").toString();
                level = Integer.parseInt(request.getParameter("npcLevel").toString());
                exp = Integer.parseInt(request.getParameter("npcExp").toString());
                gold = Integer.parseInt(request.getParameter("npcGold").toString());
                hp = Integer.parseInt(request.getParameter("npcHp").toString());
                current_hp = hp;
                energy = Integer.parseInt(request.getParameter("npcEnergy").toString());
                current_energy = energy;
                armor = Integer.parseInt(request.getParameter("npcArmor").toString());
                armor_pen = Integer.parseInt(request.getParameter("npcArmor_pen").toString());
                magic_res = Integer.parseInt(request.getParameter("npcMagic_res").toString());
                magic_pen = Integer.parseInt(request.getParameter("npcMagic_pen").toString());
                base_phys_damage = Integer.parseInt(request.getParameter("npcBase_phys_damage").toString());
                base_magic_damage = Integer.parseInt(request.getParameter("npcBase_magic_damage").toString());
                attack_speed = Integer.parseInt(request.getParameter("npcAttack_speed").toString());
                hit_chance = Float.parseFloat(request.getParameter("npcHit_chance").toString());
                crit_chance = Float.parseFloat(request.getParameter("npcCrit_chance").toString());
                evasion = Float.parseFloat(request.getParameter("npcEvasion"));
            }

		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			if (action.equals("Create")) {
                                stmt.executeUpdate("INSERT INTO `character` (`type`,`owner`,`class`,`level`,`exp`,`status`,"
                                        +"`gold`,`hp`,`energy`,`strength`,`magic`,`dexterity`,"
                                        +"`luck`,`hp_regen`,`current_hp`,`energy_regen`,`current_energy`,`armor`,"
                                        +"`armor_pen`,`magic_res`,`magic_pen`,`base_phys_damage`,`base_magic_damage`,"
                                        +"`attack_speed`,`hit_chance`,`crit_chance`,`evasion`,`required_xp`,`head`,"
                                        +"`body`,`shoulders`,`legs`,`hands`,`feet`,"
                                        +"`accessory`,`mainhand`,`offhand`,`name`,`icon_url`,`location`)"
                                        +" VALUES('"+type+"','"+owner+"','"+char_class+"','"+level
                                        +"','"+exp+"','"+status+"','"+gold+"','"+hp+"','"
                                        +energy+"','"+strength+"','"+magic+"','"+dexterity
                                        +"','"+luck+"','"+hp_regen+"','"+current_hp+"','"
                                        +energy_regen+"','"+current_energy+"','"+armor+"','"
                                        +armor_pen+"','"+magic_res+"','"+magic_pen+"','"
                                        +base_phys_damage+"','"+base_magic_damage+"','"+attack_speed
                                        +"','"+hit_chance+"','"+crit_chance+"','"+evasion+"','"
                                        +required_xp+"','"+head+"','"+body+"','"+shoulders
                                        +"','"+legs+"','"+hands+"','"+feet+"','"+accessory
                                        +"','"+mainhand+"','"+offhand+"','"+name+"','"+icon_url
                                        +"','"+location+"');");
			} else if (action.equals("Update")) {
				// TODO This section will need to be updated if more attributes are editable.
				npcId = request.getParameter("npcId");
                                stmt.executeUpdate("UPDATE `character` SET type='"+type
                                        +"',owner='"+owner
                                        +"',class='"+char_class
                                        +"',level='"+level
                                        +"',exp='"+exp
                                        +"',status='"+status
                                        +"',gold='"+gold
                                        +"',hp='"+hp
                                        +"',energy='"+energy
                                        +"',strength='"+strength
                                        +"',magic='"+magic
                                        +"',dexterity='"+dexterity
                                        +"',luck='"+luck
                                        +"',hp_regen='"+hp_regen
                                        +"',current_hp='"+current_hp
                                        +"',energy_regen='"+energy_regen
                                        +"',current_energy='"+current_energy
                                        +"',armor='"+armor
                                        +"',armor_pen='"+armor_pen
                                        +"',magic_res='"+magic_res
                                        +"',magic_pen='"+magic_pen
                                        +"',base_phys_damage='"+base_phys_damage
                                        +"',base_magic_damage='"+base_magic_damage
                                        +"',attack_speed='"+attack_speed
                                        +"',hit_chance='"+hit_chance
                                        +"',crit_chance='"+crit_chance
                                        +"',evasion='"+evasion
                                        +"',required_xp='"+required_xp
                                        +"',head='"+head
                                        +"',feet='"+feet
                                        +"',accessory='"+accessory
                                        +"',mainhand='"+mainhand
                                        +"',offhand='"+offhand
                                        +"',name='"+name
                                        +"',icon_url='"+icon_url
                                        +"',location='"+location
                                        +"' WHERE id='"+npcId+"';");
			} else if (action.equals("Delete")) {
				npcId = request.getParameter("npcId");
				stmt.executeUpdate("DELETE FROM `character` WHERE id='" + npcId +"';");
			}
			con.close();
		} catch (Exception e) {
			message = e.toString();
                        out.println("<p>I'M BROKEN! anyways...continuing..."+e);
		}
		session.setAttribute("message", message);
		response.sendRedirect("userHome.jsp");
	}

}
