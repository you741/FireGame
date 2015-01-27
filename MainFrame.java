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
	
	boolean isQuit = false;
	boolean shouldRun = true;
	boolean fired = false;
	
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
			//Color lineC = new Color((int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random()));
			if(!fired){
				mpane.fire(0);
				fired = true;
			}else{
				mpane.fire(2);
				fired = false;
			}
			break;
		case KeyEvent.VK_UP:
			mpane.moveUp(3);
			break;
		case KeyEvent.VK_DOWN:
			mpane.moveDown(3);
			break;
		case KeyEvent.VK_LEFT:
			mpane.moveLeft(3);
			break;
		case KeyEvent.VK_RIGHT:
			mpane.moveRight(3);
			break;
		case KeyEvent.VK_ESCAPE:
			dispose();
			System.exit(1);
			break;
		default:
			break;
		
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!isQuit){
			System.out.println("FIRE");
			if(shouldRun){
				if(fired){
					mpane.fire(1);

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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
