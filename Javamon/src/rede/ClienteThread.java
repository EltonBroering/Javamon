package rede;

import grafica.Batalha;
import grafica.TelaPrincipal;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import poo.Mensagem;

/**
 * 2013-11-26
 *
 * @author Emerson Ribeiro de Mello
 * <br>
 * <p>
 * Classe que fica cuidando da conexao entre cada cliente e o servidor
 * </p>
 */
public class ClienteThread extends Thread {

    private ObjectOutputStream saida;
    private ObjectInputStream entrada;
    private String enderecoIPServidor;
    private int portaServidor;
    private String nomeParticipante;
    private boolean esperando=false;
    public String getNomeParticipante() {
		return nomeParticipante;
	}

	private TelaPrincipal pai;

    public ClienteThread(TelaPrincipal p, String nome, String endIP, int porta) {
        this.nomeParticipante = nome;
        this.enderecoIPServidor = endIP;
        this.portaServidor = porta;
        this.pai = p;

    }
    /**
     * Envia uma mensagem para o cliente
     * @param m Mensagem a ser enviada
     */
    public void enviaMensagem(Mensagem m) {
        
         //escrevendo na saida da conexao
         try{
        	 saida.writeObject(m);
         }
         catch (Exception e) {
            System.err.println("Erro ao enviar msg: " + e.toString());
        }
     
}

    @Override
    public void run() {
        Socket conexao;

        try {
            conexao = new Socket(enderecoIPServidor, portaServidor);

            System.out.println("Conectado no servidor...");

            //obtem os fluxos de entrada e saida desta conexao
            this.saida = new ObjectOutputStream(conexao.getOutputStream());
            this.entrada = new ObjectInputStream(conexao.getInputStream());
            boolean conectado = true;

            
            // ao conectar, envie uma mensagem informando o nome do Participante
            Mensagem m=new Mensagem(100,nomeParticipante);
            saida.writeObject(m);

            
            // enquanto a conexao estiver ativa, veja o que vem pela rede
            // e escreva na janela do bate papo
            while (conectado) {
                Mensagem msgRecebida = (Mensagem) entrada.readObject();

                if(msgRecebida.codigo==100){
                	this.pai.setNomeAdversario(msgRecebida.corpo);
                	this.pai.notEspere();
                	esperando=false;
                }
                
                // escreva na tela do usuario
                if(msgRecebida.codigo==110){
                this.pai.adicionarMensagem(msgRecebida.corpo);
                }
              
                //desconectar
                if(msgRecebida.codigo==120){
                	if(msgRecebida.corpo.equals("sair")){
                		System.exit(0);
                	}
                	if(msgRecebida.corpo.equals("desconectar")){
                		pai.setVisible(false);
                		this.pai=new TelaPrincipal();
                		pai.setVisible(true);
                	}
                }
                if(msgRecebida.codigo==600){
                	if(esperando){
                		esperando=false;
                		pai.notEspere();
                	}
                	pai.trocaBatalha(msgRecebida.corpo);
                }
                if(msgRecebida.codigo==102){
                	pai.espere();
                	esperando=true;
                }
                if(msgRecebida.codigo==500){
                		esperando=false;
                		pai.notEspere();
                	(( Batalha) pai.getContentPane()).rodada(msgRecebida);
                	
                }
            }
            // fechando os fluxos
            saida.close();
            entrada.close();
            conexao.close();

        } catch (Exception e) {
        	System.out.println(trataVetor(e));
            this.pai.jBConectar.setText("Conectar");
        }

    }
    /*
     * Funcao que trata vetor de stackTrace e transforma em uma string
     */
    String trataVetor(Exception e){
    	StackTraceElement[] v=e.getStackTrace();
    	String r=null;
    	for(int i=0;i<v.length;i++){
    		r+="\n"+v[i].toString();
    	}
		return r;
    	
    }
}


