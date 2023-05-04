package pt.iscte.dcti.poo.sokoban.starter;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class SokobanGame implements Observer  {
	
	private static SokobanGame INSTANCE;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	private int level = 0;
	private int numlevels;
	
	private List<AbstractSObject> caixas = new ArrayList<AbstractSObject>();
	private List<AbstractSObject> alvos = new ArrayList<AbstractSObject>();
	private List<AbstractSObject> tilesA = new ArrayList<AbstractSObject>();
	private ArrayList<Integer> movesList = new ArrayList<Integer>(); 
	private Empilhadora player; 
	private Scores scores;

	public SokobanGame(){
		JOptionPane.showMessageDialog(null,"Bem-vindo ao Sokoban!\nClica no 'OK' para começar.");
		buildSampleLevel("levels/level"+level+".txt");
		ImageMatrixGUI.getInstance().setName("Sokoban");
		ImageMatrixGUI.getInstance().setStatusMessage("Level: " + level + "   Vida : " + player.Vida() + "   Moves : "  + player.Moves());
		numlevels=(new File("levels")).listFiles().length;
		
	}
	
	public static SokobanGame getInstance() {
        if (INSTANCE == null)
            INSTANCE = new SokobanGame();
        return INSTANCE;
    }
	
	
	public void buildSampleLevel(String level){
		
		List<ImageTile> tiles = new ArrayList<ImageTile>();
		// Build 10x10 floor tiles
		
		try {
			Scanner scanner = new Scanner (new File (level));
			String line = null;
			
			for(int y = 0; y< HEIGHT; y++ ){
					 line = scanner.nextLine();

				for(int x = 0; x<WIDTH; x++) {
			
					if(line.charAt(x) == '#'  ) {
						Parede P = new Parede (new Point2D(x,y), "Parede", 2); 
						tiles.add(P);
						tilesA.add(P);
					}
						
					if(line.charAt(x) == 'C' ) {
						Caixote C = new Caixote (new Point2D(x,y), "Caixote", 2);
						tiles.add(C);
						tilesA.add(C);
						caixas.add(C);
					}
					if(line.charAt(x) == 'O') {
						Buraco B = new Buraco ( new Point2D(x,y), "Buraco", 1);
						tiles.add(B);
						tilesA.add(B);
					}
						
					if(line.charAt(x) == 'b') {
						Bateria Ba =new Bateria (new Point2D(x,y), "Bateria", 1);
						tiles.add(Ba);
						tilesA.add(Ba);
					}
						
					if(line.charAt(x) == 'X') {
						Alvo A = new Alvo (new Point2D(x,y), "Alvo", 1);
						tiles.add(A);
						tilesA.add(A);
						alvos.add(A);
						
					}
					
					if(line.charAt(x) == 'm') {
						Martelo m = new Martelo (new Point2D(x,y), "Martelo", 1);
						tiles.add(m);
						tilesA.add(m);
					}
					if(line.charAt(x) == '%') {
						Parede_Partida m = new Parede_Partida (new Point2D(x,y), "Parede_Partida", 1);
						tiles.add(m);
						tilesA.add(m);
					}
					
					
					if(line.charAt(x) == 'p') {
						SmallStone Pe = new SmallStone (new Point2D(x,y), "SmallStone", 2);
						tiles.add(Pe);
						tilesA.add(Pe);
							
					}
					
					if(line.charAt(x) == 'P') {
						BigStone Pe = new BigStone (new Point2D(x,y), "BigStone", 2);
						tiles.add(Pe);
						tilesA.add(Pe);
							
					}
					
					if(line.charAt(x) == 't') {
						Portal p = new Portal (new Point2D(x,y), "Portal_Azul", 2);
						tiles.add(p);
						tilesA.add(p);
					}
					
					
					if(line.charAt(x) == 'E') {
						player = new Empilhadora(new Point2D(x,y), "Empilhadora_U", 2);
						tiles.add(player);
					}
					}
					
				}
			
			scanner.close();
			
		} catch (FileNotFoundException e) {
			
			System.err.println("Erro no ficheiro");
		}
		for (int y = 0; y != HEIGHT; y++)
			for (int x = 0; x != WIDTH ; x++)
				tiles.add(new Chao(new Point2D(x,y), "Chao", 0));		
			
		
		ImageMatrixGUI.getInstance().addImages(tiles);
		
	}
	
	
	
	public List<AbstractSObject> getTilesA(){
		return tilesA;
	}
	
	
	 public void removeImage( AbstractSObject image) {
	          tilesA.remove(image);
	        }
	 
	 public void addImage(AbstractSObject image) {
		 tilesA.add(image);
	 }
	 
	 
	 public Empilhadora getPlayer() {
		 return player;
	 }
	 
	 public int getLevel() {
		 return level;
	 }
	 
	 public boolean MaisCaixas() {
		 return caixas.size() > alvos.size();
	 }
	 	 
	 
	 public boolean nextLevel() {
		 int k = 0;
		 if(MaisCaixas()) {
			int dif = caixas.size() - alvos.size();
			caixas.remove(dif);
		 } 
		 for( AbstractSObject a : alvos) {
			 Alvo b = (Alvo) a;
			 if (b.alvoComCaixa()) k++;
		 }
		 return k == caixas.size();
	 }
	 
	 
	 public boolean theEnd() {
		 return level > numlevels -1;
		 
	 }
	 
	
	@Override
	public void update(Observed arg0) {
		int lastKeyPressed = ((ImageMatrixGUI)arg0).keyPressed();
		System.out.println("Key pressed " + lastKeyPressed);	
		if(nextLevel()) {
		movesList.add(player.Moves());	
		++level;
		if(theEnd()) {
		JOptionPane.showMessageDialog(null, "YOU WIN ! \n Clica no 'Ok' para sair " );
		scores= new Scores (movesList);
		scores.escreverScores();	
		System.exit(0);
		} else {
		Empilhadora.ganharouPerderMartelo(false);	
		ImageMatrixGUI.getInstance().clearImages();
        tilesA.removeAll(tilesA);
        caixas.removeAll(caixas);
        alvos.removeAll(alvos);
        INSTANCE.buildSampleLevel("levels/level"+ level +".txt");
        ImageMatrixGUI.getInstance().setStatusMessage("Level: " + level + "   Vida : " + player.Vida() + "   Moves : "  + player.Moves());
        ImageMatrixGUI.getInstance().update();
        return;
		}
		}
		
		if (player != null && player.Vida() > 0 ) {
			if (!theEnd()) {
				player.move(Direction.directionFor(lastKeyPressed));
				ImageMatrixGUI.getInstance().setStatusMessage("Level: " + level + "   Vida : " + player.Vida() + "   Moves : "  + player.Moves());
				}
				
		} else {
			ImageMatrixGUI.getInstance().setStatusMessage("Level: " + level + "   Vida : " + player.Vida() + "   Moves : "  + player.Moves() + "   GAME OVER");
			if( lastKeyPressed == 27)
				System.exit(0);
			JOptionPane.showMessageDialog(null, "GAME OVER\nClica no 'Ok' e pressiona 'Esc' para saíres. ");	
			}		
		}
	}
