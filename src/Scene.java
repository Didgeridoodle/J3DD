public class Scene {
	public Object terrain;
	public Object[] objlist;
	public boolean[] inuse;
	public int objindex = 10;
	
	public Scene(Object terrset) {
		terrain = terrset;
		inuse = new boolean[objindex];
		objlist = new Object[objindex];
	}
	
	public int addobj(Object addto, Vector position) {
		//Changes object's position than adds it to the object list and returns it's index
		addto.pos = position;
		objlist[findfree()] = addto;
		return findfree();
	}
	
	public void killobj(int index) {
		inuse[index] = false;
	}
	
	public int findfree() {
		int found;
		for(int i = 0; i < objindex; i++) {
			if(inuse[i]==false) {
				found = i;
				return found;
			}
		}
		found = 0;
		return found;
	}
	
}