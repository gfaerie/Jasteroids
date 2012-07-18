package se.faerie.jasteroids.graphics.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


public class StarBackgroundRenderer implements BackgroundRenderer {

	private Collection<BackgroundStar> stars;
	private List<Integer> twinkleIds = new ArrayList<Integer>();
	
	
	public StarBackgroundRenderer(int numberOfStars, GameRenderingInfo gameRenderingInfo){
		stars = new ArrayList<BackgroundStar>(numberOfStars);
		Random random = new Random();
		for(int i=0;i<numberOfStars;i++){
			BackgroundStar backgroundStar = new BackgroundStar();
			backgroundStar.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			backgroundStar.setRadius(2);
			backgroundStar.setTwinkleRadius(4);
			backgroundStar.setXPos((int) (gameRenderingInfo.getXScreenSize()*random.nextFloat()));
			backgroundStar.setYPos((int) (gameRenderingInfo.getYScreenSize()*random.nextFloat()));
		
			stars.add(backgroundStar);
		}
		
	}
	
	@Override
	public void renderBackGround(Graphics2D graphics) {
		for(BackgroundStar star : stars){
			graphics.setColor(star.getColor());
			graphics.fillOval(star.getXPos(), star.getYPos(), star.getRadius(), star.getRadius());
			
			graphics.drawLine(star.getXPos(), star.getYPos(), star.getXPos()+star.getTwinkleRadius(), star.getYPos());
			graphics.drawLine(star.getXPos(), star.getYPos(), star.getXPos(), star.getYPos()+star.getTwinkleRadius());
			graphics.drawLine(star.getXPos()-star.getTwinkleRadius(), star.getYPos(), star.getXPos(), star.getYPos());
			graphics.drawLine(star.getXPos(), star.getYPos()-star.getTwinkleRadius(),star.getXPos(), star.getYPos());
		}
	}

}
