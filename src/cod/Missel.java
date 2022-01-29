package cod;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Missel {
	private Image imagem;
	private int x,y;
	private int largura, altura;
	private boolean isVisivel;
	
	private static final int LARGURA_TELA = 980;
	private static final int VELOCIDADE = 30;
	
	public Missel(int x, int y){
		this.x = x;
		this.y = y;
		
		ImageIcon referencia = new ImageIcon("res\\tiro.png");
		this.imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura= imagem.getWidth(null);
		isVisivel = true;
		
	}
	
	public void mexer(){
		this.x += VELOCIDADE;
		
		if(this.x > LARGURA_TELA){
			this.isVisivel = false;
		}
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle getBounds(){
		 return new Rectangle(x,y,largura,altura);
	}
}
