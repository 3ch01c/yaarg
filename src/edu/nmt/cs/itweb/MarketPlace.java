/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nmt.cs.itweb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Marc
 */
public class MarketPlace {
	private String char_name;
	private int price;
	private int qty;
	private Item item;
	@SuppressWarnings("unused")
	private HashMap<GameCharacter, ArrayList<Item>> market;

	public MarketPlace() {
		super();
	}
	
	public static MarketPlace getMarketPlace(Integer id) throws SQLException {
		// System.out.print("Fetching item id "+id+"... ");
		MarketPlace mp = new MarketPlace();
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `market` WHERE (item_id='"
				+ id + "');");
		if (rs.next()) {
			mp.setChar_name(GameCharacter.getCharacter(rs.getInt("char_id")).getName());
			mp.setPrice(rs.getInt("price"));
			mp.setQty(rs.getInt("qty"));
			mp.setItem(Item.getItem(rs.getInt("item_id")));
		}
		con.close();
		//System.out.println("Working!");
		return mp;
	}

	public static ArrayList<MarketPlace> listMarketPlace() throws SQLException {
		//System.out.println("Fetching list of characters for "+email+"...");
		ArrayList<MarketPlace> mpList = new ArrayList<MarketPlace>();
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		//System.out.println("Executing query...");
		ResultSet rs = stmt.executeQuery("SELECT item_id FROM `market`;");
		while(rs.next()) {
			//System.out.println("Fetching character #"+rs.getString("id"));
			MarketPlace mp = MarketPlace.getMarketPlace(rs.getInt("item_id"));
			mpList.add(mp);
		}
		con.close();
		//System.out.println("Found "+characterList.size()+" characters!");
		return mpList;
	}

	public void setChar_name(String char_name)
	{
		this.char_name = char_name;
	}
	public String getChar_name()
	{
		return char_name;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}
	public int getPrice()
	{
		return price;
	}

	public void setQty(int qty)
	{
		this.qty = qty;
	}
	public int getQty()
	{
		return qty;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}
	public Item getItem()
	{
		return item;
	}
}
