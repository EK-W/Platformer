import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class Player {
	double wheelMinSpeed = 2;
	double walkSpeed = 1;
	double runSpeed = 1.25;
	double xVelDecay = 0.875;
	int jumpHeight = 30;
	Point2D loc;
	public boolean dead = false;
	double width = 13;
	double height = 35;
	double xVel = 0;
	double yVel = 0;
	double distanceX=0;
	double distanceY=0;
	int animationPhase=0;
	int wheelDelay = 4;
	int wheelCurrentDelay = 0;
	boolean crouching = false;
	void doMovement(){
	double currentSpeed;
	if(hitsObstacle(me())){
		dead=true;
	}
		if(!dead){
			if(Display.down){
				if(!crouching)loc.setLocation(loc.getX(),loc.getY()-12);
				height=23;
				crouching=true;
				xVelDecay=0.75;
				System.out.println(height);
			}else{
				height=35;
				crouching = false;
				xVelDecay=0.875;
			}
			if(Display.shift){
				currentSpeed=runSpeed;
			}else{
				currentSpeed=walkSpeed;
			}
		if(hitsObstacle(me(0,1))){
			if(Display.up)yVel-=jumpHeight;
		}else{
			yVel+=2;
		}
		if(Display.left)xVel-=currentSpeed;
		if(Display.right)xVel+=currentSpeed;
		//if(Display.down)yVel+=1;
		xVel*=xVelDecay;
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
		double yv;
		go=true;
		for(int i=0;i<Registry.levels[Registry.levelNum].levelComponents.length;i++){
			if(me(0,yVel).intersects(Registry.levels[Registry.levelNum].levelComponents[i].phys)){
				go=false;
				yv=yVel;
				yVel=0;
				while(!me(0,0.1*timesY).intersects(Registry.levels[Registry.levelNum].levelComponents[i].phys)){
					loc.setLocation(loc.getX(), loc.getY()+(0.1*timesY));
				}
			Registry.current.levelComponents[i].fellOnto(yv);
			}
		}
		if(go){
			loc.setLocation(loc.getX(),loc.getY()+yVel);
		}
		if(me().intersects(Registry.current.endLocation)){
			Registry.setLevel(Registry.current.nextLevel.levelID);
		}
		}
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
		if((Display.right||Display.left)||Math.abs(xVel)>0.25)wheelCurrentDelay++;
		if(wheelCurrentDelay==wheelDelay&&Math.abs(xVel)>0){
			animationPhase++;
			wheelCurrentDelay=0;
			animationPhase = animationPhase%4;
		}
		try {
			location = here.getCanonicalPath().substring(0,here.getCanonicalPath().length()-3);
			
			Image img;
			if(crouching){
				img = ImageIO.read(new File(location+"UniRobot1/Crouch.png"));
			}else{
				img = ImageIO.read(new File(location+"UniRobot1/Stand.png"));
			}
			 Image img2 = ImageIO.read(new File(location+"UniRobot1/Wheel_"+animationPhase+".png"));
		    if(xVel<0){
		    	g.drawImage((BufferedImage) flipImage(img),null,(int)Math.round(me().getX()),(int)Math.round(me().getY()));
		    	g.drawImage((BufferedImage) flipImage(img2),null,(int)Math.round(me().getX()),(int)Math.round(me().getY()));
		    }else{
		    	g.drawImage((BufferedImage) img,null,(int)Math.round(me().getX()),(int)Math.round(me().getY()));
		    	g.drawImage((BufferedImage) img2,null,(int)Math.round(me().getX()),(int)Math.round(me().getY()));
		    }
		    
		} catch (IOException e) {
		   
		    e.printStackTrace();
		}
	}
	public Image flipImage(Image img){
		Image image = img;
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter((BufferedImage) image, null);
	}
}
