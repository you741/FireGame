/* DATE: UMM I DON'T REMEMBER
 * AUTHOR: YOU ZHOU
 * SOLO PROGRESS FOR AMUSEMENT
 */
package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainFrame extends JFrame implements Runnable, KeyListener, ActionListener{
	/**JFrame is from java docs and libraries
	 * It's a swing component
	 */
	private static final long serialVersionUID = 1L;
	JButton btn = new JButton("you dumb");
	FightPane mpane;
	
	boolean isQuit = false;//Checks if user quits
	boolean shouldRun = true;//checks if it isn't paused
	boolean fired = false;//checks if a shot has been fired
	boolean punched = false;//checks if a punch has been thrown
	boolean transformed = false;//checks if they are a super-saiyan
	boolean jump = false;//checks if they jumped
	//NOTE: Timers are to make it real-time
	int count = 0;//super-saiyan timer
	int jTimer = -1;//jump timer
	int pTimer = -1;//punch timer
	int sTimer = -1;//shot timer
	
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
		switch(keyCode){
		case KeyEvent.VK_ENTER:
			if(!mpane.paused){
				//Color lineC = new Color((int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random()));
				if(sTimer < 0){
					mpane.fire(0);
					fired = true;
				}
				mpane.setMsg("Fired");
			}
			break;
		case KeyEvent.VK_SPACE:
			if(!mpane.paused)
				if(jTimer < 0)
					jump = true;
			mpane.setMsg("Jumped");
			break;
		case KeyEvent.VK_UP:
			if(!mpane.paused)
				mpane.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			if(!mpane.paused)
				mpane.moveDown();
			break;
		case KeyEvent.VK_LEFT:
			if(!mpane.paused)
				mpane.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			if(!mpane.paused)
				mpane.moveRight();
			break;
		case KeyEvent.VK_X:
			if(!mpane.paused){
				if(!transformed){
					int luck = (int)(100*Math.random());
					if(luck > 45 && luck < 55){
						mpane.setSpeed(10);
						mpane.setColor(Color.yellow);
						mpane.setFSpeed(10);
						mpane.setFColor(Color.YELLOW);
						count = 1000;
						transformed = true;
						mpane.setMsg("SUPER SAIYAN!!");
					} else{
						mpane.setMsg("Super Saiyan failed...");
					}
				}
			}
			break;
		case KeyEvent.VK_Z:
			if(!mpane.paused){
				if(pTimer < 0)
					punched = true;
				mpane.setMsg("Punched");
			}
			break;
		case KeyEvent.VK_P:
			shouldRun = shouldRun?false:true;
			if(shouldRun)
				mpane.unpause();
			else{
				mpane.pause();
			}
			break;
		case KeyEvent.VK_ESCAPE:
			dispose();
			System.exit(1);
			break;
		default:
			break;
		
		}
		mpane.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int keyCode = arg0.getKeyCode();
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
				int jumpHeight = !transformed?20:100;
				if(fired){
					fired = false;
					mpane.punch(0);
					pTimer = 15;
					sTimer = 100;
				}
				if(jump){
					mpane.setY(mpane.getY()-jumpHeight);
					jTimer = 10;
					jump = false;
				}
				if(punched){
					punched = false;
					mpane.punch(0);
					pTimer = 15;
				}
				if(sTimer >= 0){
					mpane.fire(1);
				}
				if(sTimer == 0){
					fired = false;
					mpane.fire(2);
				}
				if(jTimer == 0){
					mpane.setY(mpane.getY()+jumpHeight);
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
