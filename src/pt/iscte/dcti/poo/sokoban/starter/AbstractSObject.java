package pt.iscte.dcti.poo.sokoban.starter;



import pt.iul.ista.poo.gui.ImageTile;

import pt.iul.ista.poo.utils.Point2D;

public abstract class AbstractSObject implements ImageTile {

	
	private Point2D position;
	private String imagename;
	private int Layer;
	


	public AbstractSObject (Point2D position, String imagename, int Layer) {
		this.position = position;
		this.imagename = imagename;
		this.Layer = Layer;
		
		
	}
		
		
	
	
	@Override
		public Point2D getPosition() {
			return position;
	}
	
	

	public void setPosition(Point2D position) {
		this.position = position;
	}



	@Override
		public int getLayer() {
			return Layer;
	}
	
	
	@Override
		public String getName() {
		return imagename;
	}



	public void setImagename(String imagename) {
		this.imagename = imagename;
	}	
	
	
	
	
}
	
	
	
	
	
	
	

