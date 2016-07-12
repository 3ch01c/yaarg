package edu.nmt.cs.itweb;

import java.util.Random;

class dmg{
    int damage;
    int attacks;
    boolean crit = false;

    dmg(int damage, int attacks, boolean crit){
        this.damage = damage;
        this.attacks = attacks;
        this.crit = crit;
    }
}

class Damage{

    public static dmg getDamage(GameCharacter mychar, GameCharacter enemychar, Skill skill){

        int enemy_armor = enemychar.getArmor();
        int my_armorpen =mychar.getArmor_pen();
        int final_enemy_armor = enemy_armor-my_armorpen;

        int enemy_magicres = enemychar.getMagic_res();
        int my_magicpen = mychar.getMagic_pen();
        int final_enemy_magicres = enemy_magicres-my_magicpen;

        float phys_skill_percent = skill.getPercentage_physical_damage();
        float mag_skill_percent = skill.getPercentage_magical_damage();
        int base_skill_dmg = skill.getBase_value();
        int char_phys_dmg = mychar.getBase_phys_damage();
        int char_mag_dmg = mychar.getBase_magic_damage();

        float dmg_before_reduc = ((phys_skill_percent*(base_skill_dmg))+(float)char_phys_dmg)
                                 +((mag_skill_percent*(base_skill_dmg))+(float)char_mag_dmg);

        float damage = dmg_before_reduc;
        if(phys_skill_percent != 0){
            damage -= final_enemy_armor;
        }

        if(mag_skill_percent != 0){
            damage -= final_enemy_magicres;
        }

        float crit_chance = mychar.getCrit_chance();
        Random rand = new Random();
        int r = rand.nextInt(100);

        boolean crit = false;
        if(r <= crit_chance){
            crit = true;
        }else{
            crit = false;
        }

        double total_damage = damage;
        if(crit){
            total_damage *= 2.5;
        }

        int my_att_speed = mychar.getAttack_speed();
        int enemy_att_speed = enemychar.getAttack_speed();
        int att_speed_diff = my_att_speed-enemy_att_speed;
        int attacks = 1;

        if((att_speed_diff >= 4) && (att_speed_diff < 10)){
            attacks = 2;
        } else if(att_speed_diff >= 10){
            attacks = 3;
        }

        dmg return_damage = new dmg((int)total_damage, attacks, crit);

        return return_damage;
    }
}

/*class Battle{

    static Random rand = new Random();

    public static int firstAttacker(String[][] my_stats, String[][] enemy_stats){

        int my_dex = Integer.parseInt(Data.getData(my_stats, "dexterity"));
        int enemy_dex = Integer.parseInt(Data.getData(enemy_stats, "dexterity"));

        if(my_dex > enemy_dex){
            return 0;
        }else if(my_dex < enemy_dex){
            return 1;
        }else{
            return rand.nextInt(2);
        }
    }

    

    public static void main(String args[]){
        int myChar_id = Integer.parseInt(args[0]);
        int enemyChar_id = Integer.parseInt(args[1]);

        Battle my_char = new Battle(myChar_id);
        Battle enemy_char = new Battle(enemyChar_id);

        int first_Attacker = firstAttacker(my_char.stats, enemy_char.stats);

        // Tell first attacker to select skill from my_char.skills or enemy_char.skills

        Boolean battleInProgress = true;
        while(battleInProgress){
            String skill_name = getSkillName();
            String[][] skill_stats = new String[50][2];
            skill_stats = getSkill(skill_name);
            int damage = getDamage(my_char.stats, enemy_char.stats, skill_stats);

            for(int i = 0; i < my_char.stats.length; i++){
                if(my_char.stats[i][0].equals("current_hp")){
                    int current_hp = Integer.parseInt(my_char.stats[i][1]);
                    if(Integer.parseInt(my_char.stats[i][1]) >= damage){
                        my_char.stats[i][1] = Integer.toString(current_hp-damage);
                    }else{
                        my_char.stats[i][1] = Integer.toString(0);
                    }
                }
            }

            if(Integer.parseInt(Data.getData(my_char.stats, "current_hp")) == 0 || Integer.parseInt(Data.getData(enemy_char.stats, "current_hp")) == 0){
                battleInProgress = false;
            }
        }
    }




}*/
