package br.com.GarageMaster.entities;

public class RelationWithPeca {
	private int idServico;
	private int idPeca;
	private int idVenda;
	private int quantidade;
	
	//Atributos opcionais
	private String nomePeca;
	private String descricaoPeca;
	private String valorPeca;
	
	public int getIdServico() {
		return idServico;
	}
	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}
	
	public int getIdPeca() {
		return idPeca;
	}
	public void setIdPeca(int idPeca) {
		this.idPeca = idPeca;
	}

	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	//Atributos opicionais
	public String getNomePeca() {
		return nomePeca;
	}
	public void setNomePeca(String nomePeca) {
		this.nomePeca = nomePeca;
	}
	public String getDescricaoPeca() {
		return descricaoPeca;
	}
	public void setDescricaoPeca(String descricaoPeca) {
		this.descricaoPeca = descricaoPeca;
	}
	public String getValorPeca() {
		return valorPeca;
	}
	public void setValorPeca(String valorPeca) {
		this.valorPeca = valorPeca;
	}
}
