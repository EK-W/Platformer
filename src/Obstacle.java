import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Obstacle {
public Rectangle2D phys;
public Color[] cols;
int delay;
int currentCol=0;
int wait;
boolean textured;
BufferedImage img;
public boolean collide = true;

public Obstacle(Rectangle2D setP, Color setC){
	phys=setP;
	delay = 1;
	wait=0;
	cols=new Color[]{setC};
	currentCol=0;
}
public void fellOnto(double yv){
	
}
public void fill(Graphics2D g){
	wait++;
	if(wait==delay){
		currentCol=(currentCol+1)%cols.length;
		wait=0;
	}
	g.setColor(cols[currentCol]);
	if(textured){
		g.drawImage(img,null,(int)Math.round(phys.getX()),(int)Math.round(phys.getY()));
	}else{
		g.fill(phys);
	}
}
public Obstacle(Obstacle o){
	phys=o.phys;
	cols=o.cols;
	delay = 1;
	wait=0;
	currentCol=0;
}
public Obstacle changeColors(int delay,Color...colors){
	Color temp = cols[0];
	cols=new Color[colors.length+1];
	for(int i=0;i<colors.length;i++){
		cols[i+1]=colors[i];
	}
	cols[0]=temp;
	this.delay = ((delay==0) ? 1:Math.abs(delay));
	return this;
}
public Obstacle setTexture(String texture){
	File here = new File(".");
	String location = ".";
	try {
		textured=true;
	    location = here.getCanonicalPath().substring(0,here.getCanonicalPath().length()-3);
	    img = ImageIO.read(new File(location+texture));
	    phys=new Rectangle2D.Double(phys.getX(),phys.getY(),img.getWidth(),img.getHeight());
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return this;
}
}
