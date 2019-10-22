//Doreen He
//ID: 260761484



package comp557.a1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;



public class Geometry extends GraphNode{
	
	//location
	double tx = 0;
	double ty = 0;
	double tz = 0;
	
	//scaling
	double sx = 1;
	double sy = 1;
	double sz = 1;
	
	//rotation
	double rx = 0;
	double ry = 0;
	double rz = 0;
	
	//color
	float red = 100;
	float green = 1;
	float blue = 1;
		
	
	public Geometry(String name) {
		super(name);	
	}
	
	
	public void setPosition(double x, double y, double z) {
		this.tx = x;
		this.ty = y;
		this.tz = z;
	}
	
	public void setRotation(double x, double y, double z) {
		this.rx = x;
		this.ry = y;
		this.rz = z;
	}
	
	public void setScaling(double x, double y, double z) {
		this.sx = x;
		this.sy = y;
		this.sz = z;
	}
	
	public void setColor (float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	
	
}
