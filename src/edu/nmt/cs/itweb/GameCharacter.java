package edu.nmt.cs.itweb;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * A {@code GameCharacter} represents a player or non-player character in the game.
 * In addition to explicitly defining characters and attributes, this class also 
 * includes functions for generating random characters or attributes. 
 */
public class GameCharacter {

	private Integer id, level, exp, required_xp,
	gold, hp, energy,
	strength, magic, dexterity, luck,
	current_hp, hp_regen, current_energy, energy_regen,
	armor, armor_pen, magic_res, magic_pen,
	base_phys_damage, base_magic_damage,
	attack_speed, head, body, shoulders, legs, hands, feet, accessory, mainhand, offhand,
	kills, deaths, stat_points;

	private String name, icon_url, type, owner, location,
	char_class, status;

	private Float hit_chance, crit_chance, evasion;
	
	private ArrayList<Quest> questLog;

	public SecureRandom random = new SecureRandom();

	public GameCharacter() {
		
	}

	/**
	 * 
	 * @param Integer numOfSkills
	 * @return Integer
	 */
	public Integer RandomAttack(Integer numOfSkills)
	{
		//TODO variables will be changed depending on Battle Servlet
		Random generator = new Random(new Date().getTime());

		// Generates a number based on number of skills and the resulting number corresponds
		// to the attack to be done.
		Integer num = generator.nextInt(numOfSkills);
		return num;
	}

    /**
     * Creates a non-player character.
     * @param Integer id
     * @return {@linkplain GameCharacter} GameCharacter
     */
    //Flag Boolean is true if the generated NPC is to be commited to the database
	public static GameCharacter NPC() throws SQLException {
		GameCharacter npc = new GameCharacter();
		int level;
		try {
			level = Math.abs(npc.random.nextInt()%50);
		} catch (Exception e) {
			level = 0;
		}
		npc = GameCharacter.NPC(level, true);
		return npc;
	}

	/**
     * Creates a non-player character.
     * @param Integer id, Boolean db_insert?
     * @return {@linkplain GameCharacter} GameCharacter
     */

	/**
     * Creates a non-player character.
     * @param Integer id
     * @return {@linkplain GameCharacter} GameCharacter
     */
    //Flag Boolean is true if the generated NPC is to be commited to the database
	public static GameCharacter NPC(Integer level) throws SQLException {
		return GameCharacter.NPC(level, false);
	}

	/**
     * Creates a non-player character.
     * @param Integer id, Boolean db_insert?
     * @return {@linkplain GameCharacter} GameCharacter
     */

