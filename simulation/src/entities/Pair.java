package entities;

import java.util.Map.Entry;

// TODO Pair doc
public abstract class Pair<L, R> implements Comparable<Pair<L, R>>, Entry<L, R> {

	protected L left;
	protected R right;

	public Pair() {
	}

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int hashCode() {
		return (left == null ? 0 : left.hashCode()) ^ (right == null ? 0 : right.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pair) {
			Pair<?, ?> other = (Pair<?, ?>) obj;
			return left == other.getLeft() && right == other.getRight();
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "(" + left.toString() + right.toString() + ")";
	}

	@Override
	public L getKey() {
		return left;
	}

	@Override
	public R getValue() {
		return right;
	}

	@Override
	public R setValue(R value) {
		R former = right;
		right = value;
		return former;
	}

	public L getLeft() {
		return left;
	}

	public void setLeft(L left) {
		this.left = left;
	}

	public R getRight() {
		return right;
	}

	public void setRight(R right) {
		this.right = right;
	}

}
