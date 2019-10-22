//Doreen He
//ID: 260761484



package comp557.a1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mintools.parameters.DoubleParameter;



public class SphericalJoint extends GraphNode{
	DoubleParameter rx;
	DoubleParameter ry;
	DoubleParameter rz;
	
	//location(rotation origin)
	double x;
	double y;
	double z;
	
	
	
	public SphericalJoint( String name) {
		super(name);
		this.x = 0;
		this.y = 0;
		this.z = 0;
		dofs.add( rx = new DoubleParameter( name+" rx", 0, -180, 180 ) );		
		dofs.add( ry = new DoubleParameter( name+" ry", 0, -180, 180 ) );
		dofs.add( rz = new DoubleParameter( name+" rz", 0, -180, 180 ) );
	}
	
	public void setPosition(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setXRotationRange( double defaultValue, double min, double max) {
		rx.setMinimum(min);
		rx.setMaximum(max);
		rx.setDefaultValue(defaultValue);
	}
	
	public void setYRotationRange( double defaultValue, double min, double max) {
		ry.setMinimum(min);
		ry.setMaximum(max);
		ry.setDefaultValue(defaultValue);
	}
	
	public void setZRotationRange( double defaultValue, double min, double max) {
		rz.setMinimum(min);
		rz.setMaximum(max);
		rz.setDefaultValue(defaultValue);
	}

	@Override
	public void display( GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glPushMatrix();
		gl.glTranslated(x,y,z);
		gl.glRotated(rx.getValue(),1,0,0);
		gl.glRotated(ry.getValue(),0,1,0);
		gl.glRotated(rz.getValue(),0,0,1);
		//gl.glColor3f(1, 100, 100);
		//glut.glutSolidCylinder(0.5, 5, 6, 6);
		//glut.glutSolidSphere(4, 12, 12);
		super.display(drawable);
		gl.glPopMatrix();	
	}

}
