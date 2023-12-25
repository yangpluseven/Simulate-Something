package simulator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import constants.Constants;
import entities.Location;
import entities.Pair;
import entities.SimuObject;
import entities.Size;

/**
 * GridMap takes up all the SimuObjects in the simulation.
 * 
 * @author pluseven
 */
public class GridMap implements Map<Location, simulator.GridMap.Grid> {

	private Grid[][] grids;
	private Size numCR;
	private int numOfObjs;

	/**
	 * Default constructor.
	 */
	public GridMap() {
		numCR = Constants.NUM_OF_COL_ROW;
		int numCol = numCR.getWidth();
		int numRow = numCR.getHeight();
		grids = new Grid[numCol][numRow];
		numOfObjs = 0;
	}

	/**
	 * Create a GridMap with the given numbers of columns and rows.
	 * 
	 * @param numCR
	 */
	public GridMap(Size numCR) {
		this.numCR = numCR;
		int numCol = numCR.getWidth();
		int numRow = numCR.getHeight();
		grids = new Grid[numCol][numRow];
		numOfObjs = 0;
	}

	/**
	 * Add the SimuObject at the given location.
	 * 
	 * @param simuObj
	 * @param location
	 */
	public void addObjectAt(SimuObject simuObj, Location location) {
		grids[location.getCol()][location.getRow()].add(simuObj);
	}

	/**
	 * Return the Iterator of the Grid at the Given location.
	 * 
	 * @param location
	 * @return the Iterator at the given location.
	 */
	public Iterator<SimuObject> getIteratorAt(Location location) {
		return grids[location.getCol()][location.getRow()].iterator();
	}

	/**
	 * Return the number of SimuObjects in the GridMap.
	 *
	 * @return the number of SimuObjects.
	 */
	@Override
	public int size() {
		return numOfObjs;
	}

	/**
	 * Return {@code true} if there's no SimuObjects in this GridMap.
	 *
	 * @return {@code true} if no SimuObjects in map.
	 */
	@Override
	public boolean isEmpty() {
		return numOfObjs == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		if (!(key instanceof Location))
			return false;
		Location location = (Location) key;
		Grid grid = grids[location.getCol()][location.getRow()];
		return grid != null && grid.isEmpty();
	}

	@Override
	public boolean containsValue(Object value) {
		if (!(value instanceof SimuObject || value instanceof Grid))
			return false;
		for (int i = 0; i < numCR.getWidth(); i++)
			for (int j = 0; j < numCR.getHeight(); j++)
				if (grids[i][j].contains(value))
					return true;
		return false;
	}

	@Override
	public Grid get(Object key) {
		if (!(key instanceof Location))
			return null;
		Location location = (Location) key;
		return grids[location.getCol()][location.getRow()];
	}

	@Override
	public Grid put(Location key, Grid value) {
		return grids[key.getCol()][key.getRow()] = value;
	}

	@Override
	public Grid remove(Object key) {
		if (!(key instanceof Location))
			return null;
		Location location = (Location) key;
		Grid grid = grids[location.getCol()][location.getRow()];
		grids[location.getCol()][location.getRow()] = null;
		return grid;
	}

	@Override
	public void putAll(Map<? extends Location, ? extends Grid> m) {
		for (Entry<? extends Location, ? extends Grid> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void clear() {
		for (int i = 0; i < numCR.getWidth(); i++)
			for (int j = 0; j < numCR.getHeight(); j++)
				grids[i][j] = null;
	}

	@Override
	public Set<Location> keySet() {
		Set<Location> set = new HashSet<Location>();
		for (int i = 0; i < numCR.getWidth(); i++)
			for (int j = 0; j < numCR.getHeight(); j++)
				if (grids[i][j] != null)
					set.add(new Location(i, j));
		return set;
	}

	@Override
	public Collection<Grid> values() {
		Collection<Grid> collection = new ArrayList<Grid>(numCR.getWidth() * numCR.getHeight());
		for (int i = 0; i < numCR.getWidth(); i++)
			for (int j = 0; j < numCR.getHeight(); j++)
				if (grids[i][j] != null)
					collection.add(grids[i][j]);
		return null;
	}

	@Override
	public Set<Entry<Location, Grid>> entrySet() {
		Set<Entry<Location, Grid>> set = new HashSet<Entry<Location, Grid>>();
		for (int i = 0; i < numCR.getWidth(); i++)
			for (int j = 0; j < numCR.getHeight(); j++)
				if (grids[i][j] != null)
					set.add(new Pair<Location, Grid>(new Location(i, j), grids[i][j]));
		return set;
	}

	/**
	 * I don't know why do I need this class. I kinda just encapsulated a ArrayList
	 * in it.
	 * 
	 * @author pluseven
	 */
	public class Grid implements Collection<SimuObject> {

		private ArrayList<SimuObject> simuObjs;

		/**
		 * Default constructor.
		 */
		public Grid() {
			simuObjs = new ArrayList<SimuObject>();
		}

		@Override
		public int size() {
			return simuObjs == null ? 0 : simuObjs.size();
		}

		@Override
		public boolean isEmpty() {
			return simuObjs == null || simuObjs.size() == 0;
		}

		@Override
		public boolean contains(Object o) {
			return simuObjs == null ? false : simuObjs.contains(o);
		}

		@Override
		public Iterator<SimuObject> iterator() {
			return simuObjs.iterator();
		}

		@Override
		public Object[] toArray() {
			return simuObjs.toArray();
		}

		@Override
		public <T> T[] toArray(T[] a) {
			return simuObjs.toArray(a);
		}

		@Override
		public boolean add(SimuObject e) {
			return simuObjs.add(e);
		}

		@Override
		public boolean remove(Object o) {
			return simuObjs.remove(o);
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return simuObjs.containsAll(c);
		}

		@Override
		public boolean addAll(Collection<? extends SimuObject> c) {
			return simuObjs.addAll(c);
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			return simuObjs.removeAll(c);
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			return simuObjs.retainAll(c);
		}

		@Override
		public void clear() {
			simuObjs.clear();
		}

	}

}
