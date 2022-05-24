package com.csy;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
public abstract class GameObject {
	//添加游戏父类的变量
//图片
	public Image img;
//坐标	
	public int x;
	public int y;
	//游戏元素的宽
	public int width;
    //游戏元素的高
	public int height;
	//游戏元素的移动速度
	public int speed;
    //游戏元素的移动方向
	public Direction direction;
    //引入主界面
	 public GamePanel gamePanel;
//编写游戏父类的构造函数
	public GameObject(String img, int x, int y , GamePanel gamePanel) {
		//给变量赋值(类型是image，参数为string类型)
		this.img =Toolkit.getDefaultToolkit().getImage(img);
		this.x =x;
		this.y =y;
		this.gamePanel = gamePanel;
	}
	
	//绘制方法（游戏元素需要的共同方法）
	public abstract void paintSelft(Graphics g);
	//返回自身矩形的方法
	public abstract Rectangle gerRec();
	public abstract void painSelf(Graphics g);
	public abstract Rectangle getRec();
	public Rectangle getRec1() {
		// TODO Auto-generated method stub
		return null;
	}
}
