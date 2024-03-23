package br.com.GarageMaster.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Servico {
	private int id;
	private String descricao;
	private float valor;
	private String data;
	private int idVeiculo;
	private String modeloVeiculo; //Atirbuto Opcional
	private int idFuncionario;
	private String funcionarioNome; //Atributo opcional
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	
	public int getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	//Atributos opcionais, não condizem com a estruturação da tabela no banco de dados
	public String getModeloVeiculo() {
		return modeloVeiculo;
	}
	public void setModeloVeiculo(String modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}
	
	public String getFuncionarioNome() {
		return funcionarioNome;
	}
	public void setFuncionarioNome(String funcionarioNome) {
		this.funcionarioNome = funcionarioNome;
	}
	
	
}
