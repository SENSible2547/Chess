package modelo;

import java.util.ArrayList;

public class MovimientoLineal implements IMovimiento {
	public boolean comprobar(Tupla inicio, Tupla destino, int limite) {
		boolean movimiento_correcto = false;
		Tupla resta = inicio.restarTupla(destino);
		
		if (0 == resta.getX() && resta.getY() <= limite || 0 == resta.getY() && resta.getX() <= limite)
			movimiento_correcto = true;

		return movimiento_correcto;
	}
	
	public boolean revisarTrayectoria(Tupla inicio, Tupla destino, Tablero tablero) {
		boolean trayectoria_correcta = true;
		Ficha ficha;
		TableroIterador iterador;
		Tupla aux;
		Tupla resta = inicio.restarTupla(destino);

		// movimiento lineal horizontal
		if (0 == resta.getX()) {
			if (1 == inicio.compararY(destino))
				iterador = tablero.crearIterador(destino.getY(), inicio.getX());
			else
				iterador = tablero.crearIterador(inicio.getY(), inicio.getX());

			for (int i = 1; i < resta.getY(); i++) {
				ficha = iterador.siguiente_columna().getFicha();
				if (TIPO.NOFICHA != ficha.getTipo()) {
					trayectoria_correcta = false;
					break;
				}
			}
		} else { // movimiento lineal vertical
			if (1 == inicio.compararX(destino))
				iterador = tablero.crearIterador(destino.getX(), inicio.getY());
			else
				iterador = tablero.crearIterador(inicio.getX(), inicio.getY());

			for (int i = 1; i < resta.getX(); i++) {
				ficha = iterador.siguiente_fila().getFicha();
				if (TIPO.NOFICHA != ficha.getTipo()) {
					trayectoria_correcta = false;
					break;
				}
			}
		}
		
		return trayectoria_correcta;
	}
	
	public ArrayList<Tupla> generarPosibilidades(Tupla inicio, Tablero tablero) {
		return new ArrayList<Tupla>();
	}
}
