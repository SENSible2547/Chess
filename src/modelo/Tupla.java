package modelo;

public class Tupla {
	private final int x;
	private final int y;
	
	public Tupla(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Tupla restarTupla(Tupla a) {
		return new Tupla(Math.abs(this.x - a.x), Math.abs(this.y - a.y));
	}

	public int compararX(Tupla a) {
		if (this.x > a.getX())
			return 1;
		else if(this.x < a.getX())
			return -1;
		return 0;
	}

	public int compararY(Tupla a) {
		if (this.y > a.getY())
			return 1;
		else if(this.y < a.getY())
			return -1;
		return 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
