
package edu.nmt.cs.itweb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Quest {

    public int id;
    public String name;
    public String lore;
    public int required_level;
    public int suggested_level;
    public int difficulty;
    public String questType;
    public int reward_gold;
    public int reward_experience;
    public int repeatable;
    public int req_pre_quest;
    public Date completed_date;

    public Quest(){
        super();
    }
    
    public static Quest getQuest(Integer id) throws SQLException {
		System.out.print("Fetching quest id "+id+"... ");
    	Quest quest = new Quest();
    	Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `quest` WHERE (id='"+id+"');");
		if(rs.next()) {
			quest.setId(rs.getInt("id"));
            quest.setName(rs.getString("name"));
            quest.setLore(rs.getString("lore"));
            quest.setRequired_level(rs.getInt("required_level"));
            quest.setSuggested_level(rs.getInt("suggested_level"));
            quest.setDifficulty(rs.getInt("difficulty"));
            quest.setType(rs.getString("questType"));
            quest.setReward_gold(rs.getInt("reward_gold"));
            quest.setReward_experience(rs.getInt("reward_experience"));
            quest.setRepeatable(rs.getInt("repeatable"));
            quest.setReq_pre_quest(rs.getInt("req_pre_quest"));
		}
		System.out.println("found "+quest.getName()+"!");
    	return quest;
    }
    
	public static ArrayList<Quest> listQuests(Integer char_id) throws SQLException {
    	//System.out.println("Fetching list of characters for "+email+"...");
    	ArrayList<Quest> questList = new ArrayList<Quest>();
    	Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
    	//System.out.println("Executing query...");
		ResultSet rs = stmt.executeQuery("SELECT * FROM `quest_log` WHERE (char_id='"+char_id+"');");
		while(rs.next()) {
			//System.out.println("Fetching character #"+rs.getString("id"));
	    	Quest quest = Quest.getQuest(rs.getInt("id"));
			questList.add(quest);
		}
		con.close();
    	//System.out.println("Found "+characterList.size()+" characters!");
    	return questList;
    }

        public static ArrayList<Quest> listQuests() throws SQLException {
    	//System.out.println("Fetching list of characters for "+email+"...");
    	ArrayList<Quest> questList = new ArrayList<Quest>();
    	Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
    	//System.out.println("Executing query...");
                    ResultSet rs = stmt.executeQuery("SELECT * FROM `quest`;");
		while(rs.next()) {
			//System.out.println("Fetching character #"+rs.getString("id"));
	    	Quest quest = Quest.getQuest(rs.getInt("id"));
			questList.add(quest);
		}
		con.close();
    	//System.out.println("Found "+characterList.size()+" characters!");
    	return questList;
    }

    /**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLore()
    {
        return lore;
    }

    public void setLore(String lore)
    {
        this.lore = lore;
    }

    public Integer getRequired_level()
    {
        return required_level;
    }

    public void setRequired_level(int required_level)
    {
        this.required_level = required_level;
    }

    public Integer getSuggested_level()
    {
        return suggested_level;
    }
    public void setSuggested_level(int suggested_level)
    {
        this.suggested_level = suggested_level;
    }

    public Integer getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

    public String getType()
    {
        return questType;
    }

    public void setType(String questType)
    {
        this.questType = questType;
    }

    public Integer getReward_gold()
    {
        return reward_gold;
    }
    public void setReward_gold(int reward_gold)
    {
        this.reward_gold = reward_gold;
    }

    public Integer getReward_experience()
    {
        return reward_experience;
    }
    public void setReward_experience(int reward_experience)
    {
        this.reward_experience = reward_experience;
    }

    public Integer getRepeatable()
    {
        return repeatable;
    }

    public void setRepeatable(int repeatable)
    {
        this.repeatable = repeatable;
    }

    public Integer getReq_pre_quest()
    {
        return req_pre_quest;
    }

    public void setReq_pre_quest(Integer req_pre_quest)
    {
        this.req_pre_quest = req_pre_quest;
    }

    public Date getCompleted_date()
    {
        return completed_date;
    }

    public void setCompleted_date(Date completed_date)
    {
        this.completed_date = completed_date;
    }
}
