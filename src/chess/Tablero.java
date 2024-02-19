package chess;

// true = Blanco 
// false = negro 

//Vacio = V
// Torre = T
// Caballo = C
// Alfil = A
// Rey = R
// Reina = Ra
// Peon = P

public class Tablero {
	public static Ficha matriz[][] = new Ficha[8][8];
	
	public Tablero() {
		// inicialización del tablero 
		
		//inicialización la matriz con vacio
		for(int i = 0;i <matriz.length;i++){
	      for(int j = 0;j <matriz.length;j++){
	        matriz[j][i] = new Ficha();}
	    }
		
		//las 4 torres
		Ficha TorreNegrai= new Ficha(false,"T"); 
		matriz[0][0]=TorreNegrai;
		Ficha TorreNegrad= new Ficha(false,"T"); 
		matriz[7][0]=TorreNegrad;
		Ficha TorreBlancai= new Ficha(true,"T"); 
		matriz[0][7]=TorreBlancai;
		Ficha TorreBlancad= new Ficha(true,"T"); 
		matriz[7][7]=TorreBlancad;
		
		//los 4 alfiles
		Ficha AlfilNegroi=new Ficha(false,"A");
		matriz[2][0]=AlfilNegroi;
		Ficha AlfilNegrod=new Ficha(false,"A");
		matriz[5][0]=AlfilNegrod;
		Ficha AlfilBlancoi=new Ficha(true,"A");
		matriz[2][7]=AlfilBlancoi;
		Ficha AlfilBlancod=new Ficha(true,"A");
		matriz[5][7]=AlfilBlancod;
		
		//los reyes
		Ficha ReyNegro=new Ficha(false,"R");
		matriz[3][0]=ReyNegro;
		Ficha ReyBlanco=new Ficha(true,"R");
		matriz[3][7]=ReyBlanco;
		
		//las reinas
		Ficha ReinaNegro=new Ficha(false,"Ra");
		matriz[4][0]=ReinaNegro;
		Ficha ReinaBlanco=new Ficha(true,"Ra");
		matriz[4][7]=ReinaBlanco;
		
		//los 4 caballos
		Ficha CaballoNegroi=new Ficha(false,"C");
		matriz[1][0]=CaballoNegroi;
		Ficha CaballoNegrod=new Ficha(false,"C");
		matriz[6][0]=CaballoNegrod;
		Ficha CaballoBlancoi=new Ficha(true,"C");
		matriz[1][7]=CaballoBlancoi;
		Ficha CaballoBlancod=new Ficha(true,"C");
		matriz[6][7]=CaballoBlancod;
		
		//los peones 
		//blancos
		for(int i= 0;i <matriz.length;i++ ) {
			matriz[i][6]=new Ficha(true,"P");
		}
		//negros
		for(int i= 0;i <matriz.length;i++ ) {
			matriz[i][1]=new Ficha(false,"P");
		}
		
	}
	
	public int movimiento(int x, int y, int i , int j) {
		
		Clasifica clasificacion=new Clasifica();
		clasificacion.initProc();
		
		return j;
		
		
	}
	
	
}	
