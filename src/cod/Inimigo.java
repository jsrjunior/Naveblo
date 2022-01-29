package cod;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {
	private Image imagem;
	private int x,y;
	private int largura, altura;
	private boolean isVisivel;
	private int tipinimigo;
	private static final int LARGURA_TELA = 980;
	private static int  vel = 3;
	private static int nivel = 1;
	private static int cont;

	public Inimigo(int x, int y, int tipinimigo){
		this.x = x;
		this.y = y;
		this.tipinimigo = tipinimigo;
		ImageIcon referencia = new ImageIcon("res\\inimigo_1.gif");
		if(tipinimigo==1){
			referencia = new ImageIcon("res\\inimigo_2.gif");
		}
		
		
		this.imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura= imagem.getWidth(null);
		isVisivel = true;
		
	}
	
	
	
	public void mexer(int total){
		
		if(total==30 && nivel==1){
			vel++;
		}
		
		if(total==50 && nivel==1){
			vel++;
			nivel++;
		}
		if(total==100 && nivel==2){
			vel++;
			nivel++;
		}
		if(total==150 && nivel==3){
			vel++;
			nivel++;
		}
		if(total==200 &&nivel==4){
			vel++;
			nivel++;
		}
		if(total==250 && nivel==5){
			vel++;
			nivel++;
		}
		if(total==300 && nivel==6){
			vel++;
			nivel++;
		}
		if(total==400 && nivel==7){
			vel++;
			nivel++;
		}
		
		if(this.x < -120){
			isVisivel = false;
		}else{
			this.x -= vel;
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
	
	
	public int getTipinimigo() {
		return tipinimigo;
	}

	public void setTipinimigo(int tipinimigo) {
		this.tipinimigo = tipinimigo;
	}
	
	public static void setVel(int vel) {
		Inimigo.vel = vel;
	}



	public static void setNivel(int nivel) {
		Inimigo.nivel = nivel;
	}



	public static int getNivel() {
		return nivel;
	}

	public Rectangle getBounds(){
		 return new Rectangle(x,y,largura,altura);
	}
}
