package edu.nmt.cs.itweb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class SkillInventory {

    public static ArrayList<Skill> getSkillInventory(Integer charId) throws SQLException {
        ArrayList<Skill> skillInventory = new ArrayList<Skill>();
        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `char_skill` JOIN `skill` ON char_skill.skill=skill.name AND char_id='"+charId+"';");

        while (rs.next()) {
            // Look at all the rows that are in the inventory items
            // Get values for item
            String name = rs.getString("name");
            String element = rs.getString("element");
            float percentage_physical_damage = rs.getFloat("percentage_physical_damage");
            float percentage_magical_damage = rs.getFloat("percentage_magical_damage");
            int base_value = rs.getInt("base_value");
            String skill_type = rs.getString("skill_type");
            String target_type = rs.getString("target_type");
            int generic_modifier = rs.getInt("generic_modifier");
            //secondary skill name
            @SuppressWarnings("unused")
			String secondary_skill = rs.getString("secondary_skill");
            int cost_energy = rs.getInt("cost_energy");
            int cost_hp = rs.getInt("cost_hp");
            int required_level = rs.getInt("required_level");
            String required_class = rs.getString("required_class");
            //required skill name
            @SuppressWarnings("unused")
			String required_skill = rs.getString("required_skill");
            String image = rs.getString("image");

            // Create a new item with previous values
            Skill skill = new Skill();
            skill.setName(name);
            skill.setElement(element);
            skill.setPercentage_physical_damage(percentage_physical_damage);
            skill.setPercentage_magical_damage(percentage_magical_damage);
            skill.setBase_value(base_value);
            skill.setSkill_type(skill_type);
            skill.setTarget_type(target_type);
            skill.setGeneric_modifier(generic_modifier);
            //skill.setSecondary_skill(secondary_skill);
            skill.setCost_energy(cost_energy);
            skill.setCost_hp(cost_hp);
            skill.setRequired_level(required_level);
            //skill.setRequired_skill(required_skill);
            skill.setRequired_class(required_class);
            skill.setImage(image);
            // Add item to inventory
            skillInventory.add(skill);
        }
        return skillInventory;
    }
}

