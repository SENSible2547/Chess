package modelo;

public class Tablero {
	private final Motor motor;
	private final Casilla[][] tablero;
	
	public Tablero(Motor motor) {
		this.motor = motor;
		Casilla casilla;

		tablero = new Casilla[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				if (6 == i | 1 == i) 
					casilla = new CasillaPeonInicio(new Tupla(i, j));
				else if (4 == i | 3 == i)
					casilla = new CasillaMovimientoPeonDoble(new Tupla(i, j), motor);
				else if ((7 == i && 0 == j) | (7 == i && 7 == j) | (0 == i && 0 == j) | (0 == i && 7 == j))
					casilla = new CasillaTorreInicio(new Tupla(i, j));
				else if ((7 == i && 4 == j) | (0 == i && 4 == j))
					casilla = new CasillaReyInicio(new Tupla(i, j), motor);
				else 
					casilla = new CasillaBasica(new Tupla(i, j));

				tablero[i][j] = casilla;
				tablero[i][j].setFicha(FabricaFichas.crearFicha(null, TIPO.NOFICHA));
			}
		}
	}
	
	public void inicializar() {
		// agregar peones
		for (int i = 0; i < 8; i++) {
			tablero[6][i].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.PEON, 2));
			tablero[1][i].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.PEON, 2));
		}
				
		// agregar reyes
		tablero[7][4].setFicha(FabricaFichas.crearFicha(COLOR.BLANCO, TIPO.REY, 1));
		tablero[0][4].setFicha(FabricaFichas.crearFicha(COLOR.NEGRO, TIPO.REY, 1));
		
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
	}
	
	public void mostrar() {
		Ficha ficha;
		char letra = '.';
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ficha = tablero[i][j].getFicha();

				switch(ficha.getTipo()) {
                case PEON -> {
                    if (ficha.getColor() == COLOR.BLANCO) letra = 'P'; else letra = 'p';
                    break;
                }
                case ALFIL -> {
                    if (ficha.getColor() == COLOR.BLANCO) letra = 'A'; else letra = 'a';
                    break;
                }
                case TORRE -> {
                    if (ficha.getColor() == COLOR.BLANCO) letra = 'T'; else letra = 't';
                    break;
                }
                case REINA -> {
                    if (ficha.getColor() == COLOR.BLANCO) letra = 'R'; else letra = 'r';
                    break;
                }
                case REY -> {
                    if (ficha.getColor() == COLOR.BLANCO) letra = 'K'; else letra = 'k';
                    break;
                }
                case CABALLO -> {
                    if (ficha.getColor() == COLOR.BLANCO) letra = 'C'; else letra = 'c';
                    break;
                }
                default -> {
                    letra = '.';
				}
				}
				System.out.printf("%c\t", letra);
			}
			System.out.printf("\n");
		}
	}
}
