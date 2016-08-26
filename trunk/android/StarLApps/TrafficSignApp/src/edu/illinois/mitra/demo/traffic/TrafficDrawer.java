package edu.illinois.mitra.demo.traffic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.Iterator;

import edu.illinois.mitra.starl.interfaces.LogicThread;
import edu.illinois.mitra.starl.objects.*;
import edu.illinois.mitra.starlSim.draw.Drawer;
public class TrafficDrawer extends Drawer {

	private Stroke stroke = new BasicStroke(8);
	private Color selectColor = new Color(0,0,255,100);
	
	@Override
	public void draw(LogicThread lt, Graphics2D g) {
		TrafficApp app = (TrafficApp) lt;
		Color[] c = new Color[12] ;
		c[0] = Color.BLACK;
		c[1] = Color.BLUE;
		c[2] = Color.GREEN;
		c[3] = Color.MAGENTA;
		c[4] = Color.ORANGE;
		c[5] = Color.CYAN;
		c[6] = Color.GRAY;
		c[7] = Color.PINK;
		c[8] = Color.RED;
		c[9] = Color.LIGHT_GRAY;
		c[10] = Color.YELLOW;
		c[11] = Color.DARK_GRAY;
		if(app.robotIndex<12){
			g.setColor(c[app.robotIndex]);
		}
		else{
			g.setColor(c[0]);
		}
		Iterator<ItemPosition> iterator = app.destinations.iterator();
	    Stroke dashed = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	    g.setStroke(dashed);
	    ItemPosition prevd = null;
	    if(app.myPos != null && app.currentDestination != null)
	    	g.drawLine(app.myPos.x +10*(app.robotIndex), app.myPos.y+10*(app.robotIndex), app.currentDestination.x+10*(app.robotIndex), app.currentDestination.y+10*(app.robotIndex));
	    while(iterator.hasNext()){
	    	ItemPosition dest = (ItemPosition) iterator.next();
	    	g.fillRect(dest.getX() +10*(app.robotIndex)- 13, dest.getY()+10*(app.robotIndex) - 13, 26, 26);
	    	
	    	if(prevd != null)
	    		g.drawLine(dest.x +10*(app.robotIndex), dest.y+10*(app.robotIndex), prevd.x+10*(app.robotIndex), prevd.y+10*(app.robotIndex));
	    	prevd = dest;	    	
	    }
	    
	    g.setStroke(new BasicStroke(10));
		g.setColor(Color.GRAY);
		ObstacleList list = app.obEnvironment;
		for(int i = 0; i < list.ObList.size(); i++)
		{
			Obstacles currobs = list.ObList.get(i);
			if(currobs.hidden)
				g.setColor(Color.LIGHT_GRAY);
			else
				g.setColor(Color.GRAY);
			
			Point3d nextpoint = currobs.obstacle.firstElement();
			Point3d curpoint = currobs.obstacle.firstElement();
			int[] xs = new int[currobs.obstacle.size()]; 
			int[] ys = new int[currobs.obstacle.size()]; ;
			
			for(int j = 0; j < currobs.obstacle.size() -1 ; j++){
			curpoint = currobs.obstacle.get(j);
			nextpoint = currobs.obstacle.get(j+1);
			g.drawLine(curpoint.x, curpoint.y, nextpoint.x, nextpoint.y);
			xs[j] = curpoint.x;
			ys[j] = curpoint.y;
			}
			xs[currobs.obstacle.size()-1] = nextpoint.x;
			ys[currobs.obstacle.size()-1] = nextpoint.y;
			
			g.drawLine(nextpoint.x, nextpoint.y, currobs.obstacle.firstElement().x, currobs.obstacle.firstElement().y);
			g.fillPolygon(xs,ys,currobs.obstacle.size());
		}
			
		g.setColor(selectColor);
		g.setStroke(stroke);
		if(app.currentDestination != null)
			g.drawOval(app.currentDestination.getX() - 20, app.currentDestination.getY() - 20, 40, 40);
	}

}
