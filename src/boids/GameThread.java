package boids;

import java.util.List;

public class GameThread implements Runnable{

	private Canvas canvas;
	private List<Boid> boids;
	private long lastPaint=System.currentTimeMillis();

	public GameThread(Canvas canvas, List<Boid> boids){
		this.canvas=canvas;
		this.boids=boids;
	}

	@Override
	public void run(){
		while(true){
			long currTime=System.currentTimeMillis();
			double tpf=currTime-lastPaint;
			lastPaint=currTime;
			boids.stream().forEach(x -> x.updateBoid(boids, tpf));
			canvas.repaint();
			try{
				Thread.sleep(15);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
