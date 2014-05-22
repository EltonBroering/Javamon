package grafica;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;

import poo.Mensagem;

import java.awt.BorderLayout;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elton e Flavia<br>
 * <p>
 * Classe que carrega a parte grafica que tem as opções de ataque de um pokemon.
 * Ela tem tratamentos especiais pois é apenas uma parte do panel da tela de batalha
 * </p>
 */
public class Atacar extends javax.swing.JPanel {

    /**
     * Creates new form Atacar
     */
    JPanel selecao;
    TelaPrincipal tela;
    Batalha b;
    
 /**
  * @wbp.parser.constructor
  */
 public Atacar(TelaPrincipal t, JPanel p,Batalha b){
        initComponents();
        this.b=b;
        this.tela=t;
        this.selecao=p;
        setLayout(new BorderLayout(0, 0));
        add(jPanel);
        jBGolpe1.setText(b.Pokemons[0].getA().getNome());
        jBGolpe2.setText(b.Pokemons[0].getB().getNome());
        jBGolpe3.setText(b.Pokemons[0].getC().getNome());
        jBGolpe4.setText(b.Pokemons[0].getD().getNome());
        GroupLayout gl_jPanel = new GroupLayout(jPanel);
        gl_jPanel.setHorizontalGroup(
        	gl_jPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel.createSequentialGroup()
        			.addGap(35)
        			.addGroup(gl_jPanel.createParallelGroup(Alignment.LEADING)
        				.addComponent(jBGolpe4, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jBGolpe3, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
        			.addGap(70)
        			.addGroup(gl_jPanel.createParallelGroup(Alignment.LEADING)
        				.addComponent(jBGolpe2, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jBGolpe1, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
        );
        gl_jPanel.setVerticalGroup(
        	gl_jPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_jPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jBGolpe3)
        				.addComponent(jBGolpe1))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_jPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jBGolpe4)
        				.addComponent(jBGolpe2))
        			.addGap(31))
        );
        jPanel.setLayout(gl_jPanel);
    }
 public Atacar(TelaPrincipal t, JPanel p){
     initComponents();
     this.tela=t;
     this.selecao=p;
 }

 /**
  * Funcao que faz retornar para a tela normal de batalha
  * @param panel Panela da tela de batalha
  */
   public void  colocarPanel(JPanel panel){  
  jPanel.removeAll();  
      javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);  
        jPanel.setLayout(jPanelLayout);  
        jPanelLayout.setHorizontalGroup(  
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)  
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)  
        );  
        jPanelLayout.setVerticalGroup(  
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)  
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

        jPanel = new javax.swing.JPanel();
        jBGolpe1 = new javax.swing.JButton();
        jBGolpe2 = new javax.swing.JButton();
        jBGolpe3 = new javax.swing.JButton();
        jBGolpe4 = new javax.swing.JButton();

        jBGolpe1.setText("Golpe1");
        jBGolpe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGolpe1ActionPerformed(evt);
            }
        });

        jBGolpe2.setText("Golpe2");
        jBGolpe2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGolpe2ActionPerformed(evt);
            }
        });

        jBGolpe3.setText("Golpe3");
        jBGolpe3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGolpe3ActionPerformed(evt);
            }
        });

        jBGolpe4.setText("Golpe4");
        jBGolpe4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGolpe4ActionPerformed(evt);
            }
        });
        
        
    }// </editor-fold>//GEN-END:initComponents

    private void jBGolpe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEnvenenamentoActionPerformed
        // TODO add your handling code here:
        colocarPanel(new BatalhaOpcoes(tela, selecao,b));
        tela.cliente.enviaMensagem(new Mensagem(312,jBGolpe1.getText()));
        //AguardeDialog a=new AguardeDialog();
        //a.setVisible(true);
    }//GEN-LAST:event_jBEnvenenamentoActionPerformed

    private void jBGolpe2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBQueimaduraActionPerformed
        // TODO add your handling code here:
        colocarPanel(new BatalhaOpcoes(tela,selecao,b));
        tela.cliente.enviaMensagem(new Mensagem(313,jBGolpe2.getText()));
        //AguardeDialog a=new AguardeDialog();
        //a.setVisible(true);
    }//GEN-LAST:event_jBQueimaduraActionPerformed

    private void jBGolpe3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDormindoActionPerformed
        // TODO add your handling code here:
        colocarPanel(new BatalhaOpcoes(tela,selecao,b));
        tela.cliente.enviaMensagem(new Mensagem(301,jBGolpe3.getText()));
        //AguardeDialog a=new AguardeDialog();
        //a.setVisible(true);
    }//GEN-LAST:event_jBDormindoActionPerformed

    private void jBGolpe4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBParalisiaActionPerformed
        // TODO add your handling code here:
        colocarPanel(new BatalhaOpcoes(tela,selecao,b));
        tela.cliente.enviaMensagem(new Mensagem(311,jBGolpe4.getText()));
        //AguardeDialog a=new AguardeDialog();
        //a.setVisible(true);
    }//GEN-LAST:event_jBParalisiaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGolpe3;
    private javax.swing.JButton jBGolpe1;
    private javax.swing.JButton jBGolpe4;
    private javax.swing.JButton jBGolpe2;
    private javax.swing.JPanel jPanel;
    // End of variables declaration//GEN-END:variables

   
}
