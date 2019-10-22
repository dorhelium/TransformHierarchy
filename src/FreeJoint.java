//Doreen He
//ID: 260761484



package comp557.a1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.gl2.GLUT;

import mintools.parameters.DoubleParameter;



public class FreeJoint extends GraphNode {

	DoubleParameter tx;
	DoubleParameter ty;
	DoubleParameter tz;
	DoubleParameter rx;
	DoubleParameter ry;
	DoubleParameter rz;
		
	public FreeJoint( String name ) {
		super(name);
		dofs.add( tx = new DoubleParameter( name+" tx", 0, -20, 20 ) );		
		dofs.add( ty = new DoubleParameter( name+" ty", 0, -20, 20 ) );
		dofs.add( tz = new DoubleParameter( name+" tz", 0, -20, 20 ) );
		dofs.add( rx = new DoubleParameter( name+" rx", 0, -180, 180 ) );		
		dofs.add( ry = new DoubleParameter( name+" ry", 0, -180, 180 ) );
		dofs.add( rz = new DoubleParameter( name+" rz", 0, -180, 180 ) );
	}
	
	@Override
	public void display( GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		// TODO: implement the rest of this method​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
		
		gl.glPushMatrix();
		gl.glTranslated(tx.getValue(), ty.getValue(), tz.getValue());
		
		gl.glRotated(rx.getValue(),1,0,0);
		gl.glRotated(ry.getValue(),0,1,0);
		gl.glRotated(rz.getValue(),0,0,1);
		//glut.glutSolidSphere(3,6,3);
		super.display(drawable);
		gl.glPopMatrix();
		       
		
	}

	
}
