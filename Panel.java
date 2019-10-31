package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements KeyListener, ActionListener{
	
	ImageIcon body = new ImageIcon("body.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon food = new ImageIcon("food.png");
	
	int len =3;
	int[] snakex = new int[750]; 
	int[] snakey = new int[750]; 
	String fx = "R";
	boolean isStarted = false;
	boolean isFaild = false;
	Timer timer = new Timer(100, this);
	int foodx;
	int foody;
	Random random = new Random();
	
	public Panel(){
		initSnake();
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
	}

	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		g.fillRect(25, 25, 850, 650);
		if(fx == "R"){
		    right.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(fx == "U"){
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(fx == "D"){
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(fx == "L"){
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}
		for(int i =1;i<len;i++){
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		food.paintIcon(this, g, foodx, foody);
		if(isStarted == false){
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 40));
			g.drawString("Press Space to Start", 300, 300);
		}
		if(isFaild == true){
			g.setColor(Color.RED);
			g.setFont(new Font("arial", Font.BOLD, 40));
			g.drawString("Faild : Press Space to Start", 300, 300);
		}
	}
	
	public void initSnake(){
		len = 3;
		snakex[0] = 100; 
		snakey[0] = 25;
		snakex[1] = 75;
		snakey[1] = 25;
		snakex[2] = 50;
		snakey[2] = 25;
		foodx = 25+25*random.nextInt(34);
		foody = 25+25*random.nextInt(24);
		fx = "R";
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getExtendedKeyCode();
		if (keyCode == KeyEvent.VK_SPACE){
			if(isFaild){
				isFaild = false;
				initSnake();
			}else{
				isStarted=!isStarted;
			}
			repaint();
		}else if(keyCode == KeyEvent.VK_LEFT){
			fx = "L";
		}else if(keyCode == KeyEvent.VK_RIGHT){
			fx = "R";
		}else if(keyCode == KeyEvent.VK_UP){
			fx = "U";
		}else if(keyCode == KeyEvent.VK_DOWN){
			fx = "D";
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(isStarted&&!isFaild){
		for(int i=len-1;i>0;i--){
			snakex[i] = snakex[i-1];
			snakey[i] = snakey[i-1];
		}
		if(fx == "R"){
		snakex[0] = snakex[0] + 25;
		if(snakex[0] > 850){
			snakex[0] = 25;
		}}else if(fx == "L"){
			snakex[0] = snakex[0] - 25;
			if(snakex[0] < 25){
				snakex[0] = 850;
		}}else if(fx == "U"){
			snakey[0] = snakey[0] - 25;
			if(snakey[0] < 25){
				snakey[0] = 650;
		}}else if(fx == "D"){
			snakey[0] = snakey[0] + 25;
			if(snakey[0] > 650){
				snakey[0] = 25;
		}}
		if(snakex[0] == foodx && snakey[0] == foody){
			len++;
			foodx = 25+25*random.nextInt(34);
			foody = 25+25*random.nextInt(24);
		}
		
		for(int i=1;i<len;i++){
			if(snakex[i]==snakex[0]&&snakey[i]==snakey[0]){
				isFaild=!isFaild;
			}
		}
		repaint();
		}
		timer.start();
	}
}
