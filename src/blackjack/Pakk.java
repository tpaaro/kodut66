package blackjack;

import java.util.Random;

/**
 * Kaardipaki rakendamine
 */
public class Pakk {
	/**
	 * Kaartide array, mis moodustab kaardipaki. Pealmine
	 * kaart asub esimesel indeksil
	 */
	private Kaart [] minuKaardid;
	
	/**
	 * Kaartide arv pakis
	 */
	private int nrKaarte;
	
	/**
	 *Algse kaardipaki, mis on ilma segamata, konstruktor 
	 */
	
	public Pakk(){
		
		//kutsub välja teise konstruktori, määratleb ära segamata paki.
		this(1, false);
	}
	
	/**
	 * Pakkide koguarvu konstruktor (s.t. kui palju 52 kaardilisi pakke on laual)
	 * ja otsustatakse, kas soovitakse pakki segada v�i mitte.
	 * 
	 * @param pakkideArv	52-kaardiliste pakkide arv laual
	 * @param segada		kas segada kaarte v�i mitte.
	 */
	
	public Pakk( int pakkideArv, boolean segada) {


		
		this.nrKaarte = pakkideArv * 52;
		this.minuKaardid = new Kaart[this.nrKaarte];
		
		// kaardi l�hteindeks
		int k = 0;
		// iga paki jaoks
		for (int p = 0; p < pakkideArv; p++) {
			
			// iga masti jaoks
			for(int m = 0; m < 4; m++) {
				
				// iga numbri jaoks
				for (int n = 0; n <= 12; n++){
					
					// uue kaardi lisamine pakki
					this.minuKaardid[k] = new Kaart(Mast.values()[m], n);
					k++;
					
				}
			}
		}
		if(segada){
			this.segamine();
		}
	}

	/**
	 * Segame kaardipakki, moodustades suvalisi numbri ja masti kombinatsioone.
	 */
	public void segamine(){
		
	// suvaliste numbrite generaator
	Random sng = new Random();
	
	// ajutine kaart
	Kaart temp;
	
	int j;
	for (int i = 0; i < this.nrKaarte; i++){
		
		// suvalise kaardi saamine
		j = sng.nextInt(this.nrKaarte);
		
		//vahetus ajutise kaardiga
		temp = this.minuKaardid[i];
		this.minuKaardid[i] = this.minuKaardid[j];
		this.minuKaardid[j] = temp;
		
	}
}
/**
 * Jagame kaardipaki pealmisele kaardile j�rgneva kaardi
 * 
 * @return jagatud kaart
 */
public Kaart jagaJärgKaart(){
	
	//v�tab pealmise kaardi
	Kaart ülemine = this.minuKaardid[0];
	
	//liigutab k�ik j�rgnevad kaardid vasakule �he indeksi v�rra
	for (int k = 1; k < this.nrKaarte; k++){
		this.minuKaardid[k - 1] = this.minuKaardid[k];
		
	}
	this.minuKaardid[this.nrKaarte-1]= null;
	
	//kaartide eemaldamine pakist
	this.nrKaarte--;
	
	return ülemine;
}
/**
 * Prindib paki pealmised kaardid.
 * 
 * @param printimisNumber arv, mis n�itab, kui palju �lemisi
 * 						  kaarte on printida
 */

}




