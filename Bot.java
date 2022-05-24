package com.csy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bot extends Tank{
    int moveTime = 0;
    
	public Bot(String img, int x, int y, GamePanel gamePanel, String upImg, String leftImg, String rightImg,
			String downImg) {
		super(img, x, y, gamePanel, upImg, leftImg, rightImg, downImg);
		// TODO Auto-generated constructor stub
	}
	//获取随机方向
	public Direction getRandomDirection() {
		Random random = new Random();
		int rnum = random.nextInt(4);
		switch(rnum){
			case 0 :
				return Direction.LEFT;
			case 1:
				return Direction.RIGHT;
			case 2:
				return Direction.UP;
			case 3:
				return Direction.DOWN;
				default:
					return null;
		}
	}
	public void go() {
		if(moveTime >= 20) {
			direction = getRandomDirection();
			moveTime = 0;	
		}
		else {
			moveTime++;
		}
		switch(direction) {
		case LEFT:
			leftward();
			break;
		case RIGHT:
			rightward();
			break;
		case UP:
			upward();
			break;
		case DOWN:
			downward();
			break;
		}
	}

	@Override
	public void painSelf(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, null);
		go();
	}

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}

	@Override
	public void paintSelft(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle gerRec() {
		// TODO Auto-generated method stub
		return null;
	}

}
