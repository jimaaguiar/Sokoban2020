package pt.iscte.dcti.poo.sokoban.starter;


import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Bateria extends AbstractSObject implements ActiveObject{

	public Bateria(Point2D position, String imagename, int Layer) {
		super(position, imagename, Layer);
		
	}

	@Override
	public boolean interact(Direction direction, AbstractSObject object) {
		
		if(object instanceof Empilhadora) {
		SokobanGame.getInstance().getPlayer().setVida();
		ImageMatrixGUI.getInstance().removeImage(this);	
		SokobanGame.getInstance().removeImage(this);
		return true;
		}
		return false;
	}
}
