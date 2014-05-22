package controle;

/**
 * 
 * @author Elton e Flavia
 * 
 *	<br>
 *<p>Classe ataque, ela armazena as caracteristicas do objeto ataque.</p> 
 *
 */

public class Ataque {
	
	String nome;
	int valor;
	
	/**
	 * 
	 * @param nome Nome do golpe
	 * @param valor Valor de ataque do golpe
	 * 
	 */
	public Ataque(String nome, int valor){
		this.nome=nome;
		this.valor=valor;
	}

	/*
	 * Retorna o nome do ataque
	 */
	public String getNome() {
		return nome;
	}

	/*
	 * Retorna o valor do golpe
	 */
	public int getValor() {
		return valor;
	}
	
	
	
	
}
