//Doreen He
//ID: 260761484

package comp557.a1;
		  	  				   
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

import javax.vecmath.Tuple2d;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



/**
 * Loads an articulated character hierarchy from an XML file. 
 */
public class CharacterFromXML {

	public static GraphNode load( String filename ) {
		try {
			InputStream inputStream = new FileInputStream(new File(filename));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			return createScene( null, document.getDocumentElement() ); // we don't check the name of the document elemnet
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load simulation input file.", e);
		}
	}
	
	/**
	 * Load a subtree from a XML node.
	 * Returns the root on the call where the parent is null, but otherwise
	 * all children are added as they are created and all other deeper recursive
	 * calls will return null.
	 */
	public static GraphNode createScene( GraphNode parent, Node dataNode ) {
        NodeList nodeList = dataNode.getChildNodes();
        for ( int i = 0; i < nodeList.getLength(); i++ ) {
            Node n = nodeList.item(i);
            // skip all text, just process the ELEMENT_NODEs
            if ( n.getNodeType() != Node.ELEMENT_NODE ) continue;
            String nodeName = n.getNodeName();
            GraphNode node = null;
            if ( nodeName.equalsIgnoreCase( "node" ) ) {
            	node = CharacterFromXML.createJoint( n );
            } else if ( nodeName.equalsIgnoreCase( "geom" ) ) {        		
        		node = CharacterFromXML.createGeom( n ) ;            
            }
            // recurse to load any children of this node
            createScene( node, n );
            if ( parent == null ) {
            	// if no parent, we can only have one root... ignore other nodes at root level
            	return node;
            } else {
            	parent.add( node );
            }
        }
        return null;
	}
	
	/**​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
	 * Create a joint
	 * 
	 * TODO: Objective 5: Adapt commented code in createJoint() to create your joint nodes when loading from xml
	 */
	public static GraphNode createJoint( Node dataNode ) {
		String type = dataNode.getAttributes().getNamedItem("type").getNodeValue();
		String name = dataNode.getAttributes().getNamedItem("name").getNodeValue();
		Tuple3d t;
		
		if ( type.equals("freeJoint") ) {
			FreeJoint joint = new FreeJoint( name );
			
			return joint;
		} else if ( type.equals("sphericalJoint") ) {
			// position is optional (ignored if missing) but should probably be a required attribute!​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
			// Could add optional attributes for limits (to all joints)

			SphericalJoint joint = new SphericalJoint( name );
			if ( (t=getTuple3dAttr(dataNode,"position")) != null ) joint.setPosition( t.x, t.y, t.z );
			if ( (t=getTuple3dAttr(dataNode,"X-axisRotationRange")) != null ) joint.setXRotationRange( t.x, t.y, t.z );
			if ( (t=getTuple3dAttr(dataNode,"Y-axisRotationRange")) != null ) joint.setYRotationRange( t.x, t.y, t.z );
			if ( (t=getTuple3dAttr(dataNode,"Z-axisRotationRange")) != null ) joint.setZRotationRange( t.x, t.y, t.z );
			
			
			
					
			
			
			
			return joint;
			
		} else if ( type.equals("rotaryJoint") ) {
			// position and axis are required... passing null to set methods
			// likely to cause an execption (perhaps OK)
			
			RotaryJoint joint = new RotaryJoint( name );
			joint.setPosition( getTuple3dAttr(dataNode,"position").x, 
								getTuple3dAttr(dataNode,"position").y,
								getTuple3dAttr(dataNode,"position").z);
			joint.setAxis( getTuple3dAttr(dataNode,"axis").x,
							getTuple3dAttr(dataNode,"axis").y,
							getTuple3dAttr(dataNode,"axis").z);
			joint.setRange( getTuple3dAttr(dataNode,"range").x,
					getTuple3dAttr(dataNode,"range").y,
					getTuple3dAttr(dataNode,"range").z);
			return joint;
			
		}
		return null;
	}

	/**
	 * Creates a geometry DAG node 
	 * 
	 * TODO: Objective 5: Adapt commented code in greatGeom to create your geometry nodes when loading from xml
	 */
	public static GraphNode createGeom( Node dataNode ) {
		String type = dataNode.getAttributes().getNamedItem("type").getNodeValue();
		String name = dataNode.getAttributes().getNamedItem("name").getNodeValue();
		Tuple3d t;
		if ( type.equals("cone" ) ) {
     		Cone geom = new Cone( name );
			if ( (t=getTuple3dAttr(dataNode,"position")) != null ) geom.setPosition( t.x,t.y,t.z );
			if ( (t=getTuple3dAttr(dataNode,"scaling")) != null ) geom.setScaling( t.x, t.y, t.z );
			if ( (t=getTuple3dAttr(dataNode,"color")) != null ) geom.setColor( (float)t.x,(float) t.y, (float) t.z );
			if ( (t=getTuple3dAttr(dataNode,"rotation")) != null ) geom.setRotation( t.x, t.y, t.z );
			return geom;
		} else if ( type.equals( "cylinder" )) {
			Cylinder geom = new Cylinder( name );				
			if ( (t=getTuple3dAttr(dataNode,"position")) != null ) geom.setPosition( t.x, t.y, t.z );
			if ( (t=getTuple3dAttr(dataNode,"scaling")) != null ) geom.setScaling( t.x, t.y, t.z );
			if ( (t=getTuple3dAttr(dataNode,"color")) != null ) geom.setColor( (float)t.x,(float)t.y, (float)t.z );
			if ( (t=getTuple3dAttr(dataNode,"rotation")) != null ) geom.setRotation( t.x, t.y, t.z );
			return geom;	
		} else if ( type.equals( "sphere" )) {
			Sphere geom = new Sphere( name );				
			if ( (t=getTuple3dAttr(dataNode,"position")) != null ) geom.setPosition( t.x, t.y, t.z );
			if ( (t=getTuple3dAttr(dataNode,"scaling")) != null ) geom.setScaling( t.x, t.y, t.z );
			if ( (t=getTuple3dAttr(dataNode,"color")) != null ) geom.setColor( (float)t.x,(float)t.y, (float)t.z );
			if ( (t=getTuple3dAttr(dataNode,"rotation")) != null ) geom.setRotation( t.x, t.y, t.z );
			return geom;	
		} else if ( type.equals( "net" )) {
			Net geom = new Net( name );				
			if ( (t=getTuple3dAttr(dataNode,"position")) != null ) geom.setPosition( t.x, t.y, t.z );
			if ( (t=getTuple3dAttr(dataNode,"scaling")) != null ) geom.setScaling( t.x, t.y, t.z );
			if ( (t=getTuple3dAttr(dataNode,"color")) != null ) geom.setColor( (float)t.x,(float)t.y, (float)t.z );
			if ( (t=getTuple3dAttr(dataNode,"rotation")) != null ) geom.setRotation( t.x, t.y, t.z );
			return geom;	
		} 
		return null;		
	}
	
	/**
	 * Loads tuple3d attributes of the given name from the given node.
	 * @param dataNode
	 * @param attrName
	 * @return null if attribute not present
	 */
	public static Tuple3d getTuple3dAttr( Node dataNode, String attrName ) {
		Node attr = dataNode.getAttributes().getNamedItem( attrName);
		Vector3d tuple = null;
		if ( attr != null ) {
			Scanner s = new Scanner( attr.getNodeValue() );
			tuple = new Vector3d( s.nextDouble(), s.nextDouble(), s.nextDouble() );			
			s.close();
		}
		return tuple;
	}

}