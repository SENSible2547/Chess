package modelo;

public class CasillaPeonInicio extends Casilla {
	public CasillaPeonInicio(Tupla posicion) {
		super(posicion);
	}

	public void salir(Casilla destino) {
		if (TIPO.PEON == ficha.getTipo()) {
			if (COLOR.BLANCO == ficha.getColor())
				ficha.setLimite(1);
			else
				ficha.setLimite(-1);
		}
	}
}
