package pt.iscte.dcti.poo.sokoban.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Scores {
	
	private ArrayList<Integer> scores;
	public static String informacao;
	public Scores(ArrayList<Integer> scores) {
		this.scores = scores;
	}
	
		
	public void escreverScores()	{
		ArrayList<Integer> pontuacaoAnt = ScoresAnteriores();
		int level = 0;
		int pontosAnt = 0;
		File file = new File ("Scores/pontuacoes.txt");
		try {
			PrintWriter pw = new PrintWriter (file);
			
			 for(Integer i : scores) {
				 if(pontuacaoAnt.isEmpty())
				 pw.println("Melhor Score do nível " + level + " : " + i + " pontos");	 
				 if( i <= pontuacaoAnt.get(pontosAnt) ) {
				 pw.println("Melhor Score do nível " + level + " : " + i + " pontos");
				
			 } else {
				 pw.println("Melhor Score do nível " + level + " : " + pontuacaoAnt.get(pontosAnt) + " pontos" );	
			 }
				 pontosAnt++;
				 level++;
		}
			 pw.close();
			 
		
		} catch (FileNotFoundException e) {
			System.err.println("Erro na abertura do ficheiro para escrita");
		}
	}
	
	public ArrayList<Integer> ScoresAnteriores(){
		ArrayList<Integer> pontuacaoAnt = new ArrayList<Integer>();
		try {
			Scanner scan = new Scanner(new File ("Scores/pontuacoes.txt"));
			while ( scan.hasNextLine()) {
				String str=scan.nextLine();
                String info=str.substring(str.indexOf(":")+2,str.indexOf("p")-1);
				int score = Integer.parseInt(info);
				pontuacaoAnt.add(score);
				informacao +=  str;
			}
			scan.close();
			
		} catch(FileNotFoundException e) {
			System.err.println("Ficheiro não encontrado");
		}
		return pontuacaoAnt;
		
	}
	
	
	
	
	
	
}
