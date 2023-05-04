package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Buraco extends AbstractSObject implements ActiveObject {
	

	public Buraco(Point2D position, String imagename, int Layer) {
		super(position, imagename, Layer);
		
	}

	@Override
	public boolean interact(Direction direction, AbstractSObject object) {
		if(object instanceof Empilhadora) {
			SokobanGame.getInstance().getPlayer().Morte();
			ImageMatrixGUI.getInstance().removeImage(object);
			return true;
		}
			
			if(object instanceof MovableObject) {
				if(object instanceof BigStone) {
					
					ImageMatrixGUI.getInstance().removeImage(object);
					SokobanGame.getInstance().removeImage(object);
					ImageMatrixGUI.getInstance().removeImage(this);
					SokobanGame.getInstance().removeImage(this);
					return false;
					
				} else { 
					ImageMatrixGUI.getInstance().removeImage(object);
					SokobanGame.getInstance().removeImage(object);
					if(object instanceof Caixote)
					SokobanGame.getInstance().getPlayer().Morte();
					return true;
				}
			
				
			
			}
			ImageMatrixGUI.getInstance().update();
		return false;
		
		
	}
}


