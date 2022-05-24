package com.csy;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

public abstract class Tank extends GameObject{
	//����̹�˱������ߴ磩
	public int width = 40;
	public int height = 50;
	//̹���ٶ�
	private int speed= 3;
			//̹�˳�ʼ�������ϣ�
	protected	Direction direction = Direction.UP;
     //�ĸ�����ͼƬ
	private String upImg;
	private String leftImg;
	private String rightImg;
	private String downImg;
	//������ȴ״̬
	private boolean attackCoolDown = true;
	//������ȴʱ�������1000ms
	private int attackCoolDownTime = 1000;
	
	//̹����Ĺ��캯��
	public Tank(String img, int x, int y, GamePanel gamePanel,
			String upImg,String leftImg, String rightImg,String downImg) {
		super(img,x,y,gamePanel);
		//���ĸ�����ֵ
		this.upImg = upImg;
		this.leftImg = leftImg;
		this.rightImg = rightImg;
		this.downImg = downImg;
	}
//����ƶ�����
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
		//�߳̿�ʼ
		new AttackCD().start();
	}
	}
		
		//���߳� ���½�һ����̳�Thread�ࣩ
		class AttackCD extends Thread{
	    public void run() {                     /*��ӷ���������Ϊrun*/
				//��������������Ϊ��ȴ״̬
				attackCoolDown = false;
				//����1��
				try {
					Thread.sleep(attackCoolDownTime);
				}catch(Exception e) {
					e.printStackTrace();
				}
				//���������ܽ����ȴ״̬
				attackCoolDown = true;
				//�߳���ֹ
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
	//���setImg����
	public void setImg(String img) {
		this.img = Toolkit.getDefaultToolkit().getImage(img);
	}
	@Override
	//tank��ķ���
	public abstract void painSelf(Graphics g);
	@Override
	//ֱ�Ӽ̳и����࣬Ȼ���ڼ̳и�����
	public abstract Rectangle getRec();
}
