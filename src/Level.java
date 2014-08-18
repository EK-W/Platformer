import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Level {
	public Point2D startLocation;
	public Rectangle2D endLocation;
	public Level nextLevel;
	public int levelNum;
	public Obstacle[] levelComponents = new Obstacle[1];
	public Obstacle[] levelBackground = new Obstacle[1];
	public Obstacle[] levelForeground = new Obstacle[1];
	public void define(int n,Point2D s,Rectangle2D e, Level l){
		levelNum=n;
		startLocation=s;
		endLocation=e;
		nextLevel=l;
	}
	public void defineObstacles(Obstacle... obstacles){
		levelComponents = new Obstacle[obstacles.length+3];
		for(int i=0;i<obstacles.length;i++){
			levelComponents[i]=obstacles[i];
		}
		levelComponents[obstacles.length]= new Obstacle(new Rectangle2D.Double(-100,-100,100,1000),Color.black);
		levelComponents[obstacles.length+1]= new Obstacle(new Rectangle2D.Double(-100,700,1480,100),Color.black);
		levelComponents[obstacles.length+2]= new Obstacle(new Rectangle2D.Double(1280,-100,100,1000),Color.black);
	}
	public void defineBackground(Obstacle... graphical){
		levelBackground = graphical;
	}
	public void defineForeground(Obstacle...graphical){
		levelForeground=graphical;
	}
	public void drawLevel(Graphics2D g){
		for(int i=0;i<levelBackground.length;i++)if(levelBackground[i]!=null)levelBackground[i].fill(g);
		for(int i=0;i<levelComponents.length;i++)if(levelComponents[i]!=null)levelComponents[i].fill(g);
		
		
		
	}
	public void drawForeground(Graphics2D g){
		for(int i=0;i<levelForeground.length;i++)if(levelForeground[i]!=null)levelForeground[i].fill(g);
	}
	

}
