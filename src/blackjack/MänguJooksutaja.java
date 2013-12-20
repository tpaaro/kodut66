package blackjack;

import java.util.Scanner;

public class MänguJooksutaja {

	public static void main(String[] args) {

		boolean kestvus = true;
		int mängeVõidetud = 0;
		int kordadeArv = 0;
		int krediit = 100;

		while (kestvus) {
			// algväärtustame
			Scanner skänner = new Scanner(System.in);
			Pakk pakk = new Pakk(1, true);

			// aögväärtustame mängija objektid
			Mängija mina = new Mängija("Mängija, Tanel, ");
			Mängija diiler = new Mängija("Diileri");

			mina.lisaKaart(pakk.jagaJärgKaart());
			diiler.lisaKaart(pakk.jagaJärgKaart());
			mina.lisaKaart(pakk.jagaJärgKaart());
			diiler.lisaKaart(pakk.jagaJärgKaart());

			// prindime algsed käed
			System.out.println("Kaardid on jagatud\n");
			mina.prindiKäsi(true);
			diiler.prindiKäsi(false);
			System.out.println("\n");

			// panuste asetamine System.out.println("Krediiti: " + krediit);
			System.out.println("Krediiti: " + krediit);
			System.out.println("Asetage panus: ");
			int panus = Integer.parseInt(skänner.next());

			// kui panus ületab krediidisummat, siis määrame panuseks mängija
			// kogu krediidi
			if (panus > krediit) {
				panus = krediit;
				System.out.println("Panus = " + krediit);
			}

			// näitame, kas osalejad on lõpetanud kaartide lisamise
			boolean minaLõp = false;
			boolean diilerLõp = false;
			String vastus;

			while (!minaLõp || !diilerLõp) {

				// mängija kord
				if (!minaLõp) {
					System.out.print("Hit või Stay(Sisesta H või S ): ");
					vastus = skänner.next();
					System.out.println();

					// kui mängija otsustab lisada
					if (vastus.compareToIgnoreCase("H") == 0) {

						// lisame järgmise kaardi pakist ning kontrollime ega
						// mängija lõhki ole läinud
						minaLõp = !mina.lisaKaart(pakk.jagaJärgKaart());
						mina.prindiKäsi(true);
					} else {
						minaLõp = true;

					}
				}

				// diileri kord
				if (!diilerLõp) {
					if (diiler.käeSumma() < 17) {
						System.out.println("Diiler lisab\n");
						diilerLõp = !diiler.lisaKaart(pakk.jagaJärgKaart());
						diiler.prindiKäsi(false);
					} else {
						System.out.println("Diiler lõpetab lisamise\n");
						diilerLõp = true;
					}

				}
				System.out.println();
			}

			// prindime lõplikud käed
			mina.prindiKäsi(true);
			diiler.prindiKäsi(true);

			int minuSum = mina.käeSumma();
			int diilerSum = diiler.käeSumma();

			// prindime käte summad
			System.out.println("Mängija, Tanel, summa: " + minuSum);
			System.out.println("Diileri summa: " + diilerSum);
			System.out.println();

			// otsustame kumb käsi võitis
			if (minuSum > diilerSum && minuSum <= 21 || diilerSum > 21) {
				System.out.println("Sinu võit! :)");
				krediit += panus;
				kordadeArv++;
				mängeVõidetud++;
				System.out.println();

			} else {
				System.out.println("Diileri võit! :(");
				krediit -= panus;
				kordadeArv++;
				System.out.println();
			}

			// Kui krediiti enam pole või soovitakse katkestada, lõptame mängu.
			System.out.println("Kas soovid mangu jatkata? Jah(1) Ei (0)");

			if (skänner.next().equalsIgnoreCase("0")) {
				kestvus = false;
				System.out.println("Nägemist!");
				System.out.println("Sa võitsid " + mängeVõidetud + "/"
						+ kordadeArv);

			} else if (krediit == 0) {
				kestvus = false;
				System.out
						.println("Krediit on kahjuks otsas. Järgmise korrani!");
				System.out.println("Sa võitsid " + mängeVõidetud + "/"
						+ kordadeArv);
			}

		}
	}
}
