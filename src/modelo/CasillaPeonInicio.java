package modelo;

import java.util.ArrayList;

public class CasillaPeonInicio extends Casilla {
	public CasillaPeonInicio(Tupla posicion) {
		super(posicion);
	}
	
	public ArrayList<Tupla> salir(Tupla destino) {
		if (TIPO.PEON == ficha.getTipo()) {
			ficha.setLimite(-1);
			if (COLOR.BLANCO == ficha.getColor())
				ficha.setLimite(1);
		}

		return super.salir(destino);
	}
}
