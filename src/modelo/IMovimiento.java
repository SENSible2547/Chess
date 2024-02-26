package modelo;

import java.util.ArrayList;

public interface IMovimiento {
	public boolean comprobar(Tupla inicio, Tupla destino, Casilla tablero[][]);
	public Ficha revisarTrayectoria(Tupla inicio, Tupla destino, Casilla tablero[][]);
	public ArrayList<Tupla> generarPosibilidades(Tupla inicio, Casilla tablero[][]);
}
