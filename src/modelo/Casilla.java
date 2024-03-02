package modelo;

public abstract class Casilla {
	private final Tupla posicion;
	protected Ficha ficha;
	
	public Casilla(Tupla posicion) {
		this.posicion = posicion;
	}
		
	public void entrar(Casilla inicio) {
	}
	
	public void salir(Casilla destino) {
	}

	public Ficha getFicha() {
		return this.ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public Tupla getPosicion() {
		return posicion;
	}
}
