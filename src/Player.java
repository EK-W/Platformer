import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player {
	double wheelMinSpeed = 2;
	double jumpHeight=30;
	Point2D loc = new Point2D.Double();
	double walkSpeed=1;
	double walkDelay=0.85;
	public boolean dead = false;
	double width = 13;
	double height = 35;
	double xVel = 0;
	double yVel = 0;
	int animationPhase=0;
	int wheelDelay = 4;
	int wheelCurrentDelay = 0;
	double gravity = 0.95;
	boolean crouching = false;
	double precision = 100;
	boolean flipped = false;
	
	void doMovement(){
		if(hitsObstacle(me())){
			dead=true;
		}
		double currentSpeed=walkSpeed;
		if(Display.left)xVel-=currentSpeed;
		if(Display.right)xVel+=currentSpeed;
		if(hitsObstacle(me(0,1))){
		if(Display.up)yVel-=jumpHeight;
		}else{
			yVel+=2;
		}
		
		xVel*=walkDelay;
		yVel*=gravity;
		double uvx = xVel/precision;
		double uvy = yVel/precision;
		for(int i=0;i<=precision;i++){
			if(!hitsObstacle(me(uvx,0))){
				loc.setLocation(loc.getX()+uvx,loc.getY());
			}else{
				xVel=0;
			}
			if(!hitsObstacle(me(0,uvy))){
				loc.setLocation(loc.getX(),loc.getY()+uvy);
			}else{
				yVel=0;
			}
		}
		
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
			location = here.getCanonicalPath();
			
			Image img;
			if(crouching){
				img = ImageIO.read(new File(location+"/Assets/UniRobot1/Crouch.png"));
			}else{
				img = ImageIO.read(new File(location+"/Assets/UniRobot1/Stand.png"));
			}
			 Image img2 = ImageIO.read(new File(location+"/Assets/UniRobot1/Wheel_"+animationPhase+".png"));
			 if(xVel>0.01){
				 flipped=false;
			 }
			 if(xVel<-0.01){
				 flipped=true;
			 }
		    if(flipped){
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
	public boolean hitsObstacle(Rectangle2D test){
		for(int i=0;i<Registry.current.components.length;i++){
			if(test.intersects(Registry.current.components[i].getRect()))return true;
		}
		return false;
	}
	public boolean checkHit(Rectangle2D test,BufferedImage hits ){
			for(int i=(int) test.getX();i<=test.getX()+test.getWidth();i++){
				for(int j=(int) test.getY();j<test.getY()+test.getHeight();j++){
					if(hits.getRGB(i,j)!=0x00){
						return true;
					}
				}
			}
		return false;
	}
}
