package pt.iscte.dcti.poo.sokoban.starter;




import pt.iul.ista.poo.utils.Point2D;

public class Alvo extends AbstractSObject  {

	public Alvo(Point2D position, String imagename, int Layer) {
		super(position, imagename, Layer);
		
	}

	
	public boolean alvoComCaixa() {
		for( AbstractSObject a : SokobanGame.getInstance().getTilesA()) {
			if(getPosition().equals(a.getPosition())) {
				if(a instanceof Caixote)
					return true;
			}
		}
		return false;
	}
	
	
	
	


}