	//Flag Boolean is true if the generated NPC is to be commited to the database
    public static GameCharacter NPC(int level,boolean flag) throws SQLException {
        Random generator = new Random(new Date().getTime());
        int hp = generator.nextInt(5)*level+10;
        int energy = generator.nextInt(5)*level+10;
        //TODO add class and type parameter
        //All stats are randomized within +-10 of the level
        GameCharacter npc = new GameCharacter();

        npc.setType("NPC");
        npc.setOwner("dev@null.com");
        npc.setChar_class("NPC");
        npc.setLevel(level);
        npc.setExp(level+generator.nextInt(10));
        npc.setStatus("Normal");
        npc.setGold(level*generator.nextInt(10));
        npc.setHp(hp);
        npc.setEnergy(energy);
        npc.setStrength(level/2+generator.nextInt(10));
        npc.setMagic(level/2+generator.nextInt(10));
        npc.setDexterity(level/2+generator.nextInt(10));
        npc.setLuck(level/2+generator.nextInt(10));
        npc.setHp_regen(0);
        npc.setCurrent_hp(hp);
        npc.setEnergy_regen(0);
        npc.setCurrent_energy(energy);
        npc.setArmor(level/2+generator.nextInt(10));
        npc.setArmor_pen(level/2+generator.nextInt(10));
        npc.setMagic_res(level/2+generator.nextInt(10));
        npc.setMagic_pen(level/2+generator.nextInt(10));
        npc.setBase_phys_damage(level/2+generator.nextInt(10));
        npc.setBase_magic_damage(level/2+generator.nextInt(10));
        npc.setAttack_speed(level/2+generator.nextInt(10));
        npc.setHit_chance(((float) (level/8)/100)+(generator.nextFloat()*5));
        npc.setCrit_chance(((float) (level/8)/100)+(generator.nextFloat()*5));
        npc.setEvasion(((float) (level/8)/100)+(generator.nextFloat()*5));
        npc.setRequired_xp(0);
        npc.setName();
        npc.setIcon_url();
        npc.setLocation("801 Leroy Pl., Socorro, NM, 87801, United States of America");

        if(flag)
        {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO `character` (`type`,`owner`,`class`,`level`,`exp`,`status`,"
                                    +"`gold`,`hp`,`energy`,`strength`,`magic`,`dexterity`,"
                                    +"`luck`,`hp_regen`,`current_hp`,`energy_regen`,`current_energy`,`armor`,"
                                    +"`armor_pen`,`magic_res`,`magic_pen`,`base_phys_damage`,`base_magic_damage`,"
                                    +"`attack_speed`,`hit_chance`,`crit_chance`,`evasion`,`required_xp`,`name`,`icon_url`,`location`)"
                                    +" VALUES('"+npc.getType()+"','"+npc.getOwner()+"','"+npc.getChar_class()+"','"+npc.getLevel()
                                    +"','"+npc.getExp()+"','"+npc.getStatus()+"','"+npc.getGold()+"','"+npc.getHp()+"','"
                                    +npc.getEnergy()+"','"+npc.getStrength()+"','"+npc.getMagic()+"','"+npc.getDexterity()
                                    +"','"+npc.getLuck()+"','"+npc.getHp_regen()+"','"+npc.getCurrent_hp()+"','"
                                    +npc.getEnergy_regen()+"','"+npc.getCurrent_energy()+"','"+npc.getArmor()+"','"
                                    +npc.getArmor_pen()+"','"+npc.getMagic_res()+"','"+npc.getMagic_pen()+"','"
                                    +npc.getBase_phys_damage()+"','"+npc.getBase_magic_damage()+"','"+npc.getAttack_speed()
                                    +"','"+npc.getHit_chance()+"','"+npc.getCrit_chance()+"','"+npc.getEvasion()+"','"
                                    +npc.getRequired_xp()+"','"+npc.getName()+"','"+npc.getIcon_url()
                                    +"','"+npc.getLocation()+"');");
        }
        else
        {
            npc.setId(-1);
        }

        ArrayList<GameCharacter> npcList = GameCharacter.listCharacters("dev@null.com");
		npc = npcList.get(npcList.size()-1);
        return npc;
    }
    
    /**
	 * @return the stat_points
	 */
	public Integer getStat_points() {
		return stat_points;
	}

	/**
	 * @param statPoints the stat_points to set
	 */
	public void setStat_points(Integer statPoints) {
		stat_points = statPoints;
	}

	/**
	 * @return the questLog
	 */
	public ArrayList<Quest> getQuestLog() {
		return questLog;
	}

	/**
	 * @param questLog the questLog to set
	 */
	public void setQuestLog(ArrayList<Quest> questLog) {
		this.questLog = questLog;
	}

