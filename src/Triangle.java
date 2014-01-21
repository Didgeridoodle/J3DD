public class Triangle {
	public Vector p1;
	public Vector p2;
	public Vector p3;
	
	public Triangle(Vector point1, Vector point2, Vector point3) {
		p1 = point1;
		p2 = point2;
		p3 = point3;
	}
	
	public void assignlist(Vector[] vvv) {
		p1 = vvv[0];
		p2 = vvv[1];
		p3 = vvv[2];
	}
	
	public Vector[] getlist() {
		Vector[] veclis = {this.p1, this.p2, this.p3};
		return veclis;
	}
	
	public void IncVec(double scale) {
		p1.Scalar(scale);
		p2.Scalar(scale);
		p3.Scalar(scale);
	}
	
}