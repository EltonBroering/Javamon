package rede;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import controle.Jogador;
import controle.Logica;

import poo.Mensagem;



/**
 * Um objeto desta classe é criado para cada cliente que se conectar no servidor
 * Assim, o servidor terá um thread para atender cada cliente de forma separada
 *
 * 2013-11-26
 *
 * @author Emerson Ribeiro de Mello
 */
public class ServidorThread extends Thread {

    private Socket conexao;

    // Lista com os fluxos de saida de todos os clientes conectados no servidor
    // Esta lista é compartilhada por todos objetos da classe ServidorThread
    private ArrayList<Jogador> jogadores;
    private boolean conectado;
    Logica logica;
    ObjectOutputStream saida;
    ObjectInputStream entrada;
    

    
    public ServidorThread(Socket c, ArrayList<Jogador> aL,ServidorDaemon servidorPai) {
        this.conexao = c;
        this.jogadores = aL;
        this.conectado = false;
        
    }

    @Override
    public void run() {
        try {
            //obtendo os fluxos de entrada e de saida
            saida = new ObjectOutputStream(conexao.getOutputStream());
            entrada = new ObjectInputStream(conexao.getInputStream());

            // adicionando no ArrayList o fluxo de saida deste cliente que 
            // acabou de conectar
            // esse ArrayList é compartilhado por todas as Threads do ServidorThread
            Jogador j= new Jogador(saida);
            this.jogadores.add(j);

            this.conectado = true;
            
//            Mensagem m=new Mensagem(110,"bem vindo ao servidor");
//            saida.writeObject(m);

            // Regra deste exemplo: O cliente ao conectar sempre irá enviar seu nome. 
            Mensagem m;
            m=(Mensagem) entrada.readObject();
            if(m.codigo==100){
            if(jogadores.size()<2){
            	
            	jogadores.get(0).setNome(m.corpo);
            	jogadores.get(0).getSaida().writeObject(new Mensagem(102,null));

            }
            else{
            	
            	jogadores.get(1).setNome(m.corpo);
            	jogadores.get(0).getSaida().writeObject(new Mensagem(100,jogadores.get(1).getNome()));
            	jogadores.get(1).getSaida().writeObject(new Mensagem(100,jogadores.get(0).getNome()));
            	
            }
            }
            this.logica=new Logica(saida,jogadores);
            
            // Ficará neste laço esperando todas as mensagens que o cliente enviar
            while (conectado) {

                // Fica travado na linha abaixo até receber uma mensagem do cliente
                Mensagem mensagemRecebida = (Mensagem) entrada.readObject();

                // As linhas abaixo só serão executadas depois que o cliente
                // enviar uma mensagem
                
                // Envie a mensagem recebida para todos os outros cliente,
                    // mas não envie para o cliente que originou esta mensagem
                
                logica.tratar(mensagemRecebida);
                
                
                
                

            } // fim do while do conectado

            // removendo o fluxo de saida deste Cliente
            // fechando os fluxos e a conexao
            saida.close();
            entrada.close();
            conexao.close();
        } catch (Exception ex) {
            System.err.println("Erro no ServidorThread: " + ex.toString());
        }
    }
    


}
