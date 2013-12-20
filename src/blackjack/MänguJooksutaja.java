package blackjack;

import java.util.Scanner;

public class M�nguJooksutaja {

	public static void main(String[] args) {

		boolean kestvus = true;
		int m�ngeV�idetud = 0;
		int kordadeArv = 0;
		int krediit = 100;

		while (kestvus) {
			// algv��rtustame
			Scanner sk�nner = new Scanner(System.in);
			Pakk pakk = new Pakk(1, true);

			// a�gv��rtustame m�ngija objektid
			M�ngija mina = new M�ngija("M�ngija, Tanel, ");
			M�ngija diiler = new M�ngija("Diileri");

			mina.lisaKaart(pakk.jagaJ�rgKaart());
			diiler.lisaKaart(pakk.jagaJ�rgKaart());
			mina.lisaKaart(pakk.jagaJ�rgKaart());
			diiler.lisaKaart(pakk.jagaJ�rgKaart());

			// prindime algsed k�ed
			System.out.println("Kaardid on jagatud\n");
			mina.prindiK�si(true);
			diiler.prindiK�si(false);
			System.out.println("\n");

			// panuste asetamine System.out.println("Krediiti: " + krediit);
			System.out.println("Krediiti: " + krediit);
			System.out.println("Asetage panus: ");
			int panus = Integer.parseInt(sk�nner.next());

			// kui panus �letab krediidisummat, siis m��rame panuseks m�ngija
			// kogu krediidi
			if (panus > krediit) {
				panus = krediit;
				System.out.println("Panus = " + krediit);
			}

			// n�itame, kas osalejad on l�petanud kaartide lisamise
			boolean minaL�p = false;
			boolean diilerL�p = false;
			String vastus;

			while (!minaL�p || !diilerL�p) {

				// m�ngija kord
				if (!minaL�p) {
					System.out.print("Hit v�i Stay(Sisesta H v�i S ): ");
					vastus = sk�nner.next();
					System.out.println();

					// kui m�ngija otsustab lisada
					if (vastus.compareToIgnoreCase("H") == 0) {

						// lisame j�rgmise kaardi pakist ning kontrollime ega
						// m�ngija l�hki ole l�inud
						minaL�p = !mina.lisaKaart(pakk.jagaJ�rgKaart());
						mina.prindiK�si(true);
					} else {
						minaL�p = true;

					}
				}

				// diileri kord
				if (!diilerL�p) {
					if (diiler.k�eSumma() < 17) {
						System.out.println("Diiler lisab\n");
						diilerL�p = !diiler.lisaKaart(pakk.jagaJ�rgKaart());
						diiler.prindiK�si(false);
					} else {
						System.out.println("Diiler l�petab lisamise\n");
						diilerL�p = true;
					}

				}
				System.out.println();
			}

			// prindime l�plikud k�ed
			mina.prindiK�si(true);
			diiler.prindiK�si(true);

			int minuSum = mina.k�eSumma();
			int diilerSum = diiler.k�eSumma();

			// prindime k�te summad
			System.out.println("M�ngija, Tanel, summa: " + minuSum);
			System.out.println("Diileri summa: " + diilerSum);
			System.out.println();

			// otsustame kumb k�si v�itis
			if (minuSum > diilerSum && minuSum <= 21 || diilerSum > 21) {
				System.out.println("Sinu v�it! :)");
				krediit += panus;
				kordadeArv++;
				m�ngeV�idetud++;
				System.out.println();

			} else {
				System.out.println("Diileri v�it! :(");
				krediit -= panus;
				kordadeArv++;
				System.out.println();
			}

			// Kui krediiti enam pole v�i soovitakse katkestada, l�ptame m�ngu.
			System.out.println("Kas soovid mangu jatkata? Jah(1) Ei (0)");

			if (sk�nner.next().equalsIgnoreCase("0")) {
				kestvus = false;
				System.out.println("N�gemist!");
				System.out.println("Sa v�itsid " + m�ngeV�idetud + "/"
						+ kordadeArv);

			} else if (krediit == 0) {
				kestvus = false;
				System.out
						.println("Krediit on kahjuks otsas. J�rgmise korrani!");
				System.out.println("Sa v�itsid " + m�ngeV�idetud + "/"
						+ kordadeArv);
			}

		}
	}
}
