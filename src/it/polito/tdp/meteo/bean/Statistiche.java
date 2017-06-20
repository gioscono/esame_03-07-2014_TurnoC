package it.polito.tdp.meteo.bean;

public class Statistiche {

	private int assunzioni;
	private int costo;
	
	public Statistiche(int assunzioni, int costoTot) {
		this.assunzioni = assunzioni;
		this.costo = costoTot;
	}

	public int getAssunzioni() {
		return assunzioni;
	}

	public void setAssunzioni(int assunzioni) {
		this.assunzioni = assunzioni;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

}
