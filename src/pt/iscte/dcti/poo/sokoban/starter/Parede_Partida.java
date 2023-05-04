package pt.iscte.dcti.poo.sokoban.starter;


import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Parede_Partida extends AbstractSObject implements ActiveObject{

	public Parede_Partida(Point2D position, String imagename, int Layer) {
		super(position, imagename, Layer);
		
	}

	@Override
	public boolean interact(Direction direction, AbstractSObject object) {
		
		if(object instanceof Empilhadora && Empilhadora.getMartelo() == true) {

		
		ImageMatrixGUI.getInstance().removeImage(this);	
		SokobanGame.getInstance().removeImage(this);
		return true;
		 
	}
		if(object instanceof Empilhadora && Empilhadora.getMartelo() == false)
			return false;
		
		
		if(object instanceof MovableObject) {
			return false;
		}
		return false;

	}
}