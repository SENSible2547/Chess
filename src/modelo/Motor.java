package modelo;

import java.util.ArrayList;

public class Motor {
	private boolean jaqueAlBlanco;
	private boolean jaqueAlNegros;
	private COLOR turno;
	
	private final Tablero tablero;
	private final ArrayList<Ficha> fichasSeleccionadas = new ArrayList<>();

	public Motor (Tablero tablero) {
		this.tablero = tablero;
	}
	
	public void inicio() {
		turno = COLOR.BLANCO;

		// inyectar motor a las casillas que lo necesitan
		CasillaReyInicio casillaRey;
		casillaRey = (CasillaReyInicio) tablero.getCasilla(new Tupla(7, 4));
		casillaRey.agregarMotor(this);
		casillaRey = (CasillaReyInicio) tablero.getCasilla(new Tupla(0, 4));
		casillaRey.agregarMotor(this);

		CasillaMovimientoPeonDoble casillaPeonDoble;
		for (int i = 3; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				casillaPeonDoble = (CasillaMovimientoPeonDoble) tablero.getCasilla(new Tupla(i, j));
				casillaPeonDoble.agregarMotor(this);
			}
		}
	}
	
	public boolean mover(Tupla inicio, Tupla destino) {
		boolean movimientoValido = true;

		if (!tablero.estaDentro(inicio) || !tablero.estaDentro(destino))
			return false;

		Casilla cinicio = tablero.getCasilla(inicio);
		Casilla cdestino = tablero.getCasilla(destino);

		boolean comprobacionAprovada = comprobar(cinicio, cdestino);
		if (!comprobacionAprovada)
			return false;

		// acciones de salida
		cinicio.salir(cdestino);

		cdestino.setFicha(cinicio.getFicha());
		cinicio.setFicha(tablero.getNoficha());

		// acciones de entrada
		cdestino.entrar(cinicio);

		// remover las posibilidades de las fichas que se seleccionaron
		for (int i = 0; i < fichasSeleccionadas.size(); i++) {
			fichasSeleccionadas.get(i).getPosibilidades().clear();
			fichasSeleccionadas.remove(i);
			i--;
		}

		if (COLOR.BLANCO == turno)
			turno = COLOR.NEGRO;
		else
			turno = COLOR.BLANCO;

		return movimientoValido;
	}
	
	private boolean comprobar(Casilla inicio, Casilla destino) {
		boolean comprobacionValida = true;
		Ficha inicio_ficha = inicio.getFicha();
		Ficha destino_ficha = destino.getFicha();

		// 0 Comprobar si la ficha a mover existe
		if (TIPO.NOFICHA == inicio_ficha.getTipo())
			return false;

		// 0.5 Comprobar turno
		if (this.turno != inicio_ficha.getColor())
			return false;

		// 1. Casilla no ocupada por el mismo bando
		if (destino_ficha.getColor() == inicio_ficha.getColor())
			return false;

		// 2. Movimiento Valido
		boolean comprobacionFichaAprovada = inicio_ficha.comprobar(inicio, destino, tablero);
		if (!comprobacionFichaAprovada)
			return false;

		return comprobacionValida;
	}

	private void generarPosibilidades(Casilla inicio) {
		Ficha fichaInicio = inicio.getFicha();

		fichaInicio.generarPosibilidades(inicio, tablero);

		TableroIterador iterador;
		Ficha fichaPosible;
		Tupla posibilidad;
		for (int i = 0; i < fichaInicio.getPosibilidades().size(); i++) {
			posibilidad = fichaInicio.getPosibilidades().get(i);

			// eliminar casillas del mismo bando
			fichaPosible = tablero.getCasilla(posibilidad).getFicha();
			if (fichaPosible.getColor() == fichaInicio.getColor()) {
				iterador = tablero.crearIterador(tablero.getCasilla(posibilidad), inicio);
				posibilidad = iterador.siguiente().getPosicion();

				// si es la misma casilla simplemente elimina la posibilidad
				if (posibilidad == inicio.getPosicion()) {
					fichaInicio.getPosibilidades().remove(i);
					i--;
				} else {
					fichaInicio.getPosibilidades().set(i, posibilidad);
				}
			}

		}
	}

	public ArrayList<Tupla> seleccionar(Tupla casilla) {
		ArrayList<Tupla> lista_posibilidades = new ArrayList<>();

		if (tablero.estaDentro(casilla)) {
			Ficha ficha = tablero.getCasilla(casilla).getFicha();

			// si la ficha no pertenece al bando y la casilla existe
			if (ficha.getColor() == this.turno) {
				generarPosibilidades(tablero.getCasilla(casilla));
				fichasSeleccionadas.add(ficha);
				lista_posibilidades = ficha.getPosibilidades();
			}
		}

		return lista_posibilidades;
	}

	public COLOR getTurno() {
		return turno;
	}
}
