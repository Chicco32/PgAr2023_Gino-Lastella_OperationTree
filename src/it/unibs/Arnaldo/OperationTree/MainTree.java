package it.unibs.Arnaldo.OperationTree;
public class MainTree {
    public static void main(String[] args) throws Exception {
        System.out.println("Avvio Albero");

        TreeBranch testa = TreeBranch.inizializzaAlberoRandom();

        TreeBranch.stampaOperazioni(testa);

    }
}
