import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class DeadlyMovingObstacle extends MovingObstacle2{

	public DeadlyMovingObstacle(Rectangle2D setP, Color setC,Point2D setP2, double setS, boolean BaF) {
		super(setP, setC,setP2,setS,BaF);
	}
	public DeadlyMovingObstacle(Rectangle2D setP, Color setC,Point2D setP2, double setS) {
		super(setP, setC,setP2,setS);
	}
	public DeadlyMovingObstacle(Rectangle2D setP, Color setC,Point2D setP2) {
		super(setP, setC,setP2);
	}
	public void fill(Graphics2D g){
		if(Display.player.me(0,1).intersects(phys))Display.player.dead=true;
		if(Display.player.me(0,-1).intersects(phys))Display.player.dead=true;
		if(Display.player.me(1,0).intersects(phys))Display.player.dead=true;
		if(Display.player.me(-1,0).intersects(phys))Display.player.dead=true;
		Point2D prev;
	if(!Display.player.dead){
	boolean moveWithPlayer = false;
	if(Display.player.me(0,1).intersects(phys)){
		Display.player.dead=true;
	}
	prev=new Point2D.Double(phys.getX(),phys.getY());
	phys=new Rectangle2D.Double(phys.getX()+(udX*speed*forwards),phys.getY(),phys.getWidth(),phys.getHeight());
	if(phys.intersects(Display.player.me())){
		Display.player.dead=true;
	}
	if(Display.player.hitsObstacle(Display.player.me()))Display.player.dead=true;
	phys=new Rectangle2D.Double(phys.getX(),phys.getY()+(udY*speed*forwards),phys.getWidth(),phys.getHeight());
	if(phys.intersects(Display.player.me())){
		Display.player.dead=true;
	}
	if(Display.player.hitsObstacle(Display.player.me()))Display.player.dead=true;
	if(tdX>0&&phys.getX()<oldPoint.getX()){phys=new Rectangle2D.Double(oldPoint.getX(),phys.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	if(tdX>0&&phys.getX()>newPoint.getX()){phys=new Rectangle2D.Double(newPoint.getX(),phys.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	if(tdX<0&&phys.getX()>oldPoint.getX()){phys=new Rectangle2D.Double(oldPoint.getX(),phys.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	if(tdX<0&&phys.getX()<newPoint.getX()){phys=new Rectangle2D.Double(newPoint.getX(),phys.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	if(tdY>0&&phys.getY()<oldPoint.getY()){phys=new Rectangle2D.Double(phys.getX(),oldPoint.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	if(tdY>0&&phys.getY()>newPoint.getY()){phys=new Rectangle2D.Double(phys.getX(),newPoint.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	if(tdY<0&&phys.getY()>oldPoint.getY()){phys=new Rectangle2D.Double(phys.getX(),oldPoint.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	if(tdY<0&&phys.getY()<newPoint.getY()){phys=new Rectangle2D.Double(phys.getX(),newPoint.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	if(Display.player.me(0,1).intersects(phys))Display.player.dead=true;
	if(Display.player.me(0,-1).intersects(phys))Display.player.dead=true;
	if(Display.player.me(1,0).intersects(phys))Display.player.dead=true;
	if(Display.player.me(-1,0).intersects(phys))Display.player.dead=true;
	}
	super.fill(g);
	}
}