	/**
     * Gets a character from the database given an ID.
     * @param Integer id
     * @return {@linkplain GameCharacter}
     * @throws SQLException
     */
    public static GameCharacter getCharacter(Integer id) throws SQLException {
		System.out.print("Fetching character id "+id+"... ");
		GameCharacter character = new GameCharacter();
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `character` WHERE (id='"+id+"');");
		if(rs.next()) {
			character.setId(rs.getInt("id"));
			character.setType(rs.getString("type"));
			character.setOwner(rs.getString("owner"));
			character.setChar_class(rs.getString("class"));
                        character.setGold(rs.getInt("gold"));
			character.setLevel(rs.getInt("level"));
			character.setExp(rs.getInt("exp"));
			character.setStatus(rs.getString("status"));
			character.setHp(rs.getInt("hp"));
			character.setEnergy(rs.getInt("energy"));
			character.setStrength(rs.getInt("strength"));
			character.setMagic(rs.getInt("magic"));
			character.setDexterity(rs.getInt("dexterity"));
			character.setLuck(rs.getInt("luck"));
			character.setHp_regen(rs.getInt("hp_regen"));
			character.setCurrent_hp(rs.getInt("current_hp"));
			character.setEnergy_regen(rs.getInt("energy_regen"));
			character.setCurrent_energy(rs.getInt("current_energy"));
			character.setArmor(rs.getInt("armor"));
			character.setArmor_pen(rs.getInt("armor_pen"));
			character.setMagic_res(rs.getInt("magic_res"));
			character.setMagic_pen(rs.getInt("magic_pen"));
			character.setBase_phys_damage(rs.getInt("base_phys_damage"));
			character.setBase_magic_damage(rs.getInt("base_magic_damage"));
			character.setAttack_speed(rs.getInt("attack_speed"));
			character.setHit_chance(rs.getFloat("hit_chance"));
			character.setCrit_chance(rs.getFloat("crit_chance"));
			character.setEvasion(rs.getFloat("evasion"));
			character.setRequired_xp(rs.getInt("required_xp"));
			character.setHead(rs.getInt("head"));
			character.setBody(rs.getInt("body"));
			character.setShoulders(rs.getInt("shoulders"));
			character.setLegs(rs.getInt("legs"));
			character.setHands(rs.getInt("hands"));
			character.setFeet(rs.getInt("feet"));
			character.setAccessory(rs.getInt("accessory"));
			character.setMainhand(rs.getInt("mainhand"));
			character.setOffhand(rs.getInt("offhand"));
			character.setName(rs.getString("name"));
			character.setIcon_url(rs.getString("icon_url"));
			character.setLocation(rs.getString("location"));
                        character.setStatPoints(rs.getInt("stat_points"));
			character.setLocation(rs.getString("location"));
			character.setKills(rs.getInt("kills"));
			character.setDeaths(rs.getInt("deaths"));

		}
		System.out.println("found "+character.getName()+"!");
		return character;
	}

	/**
	 * Creates a list of all characters in the database.
	 * @param String email
	 * @return ArrayList<{@linkplain GameCharacter}>
	 * @throws SQLException
	 */
	public static ArrayList<GameCharacter> listCharacters() throws SQLException {
		//System.out.println("Fetching entire list of characters...");
		ArrayList<GameCharacter> characterList = new ArrayList<GameCharacter>();
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		//System.out.println("Executing query...");
		ResultSet rs = stmt.executeQuery("SELECT id FROM `character`;");
		while(rs.next()) {
			//System.out.println("Fetching character #"+rs.getString("id"));
			GameCharacter character = GameCharacter.getCharacter(rs.getInt("id"));
			characterList.add(character);
		}
		con.close();
		//System.out.println("Found "+characterList.size()+" characters!");
		return characterList;
	}

	/**
	 * Creates a list of characters for a given user.
	 * @param String email
	 * @return ArrayList<{@linkplain GameCharacter}>
	 * @throws SQLException
	 */
	public static ArrayList<GameCharacter> listCharacters(String email) throws SQLException {
		System.out.println("Fetching list of characters for "+email+"...");
		ArrayList<GameCharacter> characterList = new ArrayList<GameCharacter>();
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		//System.out.println("Executing query...");
		ResultSet rs = stmt.executeQuery("SELECT id FROM `character` WHERE (owner='"+email+"');");
		while(rs.next()) {
			//System.out.println("Fetching character #"+rs.getString("id"));
			GameCharacter character = GameCharacter.getCharacter(rs.getInt("id"));
			characterList.add(character);
		}
		con.close();
		//System.out.println("Found "+characterList.size()+" characters!");
		return characterList;
	}

