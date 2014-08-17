import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;


public class MovingObstacle extends Obstacle{

	Point2D newPoint;
	Point2D oldPoint;
	double percent = 0;
	int forwards;
	boolean BaF;
	double percentSpeed;
	//b-a=delta
	
	public MovingObstacle(Rectangle2D setP, Color setC,Point2D setP2, double setS, boolean BaF) {
		super(setP, setC);
		newPoint=setP2;
		percentSpeed = setS;
		this.BaF=BaF;
		forwards = 1;
		oldPoint=new Point2D.Double(super.phys.getX(),super.phys.getY());
	}
	public MovingObstacle(Rectangle2D setP, Color setC,Point2D setP2, double setS) {
		super(setP, setC);
		newPoint=setP2;
		percentSpeed = setS;
		BaF=true;
		forwards = 1;
		oldPoint=new Point2D.Double(super.phys.getX(),super.phys.getY());
	}
	public MovingObstacle(Rectangle2D setP, Color setC,Point2D setP2) {
		super(setP, setC);
		newPoint=setP2;
		percentSpeed=1;
		BaF=true;
		forwards = 1;
		oldPoint=new Point2D.Double(super.phys.getX(),super.phys.getY());
	}
	
	@Override
	public void fill(Graphics2D g){
		if(!Display.player.dead){
		double dX = newPoint.getX()-oldPoint.getX();
		double dY = newPoint.getY()-oldPoint.getY();
		double tdX = ((dX == 0) ? 0:dX/Math.abs(dX));
		double tdY = ((dY == 0) ? 0:dY/Math.abs(dY));
		Point2D current = new Point2D.Double();
		boolean moveXWithPlayer = false;
		
		if(Display.player.me(0,1).intersects(super.phys)||Display.player.me(1,0).intersects(super.phys)||Display.player.me(-1,0).intersects(super.phys)){
			moveXWithPlayer=true;
		}
		current.setLocation(super.phys.getX(),super.phys.getY());
		super.phys=new Rectangle2D.Double(oldPoint.getX()+((dX/100)*percent),super.phys.getY(),
				super.phys.getWidth(),super.phys.getHeight());
		if(super.phys.intersects(Display.player.me())){
		while(super.phys.intersects(Display.player.me())){
			Display.player.loc.setLocation(Display.player.loc.getX()+(tdX*forwards),Display.player.loc.getY());
		}
		if(!super.phys.intersects(Display.player.me())&&Display.player.hitsObstacle(Display.player.me())){
			Display.player.dead=true;
		}
		}
		//if(Display.player.me(0,1).intersects(super.phys))
		super.phys=new Rectangle2D.Double(super.phys.getX(),oldPoint.getY()+((dY/100)*percent),
				super.phys.getWidth(),super.phys.getHeight());
		if(super.phys.intersects(Display.player.me())){
		while(super.phys.intersects(Display.player.me())){
			Display.player.loc.setLocation(Display.player.loc.getX(),Display.player.loc.getY()+(tdY*forwards));
		}
		if(!super.phys.intersects(Display.player.me())&&Display.player.hitsObstacle(Display.player.me())){
			Display.player.dead=true;
		}
		}
		if(percent==100){
			forwards=-1;
		}
		if(percent==0){
			forwards=1;
		}

		percent+=(percentSpeed*forwards);
		if(percent>100)percent=100;
		if(percent<0)percent=0;
		if(moveXWithPlayer){
		Display.player.loc.setLocation(Display.player.loc.getX()+(super.phys.getX()-current.getX()),Display.player.loc.getY());
		}
	}
		g.setColor(col);
		g.fill(super.phys);
		
	}

}
