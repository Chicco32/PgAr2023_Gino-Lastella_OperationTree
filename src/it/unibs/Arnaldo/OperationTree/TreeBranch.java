package it.unibs.Arnaldo.OperationTree;

public class TreeBranch {
    
    public static int PIANO_DI_PARTENZA = 0;

    private Operatore radice;
    private int piano; //l'altezza ipotetica a cui si trova qesta radice
    private TreeBranch figlioDestro;
    private TreeBranch figlioSinistro;

    private TreeBranch(Operatore padre, int piano) {
        this.radice = padre;
        this.piano = piano;
    }
    
    /**
     * Ritorna la radice di quello specifico ramo dell'albero binario contenente l'operatore specifico del ramo
     * @return un oggetto di tipo Operatore con il valore del ramo
     */
    public Operatore getRadice() {
        return radice;
    }

    public int getPiano() {
        return piano;
    }

    public TreeBranch getFiglioDestro() {
        return figlioDestro;
    }

    public TreeBranch getFiglioSinistro() {
        return figlioSinistro;
    }

    public boolean hasChildren() {
        if(this.figlioDestro == null) return false;
        else if (this.figlioSinistro == null) return false;
        else return true;
    }

    public static TreeBranch inizializzaAlberoRandom() {
        Operatore radice = Operatore.generaOperazioneRandom();
        TreeBranch ramo = new TreeBranch(radice,PIANO_DI_PARTENZA);
        ramo.figlioDestro = GeneraFigliRandom(PIANO_DI_PARTENZA);
        ramo.figlioSinistro = GeneraFigliRandom(PIANO_DI_PARTENZA);
        return ramo;
    } 

    private static TreeBranch GeneraFigliRandom(int pianoPrecedente) {
        pianoPrecedente ++;
        Operatore radice = Operatore.generaOperatoreGenericoRandom();
        TreeBranch ramo = new TreeBranch(radice, pianoPrecedente);
        if (radice.getChiave() <= 9) { //se è una cifra
            ramo.figlioDestro = null;
            ramo.figlioSinistro = null;
        }
        else { //se è un operatore
            ramo.figlioSinistro = GeneraFigliRandom(pianoPrecedente);
            ramo.figlioDestro = GeneraFigliRandom(pianoPrecedente);
        }
        return ramo;
    }

    public static void stampaOperazioni(TreeBranch ramo) {
        if (ramo.radice.getChiave() <= 9) System.out.print(String.format("%s ",ramo.radice.getValore()));
        else {
            if (ramo.getPiano() != 0) System.out.print("(");
            stampaOperazioni(ramo.figlioSinistro);
            System.out.print(String.format("%s ",ramo.radice.getValore()));
            stampaOperazioni(ramo.figlioDestro);
            if (ramo.getPiano() != 0) System.out.print(") ");
        }
    }

    public static int calcolaRisultatoEspressione (TreeBranch ramo) throws IllegalArgumentException {
        int risultatoAggiornato = 0;
        if (ramo.radice.getChiave() <= 9) return risultatoAggiornato = ramo.radice.getChiave();
        else if (ramo.radice.getValore().equalsIgnoreCase("+")) risultatoAggiornato = calcolaRisultatoEspressione(ramo.figlioSinistro) + calcolaRisultatoEspressione(ramo.figlioDestro); 
        else if (ramo.radice.getValore().equalsIgnoreCase("-")) risultatoAggiornato = calcolaRisultatoEspressione(ramo.figlioSinistro) - calcolaRisultatoEspressione(ramo.figlioDestro); 
        else if (ramo.radice.getValore().equalsIgnoreCase("*")) risultatoAggiornato = calcolaRisultatoEspressione(ramo.figlioSinistro) * calcolaRisultatoEspressione(ramo.figlioDestro); 
        else if (ramo.radice.getValore().equalsIgnoreCase("+")) {
            int divisore = calcolaRisultatoEspressione(ramo.figlioDestro);
            if (divisore == 0) throw  new IllegalArgumentException();  
            else risultatoAggiornato = calcolaRisultatoEspressione(ramo.figlioSinistro) / calcolaRisultatoEspressione(ramo.figlioDestro);
        }
        return risultatoAggiornato;
    }
}
