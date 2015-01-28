//package main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;

public class FightPane extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Shape[] fireArray;
	int x = 50;
	int y = 70;
	int fx = x;
	int fy = y;
	int speed = 3;
	int defaultfspeed = 3;
	int fspeed = 3;
	boolean shouldFire = false;
	boolean shouldPunch = false;
	boolean paused = false;
	String inf = "Bob The Saiyan HP: 50";
	String msg = "";
	
	Color lineCol = new Color(0,0,0);//Color of character
	Color fireCol = new Color(255,0,0);//Color of fire shot
	public FightPane(){
		setLayout(new GridBagLayout());
		addMouseListener(this);
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
	public void moveLeft(){
		x -= speed;
	}
	public void moveRight(){
		x += speed;
	}
	public void moveDown(){
		y += speed;
	}
	public void moveUp(){
		y -= speed;
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
			fx = x+50;
			fy = y+50;
			shouldFire = true;
		}
		else if(func == 1){
			fx += fspeed;
		}
		else{
			shouldFire = false;
		}
	}
	public void pause(){
		paused = true;
	}
	public void unpause(){
		paused = false;
	}
	public void punch(int func){
		if(func == 0)
			shouldPunch = true;
		else{
			shouldPunch = false;
		}
	}
	@Override
	public void paint(Graphics g){
		//This is the painting thing
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLUE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.setColor(lineCol);
		g2.fillOval(x,y,50,100);
		g2.fillOval(x, y-40, 50, 50);
		g2.fillRect(x+13, y+90, 25, 50);
		g2.drawString(inf, x, y-50);
		g2.drawString(msg,x,y+160);

		if(shouldFire){
			g2.setColor(fireCol);
			g2.fillOval(fx, fy, 30, 10);
		}
		if(shouldPunch){
			g2.setColor(lineCol);
			g2.fillOval(x+50, y+40, 40, 40);
		}
		if(paused){
			g2.setColor(Color.BLACK);
			g2.drawString("PAUSED", this.getWidth()/2-5, 10);
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
