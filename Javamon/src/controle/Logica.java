package controle;
/**
 * @author Elton e Flavia<br>
 * <p>
 * Classe de controle que armazena toda a logica do servidor do jogo
 * </p>
 */
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import poo.Mensagem;

public class Logica {
	
	ObjectOutputStream saida;
	ArrayList<Jogador> jogadores;
	static int cont=0;
	static int contRodada=0;
	/**
	 * 
	 * @param saida Conexao do ServidorThread com o jogador conectado a ele.
	 * @param jogadores Lista com todos os jogadores conectados no servidor.
	 */
	public Logica(ObjectOutputStream saida, ArrayList<Jogador> jogadores) {
		this.saida=saida;
		this.jogadores=jogadores;
		}
	/**
	 * Funcao que trata as mensagens recebida pelo servidor de acordo com o codigo da mensagem.
	 * @param m Mensagem a ser tratada
	 */
	public void tratar(Mensagem m){
		switch(m.codigo){
		
			case 110:
				this.enviarChat(m);
				break;
			case 120:
				desconectar(m);
				break;
			case 200:
				escolhido(m);
				break;
			default:
				if(m.codigo/100==3){
					rodada(m);
				}
				
			
		}
		
	}
	/*
	 * Função que trata as mensagens de rodada, quando os dois jogadores acabaram sua escolha de 
	 * ação na rodada ela chama a função que trata as escolhas da rodada.
	 * 
	 * Ela armazena cada escolha em um vetor.
	 */
    private synchronized void rodada(Mensagem m) {
		// TODO Auto-generated method stub
		contRodada++;
    	if(contRodada>1){
			contRodada=0;
			for (Jogador clientes : jogadores) {
				if (clientes.getSaida() == saida){
					try {
					clientes.getSaida().writeObject(new Mensagem(102,null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		clientes.setCodAcao(m);
				}
			}
		tratarRodada();
    	}else{
			for (Jogador clientes : jogadores) {
				if (clientes.getSaida() == saida){
					try {
					if((m.codigo==330 || m.codigo==331 || m.codigo==332 || m.codigo==333 || m.codigo==334 || m.codigo==335 || m.codigo==336 || m.codigo==337 || m.codigo==338 || m.codigo==339)&&clientes.getPokemons().split(":")[0].split("=")[0].equals("0")){
						trocaPokemonMorto(m,clientes);
					}else{
					clientes.getSaida().writeObject(new Mensagem(102,null));
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		clientes.setCodAcao(m);
	       	}
			}
		}
	}
    /*
     * Função que trata o caso de o pokemon do jogador morrer e ele tem que fazer a troca
     */
    protected void trocaPokemonMorto(Mensagem m,Jogador cliente){
    	//troca jogador que teve o pokemon morto
		int id=retornoId(m.codigo);
		int z;
		for(z=0;id!=Integer.parseInt(jogadores.get(0).getPokemons().split(":")[z].split("=")[0]);z++);
		String auxi;
		String[] aux=cliente.getPokemons().split(":");
		auxi=aux[0];
		aux[0]=aux[z];
		aux[z]=auxi;
		cliente.setPokemons(aux[0]+":"+aux[1]+":"+aux[2]+":"+aux[3]+":"+aux[4]+":"+aux[5]);
		try {
			jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /*
     * Função que trata as escolhas da rodada
     */
    protected void tratarRodada(){
    	int[] vetCod=new int[2];
    	int i=0;
    	for (Jogador clientes : jogadores) {
    		vetCod[i]=clientes.getCodAcao();
    		i++;
    	}
    	
    	/*
    	 * Se os dois jogadores escolheram atacar
    	 */
    	
    	if((vetCod[0]==301 || vetCod[0]==311 || vetCod[0]==312 || vetCod[0]==313) &&(vetCod[1]==301 || vetCod[1]==311 || vetCod[1]==312 || vetCod[1]==313)){
    		if(velocidade(jogadores.get(0).getPokemons().split(":")[0].split("=")[0])>=velocidade(jogadores.get(1).getPokemons().split(":")[0].split("=")[0])){
    			try {
					//ataque jogador 0
    				int vida=Integer.parseInt(jogadores.get(1).getPokemons().split(":")[0].split("=")[1]);
					vida-=calculoDano(jogadores.get(0).getPokemons().split(":")[0].split("=")[0],jogadores.get(1).getPokemons().split(":")[0].split("=")[0],vetCod[0]);
    				if(vida>0){
    					jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);
    					
    					
    					//ataque jogador 1
    					vida=Integer.parseInt(jogadores.get(0).getPokemons().split(":")[0].split("=")[1]);
    					vida-=calculoDano(jogadores.get(1).getPokemons().split(":")[0].split("=")[0],jogadores.get(0).getPokemons().split(":")[0].split("=")[0],vetCod[1]);
        				if(vida>0){
        					jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(0).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);	
        				}
        				else{
        					
        					jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+0+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(0).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);
        					jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
            				jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
            				try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
            				jogadores.get(1).getSaida().writeObject(new Mensagem(102,null));
            				
        					return;
        				}
    				}else{
    					
    					jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+0+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);
    					jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
    					jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
    					try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    					jogadores.get(0).getSaida().writeObject(new Mensagem(102,null));
    					
        				return;
    				}
    				
    				jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
    				jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}else{
    			try {
					//ataque jogador 1
    				int vida=Integer.parseInt(jogadores.get(0).getPokemons().split(":")[0].split("=")[1]);
					vida-=calculoDano(jogadores.get(1).getPokemons().split(":")[0].split("=")[0],jogadores.get(0).getPokemons().split(":")[0].split("=")[0],vetCod[1]);
    				if(vida>0){
    					jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);
    					
    					
    					//ataque jogador 0
    					vida=Integer.parseInt(jogadores.get(1).getPokemons().split(":")[0].split("=")[1]);
    					vida-=calculoDano(jogadores.get(0).getPokemons().split(":")[0].split("=")[0],jogadores.get(1).getPokemons().split(":")[0].split("=")[0],vetCod[0]);
        				if(vida>0){
        					jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(0).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);	
        				}
        				else{
        					
        					jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+0+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(0).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);
            		}
    				}else{
    					
    					jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+0+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(0).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);
    				}
    				
    				jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(1):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
    				jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(1):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
				} catch (IOException e) {
					e.printStackTrace();
				}		
    		}
    	}
    	
    	/*
    	 * 2 jogadores resolveram trocar os pokemons
    	 */
    	
    	if((vetCod[0]==330 || vetCod[0]==331 || vetCod[0]==332 || vetCod[0]==333 || vetCod[0]==334 || vetCod[0]==335 || vetCod[0]==336 || vetCod[0]==337 || vetCod[0]==338 || vetCod[0]==339) && (vetCod[1]==330 || vetCod[1]==331 || vetCod[1]==332 || vetCod[1]==333 || vetCod[1]==334 || vetCod[1]==335 || vetCod[1]==336 || vetCod[1]==337 || vetCod[1]==338 || vetCod[1]==339)){
    		//troca jogador 1
    		int id=retornoId(vetCod[0]);
    		int z;
    		for(z=0;id!=Integer.parseInt(jogadores.get(0).getPokemons().split(":")[z].split("=")[0]);z++);
    		String auxi;
    		String[] aux=jogadores.get(0).getPokemons().split(":");
    		auxi=aux[0];
    		aux[0]=aux[z];
    		aux[z]=auxi;
    		jogadores.get(0).setPokemons(aux[0]+":"+aux[1]+":"+aux[2]+":"+aux[3]+":"+aux[4]+":"+aux[5]);
    		//troca jogador 2
    		id=retornoId(vetCod[1]);
    		for(z=0;id!=Integer.parseInt(jogadores.get(1).getPokemons().split(":")[z].split("=")[0]);z++);
    		aux=jogadores.get(1).getPokemons().split(":");
    		auxi=aux[0];
    		aux[0]=aux[z];
    		aux[z]=auxi;
    		jogadores.get(1).setPokemons(aux[0]+":"+aux[1]+":"+aux[2]+":"+aux[3]+":"+aux[4]+":"+aux[5]);
    		try {
				jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    	}
    	
    	/*
    	 * 1 jogador troca pokemon e o outro ataca
    	 */
    	
    	if((vetCod[0]==330 || vetCod[0]==331 || vetCod[0]==332 || vetCod[0]==333 || vetCod[0]==334 || vetCod[0]==335 || vetCod[0]==336 || vetCod[0]==337 || vetCod[0]==338 || vetCod[0]==339) && (vetCod[1]==301 || vetCod[1]==311 || vetCod[1]==312 || vetCod[1]==313)){
    	
    		//troca jogador 1
    		int id=retornoId(vetCod[0]);
    		int z;
    		for(z=0;id!=Integer.parseInt(jogadores.get(0).getPokemons().split(":")[z].split("=")[0]);z++);
    		String auxi;
    		String[] aux=jogadores.get(0).getPokemons().split(":");
    		auxi=aux[0];
    		aux[0]=aux[z];
    		aux[z]=auxi;
    		jogadores.get(0).setPokemons(aux[0]+":"+aux[1]+":"+aux[2]+":"+aux[3]+":"+aux[4]+":"+aux[5]);
    		
    		//ataque jogador 2
    		int vida;
    		vida=Integer.parseInt(jogadores.get(0).getPokemons().split(":")[0].split("=")[1]);
			vida-=calculoDano(jogadores.get(1).getPokemons().split(":")[0].split("=")[0],jogadores.get(0).getPokemons().split(":")[0].split("=")[0],vetCod[1]);
			if(vida>0){
				jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(0).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);	
			}
			else{
				
				jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+0+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(0).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);
		}
			try {
				jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    	/*
    	 * 1 jogador troca pokemon e o outro ataca
    	 */
    	
    	if((vetCod[1]==330 || vetCod[1]==331 || vetCod[1]==332 || vetCod[1]==333 || vetCod[1]==334 || vetCod[1]==335 || vetCod[1]==336 || vetCod[1]==337 || vetCod[1]==338 || vetCod[1]==339) && (vetCod[0]==301 || vetCod[0]==311 || vetCod[0]==312 || vetCod[0]==313)){
    		
    		//troca jogador 2
    		int id=retornoId(vetCod[1]);
    		int z;
    		for(z=0;id!=Integer.parseInt(jogadores.get(1).getPokemons().split(":")[z].split("=")[0]);z++);
    		String auxi;
    		String[] aux=jogadores.get(1).getPokemons().split(":");
    		auxi=aux[0];
    		aux[0]=aux[z];
    		aux[z]=auxi;
    		jogadores.get(1).setPokemons(aux[0]+":"+aux[1]+":"+aux[2]+":"+aux[3]+":"+aux[4]+":"+aux[5]);
    		
    		//ataque jogador 1
    		int vida;
    		vida=Integer.parseInt(jogadores.get(1).getPokemons().split(":")[0].split("=")[1]);
			vida-=calculoDano(jogadores.get(0).getPokemons().split(":")[0].split("=")[0],jogadores.get(1).getPokemons().split(":")[0].split("=")[0],vetCod[0]);
			if(vida>0){
				jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);	
			}
			else{
				
				jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+0+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);
		}
			try {
				jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	/*
    	 * Os dois usam item
    	 */
    	
    	if((vetCod[0]==320 || vetCod[0]==321) && (vetCod[1]==320 || vetCod[1]==321)){
		
    		//item jogador 1
    		int vida;
	    	vida=Integer.parseInt(jogadores.get(0).getPokemons().split(":")[0].split("=")[1]);
				vida+=retornaItem(vetCod[0]);
				if(vida<100){
					jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(0).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);	
				}
				else{
					
					jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+100+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);
			}
				
				//item jogador 2
				vida=Integer.parseInt(jogadores.get(1).getPokemons().split(":")[0].split("=")[1]);
				vida+=retornaItem(vetCod[1]);
				if(vida<100){
					jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);	
				}
				else{
					
					jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+100+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);
			}

			//enviar	
			try {
					jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    	
    	/*
    	 * 1 troca pokemon e outra usa item,jogador 2 fez a troca
    	 */
    	
    	if((vetCod[0]==320 || vetCod[0]==321) && ((vetCod[1]==330 || vetCod[1]==331 || vetCod[1]==332 || vetCod[1]==333 || vetCod[1]==334 || vetCod[1]==335 || vetCod[1]==336 || vetCod[1]==337 || vetCod[1]==338 || vetCod[1]==339))){
    		
    		
    		//troca jogador 2
    		int id=retornoId(vetCod[1]);
    		int z;
    		for(z=0;id!=Integer.parseInt(jogadores.get(1).getPokemons().split(":")[z].split("=")[0]);z++);
    		String auxi;
    		String[] aux=jogadores.get(1).getPokemons().split(":");
    		auxi=aux[0];
    		aux[0]=aux[z];
    		aux[z]=auxi;
    		jogadores.get(1).setPokemons(aux[0]+":"+aux[1]+":"+aux[2]+":"+aux[3]+":"+aux[4]+":"+aux[5]);
    		
    		
    		//item jogador 1
    		int vida;
	    	vida=Integer.parseInt(jogadores.get(0).getPokemons().split(":")[0].split("=")[1]);
				vida+=retornaItem(vetCod[0]);
				if(vida<100){
					jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(0).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);	
				}
				else{
					
					jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+100+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);
			}
				
				

			//enviar	
			try {
					jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    	
    	
    	/*
    	 * 1 troca pokemon e outra usa item,jogador 1 fez a troca
    	 */
    	
    	if((vetCod[0]==330 || vetCod[0]==331 || vetCod[0]==332 || vetCod[0]==333 || vetCod[0]==334 || vetCod[0]==335 || vetCod[0]==336 || vetCod[0]==337 || vetCod[0]==338 || vetCod[0]==339) && (vetCod[1]==320 || vetCod[1]==321)){
    		
    		
    		//troca jogador 1
    		int id=retornoId(vetCod[0]);
    		int z;
    		for(z=0;id!=Integer.parseInt(jogadores.get(0).getPokemons().split(":")[z].split("=")[0]);z++);
    		String auxi;
    		String[] aux=jogadores.get(0).getPokemons().split(":");
    		auxi=aux[0];
    		aux[0]=aux[z];
    		aux[z]=auxi;
    		jogadores.get(0).setPokemons(aux[0]+":"+aux[1]+":"+aux[2]+":"+aux[3]+":"+aux[4]+":"+aux[5]);
    		
    		
    		
    		//item jogador 2
    		int vida;
			vida=Integer.parseInt(jogadores.get(1).getPokemons().split(":")[0].split("=")[1]);
			vida+=retornaItem(vetCod[1]);
			if(vida<100){
				jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);	
			}
			else{
				
				jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+100+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);
		}
	
				

			//enviar	
			try {
					jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    
    	/*
    	 * 1 jogador ataca e outra usa item,jogador 1 fez o ataque
    	 */
    	
    	if((vetCod[0]==301 || vetCod[0]==311 || vetCod[0]==312 || vetCod[0]==313) && (vetCod[1]==320 || vetCod[1]==321)){
    		
    		
    		//ataque jogador 1
    		int vida;
    		vida=Integer.parseInt(jogadores.get(1).getPokemons().split(":")[0].split("=")[1]);
			vida-=calculoDano(jogadores.get(0).getPokemons().split(":")[0].split("=")[0],jogadores.get(1).getPokemons().split(":")[0].split("=")[0],vetCod[0]);
			if(vida>0){
				jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);	
			}
			else{
				
				jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+0+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);
		}
    		
    		
    		//item jogador 2
    		vida=Integer.parseInt(jogadores.get(1).getPokemons().split(":")[0].split("=")[1]);
			vida+=retornaItem(vetCod[1]);
			if(vida<100){
				jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);	
			}
			else{
				
				jogadores.get(1).setPokemons(jogadores.get(1).getPokemons().split(":")[0].split("=")[0]+"="+100+":"+jogadores.get(1).getPokemons().split(":")[1]+":"+jogadores.get(1).getPokemons().split(":")[2]+":"+jogadores.get(1).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(1).getPokemons().split(":")[5]);
		}
	
				

			//enviar	
			try {
					jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

    	/*
    	 * 1 pokemon ataca e outra usa item,jogador 2 fez o ataque
    	 */
    	
    	if((vetCod[1]==301 || vetCod[1]==311 || vetCod[1]==312 || vetCod[1]==313) && (vetCod[0]==320 || vetCod[0]==321)){
    		
    		
    		//ataque jogador 2
    		int vida;
    		vida=Integer.parseInt(jogadores.get(0).getPokemons().split(":")[0].split("=")[1]);
			vida-=calculoDano(jogadores.get(1).getPokemons().split(":")[0].split("=")[0],jogadores.get(0).getPokemons().split(":")[0].split("=")[0],vetCod[1]);
			if(vida>0){
				jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(0).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);	
			}
			else{
				
				jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+0+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(0).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);
		}
    		
    		
			//item jogador 1
    		vida=Integer.parseInt(jogadores.get(0).getPokemons().split(":")[0].split("=")[1]);
				vida+=retornaItem(vetCod[0]);
				if(vida<100){
					jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+vida+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(0).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);	
				}
				else{
					
					jogadores.get(0).setPokemons(jogadores.get(0).getPokemons().split(":")[0].split("=")[0]+"="+100+":"+jogadores.get(0).getPokemons().split(":")[1]+":"+jogadores.get(0).getPokemons().split(":")[2]+":"+jogadores.get(0).getPokemons().split(":")[3]+":"+jogadores.get(1).getPokemons().split(":")[4]+":"+jogadores.get(0).getPokemons().split(":")[5]);
			}
			
				

			//enviar	
			try {
					jogadores.get(0).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(0).getPokemons().split(":")[0]+":"+jogadores.get(1).getPokemons().split(":")[0]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					jogadores.get(1).getSaida().writeObject(new Mensagem(500,"(0):"+jogadores.get(1).getPokemons().split(":")[0]+":"+jogadores.get(0).getPokemons().split(":")[0]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

    	
    }
    
    /*
     * Função que retorna ação do item que foi utilizado de acordo com o codigo da mensagem
     */
    
    private int retornaItem(int i){
    	switch(i){
    	case 320:
    		return 20;
    	case 321:
    		return 40;
    	}
    	return 0;
    }
    
    /*
     * retorna o id do pokemon de acordo com o codigo da mensagem
     */
    
    private int retornoId(int i){
    	switch(i){
    		case 330:
    			return 0;
    		case 331:
    			return 1;
    		case 332:
    			return 2;
    		case 333:
    			return 3;
    		case 334:
    			return 4;
    		case 335:
    			return 5;
    		case 336:
    			return 6;
    		case 337:
    			return 7;
    		case 338:
    			return 8;
    		case 339:
    			return 9;
    		default:
    			return -1;
    				
    	}
    }
    
    /*
     * Função que calcula o dano do golpe de acordo com o pokemon que atacou, o pokemon que defendeu e o golpe que foi utilizado.
     * Obs: Ela tem um fator sorte de acordo com o golpe, se for um golpe forte tem menos chance de acertar.
     */
        
    
    private int calculoDano(String idAtaque,String idDefesa,int cod){
    	boolean acertou=false;
    	Random r=new Random();
    	Pokemon atk=new Pokemon(Integer.parseInt(idAtaque));
    	Pokemon def=new Pokemon(Integer.parseInt(idDefesa));
    	if(valorAtk(atk,cod)==40){
    		int x=r.nextInt(5);
    		if(x==1 || x==2){
    			acertou=true;
    		}
    	}
    	if(valorAtk(atk,cod)==20){
    		int x=r.nextInt(5);
    		if(x!=1){
    			acertou=true;
    		}
    	}
    	if(acertou){
    		int dano=(atk.getAtaque()-def.getDefesa()+valorAtk(atk,cod))*getVantagem(atk,def);	
    		return dano;
    	}
    	else{
    		return 0;
    	}
    	
    }
    
    /*
     * Função que retorna se um pokemon tem vantagem sobre o outro ou não de acordos com o ID do pokemon que ataca e do pokemon que defende
     */
    private int getVantagem(Pokemon atk,Pokemon def){
    	if(atk.getVantagem()[0]==def.getID() || atk.getVantagem()[1]==def.getID()){
    		return 2;
    	}
    	if(atk.getDesvantagem()[0]==def.getID() || atk.getDesvantagem()[1]==def.getID()){
    		return 1/2;
    	}
    	return 1;
    }
    
    /*
     * Função que retorna o valor do dano do ataque utilizado de acordo com o pokemon e o codigo da mensagem
     */
    
    public int valorAtk(Pokemon atk,int cod){
    	switch(cod){
    	case 301:
    		return atk.getC().getValor();
    	case 311:
    		return atk.getD().getValor();
    	case 312:
    		return atk.getA().getValor();
    	case 313:
    		return atk.getB().getValor();
    	default: return 0;
    	}
    }
    /*
     * Função que retorna a velocidade do pokemon de acordo com seu id
     */
    public int velocidade(String id){
    	switch(Integer.parseInt(id)){
    	case 0:
    		return 39;
    	case 1:
    		return 19;
    	case 2:
    		return 32;
    	case 3:
    		return 34;
    	case 4:
    		return 10;
    	case 5:
    		return 46;
    	case 6:
    		return 20;
    	case 7:
    		return 19;
    	case 8:
    		return 50;
    	case 9:
    		return 38;
    	default: return 0;
    	}
    }
	
    /*
     * Função que envia uma mensagem de chat para o cliente caso o codigo da mensagem seja 110, ela envia a mensagem 
     * só para o cliente que não enviou a mensagem para o servidor. 
     */
	protected void enviarChat(Mensagem msg){
    	
    	
    	for (Jogador clientes : jogadores) {
            // Garanta que não irá enviar para o cliente que originou a mensagem
            if (clientes.getSaida() != saida) {
                try {
					clientes.getSaida().writeObject(msg);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

        }
    }
	/*
	 * Função que envia as mensagens de desconexao se o codigo da mensagem for 120
	 */
    public void desconectar(Mensagem msg){
    	for(Jogador clientes : jogadores){
    		
    	        try {
    				clientes.getSaida().writeObject(msg);
    				
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    		
    		
    	}	
    }
    /*
     * Funcao que é chamada quando o servidor recebe uma mensagem dizendo que um dos jogadores já terminaram de escolher seus pokemons
     */
    public void escolhido(Mensagem m){
     	Jogador j = null;
    	for (Jogador clientes : jogadores) {
    		if (clientes.getSaida() == saida) {
    			try {
    				clientes.getSaida().writeObject(new Mensagem(102,null));
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			String[] s;
            	s=m.corpo.split(":");
            	clientes.setPokemons(s[0]+"=100:"+s[1]+"=100:"+s[2]+"=100:"+s[3]+"=100:"+s[4]+"=100:"+s[5]+"=100");
            	j=clientes;
            	this.cont++;
            }
    		
        }
     	if(cont>1){
     		for (Jogador clientes : jogadores) {
            if (clientes.getSaida() != saida) {
                try {
					j.getSaida().writeObject(new Mensagem(600,j.getPokemons()+":"+clientes.getPokemons().split(":")[0]));
					clientes.getSaida().writeObject(new Mensagem(600,clientes.getPokemons()+":"+j.getPokemons().split(":")[0]));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

        }
     	}
    }
     

}
