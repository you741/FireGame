//package main;
/**
 * @author You
 * This is a data-storing class for characters.
 */
public class Character {
	int attack;
	int defense;
	int speed;
	int fspeed;
	int maxHealth;
	int maxEnergy;
	int hp;
	int ep;
	int chargerate;
	
	String name;
	public Character(){
		name = "default";
	}
	public Character(String newName){
		name = newName;
	}
	public int getAttack(){
		return attack;
	}
	public int getDefense(){
		return defense;
	}
	public int getSpeed(){
		return speed;
	}
	public int getFSpeed(){
		return fspeed;
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public int getMaxEnergy(){
		return maxEnergy;
	}
	public int getHP(){
		return hp;
	}
	public int getEP(){
		return ep;
	}
	public int getCR(){
		return chargerate;
	}
	public void setAttack(int atk){
		attack = atk;
	}
	public void setDefense(int def){
		defense = def;
	}
	public void setSpeed(int spd){
		speed = spd;
	}
	public void setFSpeed(int fspd){
		fspeed = fspd;
	}
	public void setMaxHealth(int mhp){
		maxHealth = mhp;
	}
	public void setMaxEnergy(int mep){
		maxEnergy = mep;
	}
	public void setHP(int healthpoints){
		hp = healthpoints;
	}
	public void setEP(int energypoints){
		ep = energypoints;
	}
	public void setCR(int cr){
		chargerate = cr;
	}
}
