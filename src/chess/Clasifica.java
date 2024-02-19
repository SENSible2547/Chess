package chess;

import java.util.HashMap;

import fichitas.Alfil;
import fichitas.Caballo;
import fichitas.Fichita;
import fichitas.Peon;
import fichitas.Reina;
import fichitas.Rey;
import fichitas.Torre;

public class Clasifica {
	public HashMap<String, Fichita> initProc(){
		HashMap<String, Fichita> myCl=new HashMap<>();
		myCl.put("T", new Torre());
		myCl.put("P", new Peon());
		myCl.put("A", new Alfil());
		myCl.put("C", new Caballo());
		myCl.put("Ra", new Reina());
		myCl.put("R", new Rey());
		return myCl;
	}
}
