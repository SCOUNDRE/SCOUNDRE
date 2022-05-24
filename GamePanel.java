package com.csy;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class GamePanel extends JFrame {
	
	public static Object gamePanel;
	//定义双缓存图片
	Image offScreemImage = null;
	//添加游戏模式 0 游戏未开始，1 单人模式，2 双人模式
     int state = 0;
	//游戏是否开始
	private boolean start = false;
	//临时变量
	 int a = 1;
	 //重绘次数
	 int count = 0;
	 //已生成敌人数量
	 int enemyCount = 0;
//窗口尺寸
	 int width = 800;
	 int height = 610;
	 //物体集合
	    public List<Tank> tankList = new ArrayList<>();
	    public List<Bullet> bulletList = new ArrayList<Bullet>();
	    public List<Bot> botList1 = new ArrayList<Bot>();
	    
//指针图片
	 Image select = Toolkit.getDefaultToolkit().getImage("images/selecttank.gif");
//指针初始纵坐标
     int y=150;
     //游戏元素列表
     ArrayList<Bullet> bulletlist = new ArrayList<Bullet>();
     ArrayList<Bot> botList = new ArrayList<Bot>();
     ArrayList<Bullet> removeList = new ArrayList<Bullet>();
     PlayerOne playerOne = new PlayerOne("images/p1tankU.gif",125,510,this,
    		 "images/p1tankU.gif","images/p1tankL.gif",
    		 "images/p1tankR.gif","images/p1tankD.gif");
     //bot
     /*Bot bot = new Bot("images/enemy1U.gif",500,110,this,
    		 "images/enemy1U.gif","images/enemy1L.gif",
    		 "images/enemy1R.gif","images/enemy1D.gif");*/
//窗口启动方法
    public void lanuch() {
	//标题
	setTitle("坦克大战");
	//窗口初始大小
	setSize(width,height);
	//使屏幕居中
	setLocationRelativeTo(null);
	//添加关闭事件
	setDefaultCloseOperation(3);
	//用户不能调整大小
	setResizable(false);
	//使窗口可见
	setVisible(true);
	//给窗口添加键盘监视器
	this.addKeyListener(new GamePanel.KeyMonitor());
	//重绘
	while(true) {
		//添加电脑坦克
		if(count % 100 == 1 && enemyCount < 10) {
			Random random = new Random();
			int rnum = random.nextInt(800);
			botList1.add(new Bot("images/enemy1U.gif",rnum,110,this, 
					   "images/enemy1U.gif","images/enemy1L.gif", 
					   "images/enemy1R.gif","images/enemy1D.gif"));
			enemyCount ++;
		}
		repaint();
		try {
			//线程休眠 1秒 = 1000毫秒
			Thread.sleep(25);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

//首先创建Paint（）方法
@ Override
public void paint (Graphics g) {
	//创建和容器一样大小的Image图片
	if(offScreemImage == null) {
		offScreemImage = this.createImage(width, height);
	}
	//获得该图片的画布
	Graphics gImage = offScreemImage.getGraphics();
	//设置背景颜色（默认黑色）
	gImage.setColor(Color.gray);
	//为窗口添加背景（绘制实心矩形，然后填充整个画布）
	gImage.fillRect(0, 0, width, height);
	//改变画笔颜色，让它和背景颜色区分开
	gImage.setColor(Color.blue);
	//改变文字大小和样式
	gImage.setFont(new Font ("仿宋",Font.BOLD,50));
	//state = 0,游戏未开始；
	if(state == 0) {
		//添加文字
		gImage.drawString("选择游戏模式", 220, 100);
		gImage.drawString("单人模式", 220, 200);
		gImage.drawString("双人模式", 220, 300);
		gImage.drawString("按1，2选择模式,按回车开始游戏", 0,400);
		//绘制指针
		gImage.drawImage(select, 160, y, null);
	}
	//state ==0/1,游戏开始;
	else if (state == 1 || state == 2) {
		gImage.drawString("游戏开始", 220, 100);
	if (state == 1) {
		gImage.drawString("单人模式", 220, 200);
	}
	else {
		gImage.drawString("双人模式", 220, 200);
		}
	//添加游戏元素
	playerOne.painSelf(gImage);
	//循环子弹列表
	for(Bullet bullet: bulletlist) {
		bullet.paintSelft(gImage);
	}
	bulletList.removeAll(removeList);
	/*bot.painSelf(gImage);*/
	for(Bot bot:botList1) {
		bot.painSelf(gImage);
	}
	//重绘一次
	count++;
	}//将缓存区绘制好的图形整个绘制到容器的画布中
	g.drawImage(offScreemImage, 0, 0, null);
	
}
//编写键盘监视器
private class KeyMonitor extends KeyAdapter{
//按下键盘
	@Override
	public void keyPressed(KeyEvent e) {
		//返回键值（输出按键）
		int key = e.getKeyCode();
		//通过switch方法来判断按键1的键值
		switch(key) {
		case KeyEvent.VK_1://按键1的键值
			  if(!start){
				a=1;
		        y=150;
			  }
		break;
		case KeyEvent.VK_2:
			  if(!start){
		a=2;
		y=250;
			  }
		break;
		case KeyEvent.VK_ENTER:
			tankList.add(playerOne);
		state = a;
		start = true;
		break;
		default:
		playerOne.keyPressed(e);
		break;
		}
		System.out.print(e.getKeyChar());
	}
	//松开键盘
	@Override
	public void keyReleased(KeyEvent e) {
		playerOne.KeyRleased(e);
	}
	}
	//添加main方法
	public static void main(String[] args) {
		GamePanel gamePanel = new GamePanel();
		gamePanel.lanuch();
	}
  }



