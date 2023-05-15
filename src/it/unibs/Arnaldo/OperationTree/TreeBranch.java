package it.unibs.Arnaldo.OperationTree;

public class TreeBranch {
    
    public static int PIANO_DI_PARTENZA = 0;
    public static int POSIZIONE_DI_PARTENZA = 0;

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

    /**
     * la funzione che genera un albero binario che rappresenta una espresisone random 
     * @return la testa dell'albero
     */
    public static TreeBranch inizializzaAlberoRandom() {
        Operatore radice = Operatore.generaOperazioneRandom();
        TreeBranch ramo = new TreeBranch(radice,PIANO_DI_PARTENZA);
        ramo.figlioDestro = GeneraFigliRandom(PIANO_DI_PARTENZA);
        ramo.figlioSinistro = GeneraFigliRandom(PIANO_DI_PARTENZA);
        return ramo;
    } 

    /**
     * la funzione che genera un albero binario che rappresenta una espresisone random
     * @param espressione  la stringa espressione matematica da leggere e tradurre in albero binario 
     * @return la testa dell'albero
     */
    public static TreeBranch inizializzaAlberoDaStringa(String espressione) {
       String espressioneInizializzata = "(".concat(espressione).concat(")"); //mette tutta l'espressione fra parentesi per fargli capire che si tratta di un operazione
        TreeBranch ramo = generaAlberoDaStringa(espressioneInizializzata, TreeBranch.PIANO_DI_PARTENZA, POSIZIONE_DI_PARTENZA);
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

    private static TreeBranch generaAlberoDaStringa(String espressione, int pianoPrecedente, int posizione) {
        pianoPrecedente ++; //piano indica il piano dell'albero, posizione la posizione nella lettura della stringa che andra avanti per figli destri e indietro per filgi sinistri
       //posiozne +1 è il prossimo carattere, +2 è il carattere successivo


        String prossimoCarattere = espressione.substring(posizione, posizione + 1); //stacca carattere dalla stringa nella posizione ricevuta come parametro
        TreeBranch ramo = new TreeBranch(new Operatore(prossimoCarattere), pianoPrecedente); //crea il ramo a partire dal primo valore
        
        if (Operatore.parserValoreOperatore(prossimoCarattere) == 14) { //caso parentesi aperta,il piu difficile, il ramo sarà poi trasformato in ramo operazione
            //dopo una parentesi tonda ci sono due casi casi, o un altra parentesi tonda o un numero
            if (Operatore.parserValoreOperatore(espressione.substring(posizione + 1, posizione +2 )) == 14) {
                ramo.figlioSinistro = generaAlberoDaStringa(espressione, pianoPrecedente, posizione + 1); //se è ancora una tonda semplicemnte ricomincia e diverrà l'inizio del calcolo una volta trasformata
            }
            else { //per forza un numero
                ramo.figlioSinistro = generaAlberoDaStringa(espressione, pianoPrecedente, posizione + 1);
                String primoOperatore = espressione.substring(posizione + 2, posizione + 3); //salta il primo numero e cerca la prima operazione
                ramo.radice.setValore(primoOperatore); //il momento  in cui trasfrorma la parentesi in operazione
                ramo.figlioDestro = generaAlberoDaStringa(espressione, pianoPrecedente, posizione + 4); //salta il secondo numero e va alla parentesi chiusa
            }
            
        } 
        else if (Operatore.parserValoreOperatore(prossimoCarattere) == 15) { //caso parentesi chiusa, il ramo dovra trasmorarsi in ramo numero
            //dopo una parentesi tonda ci può essere di tutto ma non mi interessa davvero, invece prima ci sono due casi o un altra parentesi tonda o un numero
            String operatorePrecedente = espressione.substring(posizione -1, posizione);
            if (Operatore.parserValoreOperatore(operatorePrecedente) == 15) { 
                ramo.figlioDestro = generaAlberoDaStringa(espressione, pianoPrecedente, posizione -1);//se è ancora una tonda semplicemnte ricomincia e diverrà la fine del calcolo una volta trasformata
            }
            else { //per forza un numero
                ramo.figlioDestro = null;
                ramo.figlioSinistro = null;
                ramo.radice.setValore(operatorePrecedente);
            }
        }
        else if (Operatore.parserValoreOperatore(prossimoCarattere) <= 9 ) { //caso cifra che funge da secodno caso base
            ramo.figlioDestro = null;
            ramo.figlioSinistro = null;
        }
        //l'invocazione come operatore non avverrà mai perchè è sostituita dall'invocazione parentesi aperta
        return ramo;
    }

    /**
     * la funzione void che stampa a video l'albero binario che legge ricorsviamente
     * @param ramo il ramo che deve leggere dell'albero (se invocata da esterno è la testa dell'albero)
     */
    public static void stampaOperazioni(TreeBranch ramo) {
        if (ramo.radice.getChiave() <= 9) System.out.print(String.format("%s ",ramo.radice.getValore()));
        else {
            if (ramo.getPiano() != TreeBranch.PIANO_DI_PARTENZA) System.out.print("(");
            stampaOperazioni(ramo.figlioSinistro);
            System.out.print(String.format("%s ",ramo.radice.getValore()));
            stampaOperazioni(ramo.figlioDestro);
            if (ramo.getPiano() != TreeBranch.PIANO_DI_PARTENZA) System.out.print(") ");
        }
    }

    /**
     * funzione ricorsiva che calcola l'espressione rappresentata da un albero binario
     * @param ramo il ramo che deve leggere dell'albero (se invocata da esterno è la testa dell'albero)
     * @return a se stesso i valori parziali della soluzione, all'utente la soluzione finale
     * @throws IllegalArgumentException in caso durante l'operazione avvengano delle divisoni per zero
     */
    public static int calcolaRisultatoEspressione (TreeBranch ramo) throws IllegalArgumentException {
        int risultatoAggiornato = 0;
        if (ramo.radice.getChiave() <= 9) return risultatoAggiornato = ramo.radice.getChiave();
        else if (ramo.radice.getValore().equalsIgnoreCase("+")) risultatoAggiornato = calcolaRisultatoEspressione(ramo.figlioSinistro) + calcolaRisultatoEspressione(ramo.figlioDestro); 
        else if (ramo.radice.getValore().equalsIgnoreCase("-")) risultatoAggiornato = calcolaRisultatoEspressione(ramo.figlioSinistro) - calcolaRisultatoEspressione(ramo.figlioDestro); 
        else if (ramo.radice.getValore().equalsIgnoreCase("*")) risultatoAggiornato = calcolaRisultatoEspressione(ramo.figlioSinistro) * calcolaRisultatoEspressione(ramo.figlioDestro); 
        else if (ramo.radice.getValore().equalsIgnoreCase("/")) {
            int divisore = calcolaRisultatoEspressione(ramo.figlioDestro);
            if (divisore == 0) throw  new IllegalArgumentException();  
            else risultatoAggiornato = calcolaRisultatoEspressione(ramo.figlioSinistro) / calcolaRisultatoEspressione(ramo.figlioDestro);
        }
        return risultatoAggiornato;
    }
}
