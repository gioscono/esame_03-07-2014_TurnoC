package it.polito.tdp.meteo.bean;

import java.time.LocalDate;


public class Evento implements Comparable<Evento>{
	
	public enum eventType{
		OCCUPA_TECNICO,
		SVINCOLA_TECNICO
	}
	private LocalDate data;
	private eventType type;
	public Evento(eventType type, LocalDate data) {
		super();
		this.data = data;
		this.type = type;
	}
	public LocalDate getData() {
		return data;
	}
	public eventType getType() {
		return type;
	}
	@Override
	public int compareTo(Evento arg0) {
		// TODO Auto-generated method stub
		return this.data.compareTo(arg0.data);
	}
	
	

}
