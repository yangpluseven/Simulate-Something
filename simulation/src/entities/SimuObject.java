package entities;

// TODO SimuObject doc
public abstract class SimuObject {
	
	protected Location location;
	protected View view;

	public SimuObject() {
		location = new Location();
		view = new View();
	}
	
	

}
