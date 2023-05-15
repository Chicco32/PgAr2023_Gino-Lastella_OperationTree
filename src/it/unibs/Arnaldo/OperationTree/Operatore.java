package it.unibs.Arnaldo.OperationTree;

import java.util.Map;
import java.util.Random;
import static java.util.Map.entry; 

public class Operatore {

    public static Random rand = new Random();
    public static Map<Integer, String> mappaOperatori = Map.ofEntries( //uniformare valori e operatori in un unica mappa lavorando con le rispettive chiavi e riconvertendoli all'occorenza
    entry(0, "0"), entry(1, "1"), entry(2, "2"), entry(3, "3"),
    entry(4, "4"), entry(5, "5"), entry(6, "6"), entry(7, "7"),
    entry(8, "8"), entry(9, "9"), entry(10, "+"), entry(11, "-"),
    entry(12, "*"), entry(13, "/"), entry(14, "("), entry(15, ")")
    );

    private int valore; //salverà il valore della chiave corrispondente piuttosto che effettivamente l'operaotre stesso
    
    /**
     * costruttore a partire dalla chiave
     * @param chiave
     */
    public Operatore(int chiave) throws IllegalArgumentException {
        if (chiave < mappaOperatori.size()) this.valore = chiave;
        else throw new IllegalArgumentException();
    }

    /**
     * @override del costruttore dato il valore
     * @param valore la stringa dell'oggetto da controllare
     */
    public Operatore(String valore) throws IllegalArgumentException {
        if(mappaOperatori.containsValue(valore)) {
            this.valore = parserValoreOperatore(valore);
        }
        else throw new IllegalArgumentException();
    }

    public static int parserValoreOperatore(String valore) {
        for (int i=0; i<mappaOperatori.size(); i++) {
            if (mappaOperatori.get(i).equals(valore)) return i; //cerca nella mappa la chiave corrispondente a quel valore
        }
        return mappaOperatori.size();
    }

    /**
     * Ritorna il valore gia convertito
     * @return String con il valore o il segno dell'operazione
     */
    public String getValore() { 
        return mappaOperatori.get(this.valore);
    }
    
    public void setValore(String valore) {
        this.valore = Operatore.parserValoreOperatore(valore);
    }

    /**
     * ritorna la chiave dell'operatore
     * @return l'intero chiave
     */
    public int getChiave() {
        return this.valore;
    }

        /**
     * Un qualsiasi operatore random di tipo cifra o operazione
     * @return un oggetto di tipo operatore random
     */
    public static Operatore generaOperatoreGenericoRandom () { 
        int nRandom = rand.nextInt(0, 14);
        return new Operatore(nRandom);
    }

    /**
     * Un qualsiasi operatore random che rappresenta specificamente una delle quattro operazioni
     * @return un oggetto di tipo operatore che è specificatamente + - * /
     */
    public static Operatore generaOperazioneRandom () {
        int nRandom = rand.nextInt(10, 14);
        return new Operatore(nRandom);
    }

    public static void setRand(Random rand) {
        Operatore.rand = rand;
    }

    public static void setMappaOperatori(Map<Integer, String> mappaOperatori) {
        Operatore.mappaOperatori = mappaOperatori;
    }

}
