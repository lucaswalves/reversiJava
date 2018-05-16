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

public class HumaPC {

	public JFrame humaPCFrame;
	protected Object frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HumaPC window = new HumaPC();
					window.humaPCFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HumaPC() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		humaPCFrame = new JFrame();
		humaPCFrame.setFont(new Font("Constantia", Font.PLAIN, 12));
		humaPCFrame.setTitle("Reversi Game");
		humaPCFrame.setBounds(100, 100, 802, 573);
		humaPCFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		humaPCFrame.getContentPane().setLayout(null);

		JLabel lblEscolhaSuaDif = new JLabel("Escolha sua dificuldade?");
		lblEscolhaSuaDif.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEscolhaSuaDif.setBounds(263, 190, 272, 47);
		humaPCFrame.getContentPane().add(lblEscolhaSuaDif);

		JLabel lblPreenchaOCampo = new JLabel("Preencha os campos abaixo com os nomes dos jogadores:");
		lblPreenchaOCampo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPreenchaOCampo.setBounds(101, 95, 557, 30);
		humaPCFrame.getContentPane().add(lblPreenchaOCampo);

		JTextField nomePlayer1Field;
		nomePlayer1Field = new JTextField();
		nomePlayer1Field.setBounds(101, 174, 242, 20);
		humaPCFrame.getContentPane().add(nomePlayer1Field);
		nomePlayer1Field.setColumns(10);

		JLabel nomePlayer1Label = new JLabel("Nome player 1:");
		nomePlayer1Label.setBounds(103, 149, 100, 14);
		humaPCFrame.getContentPane().add(nomePlayer1Label);


		JButton addBotao = new JButton("Adicionar");
		JLabel sucessoNomesLabel = new JLabel("Turma Adicionada com Sucesso");
		sucessoNomesLabel.setBounds(523, 208, 0, 14);
		humaPCFrame.getContentPane().add(sucessoNomesLabel);
		addBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomePlayer1, nomePlayer2;

				if(nomePlayer1Field.getText().equals("") ){
					sucessoNomesLabel.setText("Preencha todos os campos.");
					sucessoNomesLabel.setBounds(523, 208, 180, 14);
				} 

				else{
					sucessoNomesLabel.setBounds(523, 208, 0, 14);

					nomePlayer1 = nomePlayer1Field.getText();

				}
			}
		});
		addBotao.setBounds(101, 379, 89, 23);
		humaPCFrame.getContentPane().add(addBotao);
	}
}



