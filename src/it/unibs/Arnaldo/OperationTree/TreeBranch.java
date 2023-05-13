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

    public TreeBranch GeneraFigli(String str) {
        Character riferiemento = new Character(str.charAt(0)); //cast s into a char
        TreeBranch branch = new TreeBranch(riferiemento);
        if (riferiemento.isDigit(0)) {
            branch.figlioDestro = null;
            branch.figlioSinistro = null;
            return branch;
        }
        else {
            String stringDx = str.substring(5, 10);
            String stringSx = str.substring(0, 4);
            branch.figlioDestro = GeneraFigli(stringDx);
            branch.figlioSinistro = GeneraFigli(stringSx);
        }
    }
}
