/* DATE: UMM I DON'T REMEMBER
 * AUTHOR: YOU ZHOU
 * SOLO PROGRESS FOR AMUSEMENT
 */
//package main;

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
	
	boolean isQuit = false;
	boolean shouldRun = true;
	boolean fired = false;
	boolean transformed = false;
	boolean jump = false;
	
	int count = 0;
	int jTimer = -1;
	
	public MainFrame(FightPane pane){
		mpane = pane;
		setTitle("TheLastGuardian");
		setFocusable(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		addKeyListener(this);
		
		add(mpane, BorderLayout.CENTER);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int keyCode = arg0.getKeyCode();
		switch(keyCode){
		case KeyEvent.VK_ENTER:
			if(!mpane.paused){
				//Color lineC = new Color((int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random()));
				if(!fired){
					mpane.fire(0);
					fired = true;
				}else{
					mpane.fire(2);
					fired = false;
				}
			}
			break;
		case KeyEvent.VK_SPACE:
			if(!mpane.paused)
				jump = true;
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
						mpane.setFSpeed(5);
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
				count--;
				jTimer--;
				int jumpHeight = !transformed?10:100;
				if(fired){
					mpane.fire(1);
				}
				if(jump){
					mpane.setY(mpane.getY()-jumpHeight);
					jTimer = 10;
					mpane.repaint();
					jump = false;
				}
				if(jTimer == 0){
					mpane.setY(mpane.getY()+jumpHeight);
	
				}
				if(count<=0){
					mpane.setSpeed(3);
					mpane.setColor(Color.black);
					mpane.setFSpeed(1);
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
