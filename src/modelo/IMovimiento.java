package modelo;

import java.util.ArrayList;

public interface IMovimiento {
	public boolean comprobar(Tupla inicio, Tupla destino, int limite);
	public boolean revisarTrayectoria(Tupla inicio, Tupla destino, Tablero tablero);
	public ArrayList<Tupla> generarPosibilidades(Tupla inicio, Tablero tablero);
}
