package it.polito.tdp.meteo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {
	
	private MeteoDAO dao;
	private List<Situazione> rilevazioni;
	private List<Situazione> possibiliRotture;
	
	public Model(){
		dao = new MeteoDAO();
		possibiliRotture = new ArrayList<>();
	}
	
	public List<String> getAllCitta(){
		return dao.getAllCitta();
	}
	
	public List<Situazione> getRilevamentiCitta(String citta){
		if(rilevazioni == null){
			rilevazioni = dao.getAllRilevazioniCitta(citta);
		}
		return rilevazioni;
	}
	
	public List<Situazione> listaDate(String citta){
		
		List<Situazione> ris = new ArrayList<>();
		//System.out.println(this.getRilevamentiCitta(citta).size());
		for(int i =1; i<this.getRilevamentiCitta(citta).size(); i++){
			if(rilevazioni.get(i).getTMax()>rilevazioni.get(i-1).getTMax()+2){
				//System.out.println(rilevazioni.get(i).getTMax()+"-"+rilevazioni.get(i-1).getTMax());
				ris.add(rilevazioni.get(i));
			}
		}
		possibiliRotture.addAll(ris);
		return ris;
	}
	
	public Statistiche avviaSimulazione(double probabilita, int numeroTecnici){
		
		Simulazione sim = new Simulazione(probabilita, numeroTecnici);
		
		sim.inserisciPrimiEventi(this.possibiliRotture);
		
		return sim.run();
		
		
		
	}

}
