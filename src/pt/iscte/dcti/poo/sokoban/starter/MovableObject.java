package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class MovableObject extends AbstractSObject {

	private boolean Moviment;
	
	
	public MovableObject(Point2D position, String imagename, int Layer) {
		super(position, imagename, Layer);
		
	}
	
	public void move(Direction direction) {
		boolean bool = true;
		Point2D pi = getPosition();
		Point2D newPosition = pi.plus(direction.asVector());
		Moviment = true;		
		for( AbstractSObject a : SokobanGame.getInstance().getTilesA()) {
			if(newPosition.equals(a.getPosition())) {
				if(!(a instanceof Alvo || a instanceof Buraco)) {
					Moviment = false;
					return;
				}
				
				
				if(a instanceof ActiveObject) {
					ActiveObject o = (ActiveObject) a;
					bool = o.interact(direction, this);
					if(bool == false)
						return;
					break;
				}
			}			
		}
		
		

		if (newPosition.getX() >= 0 && newPosition.getX() < SokobanGame.WIDTH && 
				newPosition.getY() >= 0 && newPosition.getY() < SokobanGame.HEIGHT ){
			
			pi = newPosition;
			setPosition(pi);
			
			
		
	}
	ImageMatrixGUI.getInstance().update();
}

	public boolean canMoviment() {
		return Moviment;
		
	}
	
	

}
