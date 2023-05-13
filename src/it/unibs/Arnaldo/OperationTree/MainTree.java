package it.unibs.Arnaldo.OperationTree;

import it.unibs.fp.mylib.InputDati;

public class MainTree {
    public static void main(String[] args) throws Exception {
        System.out.println("Avvio Albero");

        TreeBranch testa = TreeBranch.inizializzaAlberoRandom();

        TreeBranch.stampaOperazioni(testa);
        boolean calcola = InputDati.yesOrNo("Tento di azzeccare il risultato?");
        if (calcola) {
            try {
                int risultato = TreeBranch.calcolaRisultatoEspressione(testa);
                System.out.println(String.format("Il risultato vale per caso %d?", risultato));
            }
            catch (IllegalArgumentException e) {
                System.out.println("Secondo me da qualche parte c'è stata una divisione per zero");
            }
        }


    }
}
