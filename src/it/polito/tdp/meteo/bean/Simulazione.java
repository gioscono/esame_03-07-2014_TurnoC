package it.polito.tdp.meteo.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.meteo.bean.Evento.eventType;

public class Simulazione {

	private double probabilita;
	private int numeroTecnici;
	private int tecniciInUso;
	private int costoTot;
	private int assunzioni;
	private int COSTO_TECNICO_ANNUALE = 20000;
	private int COSTO_TECNICO_CHIAMATA = 2000;
	
	private PriorityQueue<Evento> queue;
	
	public Simulazione(double probabilita, int numeroTecnici) {
		this.numeroTecnici=numeroTecnici;
		this.probabilita=probabilita;
		this.tecniciInUso = 0;
		queue = new PriorityQueue<>();
		this.costoTot = numeroTecnici*COSTO_TECNICO_ANNUALE;
		this.assunzioni = 0;
	}

	public void inserisciPrimiEventi(List<Situazione> possibiliRotture) {
		
		for(Situazione rotture : possibiliRotture){
			double prob = Math.random();
			if(prob<probabilita)
				queue.add(new Evento(eventType.OCCUPA_TECNICO, rotture.getData()));
		}
	}
	
	public Statistiche run(){
		
		while(!queue.isEmpty()){
			
			Evento e = queue.poll();
			
			switch(e.getType()){
			
			case OCCUPA_TECNICO:
				if(tecniciInUso<this.numeroTecnici){
					tecniciInUso++;
					Random r = new Random();
					int giorni = r.nextInt(9)+2;
					System.out.println(e.getData());
					System.out.println(giorni);
					System.out.println(e.getData().plusDays(giorni));
					queue.add(new Evento(eventType.SVINCOLA_TECNICO, e.getData().plusDays(giorni)));
				}else{
					costoTot+=this.COSTO_TECNICO_CHIAMATA;
					assunzioni++;
				}
				break;
			
			case SVINCOLA_TECNICO:
				tecniciInUso--;
				break;
			}
			
		}
		Statistiche s = new Statistiche(assunzioni, costoTot);
		
		return s;
		
	}
	
	

}
