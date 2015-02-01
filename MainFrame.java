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
	int gTimer2 = -1;//galick gun timer for character 2
	int fcd2 = 75;//fire cooldown for character 2
	int globalTimer = 0;//Total time since game started	
	
	JLabel timeLabel = new JLabel("Time: 0");
	JLabel pauseLabel = new JLabel("PAUSED");
	//below is an array that contains all elements that have to do with character 1
	int[] p1 = {KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_RIGHT,KeyEvent.VK_LEFT,KeyEvent.VK_NUMPAD0,KeyEvent.VK_NUMPAD1,KeyEvent.VK_NUMPAD2,KeyEvent.VK_NUMPAD3,KeyEvent.VK_ENTER};//Any key that player 1 uses
	int[] p2 = {KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_A,KeyEvent.VK_D,KeyEvent.VK_SPACE,KeyEvent.VK_Z,KeyEvent.VK_X,KeyEvent.VK_C,KeyEvent.VK_F,KeyEvent.VK_E,KeyEvent.VK_G};//Any key that player 2 uses
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
		JPanel topPane = new JPanel();
		topPane.setBackground(Color.gray);
		topPane.setLayout(new BorderLayout());

		JButton qbutton = new JButton("QUIT");
		qbutton.setBackground(Color.red);
		qbutton.addActionListener(this);
		topPane.add(qbutton, BorderLayout.LINE_END);
		
		timeLabel.setText("Time: "+globalTimer);
		topPane.add(timeLabel, BorderLayout.LINE_START);

		pauseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pauseLabel.setVisible(false);
		topPane.add(pauseLabel, BorderLayout.CENTER);
		
		add(topPane, BorderLayout.PAGE_START);
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
					if(shouldRun){
						mpane.unpause();
						pauseLabel.setVisible(false);
					}
					else{
						mpane.pause();
						pauseLabel.setVisible(true);
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
				int p1Act = 0;//number of player 1 actions
				for(int i = 0;i < p1.length;i++){
					if(pressed.contains(p1[i]))
						p1Act++;
				}
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
					else{
						mpane.setMsg("On Cooldown!");
					}
				}
				if(pressed.contains(KeyEvent.VK_NUMPAD0)){
					if(jTimer < 0){
						mpane.setEnergy(mpane.energy - 1);
						if(mpane.energy < 0){
							mpane.setEnergy(mpane.energy + 1);
							mpane.setMsg("Not enough energy");
						}
						else{
							jTimer = 10;
						}
					}
				}
				if(pressed.contains(KeyEvent.VK_NUMPAD1)){
					if(pTimer < -5){
						mpane.punch(0);
						pTimer = 15;
						mpane.setMsg("Punched");
					}
				}
				if(pressed.contains(KeyEvent.VK_NUMPAD2)&&p1Act < 2){
					if(!transformed){
						mpane.energy -= 2;
						if(mpane.energy < 0){
							mpane.energy += 2;
							mpane.setMsg("Not enough energy!");
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
				if(pressed.contains(KeyEvent.VK_NUMPAD3)&&globalTimer%10 == 0&&p1Act < 2){
					mpane.charge();
				}
				if(mpane.energy <= 0){
					count = -1;
				}
				if(mpane.energy2 <= 0){
					count2 = -1;
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
				//the following 5 if statements prevents variables from going below minimum value of an integer
				if(count2 == Integer.MIN_VALUE+1){
					count2 = -1;
				}
				if(jTimer2 == Integer.MIN_VALUE+1){
					jTimer2 = -1;
				}
				if(pTimer2 == Integer.MIN_VALUE+1){
					pTimer2 = -1;
				}
				if(sTimer2 == Integer.MIN_VALUE+1){
					sTimer2 = -1;
				}
				if(gTimer2 == Integer.MIN_VALUE+1){
					gTimer2 = -1;
				}

				//the following are for the 2nd character
				count2--;
				jTimer2--;
				pTimer2--;
				sTimer2--;
				gTimer2--;
				int p2Act = 0;//number of player 2 actions
				for(int i = 0;i < p2.length;i++){
					if(pressed.contains(p2[i]))
						p2Act++;
				}
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
				if(pressed.contains(KeyEvent.VK_C)&&globalTimer%10 == 0&&p2Act < 2){
					mpane.charge2();
				}
				if(pressed.contains(KeyEvent.VK_X)&&p2Act<2){
					if(!transformed2){
						mpane.energy2 -= 5;
						if(mpane.energy2 < 0){
							mpane.energy2 += 5;
							mpane.setMsg2("Not enough energy!");
						}
						else{
							int luck = (int)(100*Math.random());
							if(luck > 45 && luck < 55){
								transformed2 = true;
								mpane.transform2();
								count2 = 1200;
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
				//galick gun
				if(pressed.contains(KeyEvent.VK_G) && gTimer2 < 0&&p2Act < 2){
					mpane.galickGun2(0);
					gTimer2 = mpane.fcd2;
				}
				if(gTimer2>=0){
					mpane.galickGun2(1);
				}
				if(gTimer2 == 0){
					mpane.galickGun2(-1);
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
				mpane.timeUp();
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
