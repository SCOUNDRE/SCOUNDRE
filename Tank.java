package com.csy;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

public abstract class Tank extends GameObject{
	//定义坦克变量（尺寸）
	public int width = 40;
	public int height = 50;
	//坦克速度
	private int speed= 3;
			//坦克初始方向（向上）
	protected	Direction direction = Direction.UP;
     //四个方向图片
	private String upImg;
	private String leftImg;
	private String rightImg;
	private String downImg;
	//攻击冷却状态
	private boolean attackCoolDown = true;
	//攻击冷却时间毫秒间隔1000ms
	private int attackCoolDownTime = 1000;
	
	//坦克类的构造函数
	public Tank(String img, int x, int y, GamePanel gamePanel,
			String upImg,String leftImg, String rightImg,String downImg) {
		super(img,x,y,gamePanel);
		//给四个方向赋值
		this.upImg = upImg;
		this.leftImg = leftImg;
		this.rightImg = rightImg;
		this.downImg = downImg;
	}
//添加移动方法
	public void leftward() {
		direction = Direction.LEFT;
		setImg(leftImg);
		this.x -= speed;
	}
	public void upward() {
		direction = Direction.UP;
		setImg(upImg);
		this.y -= speed;
	}
	public void rightward() {
		direction = Direction.RIGHT;
		setImg(rightImg);
		this.x += speed;
	}
	public void downward() {
		direction = Direction.DOWN;
		setImg(downImg);
		this.y += speed;
		
	}
	public void attack() {
		if(attackCoolDown) {
		Point p = this.getHeadPoint();
		Bullet bullet = new Bullet("images/bulletGreen.gif",p.x,p.y,this.gamePanel,direction);
		this.gamePanel.bulletlist.add(bullet);
		//线程开始
		new AttackCD().start();
	}
	}
		
		//新线程 （新建一个类继承Thread类）
		class AttackCD extends Thread{
	    public void run() {                     /*添加方法，命名为run*/
				//将攻击功能设置为冷却状态
				attackCoolDown = false;
				//休眠1秒
				try {
					Thread.sleep(attackCoolDownTime);
				}catch(Exception e) {
					e.printStackTrace();
				}
				//将攻击功能解除冷却状态
				attackCoolDown = true;
				//线程终止
				this.stop();
				
				
			}
		}
	
	public Point getHeadPoint() {
		switch(direction) {
		case LEFT:
			return new Point(x,y + height/2);
		case RIGHT:
			return new Point(x+width,y+height/2);
		case UP:
			return new Point(x+width/2,y);
		case DOWN:
			return new Point(x+width/2,y+height);
			default:
				return null;
		}
	}
	//添加setImg方法
	public void setImg(String img) {
		this.img = Toolkit.getDefaultToolkit().getImage(img);
	}
	@Override
	//tank类的方法
	public abstract void painSelf(Graphics g);
	@Override
	//直接继承给父类，然后在继承给子类
	public abstract Rectangle getRec();
}
