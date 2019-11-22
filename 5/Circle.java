public class Circle {

	private Vector center;
	private double radius;

	public Circle(Vector center, double radius) {
		this.center = center.copy();
		if (center.getX() < 0 || center.getY() < 0) {
			throw new IllegalArgumentException("Invalid arguments : x < 0 or y < 0");
		}
		if (radius < 0) {
			throw new IllegalArgumentException("Negative radius");
		}
		this.center = center;
		this.radius = radius;
	}

	public Vector getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	public String toString() {
		return ("Circle(center = " + center + ", radius = " + radius + ")");
	}
}