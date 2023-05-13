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

    public Operatore(int valore) {
        this.valore = valore;
    }

    /**
     * Ritorna il valore gia convertito
     * @return String con il valore o il segno dell'operazione
     */
    public String getValore() { 
        return mappaOperatori.get(this.valore);
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
}
