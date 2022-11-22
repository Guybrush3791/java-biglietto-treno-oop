package org.trenitalia;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Biglietto {

	private static final BigDecimal COSTO_KM = new BigDecimal(.21); 
	private static final BigDecimal SCONTO_OVER_65 = new BigDecimal(.40);
	private static final BigDecimal SCONTO_UNDER_18 = new BigDecimal(.20);
	
	private static final int DURATA_NORMALE = 30;
	private static final int DURATA_FLESSIBILE = 90;
	
	private int km;
	private int eta;
	
	private LocalDate data;
	private boolean flessibile;
	
	public Biglietto(int km, int eta, boolean flessibile) throws Exception {
		
		setKm(km);
		setEta(eta);
		setFlessibile(flessibile);
		
		data = LocalDate.now();
	}

	public int getKm() {
		return km;
	}
	public void setKm(int km) throws Exception {
		
		if (!isValidKm(km)) {
			
			throw new Exception("I Km devono essere strettamente positivi");
		}
		
		this.km = km;
	}
	private boolean isValidKm(int km) {
		
//		if (km >= 0) return true;
//		else return false;
		
		return km >= 0;
	}
	
	public int getEta() {
		return eta;
	}
	public void setEta(int eta) throws Exception {
		
		if (!isValidEta(eta)) {
			
			throw new Exception("L'eta deve essere compresa tra 0 e 200 anni");
		}
		
		this.eta = eta;
	}
	public boolean isValidEta(int eta) {
		
		return eta >= 0 && eta < 200;
	}
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public boolean isFlessibile() {
		return flessibile;
	}

	public void setFlessibile(boolean flessibile) {
		this.flessibile = flessibile;
	}

	public float calcolaPrezzo() {
		
		BigDecimal prezzo = new BigDecimal(km);
		prezzo = prezzo.multiply(COSTO_KM);
		
		BigDecimal sconto = new BigDecimal(calcolaSconto()).multiply(prezzo);
		prezzo = prezzo.subtract(sconto);
		
		if (isFlessibile()) {
			
			prezzo = prezzo.add(prezzo.divide(new BigDecimal(10)));
		}
		
		return prezzo.floatValue();
	}
	private float calcolaSconto() {
		
		if (eta > 65) {
			
			return SCONTO_OVER_65.floatValue();
		}
		if (eta < 18) {
			
			return SCONTO_UNDER_18.floatValue();
		}
		
		return 0;
	}
	public LocalDate calcolaDataScadenza() {
		
//		if (isFlessibile()) {
//			
//			return data.plusDays(DURATA_FLESSIBILE);
//		} else {
//			
//			return data.plusDays(DURATA_NORMALE);
//		}
		
		return data.plusDays(
			isFlessibile() 
			? DURATA_FLESSIBILE 
			: DURATA_NORMALE
		);
	}
	
	@Override
	public String toString() {
		
		return "km: " + getKm()
			+ "\neta: " + getEta()
			+ "\nprezzo: " + calcolaPrezzo()
			+ "\ndata: " + calcolaDataScadenza()
			+ "\nflessibile: " + (isFlessibile() ? "si'" : "no");
	}
}

