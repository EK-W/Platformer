import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Obstacle2 {
	Color col;
	private double centerX;
	private double centerY;
	private double height;
	private double width;
	BufferedImage img;
	boolean textured;
	boolean deadly;
	boolean canMove;
	double speed;
	int to,from;
	double[] moveX,moveY;
	boolean debug;
	boolean collide;
	double setOpacityMult;

	public Obstacle2(){
		centerX=50;
		centerY=50;
		width=100;
		width=100;
		col=Color.black;
		canMove=false;
		deadly=false;
		collide=true;
		textured=false;
		debug=false;
		setOpacityMult = 1;
	}
	
	public Obstacle2 setBounds(double x, double y, double width, double height){
		centerX=x;
		centerY=y;
		this.width=width;
		this.height=height;
		return this;
	}
	
	public Obstacle2 setColor(Color c){
		col=c;
		return this;
	}
	
	public Obstacle2 setTexture(String t){
		File here = new File(".");
		String location = ".";
		try {
		    location = here.getCanonicalPath().substring(0,here.getCanonicalPath().length()-3);
		    img = ImageIO.read(new File(location+t));
		    textured=true;
		} catch (IOException e) {
			textured=false;
		    e.printStackTrace();
		}
		
		return this;
	}
	
	public Obstacle2 canMove(double speed, double...locs){
		this.speed=speed;
		moveX=new double[(locs.length/2)+1];
		moveY=new double[(locs.length/2)+1];
		for(int i=0;i<locs.length/2;i++){
			moveX[i+1]=locs[i*2];
			moveY[i+1]=locs[(i*2)+1];
		}
		moveX[0]=centerX;
		moveY[0]=centerY;
		canMove=true;
		from=0;
		to=1;
		return this;
	}
	
	public Obstacle2 dontCollide(){
		collide=false;
		return this;
	}

	public Rectangle2D getRect(){
		return new Rectangle2D.Double(centerX-(width/2),centerY-(height/2),width,height);
	}
	
	public void fill(Graphics2D g){
		if(canMove)doMovement();
		testTouching();
		if(textured){
			int xl = (int) Math.round(centerX-(width/2));
			int yl = (int) Math.round(centerY-(height/2));
			g.drawImage(img,(int)xl,(int)yl,xl+(int)width,yl+(int)height,0,0,(int)img.getWidth(),(int)img.getHeight(),null);
		}else{
			g.setColor(col);
			g.fill(getRect());
		}
		doDebug(g);
	}

	private void doMovement(){
		double distance	= Math.sqrt(Math.pow(moveX[to]-moveX[from],2)+Math.pow(moveY[to]-moveY[from],2));
		double uvx = (moveX[to]-moveX[from])/distance;
		double uvy = (moveY[to]-moveY[from])/distance;
		Point2D prev = new Point2D.Double(getRect().getCenterX(),getRect().getCenterY());
		boolean playerSafe = true;
		boolean moveWith = false;
		if(Display.player.hitsObstacle(Display.player.me()))playerSafe=false;
		if(getRect().intersects(Display.player.me(0,1))&&collide)moveWith=true;
		byte tX;
		byte tY;
		tX=(byte)((moveX[to]-moveX[from])/Math.abs(moveX[to]-moveX[from]));
		tY=(byte)((moveY[to]-moveY[from])/Math.abs(moveY[to]-moveY[from]));
		if((centerX>moveX[to]&&centerX+(uvx*speed)<moveX[to])||(centerX<moveX[to]&&centerX+(uvx*speed)>moveX[to])){
			centerX=moveX[to];	
		}else{
			centerX+=(uvx*speed);
		}
		
		if(moveWith){
			Display.player.loc.setLocation(Display.player.loc.getX()+(getRect().getCenterX()-prev.getX()),
					Display.player.loc.getY());
		}
		while(playerSafe&&getRect().intersects(Display.player.me())&&collide){
			Display.player.loc.setLocation(Display.player.loc.getX()+tX,Display.player.loc.getY());
		}
		if((centerY>moveY[to]&&centerY+(uvy*speed)<moveY[to])||(centerY<moveY[to]&&centerY+(uvy*speed)>moveY[to])){
			centerY=moveY[to];
		}else{
			centerY+=(uvy*speed);
		}
		if(moveWith){
			Display.player.loc.setLocation(Display.player.loc.getX(),
					Display.player.loc.getY()+(centerY-prev.getY()));
		}
		while(playerSafe&&getRect().intersects(Display.player.me())&&collide){
			Display.player.loc.setLocation(Display.player.loc.getX(),Display.player.loc.getY()+(tY));
		}
		if(Math.round(getRect().getCenterX())==Math.round(moveX[to])){
			if(Math.round(getRect().getCenterY())==Math.round(moveY[to])){
				to++;
				from++;
				to%=moveX.length;
				from%=moveX.length;
			}
		}
		
	}

	private void testTouching(){
		if(deadly){
		if(Display.player.me(0, 1).intersects(getRect()))Display.player.dead=true;
		if(Display.player.me(0, -1).intersects(getRect()))Display.player.dead=true;
		if(Display.player.me(1, 0).intersects(getRect()))Display.player.dead=true;
		if(Display.player.me(-1, 0).intersects(getRect()))Display.player.dead=true;
		}
	}

	private void doDebug(Graphics2D g){
		if(debug){
			if(canMove){
				g.setColor(Color.black);
				for(int i=0;i<moveX.length;i++){
					g.fill(new Ellipse2D.Double(moveX[i]-5,moveY[i]-5,10,10));
				}
				g.setColor(Color.red);
				g.setStroke(new BasicStroke(4,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
				for(int i=0;i<moveX.length;i++){
					g.draw(new Line2D.Double(moveX[i],moveY[i],moveX[(i+1)%moveX.length],moveY[(i+1)%moveX.length]));
				}
				byte tX=(byte)((moveX[to]-moveX[from])/Math.abs(moveX[to]-moveX[from]));
				byte tY=(byte)((moveY[to]-moveY[from])/Math.abs(moveY[to]-moveY[from]));
				g.setColor(Color.black);
				g.drawString("TX: " +tX,(int)centerX,(int)centerY-8);
				g.drawString("TY: " +tY,(int)centerX,(int)centerY+8);
			}
		}
	}

	public Obstacle2 debugMode(){
		debug=true;
		return this;
	}
}
