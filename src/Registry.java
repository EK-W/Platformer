import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;


public class Registry {
	public static int levelNum = 1;
	public static Level[] levels = new Level[3];
	public static Level current;
	
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
	}

	private static void register0(){
		levels[0].define(
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
		levels[1].define(
				
				);
		
	}
	private static void register2(){
		levels[2].define(
				);
		
		
	}
}
