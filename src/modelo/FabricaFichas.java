package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FabricaFichas {
	private static final MovimientoLineal lineal = new MovimientoLineal();
	private static final MovimientoCaballo caballo = new MovimientoCaballo();
	private static final MovimientoDiagonal diagonal = new MovimientoDiagonal();
	private static final MovimientoPeon peon = new MovimientoPeon(diagonal);
		
	public static Ficha crearFicha(COLOR color, TIPO tipo) {
		ArrayList<IMovimiento> movimientos = new ArrayList();
		Ficha ficha;
		
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
		default:
		}
		
		ficha = new Ficha(color, tipo, movimientos);
		ficha.setLimite(0);
		
		return ficha;
	}
	
	public static Ficha crearFicha(COLOR color, TIPO tipo, int limite) {
		ArrayList<IMovimiento> movimientos = new ArrayList();
		Ficha ficha;
		
		switch (tipo) {
		case REY:
			movimientos.add(diagonal);
			movimientos.add(lineal);
			break;
		default:
			// peon
			movimientos.add(peon);
		}
	
		ficha = new Ficha(color, tipo, movimientos);
		ficha.setLimite(limite);
		
		return ficha;
	}
}
