package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FabricaFichas {
	private static final MovimientoLineal lineal = new MovimientoLineal();
	private static final MovimientoCaballo caballo = new MovimientoCaballo();
	private static final MovimientoDiagonal diagonal = new MovimientoDiagonal();
	private static final MovimientoPeon peon = new MovimientoPeon();
		
	public static Ficha crearFicha(COLOR color, TIPO tipo) {
		Ficha ficha = new Ficha(color, tipo);
		ArrayList<IMovimiento> movimientos = ficha.getMovimientos();

		int limite = Tablero.getNumFilas() - 1;
		
		switch (tipo) {
		case ALFIL:
			movimientos.add(diagonal);
			break;
		case CABALLO:
			movimientos.add(caballo);
			break;
		case REINA:
			movimientos.add(diagonal);
			movimientos.add(lineal);
			break;
		case TORRE:
			movimientos.add(lineal);
			break;
		case REY:
			movimientos.add(diagonal);
			movimientos.add(lineal);
			limite = 1;
			break;
		case PEON:
			movimientos.add(peon);
			limite = 2;
			if (COLOR.NEGRO == color)
				limite = -2;
		default:
		}
		
		ficha.setLimite(limite);
		
		return ficha;
	}
}
