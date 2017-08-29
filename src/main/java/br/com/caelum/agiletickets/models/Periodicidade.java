package br.com.caelum.agiletickets.models;

public enum Periodicidade {
	
	DIARIA(1), SEMANAL(7);
	private int quantidadeDeDias;
	public int getQuantidadeDeDias() {
		return quantidadeDeDias;
	}
	private Periodicidade(int quantidade) {
		quantidadeDeDias = quantidade;
		
	}
	
}
