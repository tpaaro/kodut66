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
	 * ja otsustatakse, kas soovitakse pakki segada või mitte.
	 * 
	 * @param pakkideArv	52-kaardiliste pakkide arv laual
	 * @param segada		kas segada kaarte või mitte.
	 */
	
	public Pakk( int pakkideArv, boolean segada) {


		
		this.nrKaarte = pakkideArv * 52;
		this.minuKaardid = new Kaart[this.nrKaarte];
		
		// kaardi lähteindeks
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
		
		// suvalise kaardi j kokkupanemine suvalise väärtuse i-ga
		j = sng.nextInt(this.nrKaarte);
		
		//vahetus ajutise kaardiga
		temp = this.minuKaardid[i];
		this.minuKaardid[i] = this.minuKaardid[j];
		this.minuKaardid[j] = temp;
		
	}
}
/**
 * Jagame kaardipaki pealmisele kaardile järgneva kaardi
 * 
 * @return jagatud kaart
 */
public Kaart jagaJärgKaart(){
	
	//võtab pealmise kaardi
	Kaart ülemine = this.minuKaardid[0];
	
	//liigutab kõik järgnevad kaardid vasakule ühe indeksi võrra
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
 * @param printimisNumber arv, mis näitab, kui palju ülemisi
 * 						  kaarte on printida
 */
public void prindiPakk(int kaartePrintida){
	
	for (int k = 0; k < kaartePrintida; k++) {
		System.out.printf("% 3d/%d %s \n", k+1,this.nrKaarte, 
				this.minuKaardid[k].toString());
	}
	System.out.printf("\t [%d jäänud]\n", this.nrKaarte-kaartePrintida);
}

}


