package cod;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Obstaculo {
	private Image imagem;
	private int x,y;
	private int largura, altura;
	private boolean isVisivel;
	private int tipobstaculo;
	private static final int LARGURA_TELA = 980;
	private static int  vel = 1;
	private static int nivel = 1;
	private static int cont;

	public Obstaculo(int x, int y, int tipobstaculo){
		this.x = x;
		this.y = y;
		this.tipobstaculo = tipobstaculo;
		ImageIcon referencia = new ImageIcon("res\\obstaculo_1.png");
		if(tipobstaculo==2){
			referencia = new ImageIcon("res\\obstaculo_2.png");
		}else{
			if(tipobstaculo==3){
				referencia = new ImageIcon("res\\obstaculo_3.png");
			}
		}
		
		
		this.imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura= imagem.getWidth(null);
		isVisivel = true;
		
	}
	
	
	
	public boolean mexer(int total){
		
		if(this.x < -120){
			isVisivel = false;
		}else{
			this.x -= vel;
		}
		
		if(total==10 && nivel==1){
			vel=vel+2;
			nivel++;
		}
		if(total==30&& nivel==2){
			vel=vel+2;
			nivel++;
		}
		if(total==60&&nivel==3){
			vel=vel+2;
			nivel++;
		}
		if(total==100&&nivel==4){
			vel=vel+2;
			nivel++;
		}
		if(total==150&&nivel==5){
			vel++;
			nivel++;
		}
		if(total==200&&nivel==6){
			vel++;
			nivel++;
		}
		if(total==300&&nivel==7){
			vel++;
			nivel++;
		}
		return isVisivel;
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
	
	public int getTipobstaculo() {
		return tipobstaculo;
	}



	public void setTipobstaculo(int tipobstaculo) {
		this.tipobstaculo = tipobstaculo;
	}



	public static void setVel(int vel) {
		Obstaculo.vel = vel;
	}



	public static void setNivel(int nivel) {
		Obstaculo.nivel = nivel;
	}



	public static int getNivel() {
		return nivel;
	}

	public Rectangle getBounds(){
		 return new Rectangle(x,y,largura,altura);
	}
}
