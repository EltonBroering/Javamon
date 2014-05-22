package controle;

import java.io.ObjectOutputStream;

import poo.Mensagem;

/**
 * 
 * @author Elton e Flavia<br>
 * <p>
 * Classe jogador, armazena as informacoes sobre cada jogador conectado no servidor.
 *</p>
 */
public class Jogador {
	
	private ObjectOutputStream saida;
	private String nome;
	private String pokemons;
	private int codAcao;
	
	/**
	 * 
	 * @param m Codigo da mensagem que o cliente enviou na rodada.
	 */
	public void setCodAcao(Mensagem m){
		this.codAcao=m.codigo;
	}
	/**
	 * Retorna o codigo da mensagem que o cliente enviou na ultima rodada.
	 * 
	 * @return Valor da mensagem na ultima rodada.
	 */
	public int getCodAcao(){
		return this.codAcao;
	}
	/**
	 * Construtor que recebe o nome e a conexao do jogador.
	 * 
	 * @param saida Conexao do jogador.
	 * @param nome Nome do jogador.
	 */
	public Jogador(ObjectOutputStream saida,String nome){
		this.saida=saida;
		this.nome=nome;
	}
	/**Construtor que recebe somente a conexão do jogador
	 * 
	 * @param saida Coenxao do jogador
	 */
	public Jogador(ObjectOutputStream saida){
		this.saida=saida;
	}
	
	/**
	 * Retorna a conexao do jogador.
	 * @return conexao do jogador
	 */
	public ObjectOutputStream getSaida() {
		return saida;
	}
	/**
	 * Define o objeto conexao do jogador.
	 * @param saida
	 */
	public void setSaida(ObjectOutputStream saida) {
		this.saida = saida;
	}
	/**
	 * Retorna o nome do jogador
	 * @return Nome do jogador
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Atualiza o nome do jogador
	 * @param nome Nome do jogador
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Retorna a string com os pokemons do jogador e vida deles.
	 * @return String com os pokemons
	 */
	public String getPokemons() {
		return pokemons;
	}
	/**
	 * Atualiza a string com os pokemons do jogador
	 * @param pokemons String com os pokemons
	 */
	public void setPokemons(String pokemons) {
		this.pokemons = pokemons;
	}
	
	

}
