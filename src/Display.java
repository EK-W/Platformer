import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import javax.swing.Timer;

public class Display extends Applet implements KeyListener, ActionListener, MouseMotionListener{
	public static boolean left = false;
	public static boolean right = false;
	public static boolean up = false;
	public static boolean down = false;
	boolean debug = false;
	Point2D mouseLoc = new Point2D.Double();
	
	Timer animate = new Timer(25,this);
	public static Player player = new Player(new Point2D.Double(100,100));
	public void init(){
		Registry.registerLevels();
		Registry.setLevel(1);
		setSize(1280,700);
		if(!debug){
		animate.start();
		}
		addKeyListener(this);
		addMouseMotionListener(this);
	}
	
	public void paint(Graphics g2){
		Graphics2D g = (Graphics2D) g2;
		Registry.current.drawLevel(g);
		g.setColor(Color.red);
		g.fill(player.me());
		g.drawString(String.valueOf(mouseLoc.getX()),5,15);
		g.drawString(String.valueOf(mouseLoc.getY()),5,30);
		g.drawString(String.valueOf(player.loc.getX()),5,45);
		g.drawString(String.valueOf(player.loc.getY()),5,60);
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A)left=true;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D)right=true;
		if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W)up=true;
		if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S)down=true;
		if(e.getKeyCode()==KeyEvent.VK_SPACE&&debug)doAtActionPerformed();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A)left=false;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D)right=false;
		if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W)up=false;
		if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S)down=false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		doAtActionPerformed();
		
	}
	private void doAtActionPerformed(){
		player.doMovement();
		repaint();
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
