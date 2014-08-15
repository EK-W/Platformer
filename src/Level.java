import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Level {
	public Obstacle[] levelComponents = new Obstacle[1];
	public Obstacle[] levelBackground = new Obstacle[1];
	void define(Obstacle... obstacles){
		levelComponents = new Obstacle[obstacles.length];
		for(int i=0;i<obstacles.length;i++){
			levelComponents[i]=obstacles[i];
		}
		levelComponents[obstacles.length]= new Obstacle(new Rectangle2D.Double(-100,-100,100,1000),Color.black);
		levelComponents[obstacles.length+1]= new Obstacle(new Rectangle2D.Double(-100,700,1480,100),Color.black);
		levelComponents[obstacles.length+2]= new Obstacle(new Rectangle2D.Double(1280,-100,100,1000),Color.black);
	}
	void defineBackground(Obstacle... graphical){
		levelBackground = graphical;
	}
	void drawLevel(Graphics2D g){
		for(int i=0;i<levelBackground.length;i++)if(levelBackground[i]!=null)levelBackground[i].fill(g);
		for(int i=0;i<levelComponents.length;i++)if(levelComponents[i]!=null)levelComponents[i].fill(g);
		
		
	}
	

}
