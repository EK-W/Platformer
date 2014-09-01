import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

public class Display extends Applet implements KeyListener, ActionListener, MouseMotionListener{
	public static boolean left = false;
	public static boolean right = false;
	public static boolean up = false;
	public static boolean down = false;
	public static boolean shift = false;
	
	int levelSelect = 0;
	boolean slowMotion = false;
	static boolean debug = true;
	Point2D mouseLoc = new Point2D.Double();
	
	Timer animate = new Timer(25,this);
	public static Player player = new Player();
	public void init(){
		Registry.registerLevels();
		Registry.setLevel(0);
		setSize(1280,700);
		animate.start();
		addKeyListener(this);
		addMouseMotionListener(this);
	}
	public void paint(Graphics g2){
		Graphics2D g = (Graphics2D) g2;
		Registry.current.drawLevel(g);
		g.setColor(Color.black);
		g.drawString(String.valueOf(mouseLoc.getX()),5, 15);
		g.drawString(String.valueOf(mouseLoc.getY()),5, 30);
		g.drawString(String.valueOf(player.loc.getX()),5, 45);
		g.drawString(String.valueOf(player.loc.getY()),5, 60);
		player.loadImage(g);
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A)left=true;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D)right=true;
		if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W)up=true;
		if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S)down=true;
		if(e.getKeyCode()==KeyEvent.VK_SHIFT)shift=true;
		if(e.getKeyCode()==KeyEvent.VK_SPACE)doAtActionPerformed();
		doSwitchLevel(e);
	}
	
	private void doSwitchLevel (KeyEvent e){
		if(debug){
			int add = -1;
			if(e.getKeyCode()==KeyEvent.VK_1)add=1;
			if(e.getKeyCode()==KeyEvent.VK_2)add=2;
			if(e.getKeyCode()==KeyEvent.VK_3)add=3;
			if(e.getKeyCode()==KeyEvent.VK_4)add=4;
			if(e.getKeyCode()==KeyEvent.VK_5)add=5;
			if(e.getKeyCode()==KeyEvent.VK_6)add=6;
			if(e.getKeyCode()==KeyEvent.VK_7)add=7;
			if(e.getKeyCode()==KeyEvent.VK_8)add=8;
			if(e.getKeyCode()==KeyEvent.VK_9)add=9;
			if(e.getKeyCode()==KeyEvent.VK_0)add=0;
			if(add!=-1){
				levelSelect*=10;
				levelSelect+=add;
			}
			if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				levelSelect/=10;
			}
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				Registry.setLevel(levelSelect);
					levelSelect=0;
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A)left=false;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D)right=false;
		if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W)up=false;
		if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S)down=false;
//		if(e.getKeyCode()==KeyEvent.VK_SHIFT)shift=false;
//		if(e.getKeyCode()==KeyEvent.VK_Z)slowMotion=!slowMotion;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		doAtActionPerformed();
		
	}
	private void doAtActionPerformed(){
		if(slowMotion){
			animate.setDelay(animate.getInitialDelay()*2);
		}else{
			animate.setDelay(animate.getInitialDelay());
		}
		if(player.dead){
			Registry.setLevel(Registry.current.levelID);
			player.dead=false;
		}else{
		player.doMovement();
		repaint();
		}
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseLoc.setLocation(e.getPoint());	
	}
}
