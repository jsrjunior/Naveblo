package cod;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Estrelas {
	private Image imagem;
	private int x,y;
	private int largura, altura;
	private boolean isVisivel;
	private static final int LARGURA_TELA = 1000;
	private static final int VELOCIDADE = 10;
	

	public Estrelas(int x, int y){
		this.x = x;
		this.y = y;
		ImageIcon referencia = new ImageIcon("res\\estrelas.png");
		
		this.imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura= imagem.getWidth(null);
		isVisivel = true;
		
	}
	
	public void mexer(){
		if(this.x < -2048){
			this.x = 1024;
		}else{
			this.x-=VELOCIDADE;
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
}
