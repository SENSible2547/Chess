package modelo;

public class CasillaMovimientoPeonDoble extends Casilla {
	private boolean movimientoDoble;
	private final Motor motor;
	
	public CasillaMovimientoPeonDoble(Tupla posicion, Motor motor) {
		super(posicion);
		this.motor = motor;
	}
	
}
