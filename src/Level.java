import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class Level {
	public Point2D startLocation;
	public Rectangle2D endLocation;
	public Level nextLevel;
	public int levelID;
	int setting;
	public static int MOUNTAINS=0;
	public static int VOLCANO=1;
	public Obstacle[] levelComponents = new Obstacle[1];
	public Obstacle[] levelBackground = new Obstacle[1];
	public Obstacle[] levelForeground = new Obstacle[1];
	
	public Level(int levelNum){
		int backgroundType=MOUNTAINS;
		levelID=levelNum;
		defineObstacles();
		defineBackground();
		defineForeground();
	}
	public void define(Point2D s,Rectangle2D e, Level l, int background){
		
		startLocation=s;
		endLocation=e;
		nextLevel=l;
		setting=background;
		defineObstacles();
		defineBackground();
		defineForeground();
		
	}
	public void defineObstacles(Obstacle... obstacles){
		levelComponents = new Obstacle[obstacles.length+4];
		for(int i=0;i<obstacles.length;i++){
			levelComponents[i]=obstacles[i];
		}
		levelComponents[obstacles.length]= new Obstacle(new Rectangle2D.Double(-100,-100,100,1000),Color.black);
		levelComponents[obstacles.length+1]= new Obstacle(new Rectangle2D.Double(-100,700,1480,100),Color.black);
		levelComponents[obstacles.length+2]= new Obstacle(new Rectangle2D.Double(1280,-100,100,1000),Color.black);
		levelComponents[obstacles.length+3]= new Obstacle(new Rectangle2D.Double(-100,-100,1480,100),Color.black);
	}
	public void defineBackground(Obstacle... graphical){
		if(setting==MOUNTAINS){
		backgroundGraphical(4,graphical,0);
		levelBackground[0]=new Obstacle(new Rectangle2D.Double(),Color.red).setTexture("Assets/Sky.png");
		levelBackground[1]=new RelativeBackground("Assets/Mountains_0.png",51.2,28);
		levelBackground[2]=new RelativeBackground("Assets/Mountains_1.png",25.6,14);
		levelBackground[3]=new RelativeBackground("Assets/Mountains_2.png",12.8,7);
		}
		if(setting==VOLCANO){
		backgroundGraphical(2,graphical,0);
		levelBackground[0]=new RelativeBackground("Assets/Volcano_0.png",25.6,14);
		levelBackground[1]=new RelativeBackground("Assets/Volcano_1.png",12.8,7);
		}
	unCollide(levelBackground);	
	}
	
	public void defineForeground(Obstacle...graphical){
		if(setting==MOUNTAINS){
			foregroundGraphical(0,graphical,0);
		}
		if(setting==VOLCANO){
			foregroundGraphical(0,graphical,1);
			levelForeground[graphical.length]=
					new MovingObstacle2(new Rectangle2D.Double(-100,-100,0,0),Color.red,new Point2D.Double(0,-100)).setTexture("Assets/Volcano_2.png");
		}
		
		unCollide(levelForeground);
	}
	
	private void backgroundGraphical(int plus,Obstacle[] graphical,int plus2){
		levelBackground=new Obstacle[graphical.length+plus+plus2];
		for(int i=0;i<graphical.length;i++){
			levelBackground[i+plus]=graphical[i];
		}
	}
	private void foregroundGraphical(int plus,Obstacle[] graphical,int plus2){
		levelForeground=new Obstacle[graphical.length+plus+plus2];
		for(int i=0;i<graphical.length;i++){
			levelForeground[i+plus]=graphical[i];
		}
	}
	public void resetAll(){
		for(int i=0;i<levelComponents.length;i++)levelComponents[i].reset();
		for(int i=0;i<levelBackground.length;i++)levelBackground[i].reset();
		for(int i=0;i<levelForeground.length;i++)levelForeground[i].reset();
	}
	public void drawLevel(Graphics2D g){
		for(int i=0;i<levelBackground.length;i++)if(levelBackground[i]!=null)levelBackground[i].fill(g);
		for(int i=0;i<levelComponents.length;i++)if(levelComponents[i]!=null)levelComponents[i].fill(g);
		
		
		
	}
	public void drawForeground(Graphics2D g){
		for(int i=0;i<levelForeground.length;i++)if(levelForeground[i]!=null)levelForeground[i].fill(g);
	}
	private void unCollide(Obstacle[] uncollide){
		for(int i=0;i<uncollide.length;i++){
			uncollide[i].collide=false;
		}
	}

}
