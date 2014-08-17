import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class Player {
	int jumpHeight = 30;
	Point2D loc;
	public boolean dead = false;
	double width = 13;
	double height = 35;
	double xVel = 0;
	double yVel = 0;
	double distanceX=0;
	double distanceY=0;
	void doMovement(){
		if(!dead){
		if(hitsObstacle(me(0,1))){
			if(Display.up)yVel-=jumpHeight;
		}else{
			yVel+=2;
		}
		if(Display.left)xVel-=1.5;
		if(Display.right)xVel+=1.5;
		//if(Display.down)yVel+=1;
		xVel*=0.875;
		yVel*=0.95;
		double timesX = xVel/Math.abs(xVel);
		double timesY = yVel/Math.abs(yVel);
		boolean go = true;
		for(int i=0;i<Registry.levels[Registry.levelNum].levelComponents.length;i++){
			if(me(xVel,0).intersects(Registry.levels[Registry.levelNum].levelComponents[i].phys)){
				go=false;
				xVel=0;
				while(!me(0.1*timesX,0).intersects(Registry.levels[Registry.levelNum].levelComponents[i].phys)){
					loc.setLocation(loc.getX()+(0.1*timesX), loc.getY());
				}
			}
		}
		if(go){
			loc.setLocation(loc.getX()+xVel,loc.getY());
		}
		go=true;
		for(int i=0;i<Registry.levels[Registry.levelNum].levelComponents.length;i++){
			if(me(0,yVel).intersects(Registry.levels[Registry.levelNum].levelComponents[i].phys)){
				go=false;
				yVel=0;
				while(!me(0,0.1*timesY).intersects(Registry.levels[Registry.levelNum].levelComponents[i].phys)){
					loc.setLocation(loc.getX(), loc.getY()+(0.1*timesY));
				}
			}
		}
		if(go){
			loc.setLocation(loc.getX(),loc.getY()+yVel);
		}
		}
//		int precision = 100;
//		double unitX = xVel/precision;
//		double unitY = yVel/precision;
//		
//		for(int i=0;i<=precision;i++){
//			
//			for(int j=0; j<LevelRegistry.levels[LevelRegistry.levelNum].levelComponents.length;j++){
//				if(me(unitX*i,unitY*i).intersects(LevelRegistry.levels[LevelRegistry.levelNum].levelComponents[j])){
//					loc.setLocation(loc.getX()+(unitX*(i-1)),loc.getY()+(unitY*(i-1)));
//					break;
//				}
//			}
//		}
//		//loc.setLocation(loc.getX(),loc.getY()+yVel);
	}
	boolean hitsObstacle(Rectangle2D where){
	boolean ret = false;
	for(int i=0;i<Registry.levels[Registry.levelNum].levelComponents.length;i++){
		if(where.intersects(Registry.levels[Registry.levelNum].levelComponents[i].phys)){
			ret = true;
		}
	}
		return ret;
	}
	public Player(Point2D location){
		this.loc=location;
	}
	public Rectangle2D me(){
		return new Rectangle2D.Double(loc.getX()-width/2,loc.getY()-height,width,height);
	}
	public Rectangle2D me(double addX, double addY){
		return new Rectangle2D.Double(addX+(loc.getX()-(width)/2),addY+loc.getY()-height,width,height);
	}
	public void loadImage(Graphics2D g){
		File here = new File(".");
		String location = ".";
		try {
		    location = here.getCanonicalPath().substring(0,here.getCanonicalPath().length()-3);
		    Image img = ImageIO.read(new File(location+"UniRobot1.png"));
		    g.drawImage((BufferedImage) img,null,(int)Math.round(me().getX()),(int)Math.round(me().getY()));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
}
