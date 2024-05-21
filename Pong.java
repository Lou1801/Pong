package PongV2;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends Applet implements Runnable, KeyListener{
		//this is our applet which opens up a window for the game
		
		final int WIDTH = 700, HEIGHT = 500;
		Thread thread;
		public Player p1;
		public Player p2;
		Ball b1;
		boolean gameStarted,gameOver=false;
		Graphics gfx;
		Image img;
	
		public void init(){
			this.resize(WIDTH, HEIGHT); //this resizes the window to make it larger for the user
			 
			 this.addKeyListener(this);
			 gameStarted = false;
			 p1 = new Player(1);
			 b1 = new Ball(); 
			 p2 = new Player(2);
			 img = createImage(WIDTH, HEIGHT);
			 gfx = img.getGraphics();
			 thread = new Thread(this);
			 thread.start();
		}
	
		public void paint(Graphics g){
			gfx.setFont(new Font("Arial",Font.BOLD,22));
			//set the font and size for the starting screen, the score and the game over screen
			gfx.setColor(Color.black);
			gfx.fillRect(0, 0, WIDTH, HEIGHT); //this is our backdrop, it sets the background color to black as we will be using white for the paddles
			if(gameOver){
				gfx.setColor(Color.green);
				gfx.drawString("Game Over", 290, 250);
				//draw score
				String score = p1.getScore() + "    " + p2.getScore();
				//presenting the current score the quotation marks are to space out the two numbers which represent the score
				gfx.drawString(score, 320, 450);
			}
			else{
			p1.draw(gfx);
			p2.draw(gfx);
			b1.draw(gfx);
			}
			if(!gameStarted){
				gfx.setColor(Color.white);
				gfx.drawString("Pong", 320, 100);
				gfx.drawString("Press Enter to Begin", 250, 130);
			}
			g.drawImage(img, 0, 0, this);
			
		}
		public void update(Graphics g){
			if(b1.getX() <-10){
				gameOver=true;
				gameStarted=false;
				p2.scored();
				b1.x=350;
			}else if(b1.getX() > 710){
				gameOver=true;
				gameStarted=false;
				p1.scored();
				b1.x=350;
			}
			paint(g);
			/*this updates the game by checking the position of the ball about every millisecond and if the ball's x position is 
			 -10 then player 2 gets a point, if the x position is 710 that means that player 1 has scored. If either player scores
			 then the game is stopped as gameOver is now true and the game will not start because gameStarted is false*/
		}
	
		public void run() {
			while(true){
				if(gameStarted){
				p1.move();
				p2.move();
				b1.move();
				b1.checkPaddleCollision(p1, p2);
				}
				    
					repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
						//this is my loop it is what keeps the game going it will pause it for 10milliseconds 
						//limits the framerate
					}
			}
			
			
		}
	
			
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				//VK means virtual keyboard up on the key being pressed
				p2.setUpAccel(true);
				//this is for the down key
			}
			else if (e.getKeyCode() == KeyEvent.VK_UP){
				p2.setDownAccel(true);
				//this is for the up key
			} 
			else if(e.getKeyCode() == KeyEvent.VK_ENTER){
				gameStarted = true;
				gameOver = false;
				p1.resetPosition();
				p2.resetPosition();
				b1 = new Ball();
			}
			else if(e.getKeyCode() == KeyEvent.VK_S){
				//VK means virtual keyboard up on the key being pressed
				p1.setUpAccel(true);
				//this is for the down key
			}
			else if (e.getKeyCode() == KeyEvent.VK_W){
				p1.setDownAccel(true);
				//this is for the up key
			} else if (e.getKeyCode() == KeyEvent.VK_F){
				b1.xVel = Math.abs(b1.xVel)*5;
				b1.yVel = (b1.yVel)*5;
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT){
				b1.xVel = -Math.abs(b1.xVel)*5;
				b1.yVel = (b1.yVel)*5;
			}
		}
		
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				p2.setUpAccel(false);
				
				//VK means virtual keyboard up on the key being pressed
			}
			else if (e.getKeyCode() == KeyEvent.VK_UP){
				p2.setDownAccel(false);
		
			}
			else if(e.getKeyCode() == KeyEvent.VK_S){
				p1.setUpAccel(false);
				
				//VK means virtual keyboard up on the key being pressed
			}
			else if (e.getKeyCode() == KeyEvent.VK_W) {
				p1.setDownAccel(false);
			
			}
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {	
			// TODO Auto-generated method stub
		
		}
}
		
