/*
 * This servlet provides functionality to construct an array of items based on
 * the characters inventory.
 * ISSUES:
 * Untested!
 * Stringjava needs implementation for quantity? How to handle?
 */

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
import java.util.*;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
public class Inventory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	Item[] itemlist;
	ArrayList<Item> inventory;
	
	public Inventory(GameCharacter character) throws SQLException {
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `inventory` WHERE `char_id`="+character.getId());
		while (rs.next()) {
			inventory.add(new Item(rs));
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String charID = request.getParameter("charID");
		out = response.getWriter();
		response.setContentType("text/html");
		// Gather the inventory ID's
		ResultSet inventoryItems = null;
		HttpSession session = request.getSession();

		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			inventoryItems = stmt
					.executeQuery("SELECT * FROM `inventory`, `item`  "
							+ "WHERE (char_id=" + charID + ");");

			int i = 0;

			// Create a new linked list to hold each String This will be
			// converted into an array when finished building
			LinkedList<Item> l = new LinkedList<Item>();

			while (inventoryItems.next()) {
				// Look at all the rows that are in the inventory items

				// Populate the fields for each item

				int id = Integer.parseInt(inventoryItems.getString("id"));
				String name = inventoryItems.getString("name");
				int armor_bonus = Integer.parseInt(inventoryItems
						.getString("armor_bonus"));
				int armor_pen_bonus = Integer.parseInt(inventoryItems
						.getString("armor_pen_bonus"));
				int dex_bonus = Integer.parseInt(inventoryItems
						.getString("dex_bonus"));
				String equip_slots = inventoryItems.getString("equip_slots");
				int hp_bonus = Integer.parseInt(inventoryItems
						.getString("hp_bonus"));
				int hp_regen_bonus = Integer.parseInt(inventoryItems
						.getString("hp_regen_bonus"));
				int luck_bonus = Integer.parseInt(inventoryItems
						.getString("luck_bonus"));
				int mag_bonus = Integer.parseInt(inventoryItems
						.getString("mag_bonus"));
				int magic_pen_bonus = Integer.parseInt(inventoryItems
						.getString("magic_pen_bonus"));
				int magic_res_bonus = Integer.parseInt(inventoryItems
						.getString("magic_res_bonus"));
				int mana_bonus = Integer.parseInt(inventoryItems
						.getString("mana_bonus"));
				int mana_regen_bonus = Integer.parseInt(inventoryItems
						.getString("mana_regen_bonus"));
				int str_bonus = Integer.parseInt(inventoryItems
						.getString("str_bonus"));
				String type = inventoryItems.getString("type");
				String icon = inventoryItems.getString("icon");

				// Create a new item to store values of Items in

				// Create the item here:
				/*
				 * Item item = new Item(id, name, armor_bonus, armor_pen_bonus,
				 * dex_bonus, equip_slots, hp_bonus, hp_regen_bonus, luck_bonus,
				 * mag_bonus, magic_pen_bonus, magic_res_bonus, mana_bonus,
				 * mana_regen_bonus, str_bonus, type, icon);
				 */
				Item item = new Item();
				item.setId(id);
				item.setName(name);
				item.setArmor_bonus(armor_bonus);
				item.setArmor_pen_bonus(armor_pen_bonus);
				item.setDex_bonus(dex_bonus);
				item.setEquip_slots(equip_slots);
				item.setHp_bonus(hp_bonus);
				item.setHp_regen_bonus(hp_regen_bonus);
				item.setLuck_bonus(luck_bonus);
				item.setMag_bonus(mag_bonus);
				item.setMagic_pen_bonus(magic_pen_bonus);
				item.setMagic_res_bonus(magic_res_bonus);
				item.setMana_bonus(mana_bonus);
				item.setMana_regen_bonus(mana_regen_bonus);
				item.setStr_bonus(str_bonus);
				item.setType(type);
				item.setIcon(icon);
				// add the item to the LL
				l.add(item);
			}
			// convert the linked list into an array
			String[] itemList = new String[l.size()];

			ListIterator<Item> itr = l.listIterator();
			i = 0;
			while (itr.hasNext()) {
				// loop through the list, add all of the elements into array
				itemList[i] = itr.next().toString();
				i++;
			}
			// at this point, itemList contains all of the elements as an array,
			// we will add this to the user's current session.
			session.setAttribute("itemList", itemList);

		} catch (Exception e) {
			DisplayError("<p>Error getting character inventory</p>");
		}
	}

	private void DisplayError(String e) {
		out
				.println("<head>\n"
						+ "<link rel='stylesheet' type='text/css' href='css/all.css' />\n"
						+ "</head>\n" + "<body>\n");
		out.println(e);
		out.println("\n</body>\n");
	}
	
	public static ArrayList<Item> getInventory(Integer charId) throws SQLException {
		ArrayList<Item> inventory = new ArrayList<Item>();
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `inventory` JOIN `item` ON inventory.item_id=item.id AND char_id='"+charId+"';");

		while (rs.next()) {
			// Look at all the rows that are in the inventory items
			
			// Get values for item
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int armor_bonus = rs.getInt("armor_bonus");
			int armor_pen_bonus = rs.getInt("armor_pen_bonus");
			int dex_bonus = rs.getInt("dex_bonus");
			String equip_slots = rs.getString("equip_slots");
			int hp_bonus = rs.getInt("hp_bonus");
			int hp_regen_bonus = rs.getInt("hp_regen_bonus");
			int luck_bonus = rs.getInt("luck_bonus");
			int mag_bonus = rs.getInt("mag_bonus");
			int magic_pen_bonus = rs.getInt("magic_pen_bonus");
			int magic_res_bonus = rs.getInt("magic_res_bonus");
			int mana_bonus = rs.getInt("mana_bonus");
			int mana_regen_bonus = rs.getInt("mana_regen_bonus");
			int str_bonus = rs.getInt("str_bonus");
			String type = rs.getString("type");
			String icon = rs.getString("icon");

			// Create a new item with previous values
			Item item = new Item();
			item.setId(id);
			item.setName(name);
			item.setArmor_bonus(armor_bonus);
			item.setArmor_pen_bonus(armor_pen_bonus);
			item.setDex_bonus(dex_bonus);
			item.setEquip_slots(equip_slots);
			item.setHp_bonus(hp_bonus);
			item.setHp_regen_bonus(hp_regen_bonus);
			item.setLuck_bonus(luck_bonus);
			item.setMag_bonus(mag_bonus);
			item.setMagic_pen_bonus(magic_pen_bonus);
			item.setMagic_res_bonus(magic_res_bonus);
			item.setMana_bonus(mana_bonus);
			item.setMana_regen_bonus(mana_regen_bonus);
			item.setStr_bonus(str_bonus);
			item.setType(type);
			item.setIcon(icon);

			// Add item to inventory
			inventory.add(item);
		}
		return inventory;
	}
	
	public static int getQty(int itemId, int charId) throws SQLException {
		System.out.print("Fetching quantity of item id "+itemId+" for player id "+charId+"...");
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `inventory` WHERE (`item_id`="+itemId+" AND `char_id`="+charId+");");
		if (rs.next()) {
			System.out.println(rs.getString("quantity"));
			return rs.getInt("quantity");
		} else {
			return 0;
		}
	}
}
