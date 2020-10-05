package boids;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JComponent;

public class Canvas extends JComponent{

	private List<Boid> boids;

	public Canvas(List<Boid> boids){
		this.boids=boids;
	}

	@Override
	public void paint(Graphics g){
		Graphics2D g2d=(Graphics2D)g;
		for(Boid boid : boids){
			int[] triangleX=new int[3];
			int[] triangleY=new int[3];
			double boidAngle=Math.toDegrees(Math.atan2(boid.getYChange(), boid.getXChange()));
			triangleX[0]=(int)(Math.cos(Math.toRadians(boidAngle))*8+boid.getX());
			triangleY[0]=(int)(Math.sin(Math.toRadians(boidAngle))*8+boid.getY());
			triangleX[1]=(int)(Math.cos(Math.toRadians(boidAngle+90))*3+boid.getX());
			triangleY[1]=(int)(Math.sin(Math.toRadians(boidAngle+90))*3+boid.getY());
			triangleX[2]=(int)(Math.cos(Math.toRadians(boidAngle-90))*3+boid.getX());
			triangleY[2]=(int)(Math.sin(Math.toRadians(boidAngle-90))*3+boid.getY());
			g2d.fillPolygon(triangleX, triangleY, 3);
		}
		Toolkit.getDefaultToolkit().sync();
	}

}
