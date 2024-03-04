package modelo;

import java.util.ArrayList;

public class Motor {
	private final ArrayList<Casilla> jaqueANegro = new ArrayList<>();
	private final ArrayList<Casilla> jaqueABlanco = new ArrayList<>();
	private final ArrayList<Casilla> puntosJaque = new ArrayList<>();
	private final ArrayList<Ficha> fichasSeleccionadas = new ArrayList<>();
	private boolean jaqueMate;
	private COLOR turno;
	private final Tablero tablero;

	// para propositos de pruebas
	public ArrayList<Casilla> getJaqueABlanco() {
		return jaqueABlanco;
	}
	public ArrayList<Casilla> getJaqueANegro() {
		return jaqueANegro;
	}

	public Motor (Tablero tablero) {
		this.jaqueMate = false;
		this.tablero = tablero;
		this.turno = COLOR.BLANCO;

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

	public ArrayList<Tupla> seleccionar(Tupla casilla) {
		ArrayList<Tupla> lista_posibilidades = new ArrayList<>();

		if (tablero.estaDentro(casilla)) {
			Casilla casillaSeleccion = tablero.getCasilla(casilla);
			Ficha ficha = casillaSeleccion.getFicha();

			if (ficha.getColor() == this.turno) {
				generarPosibilidades(casillaSeleccion, puntosJaque);
				fichasSeleccionadas.add(ficha);

				for (Casilla posibilidad : ficha.getPosibilidades())
					lista_posibilidades.add(posibilidad.getPosicion());
			}
		}

		return lista_posibilidades;
	}

	public MENSAJE mover(Tupla inicio, Tupla destino) {
		MENSAJE mensaje = MENSAJE.MOVIMIENTOVALIDO;

		if (jaqueMate)
			return MENSAJE.JAQUEMATE;

		if (!tablero.estaDentro(inicio) || !tablero.estaDentro(destino))
			return MENSAJE.MOVIMIENTOINVALIDO;

		Casilla cinicio = tablero.getCasilla(inicio);
		Casilla cdestino = tablero.getCasilla(destino);

		restablecerPosibilidades(fichasSeleccionadas);

		boolean comprobacionAprovada = comprobar(cinicio, cdestino, puntosJaque);
		if (!comprobacionAprovada)
			return MENSAJE.MOVIMIENTOINVALIDO;

		// acciones de salida
		cinicio.salir(cdestino, tablero);

		// acciones de entrada
		// revisar jaque indirecto y directo
		cdestino.entrar(cinicio, tablero);
		boolean jaqueEntrar = revisarJaqueEntrar(cinicio, cdestino);

		// Revisar jaque mate
		if (jaqueEntrar) {
			this.jaqueMate = revisarJaqueMate(turno, puntosJaque);
			mensaje = MENSAJE.JAQUEMATE;
		}

		this.turno = obtenerContrario(this.turno);
		return mensaje;
	}

	private boolean revisarJaqueEntrar(Casilla cinicio, Casilla cdestino) {
		boolean jaqueEntrar = false;
		COLOR turno = cdestino.getFicha().getColor();
		COLOR contrario = obtenerContrario(turno);
		Casilla crey = tablero.getBando(contrario).getPosicionRey();

		Casilla cjaqueIndirecto = revisarJaqueIndirecto(cinicio, cdestino, contrario);
		if (null != cjaqueIndirecto) {
			agregarJaque(cjaqueIndirecto, contrario);
			puntosJaque.addAll(tablero.obtenerMitad(cjaqueIndirecto, crey));
			puntosJaque.add(cjaqueIndirecto); // se agrega la ficha atacante
			jaqueEntrar = true;
		}

		boolean jaqueDirecto = cdestino.comprobar(crey, tablero);
		if (jaqueDirecto) {
			agregarJaque(cdestino, contrario);
			if (null == cjaqueIndirecto) {
				puntosJaque.addAll(tablero.obtenerMitad(cdestino, crey));
				puntosJaque.add(cdestino);
				jaqueEntrar = true;
			}
		}

		// para propositos de prueba
		System.out.print("Puntos jaque\n");
		for (Casilla puntoJaque : puntosJaque) {
			Tupla jaque = puntoJaque.getPosicion();
			System.out.printf("%s,\t", jaque);
		}
		System.out.print('\n');

		return jaqueEntrar;
	}

	private boolean revisarJaqueSalir(Casilla cinicio, Casilla cdestino) {
		boolean jaqueSalida = false;
		Ficha inicioFicha = cinicio.getFicha();
		COLOR turno = inicioFicha.getColor();

		if (inicioFicha.getTipo() != TIPO.REY) {
			Casilla jaqueIndirecto = revisarJaqueIndirecto(cinicio, cdestino, turno);
			if (jaqueIndirecto != null)
				jaqueSalida = true;
		} else {
			boolean jaqueDirectoRey = revisarJaqueDirectoRey(cinicio, cdestino);
			if (jaqueDirectoRey)
				jaqueSalida = true;
		}

		return jaqueSalida;
	}

	private void restablecerPosibilidades(ArrayList<Ficha> fichasSeleccionadas) {
		for (int i = 0; i < fichasSeleccionadas.size(); i++) {
			fichasSeleccionadas.get(i).getPosibilidades().clear();
			fichasSeleccionadas.remove(i);
			i--;
		}
	}

	private boolean revisarJaqueDirectoRey(Casilla cinicio, Casilla cdestino) {
		boolean entraAJaque = false;

		Casilla cposibilidad;
		Ficha fichaPosible;

		// Volver al rey una reina y ponerla en el destino
		Ficha reyBackup = cinicio.getFicha();
		Ficha destinoBackup = cdestino.getFicha();
		COLOR colorRey = reyBackup.getColor();
		Ficha reina = FabricaFichas.crearFicha(colorRey, TIPO.REINA);

		cinicio.setFicha(tablero.getNoficha());
		cdestino.setFicha(reina);

		// Generar posibilidades
		cdestino.generarPosibilidades(tablero);

		// Revisar posibilidades
		IMovimiento movimientoValido = null;
		for (Casilla posibilidad : reina.getPosibilidades()) {
			// revisar que no sea del mismo bando
			fichaPosible = posibilidad.getFicha();
			if (fichaPosible.getColor() != colorRey) {
				movimientoValido = posibilidad.validarMovimiento(cdestino);
				if (movimientoValido != null)
					break;
			}
		}

		// se encontro una pieza del bando opuesto que llegaría al rey
		if (movimientoValido != null) {
			entraAJaque = true;
		} else {
			// Volver al rey un caballo y ponerlo en el destino
			Ficha caballo = FabricaFichas.crearFicha(colorRey, TIPO.CABALLO);
			cdestino.setFicha(caballo);

			cdestino.generarPosibilidades(tablero);

			for (Casilla posibilidad : caballo.getPosibilidades()) {
				fichaPosible = posibilidad.getFicha();

				if (fichaPosible.getTipo() == TIPO.CABALLO && fichaPosible.getColor() != colorRey) {
					entraAJaque = true;
					break;
				}
			}
		}

		cdestino.setFicha(destinoBackup);
		cinicio.setFicha(reyBackup);

		return entraAJaque;
	}

	private boolean revisarJaqueMate(COLOR bando, ArrayList<Casilla> puntosJaque) {
		boolean jaqueMate = true;
		// por parte de bando
		Bando bbando = tablero.getBando(obtenerContrario(bando));

		// comprobacion de si el rey puede escapar
		Casilla crey = bbando.getPosicionRey();

		generarPosibilidades(crey, puntosJaque);

		if (!crey.getFicha().getPosibilidades().isEmpty())
			return false;

		ArrayList<Casilla> fichas = bbando.getFichas();
		for (Casilla ficha : fichas) {
			for (Casilla puntoJaque : puntosJaque) {
				boolean comprobacionValida = ficha.comprobar(puntoJaque, tablero);
				if (comprobacionValida) {
					jaqueMate = false;
					break;
				}
			}
		}

		return jaqueMate;
	}

	private Casilla revisarJaqueIndirecto(Casilla cinicio, Casilla cdestino, COLOR bando) {
		Casilla cjaqueIndirecto = null;

		// Volver a una reina
		Ficha backup = cinicio.getFicha();
		Ficha reina = FabricaFichas.crearFicha(backup.getColor(), TIPO.REINA);
		cinicio.setFicha(reina);

		Casilla crey = tablero.getBando(bando).getPosicionRey();

		// Revisar si en las posibilidades esta el rey ya sea del mismo bando o del contrario
		boolean posibilidadRey = cinicio.comprobar(crey, tablero);

		if (posibilidadRey) {
			// Revisar si en la linea de la posibilidad del rey hay una ficha del otro bando que pueda llegar hasta la "reina"
			Casilla atacanteAtras = revisarAtacantePotencialAtras(cinicio, crey, bando);

			if (null != atacanteAtras) {
				// Revisar si el movimiento, generado por el usuario, de la "reina" se sale de la linea de la posibilidad
				Ficha backupDestino = cdestino.getFicha();
				cinicio.setFicha(tablero.getNoficha());
				cdestino.setFicha(backup);

				boolean comprobacionValida = atacanteAtras.comprobar(crey, tablero);

				if (comprobacionValida)
					cjaqueIndirecto = atacanteAtras;

				cdestino.setFicha(backupDestino);
			}
		}

		// Volver a la normalidad
		cinicio.setFicha(backup);

		return cjaqueIndirecto;
	}

	private Casilla revisarAtacantePotencialAtras(Casilla cinicio, Casilla cficha, COLOR bando) {
		Casilla atacanteAtras = null;

		// Revisar si en la linea de la posibilidad de una ficha hay una ficha del otro bando que pueda llegar a cinicio
		COLOR contrario = obtenerContrario(bando);

		TableroIterador iterador = tablero.crearIterador(cficha, cinicio);
		iterador.setNumIteraciones(Tablero.getNumColumnas() + Tablero.getNumFilas());
		iterador.setInicio(cinicio.getPosicion());

		Casilla casillaAtacante;
		Ficha atacante;
		while (iterador.tieneSiguiente()) {
			casillaAtacante = iterador.siguiente();
			atacante = casillaAtacante.getFicha();
			if (atacante.getColor() == contrario) {
				IMovimiento movimientoValido = casillaAtacante.validarMovimiento(cficha);
				if (null != movimientoValido)
					atacanteAtras = casillaAtacante;
				break;
			}
		}

		return atacanteAtras;
	}

	private boolean comprobar(Casilla inicio, Casilla destino, ArrayList<Casilla> puntosJaque) {
		boolean comprobacionValida = true;

		Ficha inicioFicha = inicio.getFicha();
		Ficha destinoFicha = destino.getFicha();

		// 0 Comprobar si la ficha a mover existe
		if (TIPO.NOFICHA == inicioFicha.getTipo())
			return false;

		// 0.5 Comprobar turno
		if (turno != inicioFicha.getColor())
			return false;

		// 1. Casilla no ocupada por el mismo bando
		if (destinoFicha.getColor() == inicioFicha.getColor())
			return false;

		// 2. Movimiento Valido
		boolean comprobacionFichaAprovada = inicio.comprobar(destino, tablero);
		if (!comprobacionFichaAprovada)
			return false;

		// 3. Revisar si se genera jaque al salir
		boolean jaqueSalida = revisarJaqueSalir(inicio, destino);
		if (jaqueSalida)
			return false;

		// 4. Si se esta en jaque revisar si la jugada quita el jaque
		ArrayList<Casilla> jaqueA = obtenerJaque(turno);
		if (!jaqueA.isEmpty()) {
			boolean jaqueEsquivado = revisarEsquivarJaque(inicio, destino, puntosJaque);
			if (!jaqueEsquivado)
				return false;
			else {
				jaqueA.clear(); // eliminar todos los jaques
				puntosJaque.clear();
			}
		}

		return comprobacionValida;
	}

	private boolean revisarEsquivarJaque(Casilla cinicio, Casilla cdestino, ArrayList<Casilla> puntosJaque) {
		boolean jaqueEsquivado = true;

		Ficha inicioBackup = cinicio.getFicha();
		COLOR bando = inicioBackup.getColor();
		ArrayList<Casilla> jaqueA = obtenerJaque(bando);

		// si hay doble jaque solo el rey puede salir de el
		if (jaqueA.size() >= 2 && inicioBackup.getTipo() != TIPO.REY)
			return false;

		// el rey escapa distinto
		boolean dentroTrayectoriaJaque = puntosJaque.contains(cdestino);
		if (inicioBackup.getTipo() == TIPO.REY) {
			if (dentroTrayectoriaJaque && puntosJaque.size() != 1) // la ficha atacante no esta al alcance del rey
				jaqueEsquivado = false;
		} else {
			if (!dentroTrayectoriaJaque)
				jaqueEsquivado = false;
		}

		return jaqueEsquivado;
	}

	private void generarPosibilidades(Casilla inicio, ArrayList<Casilla> puntosJaque) {
		Ficha fichaInicio = inicio.getFicha();
		ArrayList<Casilla> fichaPosibilidades = fichaInicio.getPosibilidades();

		if (!fichaInicio.getPosibilidades().isEmpty())
			return;

		inicio.generarPosibilidades(tablero);

		Ficha fichaPosible;
		Casilla posibilidad;
		for (int i = 0; i < fichaPosibilidades.size(); i++) {
			posibilidad = fichaPosibilidades.get(i);
			fichaPosible = posibilidad.getFicha();

			// eliminar casillas del mismo bando
			if (fichaPosible.getColor() == fichaInicio.getColor()) {
				TableroIterador iterador = tablero.crearIterador(posibilidad, inicio);

				posibilidad = iterador.siguiente();

				// si es la misma casilla simplemente elimina la posibilidad
				if (posibilidad == inicio) {
					fichaPosibilidades.remove(i);
					i--;
					continue;
				} else {
					fichaPosibilidades.set(i, posibilidad);
				}
			}

			// borrar las posibilidades que puedan generar jaque al salir
			boolean jaqueSalida = revisarJaqueSalir(inicio, posibilidad);
			if (jaqueSalida) {
				fichaPosibilidades.remove(i);
				i--;
			}
		}

		// expandir las posibilidades
		ArrayList<Casilla> expansionPosibilidades = new ArrayList<>();
		for (int i = 0; i < fichaPosibilidades.size(); i++) {
			Casilla posibilidadExpandir = fichaPosibilidades.get(i);
			expansionPosibilidades.addAll(tablero.obtenerMitad(inicio, posibilidadExpandir));
		}
		fichaPosibilidades.addAll(expansionPosibilidades);

		ArrayList<Casilla> jaqueA = obtenerJaque(fichaInicio.getColor());
		if (!jaqueA.isEmpty()) { // si se esta en jaque borrar las posibilidades que no eviten el jaque
			for (int i = 0; i < fichaPosibilidades.size(); i++) {
				posibilidad = fichaPosibilidades.get(i);

				boolean jaqueEvitado = revisarEsquivarJaque(inicio, posibilidad, puntosJaque);
				if (!jaqueEvitado) {
					fichaPosibilidades.remove(i);
					i--;
				}
			}
		}
	}

	public COLOR getTurno() {
		return turno;
	}

	private void agregarJaque(Casilla casillaAtacante, COLOR bando) {
		if (bando == COLOR.BLANCO)
			jaqueABlanco.add(casillaAtacante);
		else
			jaqueANegro.add(casillaAtacante);
	}

	private ArrayList<Casilla> obtenerJaque(COLOR bando) {
		ArrayList<Casilla> jaqueA = null;
		if (bando == COLOR.BLANCO)
			jaqueA = jaqueABlanco;
		else
			jaqueA = jaqueANegro;
		return jaqueA;
	}

	private COLOR obtenerContrario(COLOR bando) {
		COLOR contrario;
		if (COLOR.BLANCO == bando)
			contrario = COLOR.NEGRO;
		else
			contrario = COLOR.BLANCO;
		return contrario;
	}
}
