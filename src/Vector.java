
public class Vector {
	public double xV;
	public double yV;
	public double zV;
	
	public Vector(double x, double y, double z) {
		this.xV = x;
		this.yV = y;
		this.zV = z;
	}
	
	public void Scalar(double scale) {
		xV = xV*scale;
		yV = yV*scale;
		zV = zV*scale;
	}
	
	public void assignlist(double[] xyz) {
		xV = xyz[0];
		yV = xyz[1];
		zV = xyz[2];
	}
	
	public double[] getlist() {
		double[] result = new double[3];
		result[0] = this.xV;
		result[1] = this.yV;
		result[2] = this.zV;
		return result;
	}
}