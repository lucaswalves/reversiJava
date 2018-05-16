package com.reversi.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HumaHuma {

	public JFrame humaHumaFrame;
	private JTextField nomePlayer1Field;
	private JTextField nomePlayer2Field;
	protected Object frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HumaHuma window = new HumaHuma();
					window.humaHumaFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HumaHuma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		humaHumaFrame = new JFrame();
		humaHumaFrame.setFont(new Font("Constantia", Font.PLAIN, 12));
		humaHumaFrame.setTitle("Reversi Game");
		humaHumaFrame.setBounds(100, 100, 802, 573);
		humaHumaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		humaHumaFrame.getContentPane().setLayout(null);

		/*JLabel lblBemVindo = new JLabel("Bem vindo ao Reversi Game");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblBemVindo.setIcon(null);
		lblBemVindo.setBounds(135, 77, 567, 136);
		humaHumaFrame.getContentPane().add(lblBemVindo);

		JLabel lblEscolhaSuaOp = new JLabel("O que você deseja fazer?");
		lblEscolhaSuaOp.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEscolhaSuaOp.setBounds(263, 190, 272, 47);
		humaHumaFrame.getContentPane().add(lblEscolhaSuaOp);*/

		JLabel lblPreenchaOsCampos = new JLabel("Preencha os campos abaixo com os nomes dos jogadores:");
		lblPreenchaOsCampos.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPreenchaOsCampos.setBounds(101, 95, 557, 30);
		humaHumaFrame.getContentPane().add(lblPreenchaOsCampos);

		nomePlayer1Field = new JTextField();
		nomePlayer1Field.setBounds(101, 174, 242, 20);
		humaHumaFrame.getContentPane().add(nomePlayer1Field);
		nomePlayer1Field.setColumns(10);

		JLabel nomePlayer1Label = new JLabel("Nome player 1:");
		nomePlayer1Label.setBounds(103, 149, 100, 14);
		humaHumaFrame.getContentPane().add(nomePlayer1Label);

		nomePlayer2Field = new JTextField();
		nomePlayer2Field.setBounds(101, 249, 242, 20);
		humaHumaFrame.getContentPane().add(nomePlayer2Field);
		nomePlayer2Field.setColumns(10);

		JLabel nomePlayer2Label = new JLabel("Nome player 2:");
		nomePlayer2Label.setBounds(101, 224, 100, 14);
		humaHumaFrame.getContentPane().add(nomePlayer2Label);

		JButton addBotao = new JButton("Adicionar");
		JLabel sucessoNomesLabel = new JLabel("Turma Adicionada com Sucesso");
		sucessoNomesLabel.setBounds(523, 208, 0, 14);
		humaHumaFrame.getContentPane().add(sucessoNomesLabel);
		addBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomePlayer1, nomePlayer2;

				if(nomePlayer1Field.getText().equals("") || nomePlayer2Field.getText().equals("") ){
					sucessoNomesLabel.setText("Preencha todos os campos.");
					sucessoNomesLabel.setBounds(523, 208, 180, 14);
				} 

				else{
					sucessoNomesLabel.setBounds(523, 208, 0, 14);

					nomePlayer1 = nomePlayer1Field.getText();
					nomePlayer2 = nomePlayer2Field.getText();

				}
			}
		});
		addBotao.setBounds(101, 379, 89, 23);
		humaHumaFrame.getContentPane().add(addBotao);
	}
}



