import java.io.IOException;


class Mainframe {
	//Camera Multiplier
	public static int mult = 100;
	
	public static void main(String[] args) {
		
		//Now to start the RENDERING
		Render mainf = new Render();
		mainf.appLoop(mult);
		//Testing phase try catch to keep program open
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}