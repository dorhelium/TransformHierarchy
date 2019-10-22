//Doreen He
//ID: 260761484


package comp557.a1;

import com.jogamp.opengl.GL2;



public class CharacterMaker {

	static public String name = "SpiderShowMan - Doreen He, 260761484";
	
	/** 
	 * Creates a character.
	 * @return root DAGNode
	 */
	static public GraphNode create() {
		// TODO: use for testing, and ultimately for creating a character​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
		// Here we just return null, which will not be very interesting, so write
		// some code to create a charcter and return the root node.
		
		
		return CharacterFromXML.load("/Users/doreenhe/eclipse-workspace/557A1/a1data/character.xml");
		
		
	}
}
