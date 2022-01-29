package cod;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave {
	private int x, y;
	private int dx, dy;
	private boolean isVisible;
	private int altura, largura;
	private Image image;
	private List<Missel>misseis;

	public Nave(){
		ImageIcon referencia = new ImageIcon("res\\nave.gif");
		image = referencia.getImage();
		
		altura = image.getHeight(null);
		largura= image.getWidth(null);
		
		misseis = new ArrayList<Missel>();
		
		this.x = 100;
		this.y = 100;
	}
	
	
	public Rectangle getBounds(){
		 return new Rectangle(x,y,largura,altura);
	}
	
	public void mexer(){
		
		x+=dx;
		y+=dy;
		if(this.x<-50){
			this.x = -50;
		}
		if(this.x>930){
			this.x=930;
		}
		if(this.y<-33){
			this.y=-30;
		}
		if(this.y>621){
			this.y=620;
		}
		
	}
	

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Image getImage() {
		return image;
	}

	
	public List<Missel> getMisseis() {
		return misseis;
	}

	public void atira(){
	
		this.misseis.add(new Missel(x+largura/2, y+altura/4));
		this.misseis.add(new Missel(x+largura/2, y+altura/2+(altura/5)));
	}

	public void keyPressed(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		System.out.println(x+" "+y);
		
		if(codigo == KeyEvent.VK_SPACE){
			
			atira();			
		}
		
		if(codigo == KeyEvent.VK_UP){
			dy = -3;
		}
				
		if(codigo == KeyEvent.VK_DOWN){
			dy = 3;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 3;
		}

		if(codigo == KeyEvent.VK_LEFT){
			dx = -3;
		}
	}
	
	public void keyReleased(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP){
			dy = 0;
		}
				
		if(codigo == KeyEvent.VK_DOWN){
			dy = 0;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 0;
		}

		if(codigo == KeyEvent.VK_LEFT){
			dx = 0;
		}
	}
	
}
