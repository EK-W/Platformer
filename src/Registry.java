import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;


public class Registry {
	public static int levelNum = 1;
	public static Level[] levels = new Level[3];
	public static Level current;
//  new Obstacle(new Rectangle2D.Double(530,125,75,30),new Color(88,88,88)),
//	new MovingObstacle(new Rectangle2D.Double(0,800,100,50),Color.black,new Point2D.Double(0,0), 1),
	public static void registerLevels(){
		for(int i=0;i<levels.length;i++){
			levels[i]=new Level();
		}
		register0();
		register1();
		register2();
		}
	
	public static void setLevel(int num){
		levelNum=num; 
		current = levels[levelNum];
		Display.player.loc.setLocation(current.startLocation);
	}

	private static void register0(){
		levels[0].define(0,new Point2D.Double(100,100),new Rectangle2D.Double(1270,0,10,800),levels[1]);
		levels[0].defineObstacles(
				new Obstacle(new Rectangle2D.Double(635,125,745,800),new Color(60,46,7)),
				new Obstacle(new Rectangle2D.Double(635,125,745,5),new Color(36,148,2)),
				new Obstacle(new Rectangle2D.Double(-100,600,1480,200),new Color(60,46,7)),
				
				new Obstacle(new Rectangle2D.Double(425,125,30,400),new Color(88,88,88)),
				new Obstacle(new Rectangle2D.Double(605,125,30,400),new Color(88,88,88)),
				new Obstacle(new Rectangle2D.Double(530,495,100,30),new Color(88,88,88)),
				new Obstacle(new Rectangle2D.Double(455,400,75,30),new Color(88,88,88)),
				new Obstacle(new Rectangle2D.Double(530,305,100,30),new Color(88,88,88)),
				new Obstacle(new Rectangle2D.Double(455,210,75,30),new Color(88,88,88)),
				new Obstacle(new Rectangle2D.Double(530,125,75,30),new Color(88,88,88))
				
				);
		levels[0].defineBackground(
			new Obstacle(new Rectangle2D.Double(425,125,210,400),new Color(122,122,122))	
		);
	}

	private static void register1(){
		levels[1].define(1,new Point2D.Double(25,100),new Rectangle2D.Double(1270,0,10,800),levels[2]);
		levels[1].defineObstacles(
				new Obstacle(new Rectangle2D.Double(-100,150,300,800),new Color(60,46,7)),
				//new DeadlyObstacle(new MovingObstacle2(new Rectangle2D.Double(-100,600,1480,200),Color.red,new Point2D.Double(-100,0),1))
				new MovingObstacle2(new Rectangle2D.Double(-100,600,1480,200),Color.red,new Point2D.Double(-100,0),1)
				);

		levels[1].defineBackground(
				new Obstacle(new Rectangle2D.Double(100,0,30,200),new Color(0,51,255,125))
				);
		levels[1].defineForeground(
				new Obstacle(new Rectangle2D.Double(100,0,30,200),new Color(0,51,255,130))
				);
		
	}
	private static void register2(){
		levels[2].define(2,new Point2D.Double(100,100),new Rectangle2D.Double(1270,0,10,800),levels[0]);
		levels[2].defineObstacles(
				);
		
		
	}
}
