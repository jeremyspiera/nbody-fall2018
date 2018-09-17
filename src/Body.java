public class Body {
private double myXPos;
private double myYPos;
private double myXVel;
private double myYVel;
private double myMass;
private String myFileName;


/**
 * @param xp initial x position
 * @param yp initial y position
 * @param xv initial x velocity
 * @param yv initial y velocity
 * @param mass of object
 * @param filename of image for object animation
 */
public Body (double xp, double yp, double xv, double yv, double mass, String filename) {
	myXPos = xp;
	myYPos = yp;
	myXVel = xv;
	myYVel = yv;
	myMass = mass;
	myFileName = filename;
}

/**
 * Copy constructor: copy instance variables from one 
 * body to this body
 * @param b used to initialize this body
 */

public Body(Body b) {
	this(b.getX(),b.getY(),b.getXVel(),b.getYVel(),b.getMass(),b.getName());
}

/**
 * give current x position
 */
public double getX() {
	return myXPos;
}
/**
 * give current y position
 */
public double getY() {
	return myYPos;
}
/**
 * give current x velocity
 */
public double getXVel() {
	return myXVel;
}
/**
 * give current y velocity
 */
public double getYVel() {
	return myYVel;
}
/**
 * give mass
 */
public double getMass() {
	return myMass;
}

public String getName() {
	return myFileName;
}
/**
 * @param b the other body to which distance is calculated
 * @return the distance between this body and b
 */
public double calcDistance(Body b) {
	return Math.sqrt(Math.pow(myXPos-b.getX(),2) + Math.pow(myYPos - b.getY(),2));
}
	/**
	 * calculate the force exerted by another body on this body
	 * @param b the other body affecting you
	 * @return the force
	 */
public double calcForceExertedBy(Body b) {
	final double g = Math.pow(10, -11) * 6.67;
	return (g * myMass * b.getMass())/(Math.pow(this.calcDistance(b),2));
}
/**
 * calculate the force specifically on the x axis of the body
 * @param b the body affecting you
 * @return a double of the force
 */
public double calcForceExertedByX(Body b){
	return -1 * calcForceExertedBy(b) * (myXPos - b.getX())/calcDistance(b);
}
/**
 * calculate the force specifically on the y axis of the body
 * @param b the other body affecting you
 * @return a double of the force
 */
public double calcForceExertedByY(Body b){
	return -1 * calcForceExertedBy(b) * (myYPos - b.getY())/calcDistance(b);
}
/**
 * calculate the net force of all bodies on the given body
 * but only along x axis
 * @param bodies is a list of all bodies in the field
 * @double tot compiles the sum and
 * @return final number
 */
public double calcNetForceExertedByX(Body[] bodies) {
	double tot = 0;
	for (Body body:bodies) {
		if (! body.equals(this)) {
		tot += this.calcForceExertedByX(body);}
	}
	return tot;
}
/**
 * calculate the net force of all bodies on the given body
 * but only along y axis
 * @param bodies is a list of all bodies in the field
 * @double tot compiles the sum and
 * @return final number
 */
public double calcNetForceExertedByY(Body[] bodies) {
	double tot = 0;
	for (Body body:bodies) {
		if (! body.equals(this)) {
		tot += this.calcForceExertedByY(body);}
	}
	return tot;
}
/**
 * updates each variable
 * @param deltaT is time in between last update
 * @param xForce is the total forces on the x axis
 * @param yForce is the total forces on the y axis
 */
public void update(double deltaT, double xForce, double yForce) {
	double accelerationX = xForce/myMass;
	double accelerationY = yForce/myMass;
	double nvx = myXVel + deltaT*accelerationX;
	double nvy = myYVel + deltaT*accelerationY;
	double nx = myXPos + deltaT*nvx;
	double ny = myYPos + deltaT*nvy;
	myXPos = nx;
	myYPos = ny;
	myXVel = nvx;
	myYVel = nvy;
			
	
	
}
}
