public class ObjectLib {
	
	public ObjectLib() {
		
	}
	
	public ObjForm FlatSurf() {
		Vector base = new Vector(0.0, 0.0, 0.0);
		Triangle[] result = {new Triangle(base, base, base), new Triangle(base, base, base)};
		//Surface Half 1
		result[0].p1 = new Vector(0.0, 0.0, 0.0);
		result[0].p2 = new Vector(1.0, 0.0, 0.0);
		result[0].p3 = new Vector(0.0, 0.0, 1.0);
		//Surface Half 2
		result[1].p1 = new Vector(1.0, 0.0, 1.0);
		result[1].p2 = new Vector(1.0, 0.0, 0.0);
		result[1].p3 = new Vector(0.0, 0.0, 1.0);
		
		ObjForm newobj = new ObjForm(result, new Vector(0.0, 0.0, 0.0), 0.0);
		return newobj;
	}
	
	public ObjForm Box() {
		Vector base = new Vector(0.0, 0.0, 0.0);
		Triangle[] result = createTriarray();
		for(int i = 0; i < 11; i++) {
		//Point 1
		//Triangle 1
		result[0].p1 = new Vector(0.0, 0.0, 0.0);
		result[0].p2 = new Vector(1.0, 0.0, 0.0);
		result[0].p3 = new Vector(0.0, 0.0, 1.0);
		//Triangle 2
		result[1].p1 = new Vector(0.0, 0.0, 0.0);
		result[1].p2 = new Vector(1.0, 0.0, 0.0);
		result[1].p3 = new Vector(0.0, 1.0, 0.0);
		//Triangle 3
		result[2].p1 = new Vector(0.0, 0.0, 0.0);
		result[2].p2 = new Vector(0.0, 1.0, 0.0);
		result[2].p3 = new Vector(0.0, 0.0, 1.0);
		
		//Point 2
		//Triangle 1
		result[3].p1 = new Vector(1.0, 0.0, 1.0);
		result[3].p2 = new Vector(1.0, 0.0, 0.0);
		result[3].p3 = new Vector(0.0, 0.0, 1.0);
		//Triangle 2
		result[4].p1 = new Vector(1.0, 0.0, 1.0);
		result[4].p2 = new Vector(1.0, 0.0, 0.0);
		result[4].p3 = new Vector(1.0, 1.0, 1.0);
		//Triangle 3
		result[5].p1 = new Vector(1.0, 0.0, 1.0);
		result[5].p2 = new Vector(1.0, 1.0, 1.0);
		result[5].p3 = new Vector(0.0, 0.0, 1.0);

		//Point 3
		//Triangle 1
		result[6].p1 = new Vector(0.0, 1.0, 1.0);
		result[6].p2 = new Vector(0.0, 1.0, 0.0);
		result[6].p3 = new Vector(1.0, 1.0, 1.0);
		//Triangle 2
		result[7].p1 = new Vector(0.0, 1.0, 1.0);
		result[7].p2 = new Vector(0.0, 1.0, 0.0);
		result[7].p3 = new Vector(0.0, 0.0, 1.0);
		//Triangle 3
		result[8].p1 = new Vector(0.0, 1.0, 1.0);
		result[8].p2 = new Vector(1.0, 1.0, 1.0);
		result[8].p3 = new Vector(0.0, 0.0, 1.0);	
		
		//Point4
		//Triangle 1
		result[9].p1 = new Vector(1.0, 1.0, 0.0);
		result[9].p2 = new Vector(0.0, 1.0, 0.0);
		result[9].p3 = new Vector(1.0, 1.0, 1.0);
		//Triangle 2
		result[10].p1 = new Vector(1.0, 1.0, 0.0);
		result[10].p2 = new Vector(0.0, 1.0, 0.0);
		result[10].p3 = new Vector(1.0, 0.0, 0.0);
		//Triangle 3
		result[11].p1 = new Vector(1.0, 1.0, 0.0);
		result[11].p2 = new Vector(1.0, 1.0, 1.0);
		result[11].p3 = new Vector(1.0, 0.0, 0.0);	
		}
		
		ObjForm newobj = new ObjForm(result, base, 0.0);
		return newobj;
	}
	
	public Triangle[] createTriarray() {
		Vector base = new Vector(0.0, 0.0, 0.0);
		Triangle[] result = {new Triangle(base, base, base),
				new Triangle(base, base, base),
				new Triangle(base, base, base),
				new Triangle(base, base, base),
				new Triangle(base, base, base),
				new Triangle(base, base, base),
				new Triangle(base, base, base),
				new Triangle(base, base, base),
				new Triangle(base, base, base), 
				new Triangle(base, base, base), 
				new Triangle(base, base, base), 
				new Triangle(base, base, base)};
		return result;
	}
	
	public void objprint(ObjForm objtop) {
		int lenobj = objtop.polys.length;
		//Prints the number of triangles
		System.out.println(lenobj);
		//Prints out Each Triangles information
		for(int i = 0; i < lenobj; i++) {
			System.out.println("Triangle #" + i);
			System.out.println("Vector 1");
			System.out.println(objtop.polys[i].p1.xV);
			System.out.println(objtop.polys[i].p1.yV);
			System.out.println(objtop.polys[i].p1.zV);
			System.out.println("Vector 2");
			System.out.println(objtop.polys[i].p2.xV);
			System.out.println(objtop.polys[i].p2.yV);
			System.out.println(objtop.polys[i].p2.zV);
			System.out.println("Vector 3");
			System.out.println(objtop.polys[i].p3.xV);
			System.out.println(objtop.polys[i].p3.yV);
			System.out.println(objtop.polys[i].p3.zV);
		}
	}
}