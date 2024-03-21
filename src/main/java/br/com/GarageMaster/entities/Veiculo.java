package br.com.GarageMaster.entities;

public class Veiculo {
	private int id;
	private String modelo;
	private String placa;
	private String marca;
	private int idClient;
	private String nomeClient; //Atributo Opcional só existe na classe não existe no BD

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	//Getter e Setter do atributo criado na classe (NomeClient não existe no banco de dados)
	public String getNomeClient() {
		return nomeClient;
	}
	public void setNomeClient(String nomeClient) {
		this.nomeClient = nomeClient;
	}
}
