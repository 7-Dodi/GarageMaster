package br.com.GarageMaster.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {
	private int id;
	private float valor;
	private String data;
	private String finalizacao;
	private int idClient;
	private int idFuncionario;
	
	//Atributos opcionais
	private String nameClient;
	private String nameFuncionario;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) { //Setar data com parametro
		this.data = data;
	}
	public void setData() { //Setar data automaticamente
		LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = dataAtual.format(formatter);
	}
	
	public String getFinalizacao() {
		return finalizacao;
	}
	public void setFinalizacao(String finalizacao) {
		this.finalizacao = finalizacao;
	}
	public void setFinalizacao() {
		this.finalizacao = "Não pago";
	}
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	//Métodos dos atribuutos opcionais
	public String getNameClient() {
		return nameClient;
	}
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
	public String getNameFuncionario() {
		return nameFuncionario;
	}
	public void setNameFuncionario(String nameFuncionario) {
		this.nameFuncionario = nameFuncionario;
	}	
}
