package main;

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
	

	int x = 0;
	int y = 0;
	int fx = x;
	int fy = y;
	
	boolean shouldFire = false;
	
	Color lineCol = new Color(0,0,0);
	Color fireCol = new Color(255,0,0);
	public FightPane(){
		setLayout(new GridBagLayout());
		addMouseListener(this);
	}
	public void setColor(Color newC){
		lineCol = newC;
	}
	public void setFColor(Color newC){
		fireCol = newC;
	}
	public void moveLeft(int distance){
		x -= distance;
		repaint();
	}
	public void moveRight(int distance){
		x += distance;
		repaint();
	}
	public void moveDown(int distance){
		y += distance;
		repaint();
	}
	public void moveUp(int distance){
		y -= distance;
		repaint();
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
			fx = x;
			fy = y;
			shouldFire = true;
		}
		else if(func == 1){
			fx++;
		}
		else{
			shouldFire = false;
		}
		repaint();
	}
	@Override
	public void paint(Graphics g){
		//This is the painting thing
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLUE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.setColor(lineCol);
		g2.drawLine(x,y,x+100,y+100);
		if(shouldFire){
			g2.setColor(fireCol);
			g2.fillOval(fx, fy, 30, 10);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();
		x = mx;
		y = my;
		repaint();
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
