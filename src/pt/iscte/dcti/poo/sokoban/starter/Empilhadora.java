package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Empilhadora extends AbstractSObject{
	
	private  int moves = 0;
	private  int vida = 100;
	private static boolean martelo = false;
	
	public Empilhadora(Point2D position, String imagename, int Layer) {
		super(position, imagename, Layer);
		
	}

	
	public  int Moves() {
		return moves;
	}
	
	public  int Vida() {
		return vida;
	}
	
	public void setVida() {
		vida = 102;
	}
	
	public void Morte() {
		vida = 0;
		}	
	
	public static void ganharouPerderMartelo(boolean m) {
		martelo = m;
	}
	
	public static boolean getMartelo() {
		return martelo;
	}
		

	public void move(Direction direction) {
		
			boolean bool = true;
			Point2D pi = getPosition();
			Point2D newPosition = pi.plus(direction.asVector());
			
			for( AbstractSObject a : SokobanGame.getInstance().getTilesA()) {
				if(newPosition.equals(a.getPosition())) {
					if(a instanceof Parede )
						return;	
					
					if(a instanceof MovableObject) {
						MovableObject b = (MovableObject) a;
						b.move(direction);
						if(!b.canMoviment())
							return;
						pi = newPosition;
						setPosition(pi);
						if(vida != 0)
							vida-=4;
						moves++;
						setImagename("Empilhadora_" + direction.toString().charAt(0));
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
				if(vida != 0)
				vida-=2;
				moves++;
					
		}
		setImagename("Empilhadora_" + direction.toString().charAt(0));
		ImageMatrixGUI.getInstance().update();
	}		
}
