package pt.iscte.dcti.poo.sokoban.starter;


import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Portal extends AbstractSObject implements ActiveObject {
	
	public Portal(Point2D position, String imagename, int Layer) {
		super(position, imagename, Layer);
		
	}

	@Override
	public boolean interact(Direction direction, AbstractSObject object) {
		if(object instanceof Empilhadora) {
			Point2D pi = null;	
			for( AbstractSObject a : SokobanGame.getInstance().getTilesA()) {
					if(a instanceof Portal && a != this) {
						pi = a.getPosition();
						SokobanGame.getInstance().getPlayer().setPosition(pi);
						SokobanGame.getInstance().getPlayer().setImagename("Empilhadora_" + direction.toString().charAt(0));
						return false;
					}
				}
		
			}
		return true;
		}
	}

	
	
