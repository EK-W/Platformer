import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;


public class MovingObstacle2 extends Obstacle{
	
	Point2D newPoint;
	Point2D oldPoint;
	double distance= 0;
	int forwards;
	boolean BaF;
	double speed;
	double dX;
	double dY;
	double tdX;
	double tdY;
	double udX;
	double udY;
	double delta;
	//b-a=displacement of b
	
	public void doSetup(Rectangle2D setP, Color setC,Point2D setP2, double setS, boolean BaF){
		collide=true;
		newPoint=setP2;
		speed = Math.abs(setS);
		this.BaF=BaF;
		forwards = 1;
		oldPoint=new Point2D.Double(super.phys.getX(),super.phys.getY());	
		dX=setP2.getX()-setP.getX();
		dY=setP2.getY()-setP.getY();
		tdX = ((dX==0) ? 0:dX/Math.abs(dX));
		tdY = ((dY==0) ? 0:dY/Math.abs(dY));
		delta = Math.sqrt(Math.pow(dX,2)+Math.pow(dY,2));
		udX=dX/delta;
		udY=dY/delta;
	}
	@Override
	public void reset(){
		super.reset();
		forwards=1;
	}
	public MovingObstacle2(Rectangle2D setP, Color setC,Point2D setP2, double setS, boolean BaF) {
		super(setP, setC);
		doSetup(setP,setC,setP2,setS,BaF);
	}
	public MovingObstacle2(Rectangle2D setP, Color setC,Point2D setP2, double setS) {
		super(setP, setC);
		doSetup(setP,setC,setP2,setS,true);
	}
	public MovingObstacle2(Rectangle2D setP, Color setC,Point2D setP2) {
		super(setP, setC);
		doSetup(setP,setC,setP2,1,true);
	}
	
	@Override
	public void fill(Graphics2D g){
		Point2D prev;
	if(!Display.player.dead){
	boolean moveWithPlayer = false;
	if(Display.player.me(0,1).intersects(phys)&&collide){
		moveWithPlayer=true;
	}
	prev=new Point2D.Double(phys.getX(),phys.getY());
	phys=new Rectangle2D.Double(phys.getX()+(udX*speed*forwards),phys.getY(),phys.getWidth(),phys.getHeight());
	while(phys.intersects(Display.player.me())&&collide){
		Display.player.loc.setLocation(Display.player.loc.getX()+(tdX/100*forwards),Display.player.loc.getY());
	}
	if(Display.player.hitsObstacle(Display.player.me())&&collide)Display.player.dead=true;
	phys=new Rectangle2D.Double(phys.getX(),phys.getY()+(udY*speed*forwards),phys.getWidth(),phys.getHeight());
	while(phys.intersects(Display.player.me())&&collide){
		Display.player.loc.setLocation(Display.player.loc.getX(),Display.player.loc.getY()+(tdY/100*forwards));
	}
	if(Display.player.hitsObstacle(Display.player.me())&&collide)Display.player.dead=true;
	if(tdX>0&&phys.getX()<oldPoint.getX()){phys=new Rectangle2D.Double(oldPoint.getX(),phys.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	else if(tdX>0&&phys.getX()>newPoint.getX()){phys=new Rectangle2D.Double(newPoint.getX(),phys.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	else if(tdX<0&&phys.getX()>oldPoint.getX()){phys=new Rectangle2D.Double(oldPoint.getX(),phys.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	else if(tdX<0&&phys.getX()<newPoint.getX()){phys=new Rectangle2D.Double(newPoint.getX(),phys.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	else if(tdY>0&&phys.getY()<oldPoint.getY()){phys=new Rectangle2D.Double(phys.getX(),oldPoint.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	else if(tdY>0&&phys.getY()>newPoint.getY()){phys=new Rectangle2D.Double(phys.getX(),newPoint.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	else if(tdY<0&&phys.getY()>oldPoint.getY()){phys=new Rectangle2D.Double(phys.getX(),oldPoint.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	else if(tdY<0&&phys.getY()<newPoint.getY()){phys=new Rectangle2D.Double(phys.getX(),newPoint.getY(),phys.getWidth(),phys.getHeight());forwards*=-1;}
	boolean PlayerSafe = !Display.player.hitsObstacle(Display.player.me());
	
	if(moveWithPlayer){
		if(tdY*forwards==1){
		Display.player.loc.setLocation(Display.player.loc.getX()-(prev.getX()-phys.getX()),Display.player.loc.getY()-(prev.getY()-phys.getY()));
		while(PlayerSafe&&Display.player.hitsObstacle(Display.player.me())){
			Display.player.loc.setLocation(Display.player.loc.getX(),Display.player.loc.getY()-1);
		}
		}else{
		Display.player.loc.setLocation(Display.player.loc.getX()-(prev.getX()-phys.getX()),Display.player.loc.getY());
		}
		
	}
	
	}
	super.fill(g);
	}
}
