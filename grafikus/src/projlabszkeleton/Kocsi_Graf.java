package projlabszkeleton;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Kocsi_Graf {

	private static BufferedImage kofekete;
	private static BufferedImage kofekete_kanyar;
	private static BufferedImage kokek;
	private static BufferedImage kokek_kanyar;
	private static BufferedImage koszurke;
	private static BufferedImage koszurke_kanyar;
	private static BufferedImage kozold;
	private static BufferedImage kozold_kanyar;
	private static BufferedImage kovilagoskek;
	private static BufferedImage kovilagoskek_kanyar;
	private static BufferedImage korozsa;
	private static BufferedImage korozsa_kanyar;
	private static BufferedImage kopiros;
	private static BufferedImage kopiros_kanyar;
	private static BufferedImage kofeher;
	private static BufferedImage kofeher_kanyar;
	private static BufferedImage kosarga;
	private static BufferedImage kosarga_kanyar;
	private static BufferedImage koteher;
	private static BufferedImage koteher_kanyar;
	private static BufferedImage mozdony;
	private static BufferedImage mozdony_kanyar;
	
	/**
	 * Konstruktor létrehozza a kocsi rajzoló objektumot
	 * betölti a képeket a fájlból
	 */
	public Kocsi_Graf(){
		try{
			kofekete = ImageIO.read(new File("projekt/kocsi_black.png"));
			kofekete_kanyar = ImageIO.read(new File("projekt/kocsi_black_kanyar.png"));
			kokek = ImageIO.read(new File("projekt/kocsi_blue.png"));
			kokek_kanyar = ImageIO.read(new File("projekt/kocsi_blue_kanyar.png"));
			koszurke = ImageIO.read(new File("projekt/kocsi_gray.png"));
			koszurke_kanyar = ImageIO.read(new File("projekt/kocsi_gray_kanyar.png"));
			kozold = ImageIO.read(new File("projekt/kocsi_green.png"));
			kozold_kanyar = ImageIO.read(new File("projekt/kocsi_green_kanyar.png"));
			kovilagoskek = ImageIO.read(new File("projekt/kocsi_lightblue.png"));
			kovilagoskek_kanyar = ImageIO.read(new File("projekt/kocsi_lightblue_kanyar.png"));
			korozsa = ImageIO.read(new File("projekt/kocsi_pink.png"));
			korozsa_kanyar = ImageIO.read(new File("projekt/kocsi_pink_kanyar.png"));
			kopiros = ImageIO.read(new File("projekt/kocsi_red.png"));
			kopiros_kanyar = ImageIO.read(new File("projekt/kocsi_red_kanyar.png"));
			kofeher = ImageIO.read(new File("projekt/kocsi_white.png"));
			kofeher_kanyar = ImageIO.read(new File("projekt/kocsi_white_kanyar.png"));
			kosarga = ImageIO.read(new File("projekt/kocsi_yellow.png"));
			kosarga_kanyar = ImageIO.read(new File("projekt/kocsi_yellow_kanyar.png"));
			koteher = ImageIO.read(new File("projekt/kocsi_teher.png"));
			koteher_kanyar = ImageIO.read(new File("projekt/kocsi_teher_kanyar.png"));
			mozdony = ImageIO.read(new File("projekt/mozdony_red.png"));
			mozdony_kanyar = ImageIO.read(new File("projekt/mozdony_red_kanyar.png"));
		}
		catch (IOException e){
			System.out.println("fájl beolvasás sikertelen (kocsi.png)");
			e.printStackTrace();
		}
	}
	
	/**
	 * kirajzolja a kocsit arra az elemre ami meghívja
	 * 	 * 
	 * @param g A grafikus objektum amire rajzolni kell
	 * @param k A modellbeli kocsi amit ki kell rajzolni
	 * @param allapot a sin allapota amire ki kell rajzolni a kocsit
	 * @return g az uj már megrajzolt objektum
	 */
	public Graphics2D draw(Graphics2D g, Kocsi k, String allapot){
		//milyen színű a kocsi? Ez alapján választjuk ki a képet.
		BufferedImage kocsiEgyenes = null;
		BufferedImage kocsiKanyar = null;
		Color kocsiColor = k.szin();
		if(k instanceof Mozdony){
			kocsiEgyenes = mozdony;
			kocsiKanyar = mozdony_kanyar;
		}
		else if(k instanceof TeherKocsi){
			kocsiEgyenes = koteher;
			kocsiKanyar = koteher_kanyar;
		}
		else if(k instanceof Kocsi){
			if(kocsiColor.equals(Color.black)){
				kocsiEgyenes = kofekete;
				kocsiKanyar = kofekete_kanyar;
			}
			if(kocsiColor.equals(Color.blue)){
				kocsiEgyenes = kokek;
				kocsiKanyar = kokek_kanyar;
			}
			if(kocsiColor.equals(Color.gray)){
				kocsiEgyenes = koszurke;
				kocsiKanyar = koszurke_kanyar;
			}
			if(kocsiColor.equals(Color.green)){
				kocsiEgyenes = kozold;
				kocsiKanyar = kozold_kanyar;
			}
			if(kocsiColor.equals(Color.cyan)){
				kocsiEgyenes = kovilagoskek;
				kocsiKanyar = kovilagoskek_kanyar;
			}
			if(kocsiColor.equals(Color.pink)){
				kocsiEgyenes = korozsa;
				kocsiKanyar = korozsa_kanyar;
			}
			if(kocsiColor.equals(Color.red)){
				kocsiEgyenes = kopiros;
				kocsiKanyar = kopiros_kanyar;
			}
			if(kocsiColor.equals(Color.white)){
				kocsiEgyenes = kofeher;
				kocsiKanyar = kofeher_kanyar;
			}
			if(kocsiColor.equals(Color.yellow)){
				kocsiEgyenes = kosarga;
				kocsiKanyar = kosarga_kanyar;
			}
		}
		

		if(k.sinem() instanceof Keresztezodes){
			g=keresztrajzolas(g, kocsiEgyenes, k);
			return g;
		}
		if(k.sinem() instanceof Valto){
			g=valtorajzolas(g, kocsiEgyenes,kocsiKanyar, k, allapot);
			return g;
		}
		
		if(allapot == "BF" || allapot == "FJ" || allapot == "JL" || allapot == "LB"){		//kanyarban vagyunk
			g.rotate(Math.PI, 25, 25);
			g.drawImage(kocsiKanyar, null, 0, 0);
			/*if(kocsiColor.equals(Color.white) || kocsiColor.equals(Color.yellow)){
				g.setColor(Color.black);
				g.drawString("" + k.getUtasokSzama(), 12, 22);
			}
			else if(!(k instanceof Mozdony) && !(k instanceof TeherKocsi)){		//ha mozdony v teherkocsi, ne rajzoljuk ki az utasok számát
				g.setColor(Color.white);
				g.drawString("" + k.getUtasokSzama(), 12, 22);
			}*/
			g=utasrajzolas_kanyar(g, k);
			g.rotate(-Math.PI, 25, 25);
		}
		else{										//egyenesben vagyunk
			g.drawImage(kocsiEgyenes, null, 0, 0);
			g=utasrajzolas_egyenes(g, k);
		}
		/*
		if (k instanceof Mozdony){
			g.drawImage(mozdony, null, 0, 0);
		}
		else{
			g.drawImage(kopiros, null, 0, 0);
		}
		*/
		return g;
	}
	
	/**
	 * kirajzolja a kocsit A kereszteződésre (a paintComponent hívja)
	 * A kereszteződésnél figyelni kell, hogy függőleges vagy vozszintes irányban haladunk
	 * 	 * 
	 * @param g A grafikus objektum amire rajzolni kell
	 * @param kep a megfelelő színű kocsi képe
	 * @param k a modell beli kocsi amit ki kell rajzolni
	 * @return g az uj már megrajzolt objektum
	 */
	private Graphics2D keresztrajzolas(Graphics2D g, BufferedImage kep, Kocsi k){
		Keresztezodes ker=(Keresztezodes)k.sinem();
		Sin vizsgalt=null;
		if (k.getelotte()!=null){
			vizsgalt=k.getelotte().sinem();
		}
		else {
			vizsgalt=k.getmogotte().sinem();
		}
		if(vizsgalt.equals(ker.getsin3()) || vizsgalt.equals(ker.getsin4())){
			g.rotate(Math.PI/2, 25, 25);
			g.drawImage(kep, null, 0, 0);
			g.rotate(-Math.PI/2, 25, 25);
		}
		else{
			g.drawImage(kep, null, 0, 0);
		}
		g=utasrajzolas_egyenes(g, k);
		return g;
	}

	/**
	 * kirajzolja a kocsit A váltóra (a paintComponent hívja)
	 * A váltónál figyelni kell kanyarodik-e vagy sem
	 * 	 * 
	 * @param g A grafikus objektum amire rajzolni kell
	 * @param kep_egyenes a megfelelő színű kocsi egyenes képe 
	 * @param kep_kanyar a megfelelő színű kocsi kanyarodó képe
	 * @param k a modell beli kocsi amit ki kell rajzolni
	 * @param string a váltó állapota
	 * @return g az uj már megrajzolt objektum
	 */
	private Graphics2D valtorajzolas(Graphics2D g, BufferedImage kep_egyenes, BufferedImage kep_kanyar, Kocsi k, String allapot){
		Valto valt=(Valto)k.sinem();
		Sin elozo=null;
		Sin kovetkezo=null;
		if (k.getelotte()!=null){
			kovetkezo=k.getelotte().sinem();
		}
		if(k.getmogotte()!=null){
			elozo=k.getmogotte().sinem();
		}
		if (kovetkezo==null){
			try {
				kovetkezo=k.sinem().kovetkezo(elozo);
			} catch (EndGameException e) {
				// no worries
				e.printStackTrace();
			}
		}
		if(valt.getsin3().equals(kovetkezo) || valt.getsin3().equals(elozo)){
			if (allapot.equals("FLB") || allapot.equals("BJF")){
				g.drawImage(kep_kanyar, null, 0, 0);
			}
			if (allapot.equals("BJL") || allapot.equals("LFB")){
				g.drawImage(kep_kanyar, null, 0, 0);				
			}
			if (allapot.equals("JBF") || allapot.equals("FLJ")){
				g.drawImage(kep_kanyar, null, 0, 0);
			}
			if (allapot.equals("JBL") || allapot.equals("LFJ")){
				g.drawImage(kep_kanyar, null, 0, 0);
			}
			g.rotate(Math.PI/2, 25, 25);
			g=utasrajzolas_kanyar(g, k);
			g.rotate(-Math.PI/2, 25, 25);
		}
		else{
			g.drawImage(kep_egyenes, null, 0, 0);				
		}
		g=utasrajzolas_egyenes(g, k);
		return g;
	}

	/**
	 * Egyenesen álló kocsira kiírja az utasok számát
	 * megfelelően olvasható színnel és csak akkor ha nem mozdony vagy teherkocsi
	 * 	 * 
	 * @param g A grafikus objektum amire rajzolni kell
	 * @param k a modell beli kocsi aminek ki kell rajzolni az utasainak a számát
	 * @return g az uj már megrajzolt objektum
	 */
	private Graphics2D utasrajzolas_egyenes(Graphics2D g, Kocsi k){
		if((k instanceof Mozdony) || (k instanceof TeherKocsi)){		//ha mozdony v teherkocsi, ne rajzoljuk ki az utasok számát
			return g;
		}
		if(k.szin().equals(Color.white) || k.szin().equals(Color.yellow)){
			g.setColor(Color.black);
		}
		else {	
			g.setColor(Color.white);
		}
		g.drawString("" + k.getUtasokSzama(), 22, 30);
		return g;
	}
	
	/**
	 * kanyarodó kocsira kiírja az utasok számát
	 * megfelelően olvasható színnel és csak akkor ha nem mozdony vagy teherkocsi
	 * 	 * 
	 * @param g A grafikus objektum amire rajzolni kell
	 * @param k a modell beli kocsi aminek ki kell rajzolni az utasainak a számát
	 * @return g az uj már megrajzolt objektum
	 */
	private Graphics2D utasrajzolas_kanyar(Graphics2D g, Kocsi k){
		if((k instanceof Mozdony) || (k instanceof TeherKocsi)){		//ha mozdony v teherkocsi, ne rajzoljuk ki az utasok számát
			return g;
		}
		if(k.szin().equals(Color.white) || k.szin().equals(Color.yellow)){
			g.setColor(Color.black);
		}
		else {	
			g.setColor(Color.white);
		}
		g.drawString("" + k.getUtasokSzama(), 12, 22);
		return g;
	}
}
