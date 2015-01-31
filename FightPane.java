//package main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.geom.*;

public class FightPane extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//The following variables are for character's stats
	int defaultAttack = 11;//character's default attack
	int defaultDefense = 4;//character's default defense
	int defaultAttack2 = 8;//character 2's default attack
	int defaultDefense2 = 3;//character 2's default defense
	int attack = 10;//character's attack
	int attack2 = 8;//character 2's attack
	int defense = 4;//character's defense
	int defense2 = 4;//character 2's defense
	int speed = 3;//characters speed
	int defaultspeed = 3;//characters default speed
	int defaultfspeed = 5;//characters default fire speed
	int fspeed = 5;//characters fire speed
	int speed2 = 3;//character 2's speed
	int defaultfspeed2 = 5;//character 2's default fire speed
	int defaultspeed2 = 5;//characters default speed
	int fspeed2 = 3;//character 2's fire speed
	int ssboost = 5;//super saiyan boost
	int ssboost2 = 4;//super saiyan boost for character 2
	int jumpSpeed = 10;//jumpspeed
	int health = 1000;//character's health
	int health2 = 1500;//character 2's health
	int maxhealth = health;//character's maximum health
	int maxhealth2 = health2;//character 2's maximum health
	int energy = 300;//character's energy
	int energy2 = 400;//character 2's energy
	int maxenergy = energy;//character's maximum energy
	int maxenergy2 = energy2;//character 2's maximum energy
	int fcd = 120;//character 1's fire cooldown
	int fcd2 = 100;//character 2's fire cooldown
	int eps = 5;//character's energy regeneration per second
	int eps2 = 2;//character 2's energy regeneration per second
	int hps = 3;//character's health regeneration per second
	int hps2 = 4;//character 2's health regeneration per second
	String name = "Bob";
	String name2 = "Joe";
	//the following are to help with positioning and animations
	Shape[] fireArray;//WIP

	int x = 350;//X co-ord of character
	int y = 70;//Y co-ord of character
	int fx = x;//fire ball coord of character
	int fy = y;//fire ball coord of character
	int dir = -1;//direction character is facing
	int dirf = -1;//direction fire is shooting

	int x2 = 50;//character 2's X coordinate
	int y2 = 70;//Character 2's Y coordinate
	int fx2 = x2;//character 2's fire ball x coordinate
	int fy2 = y2;//character 2's fire ball y coordinate
	int dir2 = 1;//direction character 2 is facing
	int dirf2 = 1;//direction character 2 is shooting

	boolean shouldFire = false;//if character should fire
	boolean shouldPunch = false;//if character should punch
	boolean paused = false;//if the game is paused
	String inf = name+" The Saiyan HP: "+health+" EP: "+energy;//label appears above character
	String msg = "";//label appears below character
	boolean shouldFire2 = false;//if character 2 should fire
	boolean shouldPunch2 = false;//if character 2 should punch
	boolean shouldGalickGun2 = false;//if character 2 should use galick gun
	int gIncrease = 0;//Affects size of ball by galick gun
	String inf2 = name2+" The Saiyan HP: "+health2+" EP: "+energy2;//appears above character 2
	String msg2 = "";//appears below character 2
	boolean dead = false;//if character 1 is dead
	boolean dead2 = false;//if character 2 is dead
	boolean ss = false;//checks if player is super saiyan
	boolean ss2 = false;//checks if player 2 is super saiyan
	boolean end = false;//checks if game ended
	
	Color lineCol = new Color(0,0,0);//Color of character
	Color fireCol = new Color(255,0,0);//Color of fire shot
	Color eyeCol = new Color(255,255,255);//Color of eye
	Color lineCol2 = new Color(200,255,255);//Color of character 2
	Color fireCol2 = new Color(255,0,255);//Color of fire shot for character 2
	Color eyeCol2 = new Color(255,0,0);//Color of eye for character 2
	
	public FightPane(){
		setOpaque(true);
		setPreferredSize(new Dimension(610,610));
		setBackground(Color.blue);
		setLayout(new GridBagLayout());
		addMouseListener(this);
	}
	public void setAttack(int atk){
		attack = atk;
	}	
	public void setAttack2(int atk){
		attack2 = atk;
	}
	public void setDefense(int def){
		defense = def;
	}	
	public void setDefense2(int def){
		defense2 = def;
	}
	public void setHealth(int hp){
		health = hp;
		if(health <= 0){
			dead = true;
			setMsg2("YOU WIN");
			paused = true;
			end = true;
		}
	}	
	public void setEnergy(int ep){
		energy = ep;
	}
	public void setHealth2(int hp){
		health2 = hp;
		if(health2 <= 0){
			dead2 = true;
			setMsg("YOU WIN");
			paused = true;
			end = true;
		}
	}	
	public void setEnergy2(int ep){
		energy2 = ep;
	}
	public void setMsg(String newMsg){
		msg = newMsg;
	}
	public void setInf(String newInf){
		inf = newInf;
	}
	public void setSpeed(int newS){
		speed = newS;
	}
	public void setFSpeed(int newS){
		fspeed = newS;
	}
	public void setColor(Color newC){
		lineCol = newC;
	}
	public void setFColor(Color newC){
		fireCol = newC;
	}
	public void setEColor(Color newC){
		eyeCol = newC;
	}
	public void moveLeft(){
		x -= speed;
		dir = -1;
		if(x > x2 && x < x2 + 50 && ((y - 40 > y2 - 40 && y - 40 < y2 + 140) || (y + 140 > y2 - 40 && y + 140 < y2 + 140))){
			x += speed;
		}
	}
	public void moveRight(){
		x += speed;
		dir = 1;
		if(x+50 > x2 && x+50 < x2 + 50 && ((y - 40 > y2 - 40 && y - 40 < y2 + 140) || (y + 140 > y2 - 40 && y + 140 < y2 + 140))){
			x -= speed;
		}
	}
	public void moveDown(){
		y += speed;
		if(y + 140 > y2 - 40 && y + 140 < y2 + 140 && ((x < x2 + 50 && x > x2)||(x + 50 < x2 + 50 && x + 50 > x2))){
			y -= speed;
		}
	}
	public void moveUp(){
		y -= speed;
		if(y - 40 > y2 - 40 && y - 40 < y2 + 140 && ((x < x2 + 50 && x > x2)||(x + 50 < x2 + 50 && x + 50 > x2))){
			y += speed;
		}
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int newX){
		x = newX;
	}
	public void setY(int newY){
		y = newY;
	}
	public void fire(int func){
		if(func == 0){
			fx = x+(dir*50);
			fy = y+50;
			shouldFire = true;
			dirf = dir;
		}
		else if(func == 1){
			fx += dirf*fspeed;
			int side = dirf == 1?30:0;
			if(fx + side > x2 && fx + side < x2 + 50 && ((fy + 10 < y2 + 140 && fy + 10 > y2 - 40)||(fy < y2 + 140 && y > y2 - 40))){
				int offset = attack < defense2?-1:-attack + defense2;
				setHealth2(health2 + offset);
				setInf2(name2+" The Saiyan HP: "+health2+" EP: "+energy2);
				fire(-1);
			}
		}
		else{
			shouldFire = false;
		}
	}
	public void punch(int func){
		if(func == 0){
			energy -= 2;
			shouldPunch = true;
			int pdx = dir == 1?50:-50;
			boolean hit = dir == 1?x + pdx + 50 > x2 && x + pdx + 50 < x2 + 50:x + pdx< x2 + 50 && x + pdx > x2;//checks if punch hits anyone
			if(energy < 0){
				shouldPunch = false;
				hit = false;
				energy += 2;
			}
			if(hit && ((y+40 > y2-40 && y+40 < y2+140)||(y+80 > y2 && y+80 < y2+140))){
				if(dir == 1){
					moveRight2();
					dir2 = dir2 == 1?-1:1;
				}else{
					moveLeft2();
					dir2 = dir2 == 1?-1:1;
				}
				int offset = attack < defense2?-1:-attack + defense2;
				setHealth2(health2 + offset);
			}
		}
		else{
			shouldPunch = false;
		}
	}
	public void transform(){
		if(ss){
			ss = false;
			attack = defaultAttack;
			defense = defaultDefense;
			setSpeed(defaultspeed);
			setColor(Color.black);
			setFSpeed(defaultfspeed);
			setFColor(Color.red);
			fcd = 120;
			setEColor(Color.white);
			setMsg("Normal again...");
		}else{
			ss = true;
			attack *= ssboost;
			defense *= ssboost;
			setSpeed(speed + ssboost);
			setColor(Color.YELLOW);
			setFSpeed(defaultfspeed + ssboost);
			setFColor(Color.YELLOW);
			fcd = 60;
			setEColor(Color.green);
			setMsg("SUPER SAIYAN!!!");
		}
	}
	public void pause(){
		paused = true;
	}
	public void unpause(){
		paused = false;
	}
	//methods for the other character
	public void setMsg2(String newMsg){
		msg2 = newMsg;
	}
	public void setInf2(String newInf){
		inf2 = newInf;
	}
	public void setSpeed2(int newS){
		speed2 = newS;
	}
	public void setFSpeed2(int newS){
		fspeed2 = newS;
	}
	public void setColor2(Color newC){
		lineCol2 = newC;
	}
	public void setFColor2(Color newC){
		fireCol2 = newC;
	}
	public void setEColor2(Color newC){
		eyeCol2 = newC;
	}
	public void moveLeft2(){
		x2 -= speed2;
		fire(-1);
		dir2 = -1;
		if(x2 > x && x2 < x + 50 && ((y2 - 40 > y - 40 && y2 - 40 < y + 140) || (y2 + 140 > y - 40 && y2 + 140 < y + 140))){
			x2 += speed2;
		}
	}
	public void moveRight2(){
		x2 += speed2;
		fire(-1);
		dir2 = 1;
		if(x2 + 50 > x && x2 + 50 < x + 50 && ((y2 - 40 > y - 40 && y2 - 40 < y + 140) || (y2 + 140 > y - 40 && y2 + 140 < y + 140))){
			x2 -= speed2;
		}
	}
	public void moveDown2(){
		y2 += speed2;
		if(y2 + 140 > y - 40 && y2 + 140 < y + 140 && ((x2 < x + 50 && x2 > x)||(x2 + 50 < x + 50 && x2 + 50 > x))){
			y2 -= speed2;
		}
	}
	public void moveUp2(){
		y2 -= speed2;
		if(y2 - 40 > y - 40 && y2 - 40 < y + 140 && ((x2 < x + 50 && x2 > x)||(x2 + 50 < x + 50 && x2 + 50 > x))){
			y2 += speed2;
		}
	}
	public int getX2(){
		return x2;
	}
	public int getY2(){
		return y2;
	}
	public void setX2(int newX){
		x2 = newX;
	}
	public void setY2(int newY){
		y2 = newY;
	}
	public void fire2(int func){
		if(func == 0){
			fx2 = x2+(dir2*50);
			fy2 = y2+50;
			shouldFire2 = true;
			dirf2 = dir2;
			energy2 -= 1;
			if(energy2 < 0){
				energy2 += 1;
				shouldFire2 = false;
			}
		}
		else if(func == 1){
			fx2 += dirf2*fspeed2;
			int side = dirf2 == 1?30:0;
			if(fx2 + side > x && fx2 + side < x + 50 && ((fy2 + 10 < y + 140 && fy2 + 10 > y - 40)||(fy2 < y + 140 && y2 > y - 40))){
				int offset = attack2 < defense?-1:-attack2 + defense;
				setHealth(health+offset);
				setInf(name+" The Saiyan HP: "+health+" EP: "+energy);
				fire2(-1);
			}
		}
		else{
			shouldFire2 = false;
		}
	}
	public void punch2(int func){
		if(func == 0){
			energy2 -= 2;
			shouldPunch2 = true;
			int pdx = dir2 == 1?50:-50;
			boolean hit = dir2 == 1?x2 + pdx + 50 > x && x2 + pdx + 50 < x + 50:x2 + pdx < x + 50 && x2 + pdx  > x;
			if(energy2 < 0){
				shouldPunch2 = false;
				hit = false;
				energy2 += 2;
			}
			if(hit && ((y2+40 > y-40 && y2+40 < y+140)||(y2+80 > y && y2+80 < y+140))){
				if(dir2 == 1){
					moveRight();
					dir = dir == 1?-1:1;
				}else{
					moveLeft();
					dir = dir == 1?-1:1;
				}
				int offset = attack2 < defense?-1:-attack2 + defense;
				setHealth(health + offset);
			}
		}
		else{
			shouldPunch2 = false;
		}
	}
	public void galickGun2(int func){//unused function
		if(func == 0){
			shouldGalickGun2 = true;
		}
	}
	public void transform2(){
		if(ss2){
			ss2 = false;
			attack2 = defaultAttack2;
			defense2 = defaultDefense2;
			setSpeed2(defaultspeed2);
			setColor2(new Color(200,255,255));
			setFSpeed2(defaultfspeed2);
			setFColor2(Color.magenta);
			fcd2 = 100;
			setEColor2(Color.red);
			setMsg2("Normal again...");
		}else{
			ss2 = true;
			attack2 *= ssboost2;
			defense2 *= ssboost2;
			setSpeed2(speed2 + ssboost2);
			setColor2(Color.YELLOW);
			setFSpeed2(defaultfspeed2 + ssboost2);
			setFColor2(Color.YELLOW);
			fcd2 = 50;
			setEColor2(Color.green);
			setMsg2("SUPER SAIYAN!!!");
		}
	}
	@Override
	public void paintComponent(Graphics g){
		
		//This is the painting thing
		super.paintComponent(g);

		//draws character 1
		if(!dead){
			g.setColor(lineCol);
			g.fillOval(x,y,50,100);
			g.fillOval(x, y-40, 50, 50);
			g.fillRect(x+13, y+90, 25, 50);
			g.drawString(inf, x, y-50);
			g.drawString(msg,x,y+160);
			g.setColor(eyeCol);
			int edx = dir == 1?30:10;
			g.fillOval(x+edx, y-20, 10, 10);
			g.setColor(lineCol);
			if(shouldFire){
				g.setColor(fireCol);
				g.fillOval(fx, fy, 30, 10);
			}
			if(shouldPunch){
				int pdx = dir == 1?50:-40;
				g.setColor(lineCol);
				g.fillOval(x+pdx, y+40, 40, 40);
			}
		}
		//draws character 2
		if(!dead2){
			g.setColor(lineCol2);
			g.fillOval(x2,y2,50,100);
			g.fillOval(x2, y2-40, 50, 50);
			g.fillRect(x2+13, y2+90, 25, 50);
			g.drawString(inf2, x2, y2-50);
			g.drawString(msg2,x2,y2+160);
			g.setColor(eyeCol2);
			int edx2 = dir2 == 1?30:10;
			g.fillOval(x2+edx2, y2-20, 10, 10);
			g.setColor(lineCol2);
			if(shouldFire2){
				g.setColor(fireCol2);
				g.fillOval(fx2, fy2, 30, 10);
			}
			if(shouldPunch2){
				int pdx = dir2 == 1?50:-40;
				g.setColor(lineCol2);
				g.fillOval(x2+pdx, y2+40, 40, 40);
			}
			if(shouldGalickGun2){
				int pdx = dir2 == 1?50:-40;
				g.setColor(lineCol2);
				g.fillOval(x2+pdx, y2+40, 40, 40);
			}

		}
		if(energy < 0)
			energy = 0;
		setInf2(name2+" The Saiyan HP: "+health2+" EP: "+energy2);
		setInf(name+" The Saiyan HP: "+health+" EP: "+energy);
		if(health > maxhealth)
			health = maxhealth;
		if(health2 > maxhealth2)
			health2 = maxhealth2;
		if(energy > maxenergy)
			energy = maxenergy;
		if(energy2 > maxenergy2)
			energy2 = maxenergy2;
		if(paused && !end){
			g.setColor(Color.BLACK);
			g.drawString("PAUSED", this.getWidth()/2-5, 10);
		}

		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!paused){
			// TODO Auto-generated method stub
			int mx = e.getX();
			int my = e.getY();
			x = mx-25;
			y = my-50;
			setMsg("Instant Transmission");
			repaint();
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
