package modelo;

import java.util.ArrayList;

public abstract class Casilla {
	protected final Tupla posicion;
	protected Ficha ficha;
	
	public Casilla(Tupla posicion) {
		this.posicion = posicion;
	}
		
	public ArrayList<Tupla> entrar(Tupla inicio) {
		return new ArrayList<Tupla>();
	}
	
	public ArrayList<Tupla> salir(Tupla destino) {
		return new ArrayList<Tupla>();
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public Tupla getPosicion() {
		return posicion;
	}
}
