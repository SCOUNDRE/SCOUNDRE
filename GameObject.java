package com.csy;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
public abstract class GameObject {
	//�����Ϸ����ı���
//ͼƬ
	public Image img;
//����	
	public int x;
	public int y;
	//��ϷԪ�صĿ�
	public int width;
    //��ϷԪ�صĸ�
	public int height;
	//��ϷԪ�ص��ƶ��ٶ�
	public int speed;
    //��ϷԪ�ص��ƶ�����
	public Direction direction;
    //����������
	 public GamePanel gamePanel;
//��д��Ϸ����Ĺ��캯��
	public GameObject(String img, int x, int y , GamePanel gamePanel) {
		//��������ֵ(������image������Ϊstring����)
		this.img =Toolkit.getDefaultToolkit().getImage(img);
		this.x =x;
		this.y =y;
		this.gamePanel = gamePanel;
	}
	
	//���Ʒ�������ϷԪ����Ҫ�Ĺ�ͬ������
	public abstract void paintSelft(Graphics g);
	//����������εķ���
	public abstract Rectangle gerRec();
	public abstract void painSelf(Graphics g);
	public abstract Rectangle getRec();
	public Rectangle getRec1() {
		// TODO Auto-generated method stub
		return null;
	}
}
