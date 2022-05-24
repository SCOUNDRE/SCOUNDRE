package com.csy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bullet extends GameObject {
	//尺寸
	int width = 10;
	int hight = 10;
	//速度
	int speed = 7;
	//方向
   Direction direction;
	public Bullet(String img, int x, int y, GamePanel gamePanel,Direction direction) {
		super(img, x, y, gamePanel);
		this.direction = direction;
		// TODO Auto-generated constructor stub
	}
	public void leftward() {
		x -= speed;
	}
	public void rightward() {
		x += speed;
	}
	public void upward() {
		y -= speed;
	}
	public void downward() {
		y += speed;
	}
	//新写方法go
	public void go() {
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
	public void hitBot() {
		ArrayList<Bot> bots = this.gamePanel.botList;
		for(Bot bot: bots) {
			//我方子弹与敌方坦克检测碰撞
			if(this.gerRec().intersects(bot.getRec())) {
				this.gamePanel.botList.remove(bot);
				this.gamePanel.removeList.add(this);
				break;
			}
		}
	}
	@Override
	public void paintSelft(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, null);
		this.go();
		this.hitBot();
	}
	@Override
	public Rectangle gerRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}
	@Override
	public void painSelf(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return null;
	}

}
