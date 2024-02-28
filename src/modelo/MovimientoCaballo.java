package modelo;

import java.util.ArrayList;

public class MovimientoCaballo implements IMovimiento {
	public boolean comprobar(Tupla inicio, Tupla destino, int limite) {
		boolean movimiento_correcto = false;
		Tupla resta = inicio.restarTupla(destino);

		if (2 == resta.getX() && 1 == resta.getY() || 2 == resta.getY() && 1 == resta.getX())
			movimiento_correcto = true;

		return movimiento_correcto;
	}

	public boolean revisarTrayectoria(Tupla inicio, Tupla destino, Tablero tablero) {
		return true;
	}

	public ArrayList<Tupla> generarPosibilidades(Tupla inicio, Tablero tablero) {
		return new ArrayList<Tupla>();
	}
}
