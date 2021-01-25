package br.jtech.veiculos.entidade;

public class Carro {
	
	
	private String marca;
	private String modelo;
	private String cor;
	private int ano;
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	@Override
	public String toString() {
		
		return marca + "- " + modelo + "- "+ cor + "- "+ ano +"\n\n";
	}
	
	
	
}
