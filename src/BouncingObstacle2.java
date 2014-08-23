import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class BouncingObstacle2 extends Obstacle{
	double velocity;
	Point2D old;
	double bounciness;
	double decay;
	boolean ignorePlayer = false;
	public BouncingObstacle2(Rectangle2D setP, Color setC, double bounciness,double decay) {
		super(setP, setC);
		this.bounciness=bounciness;
		this.decay=decay;
		old = new Point2D.Double(setP.getX(),setP.getY());
		velocity = 20;
	}
//	public BouncingObstacle2 ignorePlayer(){
//		ignorePlayer=true;
//		return this;
//	}
	@Override
	public void fellOnto(double yv){
		velocity=yv;
	}
	@Override
	public void fill(Graphics2D g){
		velocity*=decay;
		Point2D prev = new Point2D.Double(phys.getX(),phys.getY());
		boolean moveWithPlayer=false;
		if(Display.player.me(0,1).intersects(phys)&&collide){
			moveWithPlayer=true;
		}
		phys=new Rectangle2D.Double(phys.getX(),phys.getY()+velocity,phys.getWidth(),phys.getHeight());
		velocity-=(phys.getY()-old.getY())*(1-bounciness);
		boolean playersafe = !(Display.player.hitsObstacle(Display.player.me()));
		if(moveWithPlayer){
			Display.player.loc.setLocation(Display.player.loc.getX(),Display.player.loc.getY()+(phys.getY()-prev.getY()));
		}
		super.fill(g);
	}

}
