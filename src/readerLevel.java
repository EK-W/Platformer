import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class readerLevel {
	Obstacle2[] components;
	Point2D startLocation = new Point2D.Double(640,400);
	int levelID;
	File loc;
	int obstacleID=0;
	
	

	public readerLevel(String f,int id){
		levelID = id;
		File here = new File(".");
		String location = ".";
		try {
		    location = here.getCanonicalPath().substring(0,here.getCanonicalPath().length()-3);
		 loc= new File(location+f);
		}catch(IOException e){
			e.printStackTrace();
		}
	readLevel();
	}
	
	
	private Obstacle2 readObstacle(String str){
		obstacleID++;
		Obstacle2 ret = new Obstacle2();
		Scanner fin=new Scanner(str);
		String temp = fin.next();
			if(temp.substring(0,2).equals("//")){
			ret.setBounds(-1000,-1000, 2, 2);
			ret.dontCollide();
			return ret;
			}else if(temp.equalsIgnoreCase("Debug")){
				ret.debugMode();
				temp=fin.next();
			}else if(!temp.equals("Bounds")){	
				System.err.println("Error with obstacle "+obstacleID +": first argument in obstacle must be bounds");
			}
			
			if(temp.equals("Bounds")){
				double x=fin.nextDouble();
				double y=fin.nextDouble();
				double w=fin.nextDouble();
				double h=fin.nextDouble();
				ret.setBounds(x, y, w, h);
			}
		while(fin.hasNext()){
			temp=fin.next();
			if(temp.equalsIgnoreCase("end"))break;
			if(temp.equalsIgnoreCase("noCollide")){
				ret.dontCollide();
			}
			if(temp.equals("Color")){
				int r = (int)Math.abs(Math.round(fin.nextDouble())%256);
				int g = (int)Math.abs(Math.round(fin.nextDouble())%256);
				int b = (int)Math.abs(Math.round(fin.nextDouble())%256);
				int a = (fin.hasNextDouble()) ? (int)Math.abs(Math.round(fin.nextDouble())%256):255;
				ret.setColor(new Color(r,g,b,a));
			}
			if(temp.equals("Move")){
				double speed = fin.nextDouble();
				String locs ="";
				while(fin.hasNextDouble()){
					locs+=fin.next();
					locs+=" ";
				}
				ret.canMove(speed,readArrayDouble(locs));
			}
			if(temp.equals("Texture")){
				ret.setTexture(fin.next());
			}
		}
		
		
		return ret;
	}
	
	private double[] readArrayDouble(String str){
		Scanner fin=new Scanner(str);
		Scanner counter = new Scanner(str);
		double[] ret;
		int count = 0;
		while(counter.hasNextDouble()){
			counter.next();
			count++;
		}
		ret = new double[count];
		for(int i=0;fin.hasNextDouble();i++){
			ret[i]=fin.nextDouble();
		}
		return ret;
	}
	public void readLevel(){
		Scanner fin=null;
		Scanner counter = null;

		try {
			fin=new Scanner(loc);
			counter = new Scanner(loc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int count = 0;
		fin.useDelimiter("end+");
		counter.useDelimiter("end+");
		while (counter.hasNextLine()) {
			count++;
			counter.nextLine();
		}
		components = new Obstacle2[count+4];
		int num = 0;
		while(fin.hasNext()){
			components[num+4]=(readObstacle(fin.next()));
			num++;
		}
		
		components[0]=new Obstacle2().setBounds(-50,350,100,700);
		components[1]=new Obstacle2().setBounds(1330,350,100,700);
		components[2]=new Obstacle2().setBounds(640,-50,1280,100);
		components[3]=new Obstacle2().setBounds(640,750,1280,100);
	}

	
	public void drawLevel(Graphics2D g){
		for(int i=0;i<components.length;i++){
		components[i].fill(g);
		}
	}
}
