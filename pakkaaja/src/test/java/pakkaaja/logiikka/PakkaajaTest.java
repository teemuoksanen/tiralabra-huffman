package pakkaaja.logiikka;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

import pakkaaja.logiikka.huffmanpuu.Lehti;
import pakkaaja.logiikka.huffmanpuu.Puu;
import pakkaaja.logiikka.huffmanpuu.Solmu;

public class PakkaajaTest {
    
    Pakkaaja pakkaaja1;
    Pakkaaja pakkaaja2;
    
    AakkostoMock aakkostoMock = new AakkostoMock();
    
    @Before
    public void setUpClass() {
        File testitiedosto1 = new File("src/test/resources/test.txt");
        File testitiedosto2 = new File("src/test/resources/test2.txt");
        
        try {
            pakkaaja1 = new Pakkaaja(testitiedosto1);
            pakkaaja2 = new Pakkaaja(testitiedosto2);
        } catch (FileNotFoundException ex) {
            System.out.println("VIRHE! Testitiedostoja ei löytynyt.");
        } catch (IOException ex) {
            System.out.println("VIRHE! Bittivirran lukeminen ei onnistunut.");
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void luoOikeanAakkoston() {
        assertEquals(pakkaaja1.getAakkosto(), aakkostoMock.getAakkosto(1));
        assertEquals(pakkaaja2.getAakkosto(), aakkostoMock.getAakkosto(2));
    }
    
    @Test
    public void puunLuontiOnnistuu() {
        Puu puu = new Puu(1);
        assertEquals(puu.maara, 1);
    }
    
    @Test
    public void puutJarjestyvatMaaranMukaan() {
        PriorityQueue<Puu> puut = new PriorityQueue<>();
        Puu puuSuurin = new Puu(5);
        puut.add(puuSuurin);
        Puu puuPienin = new Puu(1);
        puut.add(puuPienin);
        Puu puuKeski = new Puu(3);
        puut.add(puuKeski);
        assertEquals(puut.poll(), puuPienin);
    }
    
    @Test
    public void lehdenLuontiOnnistuu() {
        Lehti lehti = new Lehti('a', 1);
        assertEquals(lehti.maara, 1);
        assertEquals(lehti.merkki, 'a');
    }
    
    @Test
    public void solmunLuontiOnnistuu() {
        Puu vasen = new Puu(1);
        Puu oikea = new Puu(2);
        Solmu solmu = new Solmu(vasen, oikea);
        assertEquals(solmu.maara, 3);
    }
    
    @Test
    public void palauttaaOikeanKoodiston() {
        HuffmanKoodaaja koodaaja = new HuffmanKoodaaja(pakkaaja1.getAakkosto());
        HashMap<Character, String> koodisto = koodaaja.getKoodisto();
        assertEquals(koodisto.keySet().size(), 5);
        assertEquals(koodisto.get('a'), "11");
        assertEquals(koodisto.get('e'), "010");
    }
    
    @Test
    public void testinNimi() {
        
    }
    
}