	/**
	 * Creates a list of completed quests for this character.
	 * @return ArrayList<{@linkplain Quest>
	 * @throws SQLException
	 */
	public ArrayList<Quest> getCompletedQuests() throws SQLException {
		questLog = new ArrayList<Quest>();
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT `quest_id` FROM `quest_log` WHERE (char_id='"+this.getId()+"' AND completed_date>0);");
		while(rs.next()) {
			Quest quest = Quest.getQuest(rs.getInt("char_id"));
			questLog.add(quest);
		}
		con.close();
		return questLog;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the exp
	 */
	public Integer getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(Integer exp) {
		this.exp = exp;
	}

	/**
	 * @return the required_xp
	 */
	public Integer getRequired_xp() {
		return required_xp;
	}

	/**
	 * @param requiredXp the required_xp to set
	 */
	public void setRequired_xp(Integer requiredXp) {
		required_xp = requiredXp;
	}

	/**
	 * @return the gold
	 */
	public Integer getGold() {
		return gold;
	}

	/**
	 * @param gold the gold to set
	 */
	public void setGold(Integer gold) {
		this.gold = gold;
	}

	/**
	 * @return the hp
	 */
	public Integer getHp() {
		return hp;
	}

	/**
	 * @param hp the hp to set
	 */
	public void setHp(Integer hp) {
		this.hp = hp;
	}

	/**
	 * @return the energy
	 */
	public Integer getEnergy() {
		return energy;
	}

	/**
	 * @param energy the energy to set
	 */
	public void setEnergy(Integer energy) {
		this.energy = energy;
	}

	/**
	 * @return the strength
	 */
	public Integer getStrength() {
		return strength;
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	/**
	 * @return the magic
	 */
	public Integer getMagic() {
		return magic;
	}

	/**
	 * @param magic the magic to set
	 */
	public void setMagic(Integer magic) {
		this.magic = magic;
	}

	/**
	 * @return the dexterity
	 */
	public Integer getDexterity() {
		return dexterity;
	}

	/**
	 * @param dexterity the dexterity to set
	 */
	public void setDexterity(Integer dexterity) {
		this.dexterity = dexterity;
	}

	/**
	 * @return the luck
	 */
	public Integer getLuck() {
		return luck;
	}

	/**
	 * @param luck the luck to set
	 */
	public void setLuck(Integer luck) {
		this.luck = luck;
	}

	/**
	 * @return the current_hp
	 */
	public Integer getCurrent_hp() {
		return current_hp;
	}

	/**
	 * @param currentHp the current_hp to set
	 */
	public void setCurrent_hp(Integer currentHp) {
		current_hp = currentHp;
	}

	/**
	 * @return the hp_regen
	 */
	public Integer getHp_regen() {
		return hp_regen;
	}

	/**
	 * @param hpRegen the hp_regen to set
	 */
	public void setHp_regen(Integer hpRegen) {
		hp_regen = hpRegen;
	}

	/**
	 * @return the current_energy
	 */
	public Integer getCurrent_energy() {
		return current_energy;
	}

	/**
	 * @param currentEnergy the current_energy to set
	 */
	public void setCurrent_energy(Integer currentEnergy) {
		current_energy = currentEnergy;
	}

	/**
	 * @return the energy_regen
	 */
	public Integer getEnergy_regen() {
		return energy_regen;
	}

	/**
	 * @param energyRegen the energy_regen to set
	 */
	public void setEnergy_regen(Integer energyRegen) {
		energy_regen = energyRegen;
	}

	/**
	 * @return the armor
	 */
	public Integer getArmor() {
		return armor;
	}

	/**
	 * @param armor the armor to set
	 */
	public void setArmor(Integer armor) {
		this.armor = armor;
	}

	/**
	 * @return the armor_pen
	 */
	public Integer getArmor_pen() {
		return armor_pen;
	}

	/**
	 * @param armorPen the armor_pen to set
	 */
	public void setArmor_pen(Integer armorPen) {
		armor_pen = armorPen;
	}

	/**
	 * @return the magic_res
	 */
	public Integer getMagic_res() {
		return magic_res;
	}

	/**
	 * @param magicRes the magic_res to set
	 */
	public void setMagic_res(Integer magicRes) {
		magic_res = magicRes;
	}

	/**
	 * @return the magic_pen
	 */
	public Integer getMagic_pen() {
		return magic_pen;
	}

	/**
	 * @param magicPen the magic_pen to set
	 */
	public void setMagic_pen(Integer magicPen) {
		magic_pen = magicPen;
	}

	/**
	 * @return the base_phys_damage
	 */
	public Integer getBase_phys_damage() {
		return base_phys_damage;
	}

	/**
	 * @param basePhysDamage the base_phys_damage to set
	 */
	public void setBase_phys_damage(Integer basePhysDamage) {
		base_phys_damage = basePhysDamage;
	}

	/**
	 * @return the base_magic_damage
	 */
	public Integer getBase_magic_damage() {
		return base_magic_damage;
	}

	/**
	 * @param baseMagicDamage the base_magic_damage to set
	 */
	public void setBase_magic_damage(Integer baseMagicDamage) {
		base_magic_damage = baseMagicDamage;
	}

	/**
	 * @return the attack_speed
	 */
	public Integer getAttack_speed() {
		return attack_speed;
	}

	/**
	 * @param attackSpeed the attack_speed to set
	 */
	public void setAttack_speed(Integer attackSpeed) {
		attack_speed = attackSpeed;
	}

	/**
	 * @return the hit_chance
	 */
	public Float getHit_chance() {
		return hit_chance;
	}

	/**
	 * @param hitChance the hit_chance to set
	 */
	public void setHit_chance(Float hitChance) {
		hit_chance = hitChance;
	}

	/**
	 * @return the crit_chance
	 */
	public Float getCrit_chance() {
		return crit_chance;
	}

	/**
	 * @param critChance the crit_chance to set
	 */
	public void setCrit_chance(Float critChance) {
		crit_chance = critChance;
	}

	/**
	 * @return the evasion
	 */
	public Float getEvasion() {
		return evasion;
	}

	/**
	 * @param evasion the evasion to set
	 */
	public void setEvasion(Float evasion) {
		this.evasion = evasion;
	}

	/**
	 * @return the head
	 */
	public Integer getHead() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(Integer head) {
		this.head = head;
	}

	/**
	 * @return the body
	 */
	public Integer getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(Integer body) {
		this.body = body;
	}

	/**
	 * @return the shoulders
	 */
	public Integer getShoulders() {
		return shoulders;
	}

	/**
	 * @param shoulders the shoulders to set
	 */
	public void setShoulders(Integer shoulders) {
		this.shoulders = shoulders;
	}

	/**
	 * @return the legs
	 */
	public Integer getLegs() {
		return legs;
	}

	/**
	 * @param legs the legs to set
	 */
	public void setLegs(Integer legs) {
		this.legs = legs;
	}

	/**
	 * @return the hands
	 */
	public Integer getHands() {
		return hands;
	}

	/**
	 * @param hands the hands to set
	 */
	public void setHands(Integer hands) {
		this.hands = hands;
	}

	/**
	 * @return the feet
	 */
	public Integer getFeet() {
		return feet;
	}

	/**
	 * @param feet the feet to set
	 */
	public void setFeet(Integer feet) {
		this.feet = feet;
	}

	/**
	 * @return the accessory
	 */
	public Integer getAccessory() {
		return accessory;
	}

	/**
	 * @param accessory the accessory to set
	 */
	public void setAccessory(Integer accessory) {
		this.accessory = accessory;
	}

	/**
	 * @return the mainhand
	 */
	public Integer getMainhand() {
		return mainhand;
	}

	/**
	 * @param mainhand the mainhand to set
	 */
	public void setMainhand(Integer mainhand) {
		this.mainhand = mainhand;
	}

	/**
	 * @return the offhand
	 */
	public Integer getOffhand() {
		return offhand;
	}

	/**
	 * @param offhand the offhand to set
	 */
	public void setOffhand(Integer offhand) {
		this.offhand = offhand;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return A randomly generated name from the name wordlist. 
	 */
	public void setName() {
		String[] nameList = { "Morgan Duskbane", "Midnight Diredemon",
				"Shadow Valentine", "Honor Bleu", "Fate Opera",
				"Jackal Galegrave", "Mordred Dire", "Honor Witcheater",
				"Nemesis Clash", "Maxim Gold", "Sin Earthfinder",
				"Icon Wildseeker", "Amun Beaksoul", "Blizzard Suntamer",
				"Gades Shroud", "Drago Dragonfist", "Honor Dashing",
				"Rage Madtiger", "Jewel Fire", "Eden Earthdread", "Rose Rose",
				"Dawn Mimefist", "Morax Hellbringer", "Shadow Seraphforge",
				"Ash Holyblade", "Daos Darkdevil", "Eva Angellight",
				"Erim Pyretamer", "Veil Thunderclaw", "Purity Shadowman",
				"Claw Icedevil", "Curse Mystery", "Irom Silverdark	",
				"Kaiser Ironfist", "Opera Doom", "Dagger Warlock",
				"Pandora Witchtiger", "Ragnarok Mimeslayer", "Grim Paradox",
				"Aeon Wild", "Mist Tempest", "Thulsa Windbane",
				"Coran Icequeen", "Moon Royal", "Storm the Goldchanter",
				"Jin Warlock", "Rune Mad", "Ivy Rainrage", "Purity Stonejaw",
				"Eve Maverick", "Fate Snakehammer", "Adamant Wolf",
				"Edge Royal", "Dagger Grimjaw", "Morgan Grimdark",
				"Hawk Thunderhunter", "Thulsa Hategrave", "Gem Nazareth",
				"Midnight Snowclaw", "Aelia Bonefinger", "Mimi Holy",
				"Rune Shadow", "Jack Thunder", "Cage Clash", "Maxim Fragrance",
				"Jackal Rockhammer", "Irom Grimmantle", "Lance Royal",
				"Dred Rock", "Dred Twilight", "Fury Dark", "Lynx Fragrance",
				"Nemesis Earth", "Tarot Bleu", "Thulsa Hatelight",
				"Rand Stone", "Ashe Direchanter", "Grimm Talonforge",
				"Pyre Ironcaster", "Blood Shadotendril", "Pandora Hell",
				"Rose Rockdark", "Eros Frostspear", "Ash Shadowbringer",
				"Blizzard Silverdevil", "Dred Time", "Draco Pyresayer",
				"Rain Dire", "Grail Ghost", "Eve Duskgrave", "Joanna Sun",
				"Rune Knight", "Ivy Dire", "Pyre Pyre", "Curse Angelblade",
				"Morax Knight", "Morax Nazareth", "Hawk Veil",
				"Shadow Thundergrave", "Jewel Mystery", "Shrike Sinnermourn",
				"Arcana Tempest", "Raven Earthman", "Drago Dusk",
				"Squall Royal", "Rose Moonlight", "Lain Hell", "Tsu Tempest",
				"Blood Witchmaimer", "Lynx Wildman" };
		this.name = nameList[(int) (nameList.length*Math.random())];
	}

	/**
	 * @return the icon_url
	 */
	public String getIcon_url() {
		return icon_url;
	}

	/**
	 * @param iconUrl the icon_url to set
	 */
	public void setIcon_url(String iconUrl) {
		this.icon_url = iconUrl;
	}

	/**
	 * @param iconUrl the icon_url to set
	 */
	public void setIcon_url() {
		String[] list = {"darkDude_t.png", "darkrai_t.png", "demon_t.png", "dervish_t.png", "gyarados_t.png"};
		Integer num = Math.abs((random.nextInt())%(list.length));
		System.out.println("Icon is "+list[num]+"("+num+" of "+(list.length-1)+")");
		this.icon_url = "images/"+list[num];
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the char_class
	 */
	public String getChar_class() {
		return char_class;
	}

	public String randomClass() {
		String[] charClass = { "Skeletal Rider", "Bone Hunter",
				"Wererat Champion", "Shadowy Baron", "Shining King",
				"Ivy Dancer", "Mist Sage", "Scorpion Squisher",
				"Harmony Bandit", "Phantom Chancellor", "Zombimancer",
				"Petunia Priest", "Fungal Sorceress", "Seamancer",
				"Monster Master", "Pearl Samurai", "Nightmare Sorceress",
				"Tentacle Catcher", "Dragon Slayer", "Mushroom Warlord",
				"Mysterious Hermit", "Umbral Paladin", "Mosquito Knight",
				"Mire Lord", "Onyx Defender", "Leviathan Banisher",
				"Magus Knight", "Wyrm Illusionist", "Bone Crusher",
				"Moon Summoner", "Sand Marksman", "Tree Knight",
				"Umbral Priestess", "Harmonic Rogue", "Warrior Knight",
				"Cockatrice Studier", "Bandit Catcher", "Genie Knight",
				"Cultist Knight", "Mud Princess", "Gargoyle Follower",
				"Oblivion Barbarian", "Hellfire Lancer", "Crest Dwarf",
				"Muckman Summoner", "Unholy Mercenary", "Sand Mistress",
				"Heal Lord", "Gargoyle Follower", "Whirlomancer",
				"Naga Knight", "Nether Priest", "Dungeon Soldier",
				"Genie Baron", "Ghost Crusher", "Holy Rogue", "Wavemancer",
				"Bone Charmer", "Inferno Psionic", "Turtle Exterminator",
				"Zombie Summoner", "Cyclone Ruler", "Flaming Seeker",
				"Misty Enchanter", "Moonlight Seeker", "Tree Hero",
				"Pyromancer", "Panther Visionary", "Noble Carver",
				"Doll Ruler", "Sapphire Priest", "Phantasm Slicer",
				"Psionic Enchanter", "Crest Ranger", "Archaic Bowman",
				"Darkness Chemist", "Bee Mage", "Gravity Merchant",
				"Pyro Runner", "Ninjamancer", "Harpy Knight", "Brown Bandit",
				"Spider Summoner", "Humanoid Visionary", "Tulimancer",
				"Mountain Tamer", "Nymph Banisher", "Gemstone Commander",
				"Lizardman Summoner", "Abyssall Illusionist",
				"Crescent Cavalier", "Beast Oracle", "Banshee Keeper",
				"Time Swallower", "Inferno Mercenary", "Voidbeast Master",
				"Brown Archmage", "Dracolich Summoner", "Rosemancer",
				"Ghost Studier", "Cockatrice Piercer", "Spectre Grappler",
				"Hell Lord", "Fungus Blacksmith", "Barrier Soldier",
				"Zombie Shredder", "Marionette Wiggler", "Harmonic Halfling",
				"Royal Merchant", "Grove Hero", "Sunlight Druid",
				"Plant Warlock", "Tornado Squire", "Goblin Keeper",
				"Beetle Masher", "Mage Master", "Wolf Wiggler",
				"Abyssmal Sniper", "Tundra Jester", "Fallen Archmage",
				"Monster Studier", "Oceanic Carver", "Bug Slasher",
				"Bird Warlord", "Heal Pugilist", "Wererat Summoner",
				"Crystal Bandit", "Cyclops Knight", "Rabbit Squisher",
				"Humanoid Squisher", "Kingdom Sage", "Glade Pirate",
				"Illusionary Paladin", "Mire Smith", "Monser King",
				"Ghoul Knight", "Sapphire Priestess", "Sea Assassin",
				"Ettin Knight", "Eternity Maiden", "Hell Bowman", "Vipermancer" };
		return charClass[(int) (charClass.length*Math.random())];
	}

	/**
	 * @param charClass the char_class to set
	 */
	public void setChar_class(String charClass) {
		char_class = charClass;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

        public void setStatPoints(int stat_pts) {
		this.stat_points = stat_pts;
	}

        public int getStatPoints() {
		return stat_points;
	}
	
	/**
	 * @param kills the kills to set
	 */
	public void setKills(Integer kills) {
		this.kills = kills;
	}
	/**
	 * @return the kills
	 */
	public Integer getKills() {
		return kills;
	}

	/**
	 * @param deaths the deaths to set
	 */
	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}
	/**
	 * @return the status
	 */
	public Integer getDeaths() {
		return deaths;
	}
}
