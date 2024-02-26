package modelo;

public class Motor {
	private boolean jaqueAlBlanco;
	private boolean jaqueAlNegros;
	
	private static final Tablero tablero = new Tablero(new Motor());
	
	public static void main(String[] args) {
		tablero.inicializar();
		tablero.mostrar();
	}
}
