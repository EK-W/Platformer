import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
	//The robot is 76.1677714288 pounds not counting his eye sensor and medical backpack
	//He is exactly 5 feet tall and made out of steel
	//G-force would be calculated by (velocity*40)/32

public class BouncingObstacle extends Obstacle{
	double velocity=0;
	double bouncyness=0;
	double speedback = 0;
	double target = 0;
	Point2D old;
	public BouncingObstacle(Rectangle2D setP, Color setC,double bouncyness,double speedback) {
		super(setP, setC);	
		old=new Point2D.Double(setP.getX(),setP.getY());
		this.bouncyness=bouncyness;
		this.speedback=speedback;
	}
	@Override
	public void fellOnto(double yv){
		velocity=yv;
	}
	
	@Override 
	public void fill(Graphics2D g){
		boolean moveWithPlayer=false;
		if(Display.player.me(0,1).intersects(phys)){
			moveWithPlayer=true;
		}
		velocity*=bouncyness;
		double prev = phys.getY();
		phys=new Rectangle2D.Double(phys.getX(),phys.getY()+velocity,phys.getWidth(),phys.getHeight());
//		if(phys.getY()>target){
//			phys=new Rectangle2D.Double(phys.getX(),phys.getY()-speedback,phys.getWidth(),phys.getHeight());
//		}
//		if(phys.getY()<target){
//			phys=new Rectangle2D.Double(phys.getX(),target,phys.getWidth(),phys.getHeight());
//		}
//		if(phys.getY()==target){
//			target=((phys.getY()-old.getY())*-1)*bouncyness;
//		}
		if(phys.getY()>old.getY()){
		phys=new Rectangle2D.Double(phys.getX(),phys.getY()-speedback,phys.getWidth(),phys.getHeight());
	}
	if(phys.getY()<old.getY()){
		phys=new Rectangle2D.Double(phys.getX(),old.getY(),phys.getWidth(),phys.getHeight());
	}
		if(moveWithPlayer){
		boolean PlayerSafe = !Display.player.hitsObstacle(Display.player.me());
		Display.player.loc.setLocation(Display.player.loc.getX(),Display.player.loc.getY()+(phys.getY()-prev));
		while(PlayerSafe&&Display.player.hitsObstacle(Display.player.me())){
			Display.player.loc.setLocation(Display.player.loc.getX(),Display.player.loc.getY()-1);
		}
		
		}
		super.fill(g);
	}

}
