package mx.edu.j2se.chavez.evaluation;

/**
 * Circle is a class that permit store the radius of a circle and get it is area.
 *
 * @author      Hector Chavez
 * @version     %I%, %G%
 * @since       1.0
*/
public class Circle {

    /** The radius of the circle */
    private double radius;

    /**
     * <p>This is the constructor to create a circle with a radius of one unit of length.
     * </p>
     * @since 1.0
     */
    Circle(){
        this.radius = 1;
    }

    /**
     * <p>This is the constructor to create a circle with a radius of one unit of length.
     * </p>
     * @param radius - The length of the radius.
     * @throws IllegalArgumentException - If the radius it is negative or equals to zero.
     * @since 1.0
     */
    Circle(double radius) throws IllegalArgumentException {
        if ( radius <= 0) {
            throw new IllegalArgumentException("Radius cannot be negative or zero.");
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * <p>Set the length of the circle's radius.
     * </p>
     * @param radius - The length of the radius.
     * @throws IllegalArgumentException - If the radius it is negative or equals to zero.
     * @since 1.0
     */
    public void setRadius(double radius) throws IllegalArgumentException {
        if ( radius <= 0) {
            throw new IllegalArgumentException("Radius has to be a positive number.");
        }
        this.radius = radius;
    }

    /**
     * <p>Returns the circle's area.
     * </p>
     * @return Circle's area
     * @since 1.0
     */
    public double getArea(){
        return this.radius * this.radius * Math.PI;
    }
}
