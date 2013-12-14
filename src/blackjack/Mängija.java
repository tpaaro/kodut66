package blackjack;

/**
 * Blakcjacki mängija rakendamine
 */
public class Mängija {

	/**
	 * Mängija nimi
	 */
	private String nimi;

	/**
	 * Mängija käes olevad kaardid, kuni 10 kaarti.
	 */
	int maxKaarte = 10;
	private Kaart[] käsi = new Kaart[maxKaarte];

	/**
	 * Kaartide arv mängija käes
	 */
	private int nrKaarte;

	/**
	 * Mängija konstruktor
	 * 
	 * @param nimi
	 *            mängija nimi
	 */
	public Mängija(String nimi) {

		this.nimi = nimi;

		// muudab mängija käe tühjaks
		this.tühiKäsi();
	}

	/**
	 * Eemaldab kaardid mängija käest
	 */
	public void tühiKäsi() {

		for (int k = 0; k < 10; k++) {
			this.käsi[k] = null;
		}
		this.nrKaarte = 0;
	}

	/**
	 * Lisab kaardi mängija kätte
	 * 
	 * @param kaart
	 *            kaart, mis lisatakse
	 * @return otsustab, kas summa on <= 21
	 */
	public boolean lisaKaart(Kaart kaart) {

		// prinditakse error kui mängija käes on maksimaalne arv kaarte
		if (this.nrKaarte == maxKaarte) {
			System.err.printf("%s maksimaalne arv kaarte on saavutatud. "
					+ "Ei saa enam kaarte juurde lisada.\n ", this.nimi);
			System.exit(1);
		}

		// lisame uue kaardi järgmisesse kohta ja suurendame kaartide arvu
		this.käsi[this.nrKaarte] = kaart;
		this.nrKaarte++;

		return (this.käeSumma() <= 21);
	}

	/**
	 * Mängija kaartide summa
	 * 
	 * @return summa
	 */
	public int käeSumma() {

		int käeSumma = 0;
		int kaardiNr;
		int nrÄssi = 0;

		// lisame iga kaardi väärtuse käe summasse
		for (int k = 0; k < this.nrKaarte; k++) {

			// hetkel oleva kaardi väärtus
			kaardiNr = this.käsi[k].aruvtaNumber();

			if (kaardiNr == 0) { // Äss
				nrÄssi++;
				käeSumma += 11;
			} else if (kaardiNr >= 10) { // Poiss, emand, kuningas
				käeSumma += 10;
			} else {
				käeSumma += kaardiNr + 1;
			}
		}
		// kui meil ässad ja summa on > 21, muudame mõne/kõigi ässa(de)
		// väärtused: Äss = 1
		while (käeSumma > 21 && nrÄssi > 0) {
			käeSumma -= 10;
			nrÄssi--;

		}
		return käeSumma;
	}

	/**
	 * Prinditakse mängija kaardid
	 * 
	 * @param näita1stKaarti
	 *            otsustada, kas esimene kaart jätta peidetuks või mitte
	 */
	public void prindiKäsi(boolean näita1stKaarti) {

		System.out.printf("%s kaardid: \n", this.nimi);

		for (int k = 0; k < this.nrKaarte; k++) {
			if (k == 0 && !näita1stKaarti) {
				System.out.println("  [peidetud]");
			} else {
				System.out.printf("  %s\n", this.käsi[k].toString());

			}

		}
		System.out.println();
	}
}
