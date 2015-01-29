/* DATE: UMM I DON'T REMEMBER
 * AUTHOR: YOU ZHOU
 * SOLO PROGRESS FOR AMUSEMENT
 */
//package main;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

public class MainFrame extends JFrame implements Runnable, KeyListener, ActionListener{
	/**JFrame is from java docs and libraries
	 * It's a swing component
	 */
	private static final long serialVersionUID = 1L;
	JButton btn = new JButton("you dumb");
	FightPane mpane;
	private final Set<Integer> pressed = new HashSet<Integer>();
	boolean isQuit = false;//Checks if user quits
	boolean shouldRun = true;//checks if it isn't paused
	boolean fired = false;//checks if a shot has been fired
	boolean punched = false;//checks if a punch has been thrown
	boolean transformed = false;//checks if they are a super-saiyan
	boolean jump = false;//checks if they jumped
	//the following booleans are for the second character
	boolean fired2 = false;
	boolean punched2 = false;
	boolean transformed2 = false;
	boolean jump2 = false;
	//NOTE: Timers are to make it real-time
	int count = -1;//super-saiyan timer
	int jTimer = -1;//jump timer
	int pTimer = -1;//punch timer
	int sTimer = -1;//shot timer
	int fcd = 100;//Fire cooldown
	int count2 = -1;//super-saiyan for character 2
	int jTimer2 = -1;//jump timer for character 2
	int pTimer2 = -1;//punch timer for character 2
	int sTimer2 = -1;//shot timer for character 2
	int fcd2 = 75;//fire cooldown for character 2
	int globalTimer = 0;//Total time since game started	
	public MainFrame(FightPane pane){
		mpane = pane;
		setTitle("DBZ 1 v 1");
		setFocusable(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addKeyListener(this);
		
		add(mpane);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int keyCode = arg0.getKeyCode();
		if(!mpane.paused || keyCode == KeyEvent.VK_P || keyCode== KeyEvent.VK_ESCAPE){

			pressed.add(keyCode);
			switch(keyCode){
				//the following are for pause and quit
				case KeyEvent.VK_P:
					shouldRun = shouldRun?false:true;
					if(shouldRun)
						mpane.unpause();
					else{
						mpane.pause();
					}
					break;
				case KeyEvent.VK_ESCAPE:
					isQuit = true;
					dispose();
					System.exit(1);
					break;
				default:
					break;
				
				}
				mpane.repaint();
		}	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int keyCode = arg0.getKeyCode();
		pressed.remove(keyCode);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while(!isQuit){
			if(shouldRun){
				//the following are for character 1
				if(count == Integer.MIN_VALUE){
					count = -1;
				}
				if(jTimer == Integer.MIN_VALUE){
					jTimer = -1;
				}
				if(pTimer == Integer.MIN_VALUE){
					pTimer = -1;
				}
				if(sTimer == Integer.MIN_VALUE){
					sTimer = -1;
				}
				count--;
				jTimer--;
				pTimer--;
				sTimer--;
				if(pressed.contains(KeyEvent.VK_UP)){
					mpane.moveUp();
				}
				if(pressed.contains(KeyEvent.VK_DOWN)){
					mpane.moveDown();
				}
				if(pressed.contains(KeyEvent.VK_LEFT)){
					mpane.moveLeft();
				}
				if(pressed.contains(KeyEvent.VK_RIGHT)){
					mpane.moveRight();
				}
				if(pressed.contains(KeyEvent.VK_ENTER)){
					if(sTimer < 0){
						mpane.fire(0);
						mpane.setMsg("Fired");
						fired = false;
						mpane.punch(0);
						pTimer = 15;
						sTimer = fcd;
					}
				}
				if(pressed.contains(KeyEvent.VK_NUMPAD0)){
					if(jTimer < 0){
						jTimer = 10;
					}
				}
				if(pressed.contains(KeyEvent.VK_NUMPAD1)){
					if(pTimer < -5){
						mpane.punch(0);
						pTimer = 15;
						mpane.setMsg("Punched");
					}
				}
				if(pressed.contains(KeyEvent.VK_NUMPAD2)){
					if(!transformed){
						int luck = (int)(100*Math.random());
						if(luck > 40 && luck < 55){
							mpane.setSpeed(20);
							mpane.setColor(Color.yellow);
							mpane.setFSpeed(10);
							mpane.setFColor(Color.YELLOW);
							count = 1000;
							fcd2 = 50;
							mpane.setEColor(Color.green);
							transformed = true;
							mpane.setMsg("SUPER SAIYAN!!");
						} else{
							mpane.setMsg("Super Saiyan failed...");
						}
					}
				}
				if(sTimer >= 0){
					mpane.fire(1);
				}
				if(sTimer == 0){
					fired = false;
					mpane.fire(2);		
				}
				if(jTimer > 5){
					mpane.moveUp();
				}
				if(0 < jTimer && jTimer <= 5){
					mpane.moveDown();
				}
				if(pTimer == 0){
					mpane.punch(1);
				}
				if(count<=0){
					mpane.setSpeed(3);
					mpane.setColor(Color.black);
					mpane.setFSpeed(mpane.defaultfspeed);
					mpane.setFColor(Color.red);
					if(transformed)
						mpane.setMsg("Normal Again...");
					transformed = false;
					fcd = 100;
					mpane.setEColor(Color.white);
				}
				if(count == Integer.MIN_VALUE){
					count = -1;
				}
				if(jTimer == Integer.MIN_VALUE){
					jTimer = -1;
				}
				if(pTimer == Integer.MIN_VALUE){
					pTimer = -1;
				}
				if(sTimer == Integer.MIN_VALUE){
					sTimer = -1;
				}
				//the following are for the 2nd character
				count2--;
				jTimer2--;
				pTimer2--;
				sTimer2--;
				if(pressed.contains(KeyEvent.VK_W)){
					mpane.moveUp2();
				}
				if(pressed.contains(KeyEvent.VK_S)){
					mpane.moveDown2();
				}
				if(pressed.contains(KeyEvent.VK_A)){
					mpane.moveLeft2();
				}
				if(pressed.contains(KeyEvent.VK_D)){
					mpane.moveRight2();
				}
				if(pressed.contains(KeyEvent.VK_F)){
					if(sTimer2 < 0){
						mpane.fire2(0);
						mpane.setMsg2("Fired");
						fired2 = false;
						mpane.punch2(0);
						pTimer2 = 15;
						sTimer2 = fcd;
					}
				}
				if(pressed.contains(KeyEvent.VK_SPACE)){
					if(jTimer2 < -10){
						jTimer2 = 10;
						jump2 = false;
						mpane.setMsg2("Jumped");
					}
				}
				if(pressed.contains(KeyEvent.VK_X)){
					if(!transformed2){
						int luck = (int)(100*Math.random());
						if(luck > 45 && luck < 55){
							mpane.setSpeed2(20);
							mpane.setColor2(Color.yellow);
							mpane.setFSpeed2(10);
							mpane.setFColor2(Color.YELLOW);
							count2 = 1300;
							fcd2 = 60;
							mpane.setEColor2(Color.green);
							transformed2 = true;
							mpane.setMsg2("SUPER SAIYAN!!");
						} else{
							mpane.setMsg2("Super Saiyan failed...");
						}
					}
				}
				if(pressed.contains(KeyEvent.VK_E)){
					mpane.setX2(200);
					mpane.setY2(70);
				}
				if(pressed.contains(KeyEvent.VK_Z)){
					if(pTimer2 < -5){
						punched2 = false;
						mpane.punch2(0);
						pTimer2 = 15;
						mpane.setMsg2("Punched");
					}
				}
				if(sTimer2 >= 0){
					mpane.fire2(1);
				}
				if(sTimer2 == 0){
					fired2 = false;
					mpane.fire2(2);
					
				}
				//Jumps
				if(jTimer2 > 5){
					mpane.moveUp2();
				}
				if(0 < jTimer2 && jTimer2 <= 5){
					mpane.moveDown2();
				}
				if(pTimer2 == 0){
					mpane.punch2(1);
				}
				if(count2<=0){
					mpane.setSpeed2(3);
					mpane.setColor2(new Color(200,255,255));
					mpane.setFSpeed2(mpane.defaultfspeed2);
					mpane.setFColor2(Color.red);
					if(transformed2)
						mpane.setMsg2("Normal Again...");
					transformed2 = false;
					fcd2 = 100;
					mpane.setEColor2(Color.red);
				}
			}
			try {
				mpane.repaint();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		dispose();
		System.exit(1);
	}
	public static void main(String args[]){
		FightPane mPane = new FightPane();
		MainFrame mf = new MainFrame(mPane);
		mf.run();
	}
}
