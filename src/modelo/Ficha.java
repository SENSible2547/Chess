package modelo;

import java.util.ArrayList;

public class Ficha {
	private COLOR color;
	private TIPO tipo;
	private int limite;
	private ArrayList<IMovimiento> movimientos;
	private final ArrayList<Tupla> posibilidades = new ArrayList<>();
	
	public Ficha(COLOR color, TIPO tipo, ArrayList<IMovimiento> movimientos) {
		this.color = color;
		this.tipo = tipo;
		this.movimientos = movimientos;
	}

	public TIPO getTipo() {
		return tipo;
	}

	public void setTipo(TIPO tipo) {
		this.tipo = tipo;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public ArrayList<Tupla> getPosibilidades() {
		return this.posibilidades;
	}

	public COLOR getColor() {
		return color;
	}
	
	public boolean comprobar(Casilla inicio, Casilla destino, Tablero tablero) {
		boolean comprobacionValida = true;

		// encontrar el movimiento valido para revisar la trayectoria
		boolean comprobacionAprovada;
		IMovimiento movimientoValido = null;
		for (IMovimiento movimiento : this.movimientos) {
			comprobacionAprovada = movimiento.comprobar(inicio, destino);
			if (comprobacionAprovada) {
				movimientoValido = movimiento;
				break;
			}
		}

		// no se encontro un movimiento valido
		if (null == movimientoValido)
			return false;

		boolean enPosibilidades = buscarPosibilidad(destino.getPosicion());
		if (!enPosibilidades) {
			Casilla maximo = movimientoValido.revisarTrayectoria(inicio, destino, tablero);
			if (maximo != destino)
				return false;
		}

		return comprobacionValida;
	}

	public void generarPosibilidades(Casilla casilla, Tablero tablero) {
		this.getPosibilidades().clear();
		for (IMovimiento movimiento : this.movimientos)
			movimiento.generarPosibilidades(casilla, tablero, this.posibilidades);
	}

	public boolean buscarPosibilidad(Tupla destino) {
		boolean enPosibilidades = false;

		for (Tupla posibilidad : this.posibilidades) {
			if (posibilidad.equals(destino)) {
				enPosibilidades = true;
				break;
			}
		}

		return enPosibilidades;
	}
}
