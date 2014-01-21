import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;


public class Render extends Canvas implements MouseListener, MouseMotionListener {
	//Other cool Stuff
	Camera obvs;
	Scene baseScene;
	ObjectLib objLib = new ObjectLib();
	int[] actors;
	
	//Window and Graphics
	JFrame window;
	Image backbuffer;
	Image Flash;
	Graphics backg;
	
	public Render() {
		window = new JFrame("Testing Renders");
		window.setSize(640,480);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new GridLayout());
		window.add(this);
		window.setVisible(true);
		backbuffer = createImage(640,480);
		Flash = createImage(640,480);
		backg = backbuffer.getGraphics();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void appLoop(double mult) {
		//Loads a flat surface as the base terrain
		baseScene = new Scene(objLib.FlatSurf());
		//Increases the terrains size by a multiple of 5

		//Initializes an array of objects equal to the size the scene allows
		actors = new int[baseScene.objindex];
		//Adds a prebuilt box object from the object library
		actors[0] = baseScene.addobj(objLib.Box(), new Vector(0.0, 0.0, 0.0));
		actors[0] = baseScene.addobj(objLib.Box(), new Vector(0.0, 0.0, 0.0));
		//Camera Creation time, creates a camera at the point 3, 3, 3
		obvs = new Camera(new Vector(1.0, 1.0, -5.0), new Vector(0.0, 0.0, 0.0));
		
		//err check
		baseScene.objlist[0].SizeInc(2.0);
		//err check
		
		boolean done = false;
		double bouncex = 0.004;
		double bouncey = 0.004;
		while(done==false) {
			if(obvs.pos.xV>=6.0) {
				bouncex = -0.0001;
			}
			else if(obvs.pos.xV<=-6.0) {
				bouncex = 0.0001;
			}
			if(obvs.pos.yV>=4.0) {
				bouncey = -0.0003;
			}
			else if(obvs.pos.yV<=-2.5) {
				bouncey = 0.0002;
			}
			obvs.pos.xV += bouncex;
			obvs.pos.yV += bouncey;
			this.drawScene(obvs, actors, mult);
			repaint();
		}
	}
	
	public void drawScene(Camera eyepers, int[] actors, double mult) {
		//First Draw the terrain, it's the simplest pretty much
		int terTricnt = baseScene.terrain.polys.length;
		
		for(int i = 0; i < terTricnt; i++) {
			int[] xpntarr = new int[3];
			int[] ypntarr = new int[3];
			Vector[] vecsplit = {baseScene.terrain.polys[i].p1, baseScene.terrain.polys[i].p2, baseScene.terrain.polys[i].p3};
			
			for(int p = 0; p < 3; p++) {
				int[] pixelget = pixget(eyepers, vecsplit[p], mult);
				xpntarr[p] = pixelget[0];
				ypntarr[p] = pixelget[1];
			}
			backrend(xpntarr, ypntarr, Color.green);
		}
		
		for(int k = 0; k < 2; k++){
			int terObjcnt = baseScene.objlist[actors[k]].polys.length;
			for(int i = 0; i < terObjcnt; i++) {
				int[] xpntarr = new int[3];
				int[] ypntarr = new int[3];
				Vector[] vecsplit = baseScene.objlist[actors[k]].polys[i].getlist();
				
				for(int p = 0; p < 3; p++) {
					int[] pixelget = pixget(eyepers, vecsplit[p], mult);
					xpntarr[p] = pixelget[0];
					ypntarr[p] = pixelget[1];
				}
				backrend(xpntarr, ypntarr, Color.red);
			}
		}
	}
	
	public void backrend(int[] p1, int[] p2, Color use) {
		backg.setColor(use);
		backg.drawLine(p1[0], p2[0], p1[1], p2[1]);
		backg.drawLine(p1[0], p2[0], p1[2], p2[2]);
		backg.drawLine(p1[1], p2[1], p1[2], p2[2]);
	}
	
	public int[] pixget(Camera eyespy, Vector input, double mult) {
		int[] xny = new int[2];
		double holder = 0.0;
		
		//BEWARE OF MATH
		double[] rxyz = eyespy.ypr.getlist();
		//Jibbery Joo
		//X sine cosine
		double sinx = Math.cos(rxyz[0]);
		double cosx = Math.cos(rxyz[0]);
		//Y sine cosine
		double siny = Math.cos(rxyz[1]);
		double cosy = Math.cos(rxyz[1]);
		//Z sine cosine
		double sinz = Math.cos(rxyz[2]);
		double cosz = Math.cos(rxyz[2]);
		double[] transx = {cosy*cosz, (sinx*siny*sinz)-(cosx*cosz), (cosx*siny*cosz)+(sinx*sinz)};
		double[] transy = {cosy*cosz, (sinx*siny*sinz)+(cosx*cosz), (cosx*siny*sinz)+(sinx*cosz)};
		double[] transz = {-siny, sinx*cosy, cosx*cosy};
		//Yet more transformation
		double xdisp = input.xV - eyespy.pos.xV;
		double ydisp = input.yV - eyespy.pos.yV;
		double zdisp = input.zV - eyespy.pos.zV;
		Vector fixed = new Vector(0.0, 0.0, 0.0);
		for(int i = 0; i < 3; i++) {
			fixed.xV += xdisp*transx[i];
			fixed.yV += ydisp*transy[i];
			fixed.zV += zdisp*transz[i];
		}
		
		if(fixed.zV<0.5) {
			fixed.zV = 0.5;
		}
		
		//X Coordinate Calc
		holder = (((fixed.xV*eyespy.imgplane.zV)/fixed.zV)+3.2)*mult;
		xny[0] = (int)holder;
		//Y Coordinate Calc
		holder = (((fixed.yV*eyespy.imgplane.zV)/fixed.zV)+2.4)*mult;
		xny[1] = (int)holder;
		return xny;
	}
	
	public void update(Graphics g) {
		backg.drawImage(Flash, 0, 0, this);
		g.drawImage(backbuffer, 0, 0, this);
	}
	
	public void paint(Graphics g) {
		update(g);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		e.consume();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}