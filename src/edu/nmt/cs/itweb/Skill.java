package edu.nmt.cs.itweb;

public class Skill {

	private String name;
	private String element;
	private float percentage_physical_damage;
	private float percentage_magical_damage;
	private int base_value;
	private String skill_type;
	private String target_type;
	private int generic_modifier;
	private Skill secondary_skill;
	private int cost_energy;
	private int cost_hp;
	private int required_level;
	private String required_class;
	private Skill required_skill;
	private String image;

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
	  * @return the element
	  */
	 public String getElement() {
		 return element;
	 }

	 /**
	  * @param element the element to set
	  */
	 public void setElement(String element) {
		 this.element = element;
	 }

	 /**
	  * @return the percentage_physical_damage
	  */
	 public float getPercentage_physical_damage() {
		 return percentage_physical_damage;
	 }

	 /**
	  * @param percentage_physical_damage the percentage_physical_damage to set
	  */
	 public void setPercentage_physical_damage(float percentage_physical_damage) {
		 this.percentage_physical_damage = percentage_physical_damage;
	 }

	 /**
	  * @return the percentage_magical_damage
	  */
	 public float getPercentage_magical_damage() {
		 return percentage_magical_damage;
	 }

	 /**
	  * @param percentage_magical_damage the percentage_magical_damage to set
	  */
	 public void setPercentage_magical_damage(float percentage_magical_damage) {
		 this.percentage_magical_damage = percentage_magical_damage;
	 }

	 /**
	  * @return the base_value
	  */
	 public int getBase_value() {
		 return base_value;
	 }

	 /**
	  * @param base_value the base_value to set
	  */
	 public void setBase_value(int base_value) {
		 this.base_value = base_value;
	 }

	 /**
	  * @return the skill_type
	  */
	 public String getSkill_type() {
		 return skill_type;
	 }

	 /**
	  * @param skill_type the skill_type to set
	  */
	 public void setSkill_type(String skill_type) {
		 this.skill_type = skill_type;
	 }

	 /**
	  * @return the target_type
	  */
	 public String getTarget_type() {
		 return target_type;
	 }

	 /**
	  * @param target_type the target_type to set
	  */
	 public void setTarget_type(String target_type) {
		 this.target_type = target_type;
	 }

	 /**
	  * @return the generic_modifier
	  */
	 public int getGeneric_modifier() {
		 return generic_modifier;
	 }

	 /**
	  * @param generic_modifier the generic_modifier to set
	  */
	 public void setGeneric_modifier(int generic_modifier) {
		 this.generic_modifier = generic_modifier;
	 }

	 /**
	  * @return the secondary_skill
	  */
	 public Skill getSecondary_skill() {
		 return secondary_skill;
	 }

	 /**
	  * @param secondary_skill the secondary_skill to set
	  */
	 public void setSecondary_skill(Skill secondary_skill) {
		 this.secondary_skill = secondary_skill;
	 }

	 /**
	  * @return the cost_energy
	  */
	 public int getCost_energy() {
		 return cost_energy;
	 }

	 /**
	  * @param cost_energy the cost_energy to set
	  */
	 public void setCost_energy(int cost_energy) {
		 this.cost_energy = cost_energy;
	 }

	 /**
	  * @return the cost_hp
	  */
	 public int getCost_hp() {
		 return cost_hp;
	 }

	 /**
	  * @param cost_hp the cost_hp to set
	  */
	 public void setCost_hp(int cost_hp) {
		 this.cost_hp = cost_hp;
	 }

	 /**
	  * @return the required_level
	  */
	 public int getRequired_level() {
		 return required_level;
	 }

	 /**
	  * @param required_level the required_level to set
	  */
	 public void setRequired_level(int required_level) {
		 this.required_level = required_level;
	 }

	 /**
	  * @return the required_class
	  */
	 public String getRequired_class() {
		 return required_class;
	 }

	 /**
	  * @param required_class the required_class to set
	  */
	 public void setRequired_class(String required_class) {
		 this.required_class = required_class;
	 }

	 /**
	  * @return the required_skill
	  */
	 public Skill getRequired_skill() {
		 return required_skill;
	 }

	 /**
	  * @param required_skill the required_skill to set
	  */
	 public void setRequired_skill(Skill required_skill) {
		 this.required_skill = required_skill;
	 }

	 /**
	  * @return the image
	  */
	 public String getImage() {
		 return image;
	 }

	 /**
	  * @param image the image to set
	  */
	 public void setImage(String image) {
		 this.image = image;
	 }
}
