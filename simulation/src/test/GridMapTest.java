package test;

public class GridMapTest {

	public static void main(String[] args) {
		GM gm = new GM();
		System.out.println(gm.gs[5]);
	}

}

class GM {
	public G gs[];
	public GM() {
		gs = new G[10];
	}
}

class G {
	public int value = 0;
	public G() {
		value = 0;
	}
	public G(int value) {
		this.value = value;
	}
}