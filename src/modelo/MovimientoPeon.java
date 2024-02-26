package modelo;

import java.util.ArrayList;

public class MovimientoPeon implements IMovimiento {
	private final MovimientoDiagonal diagonal;
	
	public MovimientoPeon(MovimientoDiagonal diagonal) {
		this.diagonal = diagonal;
	}

	public boolean comprobar(Tupla inicio, Tupla destino, Casilla tablero[][]) {
		return true;
	}
	public Ficha revisarTrayectoria(Tupla inicio, Tupla destino, Casilla tablero[][]) {
		return new Ficha(COLOR.BLANCO, TIPO.PEON, new ArrayList<IMovimiento>());
	}
	public ArrayList<Tupla> generarPosibilidades(Tupla inicio, Casilla tablero[][]) {
		return new ArrayList<Tupla>();
	}
}
