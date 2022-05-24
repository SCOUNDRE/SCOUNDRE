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
	//����˫����ͼƬ
	Image offScreemImage = null;
	//�����Ϸģʽ 0 ��Ϸδ��ʼ��1 ����ģʽ��2 ˫��ģʽ
     int state = 0;
	//��Ϸ�Ƿ�ʼ
	private boolean start = false;
	//��ʱ����
	 int a = 1;
	 //�ػ����
	 int count = 0;
	 //�����ɵ�������
	 int enemyCount = 0;
//���ڳߴ�
	 int width = 800;
	 int height = 610;
	 //���弯��
	    public List<Tank> tankList = new ArrayList<>();
	    public List<Bullet> bulletList = new ArrayList<Bullet>();
	    public List<Bot> botList1 = new ArrayList<Bot>();
	    
//ָ��ͼƬ
	 Image select = Toolkit.getDefaultToolkit().getImage("images/selecttank.gif");
//ָ���ʼ������
     int y=150;
     //��ϷԪ���б�
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
//������������
    public void lanuch() {
	//����
	setTitle("̹�˴�ս");
	//���ڳ�ʼ��С
	setSize(width,height);
	//ʹ��Ļ����
	setLocationRelativeTo(null);
	//��ӹر��¼�
	setDefaultCloseOperation(3);
	//�û����ܵ�����С
	setResizable(false);
	//ʹ���ڿɼ�
	setVisible(true);
	//��������Ӽ��̼�����
	this.addKeyListener(new GamePanel.KeyMonitor());
	//�ػ�
	while(true) {
		//��ӵ���̹��
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
			//�߳����� 1�� = 1000����
			Thread.sleep(25);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

//���ȴ���Paint��������
@ Override
public void paint (Graphics g) {
	//����������һ����С��ImageͼƬ
	if(offScreemImage == null) {
		offScreemImage = this.createImage(width, height);
	}
	//��ø�ͼƬ�Ļ���
	Graphics gImage = offScreemImage.getGraphics();
	//���ñ�����ɫ��Ĭ�Ϻ�ɫ��
	gImage.setColor(Color.gray);
	//Ϊ������ӱ���������ʵ�ľ��Σ�Ȼ���������������
	gImage.fillRect(0, 0, width, height);
	//�ı仭����ɫ�������ͱ�����ɫ���ֿ�
	gImage.setColor(Color.blue);
	//�ı����ִ�С����ʽ
	gImage.setFont(new Font ("����",Font.BOLD,50));
	//state = 0,��Ϸδ��ʼ��
	if(state == 0) {
		//�������
		gImage.drawString("ѡ����Ϸģʽ", 220, 100);
		gImage.drawString("����ģʽ", 220, 200);
		gImage.drawString("˫��ģʽ", 220, 300);
		gImage.drawString("��1��2ѡ��ģʽ,���س���ʼ��Ϸ", 0,400);
		//����ָ��
		gImage.drawImage(select, 160, y, null);
	}
	//state ==0/1,��Ϸ��ʼ;
	else if (state == 1 || state == 2) {
		gImage.drawString("��Ϸ��ʼ", 220, 100);
	if (state == 1) {
		gImage.drawString("����ģʽ", 220, 200);
	}
	else {
		gImage.drawString("˫��ģʽ", 220, 200);
		}
	//�����ϷԪ��
	playerOne.painSelf(gImage);
	//ѭ���ӵ��б�
	for(Bullet bullet: bulletlist) {
		bullet.paintSelft(gImage);
	}
	bulletList.removeAll(removeList);
	/*bot.painSelf(gImage);*/
	for(Bot bot:botList1) {
		bot.painSelf(gImage);
	}
	//�ػ�һ��
	count++;
	}//�����������ƺõ�ͼ���������Ƶ������Ļ�����
	g.drawImage(offScreemImage, 0, 0, null);
	
}
//��д���̼�����
private class KeyMonitor extends KeyAdapter{
//���¼���
	@Override
	public void keyPressed(KeyEvent e) {
		//���ؼ�ֵ�����������
		int key = e.getKeyCode();
		//ͨ��switch�������жϰ���1�ļ�ֵ
		switch(key) {
		case KeyEvent.VK_1://����1�ļ�ֵ
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
	//�ɿ�����
	@Override
	public void keyReleased(KeyEvent e) {
		playerOne.KeyRleased(e);
	}
	}
	//���main����
	public static void main(String[] args) {
		GamePanel gamePanel = new GamePanel();
		gamePanel.lanuch();
	}
  }



