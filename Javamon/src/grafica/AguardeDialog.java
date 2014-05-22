package grafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window.Type;
/**
 * 
 * @author Elton e Flavia<br>
 * <p>
 * Classe que coloca um Dialog na tela do usuario para espera o outro jogador conectar
 * </p>
 *
 */
public class AguardeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AguardeDialog dialog = new AguardeDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	/**
	 * Cria uma janela Dialog para fazer o jogador aguarda o outro fazer sua ação.
	 */
	public AguardeDialog() {
		setAlwaysOnTop(true);
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAguardeOOutro = new JLabel("Aguarde o outro jogador!");
		lblAguardeOOutro.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAguardeOOutro.setBounds(95, 100, 259, 60);
		contentPanel.add(lblAguardeOOutro);
		
		this.setLocationRelativeTo(null);
	}
}
