package entities;

// TODO SimuObject doc
public abstract class SimuObject {
	
	private Location location;
	private View view;

	public SimuObject() {
		location = new Location();
		view = new View();
	}

}
