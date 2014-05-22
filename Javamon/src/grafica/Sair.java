package grafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import poo.Mensagem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.ButtonGroup;
/**
 * 
 * @author Elton e Flavia<br>
 *<p>
 *Classe que tem as opcoes de escolha para o usuario quando ele acionar o botao sair
 *</p>
 */
public class Sair extends JDialog {

    TelaPrincipal tela;

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBox jCBSairJogo;
	private JCheckBox jCBSairBatalha;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			Sair dialog = new Sair();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Cria um jDialog com as opcoes de Sair para o jogador
	 * @param tela Objeto do tipo TelePrincipal
	 */
	public Sair(TelaPrincipal tela) {
		this.tela=tela;
		setBounds(100, 100, 363, 143);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			jCBSairJogo = new JCheckBox("Sair do jogo");
			jCBSairJogo.setBounds(56, 10, 110, 23);
			
			contentPanel.setLayout(null);
			buttonGroup.add(jCBSairJogo);
			
			
			contentPanel.add(jCBSairJogo);
		}
		{
			jCBSairBatalha = new JCheckBox("Sair da batalha");
			jCBSairBatalha.setBounds(171, 10, 134, 23);
			buttonGroup.add(jCBSairBatalha);
			
			contentPanel.add(jCBSairBatalha);
		}
		{
			JButton jBokSair = new JButton("Ok");
			jBokSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonOK(e);
				}
			});
			jBokSair.setBounds(113, 62, 117, 25);
			contentPanel.add(jBokSair);
		}
		this.setLocationRelativeTo(null);
	}

	/**
	 * Funcao que e chamada quando o cliente escolhe sair do jogo, ela envia uma mensagem para o servidor
	 * @param e Evento
	 */
	public void sairDoJogo(ActionEvent e){
        Mensagem m = new Mensagem(120,"sair");
        tela.cliente.enviaMensagem(m);
        		
	}
	/**
	 * Funcao que e chamada quando o cliente escolhe sair da batalha, ela envia uma mensagem para o servidor
	 * @param e Evento
	 */
	public void sairDaBatalha(ActionEvent e){
		Mensagem m = new Mensagem(120,"desconectar");
	    tela.cliente.enviaMensagem(m);
	}
	protected JCheckBox getJCBSairJogo() {
		return jCBSairJogo;
	}
	protected JCheckBox getJCBSairBatalha() {
		return jCBSairBatalha;
	}
	/**
	 * Funcao chamada quando o usuario acionao o botao OK da tela, e de acordo com o que foi selecionado ele chama o evento de<br>
	 * sair do jogo ou da batalha
	 * @param e Evento
	 */
	void buttonOK(ActionEvent e){
		this.setVisible(false);
		if(getJCBSairBatalha().isSelected()){
			sairDaBatalha(e);
		}
		if(getJCBSairJogo().isSelected()){
			sairDoJogo(e);
		}
	}
}