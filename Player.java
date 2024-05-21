package PongV2;

import java.awt.Color;
import java.awt.Graphics;

public class Player implements Paddle{
	double y, yVel;
	boolean upAccel, downAccel; //up and down faster or slower
	final double GRAVITY = 0.94;
	int player, x;
	int score;
	
	public Player (int player){
		score = 0;
		upAccel = false; downAccel = false;
		this.player = player;
		//stores the player colour depending on whether it is player 1 or player 2
		resetPosition();
		if(player == 1)
			x = 20;
		else if(player == 2)
			x = 660;
		/*if the user is player 1 then it will make x 20 which is on the left side of the screen if the user is player two x will be on 
	  the right side of the screen with an x value of 660 the y value 210 is so as that the paddle starts in the centre of the y axis 
	  and the y velocity is zero so as that it is not moving during startup	*/
	}
	public void resetPosition()
	{
		y = 210;
		yVel = 0;
	}
	public void scored()
	{
		score++;
		//adds one point to the player who scored
	}
	public void resetScore()
	{
		score=0;
		//resets the score
	}
	public int getScore()
	{
		return score;
	}
	
	//methods from interface, the x and the y are the position of the paddle, player determines whether it is player one or player two
	public void draw(Graphics g) {
	    if(player==1); 
	    {
	    	g.setColor(Color.blue);
	    	//sets player 1 as blue
	    }
		if(player==2){
			g.setColor(Color.red);
			//sets player 2 as red
		}
		g.fillRect(x,  (int)y,  20,  80);
		
	}

	
	public void move() {
		if(upAccel){
			yVel -= 2; 
			//minus=2
		}
		else if (downAccel){
			yVel += 2;
			//plus=2
		}
		else if (!upAccel && !downAccel){
			yVel *= GRAVITY;
			// *=this is timing it by something less than one so over time gravity will decrease
			//!upAccel = not up accel and !downAccel means not down accel
			
		}
		
		
		if(yVel >=5)
			yVel =5;
		else if(yVel <= -5)
			yVel = -5;
		//this slows down the speed of the paddle when moving up and down
			
			y +=yVel; // adding the yVel over and over
			if (y < 0)
					y = 0;
			if (y > 420)
				 y = 420; //this keeps the paddle from going off the top and bottom of the window/screen 
	}

		
		public void setDownAccel(boolean input){
			upAccel = input;
		}
	
		public void setUpAccel(boolean input){
			downAccel = input;
		}
		
	public int getY() {
			return (int)y;
		
	}
	public int getX(){
			return (int)x;
	}

}
