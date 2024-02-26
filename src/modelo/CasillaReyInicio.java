package modelo;

public class CasillaReyInicio extends Casilla {
	private boolean primerMovimiento;
	private final Motor motor;
	
	public CasillaReyInicio(Tupla posicion, Motor motor) {
		super(posicion);
		this.motor = motor;
	}
}
