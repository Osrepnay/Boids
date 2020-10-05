package boids;

import java.util.List;
import java.util.stream.Collectors;

public class Boid{
	private double x;
	private double y;
	private double xChange;
	private double yChange;
	private int screenWidth;
	private int screenHeight;

	public Boid(int screenWidth, int screenHeight){
		this.screenWidth=screenWidth;
		this.screenHeight=screenHeight;
		x=Math.random()*screenWidth;
		y=Math.random()*screenHeight;
		xChange=Math.random()*10-5;
		yChange=Math.random()*10-5;
	}

	public void updateBoid(List<Boid> boidList, double tpf){
		List<Boid> allBoids=boidList.stream()
				.filter(boid -> boid.getX()!=x && boid.getY()!=y
						&& Math.sqrt(Math.pow(boid.getX()-x, 2)+Math.pow(boid.getY()-y, 2))<=100)
				.collect(Collectors.toList());
		double xChangeChange=0;
		double yChangeChange=0;
		xChangeChange+=(allBoids.stream().mapToDouble(boid -> boid.getX()).sum()/allBoids.size()-x)/35;
		yChangeChange+=(allBoids.stream().mapToDouble(boid -> boid.getY()).sum()/allBoids.size()-y)/35;
		double xChangeAvg=allBoids.stream()
				.mapToDouble(boid -> boid.getXChange())
				.sum()/allBoids.size();
		double yChangeAvg=allBoids.stream()
				.mapToDouble(boid -> boid.getYChange())
				.sum()/allBoids.size();
		xChangeChange+=(xChangeAvg-xChange)/25;
		yChangeChange+=(yChangeAvg-yChange)/25;
		List<Boid> avoidBoids=allBoids.stream()
				.filter(boid -> Math.sqrt(Math.pow(boid.getX()-x, 2)+Math.pow(boid.getY()-y, 2))<=25)
				.collect(Collectors.toList());
		for(Boid boid : avoidBoids){
			xChangeChange-=(boid.getX()-x)/8;
			yChangeChange-=(boid.getY()-y)/8;
		}
		if(x<100){
			xChangeChange+=(100-x);
		}
		if(y<100){
			yChangeChange+=(100-y);
		}
		if(x+100>screenWidth){
			xChangeChange-=(100-(screenWidth-x));
		}
		if(y+100>screenHeight){
			yChangeChange-=(100-(screenHeight-y));
		}
		/*if(x<0){
			x+=screenWidth;
		}
		if(y<0){
			y+=screenHeight;
		}
		if(x>=screenWidth){
			x-=screenWidth;
		}
		if(y>=screenHeight){
			y-=screenHeight;
		}*/
		xChange+=xChangeChange;
		yChange+=yChangeChange;
		x+=xChange*tpf/500;
		y+=yChange*tpf/500;
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public double getXChange(){
		return xChange;
	}

	public double getYChange(){
		return yChange;
	}

	public void setX(double x){
		this.x=x;
	}

	public void setY(double y){
		this.y=y;
	}

	public void setXChange(double xChange){
		this.xChange=xChange;
	}

	public void setYChange(double yChange){
		this.yChange=yChange;
	}

}
