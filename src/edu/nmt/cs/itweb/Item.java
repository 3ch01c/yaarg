	package edu.nmt.cs.itweb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

/**
 * @author james
 *
 */
/**
 * @author james
 * 
 */
public class Item {

	private Integer id,
		hp_bonus,
		hp_regen_bonus,
		mana_bonus,
		mana_regen_bonus,
		str_bonus,
		mag_bonus,
		dex_bonus,
		luck_bonus,
		armor_pen_bonus,
		magic_pen_bonus,
		armor_bonus,
		magic_res_bonus,
		level;
	
	Random random = new Random(System.currentTimeMillis());

	private String name,
		itemType,
		equip_slots,
		icon;

	/**
	 * @return Empty item
	 */
	public Item() {
		super();
		
		setType();
		setLevel();
		setIcon();
		setName();
		setHealth();
		setEnergy();
		setHealthRegen();
		setEnergyRegen();
		setStr();
		setMag();
		setDex();
		setLuck();
		setArmorPen();
		setMagicPen();
		setArmor();
		setMagicRes();
	}
	
	public Item(ResultSet rs) {
		
	}

        public static ArrayList<Item> listItem() throws SQLException {
		//System.out.println("Fetching list of characters for "+email+"...");
		ArrayList<Item> itemList = new ArrayList<Item>();
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		//System.out.println("Executing query...");
		ResultSet rs = stmt.executeQuery("SELECT item_id FROM `market`;");
		while(rs.next()) {
			//System.out.println("Fetching character #"+rs.getString("id"));
			Item item = Item.getItem(rs.getInt("id"));
			itemList.add(item);
		}
		con.close();
		//System.out.println("Found "+characterList.size()+" characters!");
		return itemList;
	}

        public static ArrayList<Item> listActualItem() throws SQLException {
		//System.out.println("Fetching list of characters for "+email+"...");
		ArrayList<Item> itemList = new ArrayList<Item>();
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		//System.out.println("Executing query...");
		ResultSet rs = stmt.executeQuery("SELECT id FROM `item`;");
		while(rs.next()) {
			//System.out.println("Fetching character #"+rs.getString("id"));
			Item item = Item.getItem(rs.getInt("id"));
			itemList.add(item);
		}
		con.close();
		//System.out.println("Found "+characterList.size()+" characters!");
		return itemList;
	}

	private void setMagicRes() {
		try {
			this.magic_res_bonus = Math.abs((int) (random.nextInt()%(2 * this.level)));
		} catch (Exception e) {
			this.magic_res_bonus = 0;
		}
	}

	private void setArmor() {
		try {
			this.armor_bonus = Math.abs((int) (random.nextInt()%(7 * this.level)));
		} catch (Exception e) {
			this.armor_bonus = 0;
		}
	}

	private void setMagicPen() {
		try {
			this.magic_pen_bonus = Math.abs((int) (random.nextInt()%(2 * this.level)));
		} catch (Exception e) {
			this.magic_pen_bonus = 0;
		}
	}

	private void setArmorPen() {
		try {
			this.armor_pen_bonus = Math.abs((int) (random.nextInt()%(2 * this.level)));
		} catch (Exception e) {
			this.armor_pen_bonus = 0;
		}
	}

	private void setLuck() {
		try {
			this.luck_bonus = Math.abs((int) (random.nextInt()%(5 * this.level)));
		} catch (Exception e) {
			this.luck_bonus = 0;
		}
	}

	private void setDex() {
		try {
			this.dex_bonus = Math.abs((int) (random.nextInt()%(5 * this.level)));
		} catch (Exception e) {
			this.dex_bonus = 0;
		}
	}

	private void setMag() {
		try {
			this.mag_bonus = Math.abs((int) (random.nextInt()%(5 * this.level)));
		} catch (Exception e) {
			this.mag_bonus = 0;
		}
	}

	private void setStr() {
		try {
			this.str_bonus = Math.abs((int) (random.nextInt()%(5 * this.level)));
		} catch (Exception e) {
			this.str_bonus = 0;
		}
	}

