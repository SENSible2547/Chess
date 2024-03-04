package modelo;

import java.util.ArrayList;

public class Ficha {
	private COLOR color;
	private TIPO tipo;
	private int limite;
	private final ArrayList<IMovimiento> movimientos = new ArrayList<>();
	private final ArrayList<Casilla> posibilidades = new ArrayList<>();
	
	public Ficha(COLOR color, TIPO tipo) {
		this.color = color;
		this.tipo = tipo;
	}

	public ArrayList<IMovimiento> getMovimientos() {
		return movimientos;
	}

	public TIPO getTipo() {
		return tipo;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public ArrayList<Casilla> getPosibilidades() {
		return this.posibilidades;
	}

	public COLOR getColor() {
		return color;
	}
	
	public boolean comprobar(Casilla inicio, Casilla destino, Tablero tablero) {
		boolean comprobacionValida = false;

		// encontrar el movimiento valido para revisar la trayectoria

		if (!posibilidades.isEmpty()) {
			comprobacionValida = posibilidades.contains(destino);
		} else {
			IMovimiento movimientoValido = validarMovimiento(inicio, destino);

			if (null != movimientoValido) {
				Casilla maximo = movimientoValido.revisarTrayectoria(inicio, destino, tablero);

				if (maximo == destino)
					comprobacionValida = true;
				}
		}

		return comprobacionValida;
	}

	public IMovimiento validarMovimiento(Casilla cinicio, Casilla cdestino) {
		IMovimiento movimientoValido = null;

		boolean comprobacionAprovada;
		for (IMovimiento movimiento : this.movimientos) {
			comprobacionAprovada = movimiento.comprobar(cinicio, cdestino);
			if (comprobacionAprovada) {
				movimientoValido = movimiento;
				break;
			}
		}

		return movimientoValido;
	}

	public void generarPosibilidades(Casilla casilla, Tablero tablero) {
		for (IMovimiento movimiento : this.movimientos)
			movimiento.generarPosibilidades(casilla, tablero, this.posibilidades);
	}
}
