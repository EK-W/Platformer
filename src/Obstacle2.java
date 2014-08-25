import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Obstacle2 {
	Color col;
	private Point2D loc1;
	private Point2D loc2;
	public Obstacle2(double x1, double y1, double x2, double y2){
		loc1.setLocation(x1<x2?x1:x2, y1<y2?y1:y2);
		loc2.setLocation(x1<x2?x2:x1, y1<y2?y2:y1);
	}
	public Rectangle2D getRect(){
		return new Rectangle2D.Double(loc1.getX(),loc1.getY(),loc2.getX()-loc1.getX(),loc2.getY()-loc1.getY());
	}
	
}
