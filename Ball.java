package PongV2;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	public double xVel, yVel, x, y;
	double vmodifier = 0.1d;

	public Ball() {
		x = 350;
		y = 250;
		xVel = getRandomSpeed() * getRandomDirection();
		yVel = getRandomSpeed() * getRandomDirection();
	}

	public double getRandomSpeed() {
		return (Math.random() * 3 + 2);
	}

	public int getRandomDirection() {
		int rand = (int) (Math.random() * 2);
		return rand == 1 ? 1 : -1;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x - 10, (int)y - 10, 20, 20);
	}

	 public void checkPaddleCollision(Paddle p1, Paddle p2) {
	        // Check collision with the left paddle (p1)
	        if (x <= p1.getX() + 20 && x >= p1.getX() && y >= p1.getY() && y <= p1.getY() + 80) {
	            xVel = Math.abs(xVel); // Reverse x velocity
	        }

	        // Check collision with the right paddle (p2)
	        if (x >= p2.getX() - 20 && x <= p2.getX() && y >= p2.getY() && y <= p2.getY() + 80) {
	            xVel = -Math.abs(xVel); // Reverse x velocity
	        }
	    }


	public void move() {
		x += xVel;
		y += yVel;

		if (y < 10) {
			yVel = Math.abs(yVel);
		}
		if (y > 490) {
			yVel = -Math.abs(yVel);
		}
	}

	public int getX() {
		return (int)x;
	}

	public int getY() {
		return (int)y;
	}

	public void resetPosition() {
		this.x = 350;
		this.y = 250;
		this.xVel = getRandomSpeed() * getRandomDirection();
		this.yVel = getRandomSpeed() * getRandomDirection();
	}
}
