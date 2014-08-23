import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class DeadlyObstacle extends Obstacle{

	public DeadlyObstacle(Rectangle2D r,Color col){
		super(r,col);
	}
	@Override
	public void fill(Graphics2D g){
		if(Display.player.me(0,1).intersects(phys))Display.player.dead=true;
		if(Display.player.me(0,-1).intersects(phys))Display.player.dead=true;
		if(Display.player.me(1,0).intersects(phys))Display.player.dead=true;
		if(Display.player.me(-1,0).intersects(phys))Display.player.dead=true;
		super.fill(g);
		if(Display.player.me(0,1).intersects(phys))Display.player.dead=true;
		if(Display.player.me(0,-1).intersects(phys))Display.player.dead=true;
		if(Display.player.me(1,0).intersects(phys))Display.player.dead=true;
		if(Display.player.me(-1,0).intersects(phys))Display.player.dead=true;
	}
}
