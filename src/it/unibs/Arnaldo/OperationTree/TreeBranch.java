package it.unibs.Arnaldo.OperationTree;

import java.util.HashMap;

public class TreeBranch {
    
    public static HashMap<Integer,String> mappaOperatori = new HashMap<>(14); //i numeri da 0 a 10 e la corrispettiva chiave e i 4 simboli con chiave

    private Operatori radice;
    private TreeBranch figlioDestro;
    private TreeBranch figlioSinistro;

    public TreeBranch(Operatori padre) {
        this.radice = padre;
    }

    public boolean hasChildren() {
        
    }

    public void GeneraFigli(String str) {
        char s = str.charAt(0);
        
    }
}
