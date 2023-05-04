package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;

public interface ActiveObject {
	public boolean interact(Direction direction, AbstractSObject object);
	


}