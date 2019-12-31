# Transform Hierarchy System for Animated Characters

This project deploys a hierarchy of transformations to draw characters. The characters are a collection of rigid objects connected by various parametric joints, such as single axis rotary joints, spherical joints, etc. Unique characters can be made using the implemented elements. The transformation hierarchy is developed to pose the characters and ultimately create a short key frame animation. Each key frame is then interpolated to produce a continous animated clip.
 <img src="/spiderClip.gif">

## Instruction
The project uses Java and the JOGL openhl bindings.
Download the code and add the jogl, vecmath, and mintools jars to the root level of the project folder.
The utility files came from the 2.3.2 release folder (https://jogamp.org/wiki/index.php/Release_2.3.2) on the jogamp site. The fat jar contains all the necessary dlls and shared objects to work on all common platforms.

## Make An Animated Character

### 1. FreeJoint DAG Node
FreeJoint class extends the abstract GraphNode class. This will be for the root of your character allowing you can modify both translation and rotation of the root. 

There are 6 DoubleParameter member variables in the class for controlling the 6 degrees of rigid motion. The min and max values for each parameter can be adjusted (e.g., the translation parameters should have reasonable limits based on the size of your character. These parameters are exposed in the key frame posing interface by adding them to the GraphNode.dofs collection.


### 2. RotaryJoint Graph Node 
he rotary joint perform a translation within a parent frame to the location of the rotation axis of the joint, and a rotation about a single given axis, by an amount specified in a DoubleParameter. The constructor should let you specify the translation in the parent frame, and also let you set reasonable min and max values for the angle parameter. For instance, when creating an elbow joint, you'll be able to set the minimum and maximum elbow bend.


### 3. SphericalJoint Graph Node
A spherical joint node uses 3 Euler angles to specify the pose (i.e., orientation). Again, like in the RotaryJoint, apply this orientation change at some fixed position within the parent frame. Expose the three rotation parameters by adding them to the GraphNode.dofs collection. 


### 4. Geometry Graph Nodes  
Multiple geometry node is provided with specified non-uniform scaling, and in different colours. These geometry nodes will not likely have any parameters to interactively adjust their appearance, but feel free to design other GraphNode transformation nodes or something special to expose any appropriate parameters that will let you give your character special expressions you would like to animate.


### 5. Load Graph Nodes from XML
The code includes an xml parser. When the load from file checkbox is selected, pushing the recreate character button in the interface will rebuild your scene graph by loading the character from an xml file.


### 6. Create a Character
Create your character by editing the character.xml file.


### 7. Sample Pose 
Having finished creating your character, create a pose to show your character to the world. 

### 8. Key Frame Animation 
Create a simple key-frame animation to bring your character to life. 
Use the controls of simple key frame to set, delete, copy, and paste key poses. If you copy the pose at the beginning of the animation to the end then you can create an animation which loops. Use the save (and load buttons) to save your animation to edit again later.

One strategy for creating an animation is to create a number of keyframes with the basic motion of just a few joints, and then revisit each frame making additional adjustments and updating the keyframes with the new poses. Use the load and save buttons to save to the file data/keyposes.java!

### 9. Make a Movie Clip
Click the "animate" checkbox on the keyframe controls, and then use the "Canvas Recorder Controls" record button to save the animating canvas to png files. You will need to create a stills folder in your project directory. Use a movie making tool (e.g., mencoder, virtual dub, etc.) to create a high quality video file from the images. 

