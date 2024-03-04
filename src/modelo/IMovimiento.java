package modelo;

import java.util.ArrayList;

public interface IMovimiento {
	public boolean comprobar(Casilla inicio, Casilla destino);
	public Casilla revisarTrayectoria(Casilla inicio, Casilla destino, Tablero tablero);
	public void generarPosibilidades(Casilla inicio, Tablero tablero, ArrayList<Casilla> posibilidades);
}
