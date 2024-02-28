package modelo;

import java.util.ArrayList;

enum COLOR {
	BLANCO,
	NEGRO
}

enum TIPO {
	ALFIL,
	CABALLO,
	TORRE,
	REY,
	REINA,
	PEON,
	NOFICHA
}

public class Ficha {
	private COLOR color;
	private TIPO tipo;
	private int limite;
	private ArrayList<IMovimiento> movimientos;
	private ArrayList<Tupla> posibilidades;
	
	public Ficha(COLOR color, TIPO tipo, ArrayList<IMovimiento> movimientos) {
		super();
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
		return posibilidades;
	}
	public void setPosibilidades(ArrayList<Tupla> posibilidades) {
		this.posibilidades = posibilidades;
	}
	public COLOR getColor() {
		return color;
	}
	
	public boolean comprobar(Tupla inicio, Tupla destino, Tablero tablero) {
		IMovimiento movimientoValido = null;
		for (IMovimiento movimiento : this.movimientos) {
			boolean comprobacionAprovada = movimiento.comprobar(inicio, destino, limite);
			if (comprobacionAprovada) {
				movimientoValido = movimiento;
				break;
			}
		}
		
		if (null == movimientoValido)
			return false;

		boolean trayectoriaValida = movimientoValido.revisarTrayectoria(inicio, destino, tablero);
		if (!trayectoriaValida)
			return false;
		
		return true;
	}
}
