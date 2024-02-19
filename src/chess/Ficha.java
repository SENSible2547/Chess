package chess;

public class Ficha {
	private boolean color=true;
	private String tipo;
	
	
	public Ficha(boolean color, String tipo) {
		this.color=color;
		this.tipo=tipo;
	}
	public Ficha() {
		this.color=true;
		this.tipo="V";
	}
	
	

	/**
	 * @return the color
	 */
	public boolean isColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(boolean color) {
		this.color = color;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
