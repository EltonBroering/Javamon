package poo;

import java.io.Serializable;
/**
 * 
 * @author Elton e Flavia<br>
 * <p>
 * Classe mensagem que implementa o protocolo do formato das mensagens que vão ser trocadas entre cliente e servidor
 *</p>
 */
public class Mensagem implements Serializable{
	
	public int codigo;
	public String corpo;
	
	/**
	 * A mensagem possui um codigo que identifica de que tipo ela e, e o corpo que conteudo a mensagem carrega
	 * @param cod Inteiro que é codigo da mensagem a ser criada
	 * @param corpo String corpo da mensagem a ser criada.
	 */
	public Mensagem(int cod,String corpo){
		this.codigo=cod;
		this.corpo=corpo;
	}
	
	
	
}
