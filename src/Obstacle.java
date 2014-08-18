import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class Obstacle {
public Rectangle2D phys;
public Color col;

public Obstacle(Rectangle2D setP, Color setC){
	phys=setP;
	col=setC;
}
public void fill(Graphics2D g){
	g.setColor(col);
	g.fill(phys);
}
public Obstacle(Obstacle o){
	phys=o.phys;
	col=o.col;
}
}
