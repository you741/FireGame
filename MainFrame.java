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
	//the following boolean is for player 1
	boolean transformed = false;//checks if they are a super-saiyan
	//the following boolean is for the second character
	boolean transformed2 = false;
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
	
	JLabel timeLabel;
	public MainFrame(FightPane pane){
		mpane = pane;
		setTitle("DBZ 1 v 1");
		setFocusable(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		addKeyListener(this);
		
		add(mpane, BorderLayout.CENTER);
		
		timeLabel = new JLabel("Time: "+globalTimer);
		add(timeLabel, BorderLayout.PAGE_START);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int keyCode = arg0.getKeyCode();
		if(!mpane.paused || (keyCode == KeyEvent.VK_P && !mpane.end) || keyCode== KeyEvent.VK_ESCAPE){

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
			if(shouldRun && !mpane.paused){
				//the following are for character 1
				//the following 4 if statements prevents variables from going below minimum value of an integer
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
				if(globalTimer == Integer.MAX_VALUE-1)
					globalTimer = 0;
				globalTimer++;
				
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
						mpane.punch(0);
						pTimer = 15;
						sTimer = mpane.fcd2;
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
						mpane.energy -= 5;
						if(mpane.energy < 0){
							mpane.energy += 5;
						}
						else{
							int luck = (int)(100*Math.random());
							if(luck > 40 && luck < 55){
								mpane.transform();
								transformed = true;
								count = 1000;
							} else{
								mpane.setMsg("Super Saiyan failed...");
							}
						}
					}
					else if(count > -400 && count < 0 && transformed){
						mpane.setMsg("On Cooldown!");
					}
				}
				if(sTimer >= 0){
					mpane.fire(1);
				}
				if(sTimer == 0){
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
				if(count==0){
					mpane.transform();
				}
				if(count < -400){
					transformed = false;
				}
				//the following 4 if statements prevents variables from going below minimum value of an integer
				if(count == Integer.MIN_VALUE+1){
					count = -1;
				}
				if(jTimer == Integer.MIN_VALUE+1){
					jTimer = -1;
				}
				if(pTimer == Integer.MIN_VALUE+1){
					pTimer = -1;
				}
				if(sTimer == Integer.MIN_VALUE+1){
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
						mpane.punch2(0);
						pTimer2 = 15;
						sTimer2 = mpane.fcd;
					}
				}
				if(pressed.contains(KeyEvent.VK_SPACE)){
					if(jTimer2 < -10){
						jTimer2 = 10;
						mpane.setMsg2("Jumped");
					}
				}
				if(pressed.contains(KeyEvent.VK_X)){
					if(!transformed2){
						mpane.energy2 -= 5;
						if(mpane.energy2 < 0){
							mpane.energy2 += 5;
						}
						else{
							int luck = (int)(100*Math.random());
							if(luck > 45 && luck < 55){
								transformed2 = true;
								mpane.transform2();
								count2 = 1300;
							} else{
								mpane.setMsg2("Super Saiyan failed...");
							}
						}
					}
					else if(count2 < 0 && count2 > -300 && transformed2){
						mpane.setMsg2("On Cooldown!");
					}
				}
				if(pressed.contains(KeyEvent.VK_E)){
					mpane.setX2(200);
					mpane.setY2(70);
				}
				if(pressed.contains(KeyEvent.VK_Z)){
					if(pTimer2 < -5){
						mpane.punch2(0);
						pTimer2 = 15;
						mpane.setMsg2("Punched");
					}
				}
				if(sTimer2 >= 0){
					mpane.fire2(1);
				}
				if(sTimer2 == 0){
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
				if(count2==0){
					mpane.transform2();
				}
				if(count2 < -300){
					transformed2 = false;	
				}
				if(mpane.energy < mpane.maxenergy && globalTimer % 100 == 0)
					mpane.setEnergy(mpane.energy + mpane.eps);
				if(mpane.energy2 < mpane.maxenergy2 && globalTimer % 100 == 0)
					mpane.setEnergy2(mpane.energy2 + mpane.eps2);
				if(mpane.health < mpane.maxhealth && globalTimer % 100 == 0)
					mpane.setHealth(mpane.health + mpane.hps);
				if(mpane.health2 < mpane.maxhealth2 && globalTimer % 100 == 0)
					mpane.setHealth2(mpane.health2 + mpane.hps2);
				int gtSec = globalTimer/100;//global timer in seconds
				timeLabel.setText("Time: "+gtSec);
				if(mpane.end){
					String winner = mpane.dead?mpane.name2:mpane.name;
					timeLabel.setText(winner+" wins! Total time: "+gtSec);
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
