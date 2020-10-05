package boids;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Boids{

	JFrame frame=new JFrame();
	List<Boid> boids=new ArrayList<>();
	Canvas canvas=new Canvas(boids);
	Thread gameThread=new Thread(new GameThread(canvas, boids));
	int screenWidth;
	int screenHeight;

	public static void main(String[] args){
		new Boids();
	}

	public Boids(){
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		screenWidth=(int)ge.getMaximumWindowBounds().getWidth();
		screenHeight=(int)ge.getMaximumWindowBounds().getHeight();
		frame.setSize(screenWidth, screenHeight);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		for(int i=0; i<500; i++){
			boids.add(new Boid(screenWidth, screenHeight));
		}
		frame.add(canvas);
		frame.setVisible(true);
		gameThread.start();
	}

}
