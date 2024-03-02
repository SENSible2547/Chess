package modelo;

public class Tupla {
	private final int x;
	private final int y;
	
	public Tupla(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Tupla restarAbsTupla(Tupla a) {
		return new Tupla(Math.abs(this.x - a.x), Math.abs(this.y - a.y));
	}

	public Tupla restarTupla(Tupla a) {
		return new Tupla(this.x - a.x, this.y - a.y);
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

	public boolean equals(Tupla a) {
		boolean equal = true;
		if (this.x != a.getX() || this.y != a.getY())
			equal = false;

		return equal;
	}

	// para propositos de prueba
	public String toString(){
		return String.valueOf(this.x) + " " + String.valueOf(this.y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
