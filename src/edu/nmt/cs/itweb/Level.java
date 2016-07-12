package edu.nmt.cs.itweb;

import java.sql.*;

/**
 * Servlet implementation class createChar
 */
class Level{

        public static int Up(GameCharacter mychar, GameCharacter enemychar){
		try {
			Connection con = DBConnection.getConnection();                       
                        Statement stmt = con.createStatement();
                        ResultSet classInfo = stmt.executeQuery("select * from class where class='" + mychar.getChar_class() +
                                                                 "'");
                        classInfo.next();
                        
                        Integer charID = mychar.getId();

                        boolean levelUp = false;
                        Integer newLevel = 0;
                        Integer newExp = enemychar.getExp() + mychar.getExp();
                        if((newExp - mychar.getRequired_xp()) >= 0){
                            levelUp = true;
                            newExp -= mychar.getRequired_xp();
                            newLevel = mychar.getLevel() + 1;
                        }

                        Integer newCurrentHp = mychar.getCurrent_hp();
                        Integer newCurrentEnergy = mychar.getCurrent_energy();
                        Integer newHpRegen = 0;
                        Integer newEnergyRegen = 0;

                        if(levelUp){

                            Integer newGold = (mychar.getGold() + enemychar.getGold());
                            Integer newHp = (mychar.getHp() + Integer.parseInt(classInfo.getString("hp_per_lvl")));
                            Integer newEnergy = (mychar.getEnergy() + Integer.parseInt(classInfo.getString("energy_per_lvl")));
                            newHpRegen = (mychar.getHp_regen() + Integer.parseInt(classInfo.getString("hp_regen")));
                            newEnergyRegen = (mychar.getEnergy_regen() + Integer.parseInt(classInfo.getString("energy_regen")));
                            Integer newArmor = (mychar.getArmor() + Integer.parseInt(classInfo.getString("armor_per_lvl")));
                            Integer newArmorPen = (mychar.getArmor_pen() + Integer.parseInt(classInfo.getString("armor_pen_per_lvl")));
                            Integer newMagicRes = (mychar.getMagic_res() + Integer.parseInt(classInfo.getString("magic_res_per_lvl")));
                            Integer newMagicPen = (mychar.getMagic_pen() + Integer.parseInt(classInfo.getString("magic_pen_per_lvl")));
                            Integer newBasePhys = (mychar.getBase_phys_damage() + Integer.parseInt(classInfo.getString("phys_dmg_per_lvl")));
                            Integer newBaseMag = (mychar.getBase_magic_damage() + Integer.parseInt(classInfo.getString("mag_dmg_per_lvl")));
                            double newRequiredExp = mychar.getRequired_xp() + (mychar.getRequired_xp() * 1.5);
                            newCurrentHp += Integer.parseInt(classInfo.getString("hp_per_lvl"));
                            newCurrentEnergy += Integer.parseInt(classInfo.getString("energy_per_lvl"));

                            stmt.executeUpdate("update character set " +
                                               "level='" + newLevel +
                                               "', " + "gold='" + newGold +
                                               "', " + "hp='" + newHp +
                                               "', " + "energy='" + newEnergy +
                                               "', " + "hp_regen='" + newHpRegen +
                                               "', " + "energy_regen='" + newEnergyRegen +
                                               "', " + "armor='" + newArmor +
                                               "', " + "armor_pen='" + newArmorPen +
                                               "', " + "magic_res='" + newMagicRes +
                                               "', " + "magic_pen='" + newMagicPen +
                                               "', " + "base_phys_damage='" + newBasePhys +
                                               "', " + "base_magic_damage='" + newBaseMag +
                                               "', " + "required_xp='" + newRequiredExp +
                                               "' where id='" + charID + "'");
                        }

                        newCurrentHp += newHpRegen;
                        newCurrentEnergy += newEnergyRegen;
                        stmt.executeUpdate("update character set current_hp='" + newCurrentHp + "', current_energy ='" +
                                            newCurrentEnergy + "' where id='" + charID + "'");    

		} catch (Exception e) {
			
		}
                int returnExp = enemychar.getExp();
                if(enemychar.getType().equals("Character")){
                    returnExp = enemychar.getLevel() * 100;
                }
                return returnExp;
        }
}