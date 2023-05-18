package it.unibs.Arnaldo.OperationTree;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class MainTree {
    public static void main(String[] args) throws Exception {
        final String AVVIO = "Avvio Albero";
        final String ERRORE_DIVISIONE = "Da qualche parte ho avuto una divisione per zero";
        final String ERRORE_PARENTESI = "Hai messo qualche parentesi sbagliata";
        final String ALBERO_NON_INIZIALIZZATO = "Devi prima generare un albero";
        final String RISULTATO_CALCOLO = "Il risultato di questa espressione vale %d";
        final String SALVATAGGIO = "Ho compreso: ";
        final String INSERISCI_ESPRESSIONE = "Inserisci l'espressione da calcolare con le parentesi corrette";
        final String[] ARGOMENTI = {"Genera un albero random", "Calcola il risultato", "Genera un albero da Stringa"};
        final String TITOLO = "Benvenuto nel generatore di espressioni ad albero";

        MyMenu m1 = new MyMenu(TITOLO, ARGOMENTI);
        int scelta;
        TreeBranch testa = null;
        do {
            scelta = m1.scegli();
            switch (scelta) {
                case 1:
                    testa = TreeBranch.inizializzaAlberoRandom();
                    System.out.println(AVVIO);
                    TreeBranch.stampaOperazioni(testa);
                    System.out.println();
                    break;
                case 2:
                    if (testa == null) System.out.println(ALBERO_NON_INIZIALIZZATO);
                    else {
                        try {
                            int risultato = TreeBranch.calcolaRisultatoEspressione(testa);
                            System.out.println(String.format(RISULTATO_CALCOLO, risultato));
                        }
                        catch (IllegalArgumentException e) {
                            System.out.println(ERRORE_DIVISIONE);
                        }
                    }
                    System.out.println();
                    break;
                case 3:
                    String espressione = InputDati.leggiStringa(INSERISCI_ESPRESSIONE);
                    testa = TreeBranch.inizializzaAlberoDaStringa(espressione);
                    System.out.println(SALVATAGGIO);
                    TreeBranch.stampaOperazioni(testa);
                System.out.println();
                    break;
                default:
                    break;
            }
        } while (scelta !=0);   
    }
}
