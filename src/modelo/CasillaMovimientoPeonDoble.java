package modelo;

public class CasillaMovimientoPeonDoble extends Casilla {
	private boolean movimientoDoble;
	private Motor motor;
	
	public CasillaMovimientoPeonDoble(Tupla posicion) {
		super(posicion);
	}
	
	public void agregarMotor(Motor motor) {
		this.motor = motor;
	}
}
