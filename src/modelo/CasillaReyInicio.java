package modelo;

public class CasillaReyInicio extends Casilla {
	private boolean primerMovimiento;
	private Motor motor;
	
	public CasillaReyInicio(Tupla posicion) {
		super(posicion);
	}
	
	public void agregarMotor(Motor motor) {
		this.motor = motor;
	}
}
