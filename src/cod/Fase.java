package cod;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
	private Image fundo;
	private Nave nave;
	private List<Inimigo> inimigos;
	private List<Obstaculo> obstaculos;
	private Estrelas estrelas;
	private Estrelas estrelas2;
	private Timer timer;
	private boolean emJogo;
	private int obsUltprassados;
	private int iniMortos;
	private boolean salvo;
	private List<Save> saves;
	
	public void retornaSave() throws SQLException{
		SaveDAO sdao = new SaveDAO();
		saves = sdao.getlista();
	}
	
	public void salva(Save save) throws SQLException{
		SaveDAO sdao = new SaveDAO();
		sdao.salva(save);
	}
	
	public void addCoordenadasInimigos(){
		inimigos = new ArrayList<Inimigo>();
		Random gerador = new Random();
		int cont = 950;
		int n = 0;
		
		for(int i = 0; i <200; i++){
			Coordenadas coor = new Coordenadas();
				int op = gerador.nextInt(600);
				int tip = gerador.nextInt(2);
				
				inimigos.add(new Inimigo(cont, op, tip));			
				cont = cont + 100;
				n=op;
			}
	}
	
	public void addCoordenadasObstaculos(){
		obstaculos = new ArrayList<Obstaculo>();
		Random gerador = new Random();
		int cont = 950;
		
		for(int i = 0; i <200; i++){
			Coordenadas coor = new Coordenadas();
			int tip = gerador.nextInt(2);
			System.out.println(tip);
			if(tip==0){
				obstaculos.add(new Obstaculo(cont, -10, 1));			
				obstaculos.add(new Obstaculo(cont, 500, 2));
			}else{
				obstaculos.add(new Obstaculo(cont, 150, 3));
			}
			cont = cont + 1000;
		}
	}
	
	public Fase() throws SQLException{
		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());
		ImageIcon referencia = new ImageIcon("res\\fundo.jpg");
		fundo = referencia.getImage();
		estrelas = new Estrelas(0,0);
		estrelas2 = new Estrelas(2048,0);
		retornaSave();
		emJogo = true;
		addCoordenadasInimigos();
		addCoordenadasObstaculos();
		nave = new Nave();
		timer = new Timer (5, this);
		timer.start();
		
	}
	
	
	public void paint(Graphics g){
		
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		List<Missel> misseis = nave.getMisseis();
		
		
		
		if(emJogo){
		graficos.drawImage(estrelas.getImagem(), estrelas.getX(), estrelas.getY(), this);
		graficos.drawImage(estrelas2.getImagem(), estrelas2.getX(), estrelas2.getY(), this);
		graficos.drawImage(nave.getImage(), nave.getX(), nave.getY(), this);
		
		
			for (int i = 0; i < misseis.size(); i++) {
				
				Missel m = (Missel) misseis.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}
		
			for (int i = 0; i < inimigos.size(); i++) {
				
				Inimigo in = (Inimigo) inimigos.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
			
			for (int i = 0; i < obstaculos.size(); i++) {
				
				Obstaculo ob = (Obstaculo) obstaculos.get(i);
				graficos.drawImage(ob.getImagem(), ob.getX(), ob.getY(), this);
			}
			
			
		}else{
			ImageIcon fimJogo = new ImageIcon("res\\game_over.png");
			
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
			graficos.setColor(Color.WHITE);
			int scoreTotal = obsUltprassados+iniMortos*Inimigo.getNivel();
			graficos.drawString("Score Total: "+scoreTotal,512 , 310);
				
				int local = 40;
				graficos.drawString("Nome",700 , 20);
				graficos.drawString("| Score",800 , 20);
				graficos.drawString("_________________________________",700 , 24);
				
				for (int i = 0; i < saves.size(); i++) {
					Save save = saves.get(i);
					graficos.drawString(save.getNome(),700 , local);
					graficos.drawString("| "+save.getScore(),800 , local);
					local+=25;
				}			
		}
		
		graficos.setColor(Color.WHITE);
		graficos.drawString("Inimigos Que Passaram: "+obsUltprassados,5 , 15);
		graficos.drawString("Inimigos Destruidos: "+iniMortos,5 , 30);
		graficos.drawString("Nivel dos Inimigos: "+Inimigo.getNivel(),5 , 45);
		graficos.drawString("Nivel do Obstaculos: "+Obstaculo.getNivel(),5 , 60);
		
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		estrelas.mexer();
		estrelas2.mexer();
		List <Missel> misseis = nave.getMisseis();
		
		if(emJogo){
			
		for (int i = 0; i < misseis.size(); i++) {
			Missel m = (Missel) misseis.get(i);
			if(m.isVisivel()){	
				m.mexer();
			}else{
				misseis.remove(i);
			}
			
		}
			
		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo in = (Inimigo) inimigos.get(i);
			if(in.isVisivel()){
				in.mexer(iniMortos);
			}else{
				inimigos.remove(i);
			}
		}
		
		for (int i = 0; i < obstaculos.size(); i++) {
			Obstaculo ob = (Obstaculo) obstaculos.get(i);
			if(ob.mexer(obsUltprassados)==false){
				obsUltprassados++;
				obstaculos.remove(i);
			}
		}
		
		nave.mexer();
		checaColisoes();
		repaint();
		}else{
			if(salvo == false){
				String nome;
				nome = JOptionPane.showInputDialog("Entre com o nome para salvar");
				if(nome!=null && nome!= ""){
					Save save = new Save();
					save.setNome(nome);
					int scoreTotal = obsUltprassados+iniMortos*Inimigo.getNivel();
					save.setScore(scoreTotal);
					try {
						salva(save);
						salvo = true;
					} catch (SQLException e1) {
						salvo = true;
					}
					
				}else{
					salvo=true;
				}
			}
		}
	}
	
	public void checaColisoes(){
		Rectangle formaNave = nave.getBounds();
		Rectangle formaInimigo;
		Rectangle formaObstaculo;
		Rectangle formaMissel;
		
		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo tempInimigo = inimigos.get(i);
			formaInimigo = tempInimigo.getBounds();
			
			if(formaNave.intersects(formaInimigo)){
				nave.setVisible(false);
				tempInimigo.setVisivel(false);
				emJogo = false;
			}
		}
		
		for (int i = 0; i < obstaculos.size(); i++) {
			Obstaculo tempObstaculo= obstaculos.get(i);
			formaObstaculo = tempObstaculo.getBounds();
			
			if(formaNave.intersects(formaObstaculo)){
				nave.setVisible(false);
				tempObstaculo.setVisivel(false);
				emJogo = false;
			}
		}
		
		List <Missel> misseis = nave.getMisseis();
		
		for (int i = 0; i < misseis.size(); i++) {
			Missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();
			
			for (int j = 0; j < inimigos.size(); j++) {
				Inimigo tempInimigo = inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();
				
				if(formaMissel.intersects(formaInimigo)){
					iniMortos++;
					tempInimigo.setVisivel(false);
					tempMissel.setVisivel(false);
				}
			}
		}
		
	}
	
	
	private class TecladoAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				salvo=false;
				emJogo = true;
				addCoordenadasInimigos();
				addCoordenadasObstaculos();
				nave = new Nave();
				iniMortos=0;
				obsUltprassados = 0;
				Inimigo.setNivel(0);
				Inimigo.setVel(2);
				try {
					retornaSave();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			nave.keyPressed(e);
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}
		
	}

	public boolean isEmJogo() {
		return emJogo;
	}

	public void setEmJogo(boolean emJogo) {
		this.emJogo = emJogo;
	}
}

class Coordenadas{
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}
