import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Level {
	public Point2D startLocation;
	public Rectangle2D endLocation;
	public Level nextLevel;
	public int levelID;
	public Obstacle2[] components;
	
	public Level(int id){
		levelID=id;
	}
	public void setComponents(Obstacle2... components){
		this.components=new Obstacle2[components.length+4];
		for(int i=0;i<components.length;i++){
			this.components[i+4]=components[i];
		}
//		this.components[0]=new Obstacle2(0,0,1280,-100);
//		this.components[1]=new Obstacle2(0,0,-100,700);
//		this.components[2]=new Obstacle2(0,700,1280,800);
//		this.components[3]=new Obstacle2(1280,0,1380,700);
	}
	public void drawLevel(Graphics2D g){
		for(int i=0;i<components.length;i++){
		g.setColor(components[i].col);
		g.fill(components[i].getRect());
		}
	}
}