	private void setEnergyRegen() {
		try {
			this.mana_regen_bonus = Math.abs((int) (random.nextInt()%(this.level)));
		} catch (Exception e) {
			this.mana_regen_bonus = 0;
		}
	}

	private void setHealthRegen() {
		try {
			this.hp_regen_bonus = Math.abs((int) (random.nextInt()%(this.level)));
		} catch (Exception e) {
			this.hp_regen_bonus = 0;
		}
	}

	private void setEnergy() {
		try {
			this.mana_bonus = Math.abs((int) (random.nextInt()%(10 * this.level)));
		} catch (Exception e) {
			this.mana_bonus = 0;
		}
	}

	private void setHealth() {
		try {
			this.hp_bonus = Math.abs((int) (random.nextInt()%(10 * this.level)));
		} catch (Exception e) {
			this.hp_bonus = 0;
		}
	}

	private void setLevel() {
		try {
			this.level = Math.abs(random.nextInt()%20);
		} catch (Exception e) {
			this.level = 0;
		}
	}

	/**
	 * @return Item matching Integer id
	 */
	public static Item getItem(Integer id) throws SQLException {
		// System.out.print("Fetching item id "+id+"... ");
		Item item = new Item();
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `item` WHERE (id='"
				+ id + "');");
		if (rs.next()) {
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setType(rs.getString("type"));
			item.setEquip_slots(rs.getString("equip_slots"));
			item.setHp_bonus(rs.getInt("hp_bonus"));
			item.setHp_regen_bonus(rs.getInt("hp_regen_bonus"));
			item.setMana_bonus(rs.getInt("mana_bonus"));
			item.setMana_regen_bonus(rs.getInt("mana_regen_bonus"));
			item.setStr_bonus(rs.getInt("str_bonus"));
			item.setMag_bonus(rs.getInt("mag_bonus"));
			item.setDex_bonus(rs.getInt("dex_bonus"));
			item.setLuck_bonus(rs.getInt("luck_bonus"));
			item.setArmor_pen_bonus(rs.getInt("armor_pen_bonus"));
			item.setMagic_pen_bonus(rs.getInt("magic_pen_bonus"));
			item.setArmor_bonus(rs.getInt("armor_bonus"));
			item.setMagic_res_bonus(rs.getInt("magic_res_bonus"));
			item.setIcon(rs.getString("icon"));
		}
		// System.out.println("found "+item.getName()+"!");
		return item;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return Random name appropriate for a given item type
	 */
	public void setName() {
		String[] list = { "of Blood", "of Chaos", "of Darkness",
				"of Death", "of Decapitation", "of Despair", "of Destruction",
				"of Disembowlment", "of Fate", "of Hope", "of Knowledge",
				"of Life", "of Mastery", "of Murder", "of Nevermore",
				"of Obliteration", "of Power", "of Rebirth", "of Restoration",
				"of Salvation", "of Shadow", "of Silence", "of Sorrows",
				"of Terror", "of Thunder", "of Vigor", "of the Abyss",
				"of the Ancients", "of the Apocalypse", "of the Gods",
				"of the Hero", "of the Immortal", "of the Master",
				"of the Underworld", "of the Wilderness" };
		Integer num = Math.abs((random.nextInt())%(list.length));
		System.out.println("Icon in set is "+num+" of "+(list.length-1)+".");
		this.name = this.getType()+" "+list[num];
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return itemType;
	}

	public void setType(String itemType) {
		this.itemType = itemType;
	}
	
	/**
	 * @return Random item type
	 */
	public void setType() {
		String[] list = { "Food", "Potion", "Sword", "Dagger", "Claw",
				"Gun", "Arrow", "Dart", "Shuriken", "Spear", "Club", "Mace",
				"Wand", "Staff", "Axe", "Bow", "Shield", "Tunic", "Mail",
				"Cap", "Helm", "Sandals", "Boots", "Gloves", "Gem", "Ore",
				"Key" };
		Integer num = Math.abs((random.nextInt())%(list.length));
		System.out.println("Type is "+num+" of "+(list.length-1)+".");
		this.itemType = list[num];
	}


	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return Random icon appropriate for a given item type
	 */
	public void setIcon() {
		Integer typeId = getTypeId(this.itemType);
		String[][] icons = {
				{ "pberry", "cherry", "gapple", "rapple", "orange", "ggrapes",
						"pgrapes", "watermelon", "strawberry", "papaya",
						"lemon", "pineapple", "banana", "acorn", "radish",
						"carrot", "gpepper", "ypepper", "rpepper", "mushroom",
						"egg", "bread", "pie", "cheese", "rfish", "rsteak",
						"cfish", "csteak" },
				{ "brpot", "bopot", "bypot", "bbpot", "bppot", "bgpot",
						"bwpot", "srpot", "sopot", "sypot", "sbpot", "sppot",
						"sgpot", "swpot", "trpot", "topot", "typot", "tbpot",
						"tppot", "tgpot", "twpot", "mrpot", "mopot", "mypot",
						"mbpot", "mppot", "mgpot", "mwpot", "tepot", "sepot",
						"bepot", "mepot", "mbubpot", "gpicklepot",
						"ppicklepot", "sgbilepot", "bgbilepot", "bbbilepot",
						"mpbilepot", "sybilepot", "trocketpot", "trainbowpot",
						"greenjuice" },
				{ "ssword", "msword", "lsword", "r1sword", "r2sword",
						"sfatsword", "bfatsword", "joustsword", "arrowsword",
						"goldsword", "longsword", "cloudsword", "bentsword",
						"billysword", "godsword", "sandsword" },
				{ "mknife", "sknife", "aknife", "sawblade", "r1assassinblade",
						"r2assassinblade", "r3assassinblade" },
				{ "r1claw", "r2claw", "r3claw", "r4claw", "r5claw", "r6claw",
						"r7claw", "r1glove", "r2glove", "r3glove", "r4glove",
						"r5glove" },
				{ "r1gun", "r2gun", "r3gun" },
				{ "arrow" },
				{ "kunai" },
				{ "r1shuriken", "r2shuriken", "r3shuriken" },
				{ "r1pole", "r2pole", "r3pole", "r4pole", "r5pole", "r6pole",
						"r7pole", "r8pole", "r9pole", "r10pole", "r11pole",
						"r12pole", "r13pole" },
				{ "r1club", "r2club" },
				{ "r3club", "r4club", "r5club", "r1mace", "r1slapperchain" },
				{ "r1wand", "r2wand", "r3wand", "r4wand" },
				{ "r5wand", "r6wand", "r7wand" },
				{ "gaxe", "r1axe", "r2axe", "r3axe", "r4axe", "r5axe", "r6axe",
						"r7axe", "r8axe", "r9axe", "r10axe", "r11axe",
						"r12axe", "r13axe", "r14axe" },
				{ "r1bow", "r2bow", "r3bow", "r4bow", "r5bow", "r6bow",
						"r7bow", "r8bow", "r9bow", "r10bow", "r11bow",
						"r12bow", "r13bow", "r14bow" },
				{ "r1shield", "r2shield", "r3shield", "r4shield", "r5shield",
						"r6shield", "r7shield", "r8shield", "r9shield",
						"r10shield", "r11shield", "r12shield", "r13shield",
						"r14shield" },
				{ "r1chest", "r2chest", "r3chest" },
				{ "r4chest", "r5chest", "r6chest", "r7chest" },
				{ "r1hat", "r2hat", "r3hat" },
				{ "r4hat", "r5hat", "r6hat", "r7hat" },
				{ "r3shoe" },
				{ "r1shoe", "r2shoe", "r4shoe", "r5shoe", "r6shoe", "r7shoe" },
				{ "r1glove", "r2glove", "r3glove", "r4glove", "r5glove",
						"r6glove", "r7glove" },
				{ "r1gem", "r2gem", "r3gem", "r4gem", "r5gem", "r6gem", "r7gem" },
				{ "r1ore", "r2ore", "r3ore", "r4ore", "r5ore", "r6ore", "r7ore" },
				{ "r1key", "r2key", "r3key", "r4key", "r5key" } };
		Integer num = Math.abs((random.nextInt())%(icons.length));
		System.out.print("Icon set is "+typeId+" of "+icons.length+".");
		String[] s = icons[typeId];
		num = Math.abs((random.nextInt())%(s.length));
		System.out.println("Icon in set is "+num+" of "+(icons[typeId].length-1)+".");
		this.icon = s[num];
	}
	
	/**
	 * @return Item type ID corresponding to the type string
	 */
	private static Integer getTypeId(String itemType) {
		HashMap<String, Integer> itemMap = new HashMap<String, Integer>();
		itemMap.put("Food", 0);
		itemMap.put("Potion", 1);
		itemMap.put("Sword", 2);
		itemMap.put("Dagger", 3);
		itemMap.put("Claw", 4);
		itemMap.put("Gun", 5);
		itemMap.put("Arrow", 6);
		itemMap.put("Dart", 7);
		itemMap.put("Shuriken", 8);
		itemMap.put("Spear", 9);
		itemMap.put("Club", 10);
		itemMap.put("Mace", 11);
		itemMap.put("Wand", 12);
		itemMap.put("Staff", 13);
		itemMap.put("Axe", 14);
		itemMap.put("Bow", 15);
		itemMap.put("Shield", 16);
		itemMap.put("Tunic", 17);
		itemMap.put("Mail", 18);
		itemMap.put("Cap", 19);
		itemMap.put("Helm", 20);
		itemMap.put("Sandals", 21);
		itemMap.put("Boots", 22);
		itemMap.put("Gloves", 23);
		itemMap.put("Gem", 24);
		itemMap.put("Ore", 25);
		itemMap.put("Key", 26);
		itemMap.put("Ring", 27);
		itemMap.put("Necklace", 28);
		itemMap.put("Book", 29);
		System.out.println(itemType+" is item type "+itemMap.get(itemType));
		return itemMap.get(itemType);
	}
	
	public String getEquip_slots() {
		return equip_slots;
	}

	public void setEquip_slots(String equipSlots) {
		equip_slots = equipSlots;
	}

	public Integer getHp_bonus() {
		return hp_bonus;
	}

	public void setHp_bonus(Integer hpBonus) {
		hp_bonus = hpBonus;
	}

	public Integer getHp_regen_bonus() {
		return hp_regen_bonus;
	}

	public void setHp_regen_bonus(Integer hpRegenBonus) {
		hp_regen_bonus = hpRegenBonus;
	}

	public Integer getMana_bonus() {
		return mana_bonus;
	}

	public void setMana_bonus(Integer manaBonus) {
		mana_bonus = manaBonus;
	}

	public Integer getMana_regen_bonus() {
		return mana_regen_bonus;
	}

	public void setMana_regen_bonus(Integer manaRegenBonus) {
		mana_regen_bonus = manaRegenBonus;
	}

	public Integer getStr_bonus() {
		return str_bonus;
	}

	public void setStr_bonus(Integer strBonus) {
		str_bonus = strBonus;
	}

	public Integer getMag_bonus() {
		return mag_bonus;
	}

	public void setMag_bonus(Integer magBonus) {
		mag_bonus = magBonus;
	}

	public Integer getDex_bonus() {
		return dex_bonus;
	}

	public void setDex_bonus(Integer dexBonus) {
		dex_bonus = dexBonus;
	}

	public Integer getLuck_bonus() {
		return luck_bonus;
	}

	public void setLuck_bonus(Integer luckBonus) {
		luck_bonus = luckBonus;
	}

	public Integer getArmor_pen_bonus() {
		return armor_pen_bonus;
	}

	public void setArmor_pen_bonus(Integer armorPenBonus) {
		armor_pen_bonus = armorPenBonus;
	}

	public Integer getMagic_pen_bonus() {
		return magic_pen_bonus;
	}

	public void setMagic_pen_bonus(Integer magicPenBonus) {
		magic_pen_bonus = magicPenBonus;
	}

	public Integer getArmor_bonus() {
		return armor_bonus;
	}

	public void setArmor_bonus(Integer armorBonus) {
		armor_bonus = armorBonus;
	}

	public Integer getMagic_res_bonus() {
		return magic_res_bonus;
	}

	public void setMagic_res_bonus(Integer magicResBonus) {
		magic_res_bonus = magicResBonus;
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

}
