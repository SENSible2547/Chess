package modelo;

public class Tablero {
	private final Casilla[][] tablero;
	private final Ficha noFicha = FabricaFichas.crearFicha(COLOR.NONE, TIPO.NOFICHA);
	private final static int numFilas = 8;
	private final static int numColumnas = 8;
	
	public Tablero() {
		Casilla casilla;

		tablero = new Casilla[numFilas][numColumnas];
		for (int i = 0; i < this.numFilas; i++) {
			for (int j = 0; j < numColumnas; j++) {
				if (6 == i | 1 == i)
					casilla = new CasillaPeonInicio(new Tupla(i, j));
				else if (4 == i | 3 == i)
					casilla = new CasillaMovimientoPeonDoble(new Tupla(i, j));
				else if ((7 == i && 0 == j) | (7 == i && 7 == j) | (0 == i && 0 == j) | (0 == i && 7 == j))
					casilla = new CasillaTorreInicio(new Tupla(i, j));
				else if ((7 == i && 4 == j) | (0 == i && 4 == j))
					casilla = new CasillaReyInicio(new Tupla(i, j));
				else 
					casilla = new CasillaBasica(new Tupla(i, j));

				tablero[i][j] = casilla;
				tablero[i][j].setFicha(noFicha);
			}
		}
	}
	
	public void inicializar() {
		/*
		// agregar peones
		for (int i = 0; i < 8; i++) {
			tablero[6][i].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.PEON));
			tablero[1][i].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.PEON));
		}
				
		// agregar reyes
		tablero[7][4].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.REY));
		tablero[0][4].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.REY,));
		
		// agregar reinas
		tablero[7][3].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.REINA));
		tablero[0][3].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.REINA));


		// agregar caballos
		tablero[7][1].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.CABALLO));
		tablero[7][6].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.CABALLO));
		tablero[0][1].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.CABALLO));
		tablero[0][6].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.CABALLO));
		
		// agregar torres
		tablero[7][0].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.TORRE));
		tablero[7][7].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.TORRE));
		tablero[0][0].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.TORRE));
		tablero[0][7].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.TORRE));
		
		// agregar alfiles
		tablero[7][2].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.ALFIL));
		tablero[7][5].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.ALFIL));
		tablero[0][2].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.ALFIL));
		tablero[0][5].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.ALFIL));
		*/

		tablero[7][0].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.TORRE));
		tablero[6][0].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.TORRE));
		tablero[5][0].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.TORRE));
		tablero[5][4].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.ALFIL));
		tablero[5][3].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.REINA));
		tablero[0][4].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.REY));
		tablero[5][5].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.CABALLO));
		tablero[1][0].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.PEON));
		tablero[6][5].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.PEON));
	}

	// para propositos de pruebas
	public void mostrar() {
		Ficha ficha;
		char letra = '.';

		System.out.printf("\t");
		for (int i = 0; i < numColumnas; i++)
			System.out.printf("%d\t", i);
		System.out.print("\n");

		for (int i = 0; i < numFilas; i++) {
			System.out.printf("%d\t", i);
			for (int j = 0; j < numColumnas; j++) {
				ficha = this.getCasilla(new Tupla(i, j)).getFicha();

				switch(ficha.getTipo()) {
					case PEON:
						if (ficha.getColor() == COLOR.BLANCO) letra = 'P'; else letra = 'p';
						break;
					case ALFIL:
						if (ficha.getColor() == COLOR.BLANCO) letra = 'A'; else letra = 'a';
						break;
					case TORRE:
						if (ficha.getColor() == COLOR.BLANCO) letra = 'T'; else letra = 't';
						break;
					case REINA:
						if (ficha.getColor() == COLOR.BLANCO) letra = 'R'; else letra = 'r';
						break;
					case REY:
						if (ficha.getColor() == COLOR.BLANCO) letra = 'K'; else letra = 'k';
						break;
					case CABALLO:
						if (ficha.getColor() == COLOR.BLANCO) letra = 'C'; else letra = 'c';
						break;
					default:
						letra = '.';
				}
				System.out.printf("%c\t", letra);
			}
			System.out.print("\n");
		}
	}
	
	public Casilla getCasilla(Tupla posicion) {
		return tablero[posicion.getX()][posicion.getY()];
	}

	public Ficha getNoficha() {
		return noFicha;
	}

	public TableroIterador crearIterador(Casilla inicio, Casilla destino) {
		TableroIterador iteradorAsignado = TableroIterador.fabricarIterador(this, inicio.getPosicion(), destino.getPosicion());
		return iteradorAsignado;
	}

	public static int getNumFilas() {
		return numFilas;
	}

	public static int getNumColumnas() {
		return numColumnas;
	}

	public boolean estaDentro(Tupla posicion) {
		boolean dentro = true;
		if (posicion.getX() < 0 || posicion.getY() < 0)
			dentro = false;
		else if (posicion.getX() >= numFilas || posicion.getY() >= numColumnas)
			dentro = false;
		return dentro;
	}
}
