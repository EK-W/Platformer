import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class DeadlyObstacle extends Obstacle{

	Obstacle ob;
	public DeadlyObstacle(Obstacle o){
		super(o.phys,o.col);
		ob=o;
	}
	@Override
	public void fill(Graphics2D g){
		if(!Display.player.dead){
		ob.fill(g);
		if(Display.player.me(1,0).intersects(phys)||Display.player.me(-1,0).intersects(phys)||
				Display.player.me(0,1).intersects(phys)||Display.player.me(0,-1).intersects(phys)){
					Display.player.dead=true;
				}
	}
	}
}
