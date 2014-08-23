import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class RelativeBackground extends Obstacle{
	double multipliedX;
	double multipliedY;
public RelativeBackground(String filename, double multipliedX, double multipliedY){
	
	super(new Rectangle2D.Double(0,0,100,100),Color.black);
	this.multipliedX=multipliedX;
	this.multipliedY=multipliedY;
		File here = new File(".");
		String location = ".";
		try {
		    location = here.getCanonicalPath().substring(0,here.getCanonicalPath().length()-3);
		    img = ImageIO.read(new File(location+filename));
		    phys=new Rectangle2D.Double(phys.getX(),phys.getY(),img.getWidth(),img.getHeight());
		} catch (IOException e) {
		    e.printStackTrace();
		}
}
@Override
public void fill(Graphics2D g){
	g.drawImage(img,null,0-(int)Math.round(Display.player.loc.getX()/multipliedX),0-(int)Math.round(Display.player.loc.getY()/multipliedY));
}
}
