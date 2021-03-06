package grafica;


import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elton e Flavia<br>
 * <p>Classe que atualiza as opcoes de escolha da rodada do usuario, necessaria pois ela atualiza so
 * uma parte da label batalha.
 * </p>
 */
public class BatalhaOpcoes extends javax.swing.JPanel {

    /**
     * Creates new form BatalhaOpcoes
     */
    JPanel j;
    TelaPrincipal tela;
    Batalha b;

    /**
     * 
     * @param t Objeto do tipo TelaPrincipal
     * @param p Objeto do tipo Selecao
     * @param b Objeto do tipo Batalha
     */
    public BatalhaOpcoes(TelaPrincipal t, JPanel p,Batalha b) {
        initComponents();
        this.j=p;
        this.tela=t;
        this.b=b;
    }

    /**
     * Funcao que faz retornar para a tela normal de batalha
     * @param panel Panela da tela de batalha
     */
    public void  colocarPanel(JPanel panel){  
    	jPanel6.removeAll();  
      javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);  
        jPanel6.setLayout(jPanel6Layout);  
        jPanel6Layout.setHorizontalGroup(  
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)  
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)  
        );  
        jPanel6Layout.setVerticalGroup(  
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)  
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)  
        );  

}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jBTrocarPokemon = new javax.swing.JButton();
        jBItem = new javax.swing.JButton();
        jBAtacar = new javax.swing.JButton();

        jBTrocarPokemon.setText("Trocar Pokémon");
        jBTrocarPokemon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTrocarPokemonActionPerformed(evt);
            }
        });

        jBItem.setText("Usar Item");
        jBItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBItemActionPerformed(evt);
            }
        });

        jBAtacar.setText("Atacar");
        jBAtacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtacarActionPerformed(evt);
            }
        });
        
        btnSair = new JButton("Sair");
        btnSair.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		sair(e);
        	}
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6Layout.setHorizontalGroup(
        	jPanel6Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel6Layout.createSequentialGroup()
        			.addGap(12)
        			.addGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(jBTrocarPokemon, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jBAtacar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(55)
        			.addGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jBItem)
        				.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
        	jPanel6Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel6Layout.createSequentialGroup()
        			.addGroup(jPanel6Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jBAtacar)
        				.addComponent(jBItem))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel6Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jBTrocarPokemon)
        				.addComponent(btnSair))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6.setLayout(jPanel6Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    /*
     * Função que é chamada quando o botão trocar pokemon é acionado
     */
    private void jBTrocarPokemonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTrocarPokemonActionPerformed
    	//jPanel6.removeAll();
    	TrocaPokemon t=new TrocaPokemon(b);
    	t.setBounds(0,0,b.getWidth(),b.getHeight());
    	t.setVisible(true);
    	tela.setContentPane(t);
    }//GEN-LAST:event_jBTrocarPokemonActionPerformed
    /*
     * Função que é chamada quando o botão Itens é acionado
     */
    private void jBItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBItemActionPerformed
        // TODO add your handling code here:
    colocarPanel(new Itens(tela, j,b));
    }//GEN-LAST:event_jBItemActionPerformed
    /*
     * Função que é chamada quando o botão Atacar é acioando
     */
    private void jBAtacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtacarActionPerformed
        // TODO add your handling code here:
     colocarPanel(new Atacar(tela,j,b));

    }//GEN-LAST:event_jBAtacarActionPerformed
    /*
     * Função que é chamada quando o botão Sair é acionado
     */
    public void sair(ActionEvent e){
    	Sair s = new Sair(tela);
    	s.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAtacar;
    private javax.swing.JButton jBItem;
    private javax.swing.JButton jBTrocarPokemon;
    private javax.swing.JPanel jPanel6;
    private JButton btnSair;
    // End of variables declaration//GEN-END:variables
}
