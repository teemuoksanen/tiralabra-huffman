
package pakkaaja.logiikka;

import pakkaaja.tietorakenteet.puu.Lehti;
import pakkaaja.tietorakenteet.puu.Solmu;
import pakkaaja.tietorakenteet.puu.Puu;
import java.util.PriorityQueue;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.tietorakenteet.lista.Lista;

/**
 * Luokka muodostaa aakkostolle Huffman-koodiston (merkki- ja Huffman-binäärikoodi -pari hajautustauluna).
 */
public class HuffmanKoodaaja {
    
    private Hajautustaulu<Character, Integer> aakkosto;
    private Hajautustaulu<Character, String> koodisto;
    private Puu puu;
    private Lista<Object> avain;
    
    /**
     * HuffmanKoodaajan konstruktori, joka kutsuttaessa samalla luo Huffman-puun sekä -koodiston sille syötteenä annetusta aakkostosta.
     * @param aakkosto  aakkosto, josta Huffman-koodisto muodostetaan
     */
    public HuffmanKoodaaja(Hajautustaulu<Character, Integer> aakkosto) {
        this.aakkosto = aakkosto;
        this.puu = rakennaPuu();
        this.koodisto = new Hajautustaulu<>();
        this.avain = new Lista();
        luoKoodisto(this.puu, "");
    }
    
    /**
     * Palauttaa Huffman-koodiston.
     * @return koodisto eli merkki- ja Huffman-binäärikoodi -pari hajautustauluna
     */
    public Hajautustaulu<Character, String> getKoodisto() {
        return this.koodisto;
    }
    /**
     * Palauttaa Huffman-avaimen.
     * @return avain eli Huffman-puu lyhyeksi koodattuna
     */
    public Lista<Object> getAvain() {
        return this.avain;
    }
    
    private Puu rakennaPuu() {
        PriorityQueue<Puu> puut = new PriorityQueue<>();
        
        Object[] merkit = this.aakkosto.avaimet();
        for (int i = 0; i < merkit.length; i++) {
            char merkki = (Character) merkit[i];
            Lehti lehti = new Lehti(merkki, this.aakkosto.hae(merkki));
            puut.add(lehti);
        }
        
        while (puut.size() > 1) {
            Puu a = puut.poll();
            Puu b = puut.poll();
            
            Solmu uusiSolmu = new Solmu(a, b);
            puut.add(uusiSolmu);
        }
        
        return puut.poll();
    }

    private void luoKoodisto(Puu puu, String koodijono) {
        if (puu instanceof Lehti) {
            Lehti lehti = (Lehti) puu;
            this.koodisto.lisaa(lehti.merkki, koodijono);
            this.avain.lisaa((int) 1);
            this.avain.lisaa((char) lehti.merkki);
        } else {
            Solmu solmu = (Solmu) puu;
            
            this.avain.lisaa((int) 0);
            
            String koodijonoVasen = koodijono + "0";
            luoKoodisto(solmu.vasen, koodijonoVasen);
            
            String koodijonoOikea = koodijono + "1";
            luoKoodisto(solmu.oikea, koodijonoOikea);
        }
    }
    
}
