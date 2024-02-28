package modelo;

public class Motor {
	private boolean jaqueAlBlanco;
	private boolean jaqueAlNegros;
	private COLOR turno;
	
	private final Tablero tablero;

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
		Casilla cinicio = tablero.getCasilla(inicio);
		Casilla cdestino = tablero.getCasilla(destino);

		boolean comprobacionAprovada = comprobar(cinicio, cdestino);
		if (!comprobacionAprovada)
			return false;

		// mover
		cinicio.salir(cdestino.getPosicion());

		cdestino.setFicha(cinicio.getFicha());
		cinicio.setFicha(Tablero.getNoficha());

		if (COLOR.BLANCO == turno)
			turno = COLOR.NEGRO;
		else
			turno = COLOR.BLANCO;


		return true;
	}
	
	private boolean comprobar(Casilla inicio, Casilla destino) {
		Ficha inicio_ficha = inicio.getFicha();
		Ficha destino_ficha = destino.getFicha();

		// 0. Comprobar turno
		if (turno != inicio_ficha.getColor())
			return false;

		// 0.5 Comprobar si la ficha a mover existe
		if (TIPO.NOFICHA == inicio_ficha.getTipo())
			return false;

		// 1. Casilla no ocupada por el mismo bando
		if (TIPO.NOFICHA != destino_ficha.getTipo()) {
			if (destino_ficha.getColor() == inicio_ficha.getColor())
				return false;
		}
				
		// 2. Movimiento Valido
		boolean comprobacionFichaAprovada = inicio_ficha.comprobar(inicio.posicion, destino.posicion, tablero);
		if (!comprobacionFichaAprovada)
			return false;
		
		return true;
	}

	public COLOR getTurno() {
		return turno;
	}
}
