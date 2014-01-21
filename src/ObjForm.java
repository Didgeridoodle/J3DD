public class ObjForm {
	public Triangle[] polys;
	public Vector pos;
	public double rot;
	public int len;
	
	public ObjForm(Triangle[] polygon, Vector position, double rotation) {
		polys = polygon;
		pos = position;
		rot = rotation;
		len = polys.length;
	}
	
	public void SizeInc(double scale) {
		for(int i = 0; i < len; i++) {
			polys[i].IncVec(scale);
		}
	}
}