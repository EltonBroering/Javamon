package grafica;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import poo.Mensagem;

import controle.Pokemon;
/**
 * 
 * @author Elton e Flavia
 *<br>
 *<p>
 *Classe que contem a tela de troca de pokemons, nela aparece os pokemons escolhidos pelo jogador
 *e sua vida.
 *</p>
 */
public class TrocaPokemon extends JPanel {
	private JButton jBPokemon;

	JPanel j;
    TelaPrincipal tela;
    private JButton jB0;
    Batalha b;
    String imgB;
    private JButton jB3;
    private JButton jB2;
    private JButton jB4;
    private JButton jB5;
    private JButton jB1;
    private int v;
    private JProgressBar jPBVida;
    
	/**
	 * Create the panel.
	 */
    /**
     * Tem como parametro a tela de batalha do jogo
     * @param b Panel Batalha da tela do jogo
     */
	public TrocaPokemon(Batalha b) {
		setLayout(null);
		
		this.b=b;
		
		jB3 = new JButton("");
		jB3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jB3focusGained(e);
			}
		});
		jB3.setBounds(64, 12, 40, 40);
		add(jB3);
		
		jB0 = new JButton("");
		jB0.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jB0focusGained(e);
			}
		});
		jB0.setBounds(116, 12, 40, 40);
		add(jB0);
		
		jB1 = new JButton("");
		jB1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jB1focusGained(e);
			}
		});
		jB1.setBounds(12, 58, 40, 40);
		add(jB1);
		
		jB4 = new JButton("");
		jB4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jB4focusGained(e);
			}
		});
		jB4.setBounds(64, 58, 40, 40);
		add(jB4);
		
		jB2 = new JButton("");
		jB2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jB2focusGained(e);
			}
		});
		jB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jB2.setBounds(116, 58, 40, 40);
		add(jB2);
		
		jB5 = new JButton("");
		jB5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jB5focusGained(e);
			}
		});
		jB5.setBounds(12, 12, 40, 40);
		add(jB5);
		
		jBPokemon = new JButton("");
		jBPokemon.setBounds(12, 110, 117, 96);
		add(jBPokemon);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoPokemon(e);
			}
		});
		btnOk.setBounds(223, 178, 79, 25);
		add(btnOk);
		
		jPBVida = new JProgressBar();
		jPBVida.setValue(100);
		jPBVida.setForeground(new Color(0, 128, 0));
		jPBVida.setStringPainted(true);
		jPBVida.setBounds(12, 213, 148, 14);
		add(jPBVida);

		label();
	}
	
	/*
	 * Troca a imagem de JBPokemon para a do pokemon respectivo ao botão que está com foco.
	 */
	private void setIconFocus(String img){
    	jBPokemon.setIcon(new javax.swing.ImageIcon(getClass().getResource(img)));
    }
	
	
	/**
	 * Atualiza as imagens de todos os pokemons escolhido nos botoes da tela de troca
	 */
	public void label(){
		jB0.setIcon(new javax.swing.ImageIcon(getClass().getResource(b.vetPoke[0][0])));
		jB1.setIcon(new javax.swing.ImageIcon(getClass().getResource(b.vetPoke[1][0])));
		jB2.setIcon(new javax.swing.ImageIcon(getClass().getResource(b.vetPoke[2][0])));
		jB3.setIcon(new javax.swing.ImageIcon(getClass().getResource(b.vetPoke[3][0])));
		jB4.setIcon(new javax.swing.ImageIcon(getClass().getResource(b.vetPoke[4][0])));
		jB5.setIcon(new javax.swing.ImageIcon(getClass().getResource(b.vetPoke[5][0])));
	}
	/*
	 * Atualiza a imagem grande com a imagem grande do pokemon com foco,a barra de vida também.
	 * Quando o usuario apertar OK o pokemon que estiver com foco sera o escolhido
	 */
	public void jB0focusGained(FocusEvent e){
		this.setIconFocus(b.vetPoke[0][1]);
		this.imgB=b.vetPoke[0][2];
		this.jPBVida.setValue(this.b.Pokemons[0].getVida());
		this.v=0;
	}

	/*
	 * Atualiza a imagem grande com a imagem grande do pokemon com foco,a barra de vida também.
	 * Quando o usuario apertar OK o pokemon que estiver com foco sera o escolhido
	 */
	public void jB1focusGained(FocusEvent e){
		this.setIconFocus(b.vetPoke[1][1]);
		this.imgB=b.vetPoke[1][2];
		this.jPBVida.setValue(this.b.Pokemons[1].getVida());
		this.v=1;
	}

	/*
	 * Atualiza a imagem grande com a imagem grande do pokemon com foco,a barra de vida também.
	 * Quando o usuario apertar OK o pokemon que estiver com foco sera o escolhido
	 */
	public void jB2focusGained(FocusEvent e){
		this.setIconFocus(b.vetPoke[2][1]);
		this.imgB=b.vetPoke[2][2];
		this.jPBVida.setValue(this.b.Pokemons[2].getVida());
		this.v=2;
	}

	/*
	 * Atualiza a imagem grande com a imagem grande do pokemon com foco,a barra de vida também.
	 * Quando o usuario apertar OK o pokemon que estiver com foco sera o escolhido
	 */
	public void jB3focusGained(FocusEvent e){
		this.setIconFocus(b.vetPoke[3][1]);
		this.imgB=b.vetPoke[3][2];
		this.jPBVida.setValue(this.b.Pokemons[3].getVida());
		this.v=3;
	}
	/*
	 * Atualiza a imagem grande com a imagem grande do pokemon com foco,a barra de vida também.
	 * Quando o usuario apertar OK o pokemon que estiver com foco sera o escolhido
	 */
	
	public void jB4focusGained(FocusEvent e){
		this.setIconFocus(b.vetPoke[4][1]);
		this.imgB=b.vetPoke[4][2];
		this.jPBVida.setValue(this.b.Pokemons[4].getVida());
		this.v=4;
	}
	/*
	 * Atualiza a imagem grande com a imagem grande do pokemon com foco,a barra de vida também.
	 * Quando o usuario apertar OK o pokemon que estiver com foco sera o escolhido
	 */
	public void jB5focusGained(FocusEvent e){
		this.setIconFocus(b.vetPoke[5][1]);
		this.imgB=b.vetPoke[5][2];
		this.jPBVida.setValue(this.b.Pokemons[5].getVida());
		this.v=5;
	}
	/**
	 * Funcao que atualiza o pokemon que vai estar na tela de batalha pelo pokemon que esta sendo escolhido na tela de troca
	 * o pokemon que estiver com o foco,sua imagem aparecendo na imagem grande vai ser o escolhido.
	 * @param e
	 */
	public void novoPokemon(ActionEvent e){
		
		if(b.Pokemons[v].getVida()==0){
			return;
		}
		
		b.getJLPokemonEscolhido().setIcon(new javax.swing.ImageIcon(getClass().getResource(imgB)));
		//b.getJLPokemonEscolhido().setIcon(jBPokemon.getIcon());
		b.tela.setContentPane(b);
		
		
		//coloca o novo pokemon escolhido na primeira posicao do vetor de objeto pokemons
		Pokemon aux;
				
				aux=b.Pokemons[v];
				b.Pokemons[v]=b.Pokemons[0];
				b.Pokemons[0]=aux;
				
				//coloca o novo pokemon escolhido na primeira posicao do vetor de imagens de pokemons
				
				String[] mv;
				
				mv=b.vetPoke[v];
				b.vetPoke[v]=b.vetPoke[0];
				b.vetPoke[0]=mv;
				b.jPBBarradeVida.setValue(b.Pokemons[0].getVida());


				//envia mensagem para o servidos avisando qual pokemon foi escolhido
				int i = b.Pokemons[0].getID();
				switch (i){
				
				case 0:
					b.tela.cliente.enviaMensagem(new Mensagem(330,null));
					break;
				case 1:
					b.tela.cliente.enviaMensagem(new Mensagem (331, null));
					break;
				case 2:
					b.tela.cliente.enviaMensagem(new Mensagem(332,null));
					break;
				case 3:
					b.tela.cliente.enviaMensagem(new Mensagem(333,null));
					break;
				case 4:
					b.tela.cliente.enviaMensagem(new Mensagem(334,null));
					break;
				case 5:
					b.tela.cliente.enviaMensagem(new Mensagem (335,null));
					break;
				case 6:
					b.tela.cliente.enviaMensagem(new Mensagem(336,null));
					break;
				case 7:
					b.tela.cliente.enviaMensagem(new Mensagem (337,null));
					break;
				case 8:
					b.tela.cliente.enviaMensagem(new Mensagem(338,null));
					break;
				case 9:
					b.tela.cliente.enviaMensagem(new Mensagem (339, null));
					break;
				}

	
	
	}

	
}

