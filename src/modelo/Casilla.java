package modelo;

public abstract class Casilla {
	private final Tupla posicion;
	protected Ficha ficha;
	
	public Casilla(Tupla posicion) {
		this.posicion = posicion;
	}
		
	public void entrar(Casilla inicio, Tablero tablero) {
		Bando bando = tablero.getBando(this.ficha.getColor());
		if (this.ficha.getTipo() != TIPO.NOFICHA)
			bando.getFichas().remove(this);

		this.ficha = inicio.getFicha();
		inicio.setFicha(tablero.getNoficha());

		// nuevo bando
		bando = tablero.getBando(this.ficha.getColor());
		if (this.ficha.getTipo() == TIPO.REY)	// actualizar la posicion de los reyes
			bando.setPosicionRey(this);
		else
			bando.getFichas().add(this);

		inicio.setFicha(tablero.getNoficha());
	}
	
	public void salir(Casilla destino, Tablero tablero) {
		if (this.ficha.getTipo() != TIPO.REY) {
			Bando bando = tablero.getBando(this.ficha.getColor());
			bando.getFichas().remove(this);
		}
	}

	public Ficha getFicha() {
		return this.ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public Tupla getPosicion() {
		return posicion;
	}

	public boolean comprobar(Casilla cdestino, Tablero tablero) {
		return this.ficha.comprobar(this, cdestino, tablero);
	}

	public IMovimiento validarMovimiento(Casilla cdestino) {
		return this.ficha.validarMovimiento(this, cdestino);
	}

	public void generarPosibilidades(Tablero tablero) {
		this.ficha.generarPosibilidades(this, tablero);
	}
}
