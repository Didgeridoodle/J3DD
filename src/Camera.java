public class Camera {
	public Vector pos;
	public Vector ypr;
	public Vector imgplane;
	
	public Camera(Vector position, Vector rotation) {
		pos = position;
		ypr = rotation;
		imgplane = new Vector(-3.2,-2.4,0.50);
	}
}