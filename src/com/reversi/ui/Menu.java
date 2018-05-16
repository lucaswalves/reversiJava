package com.reversi.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;


import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu {

	public JFrame menuFrame;
	protected Object frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.menuFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		menuFrame = new JFrame();
		menuFrame.setFont(new Font("Constantia", Font.PLAIN, 12));
		menuFrame.setTitle("Reversi Game");
		menuFrame.setBounds(100, 100, 802, 573);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.getContentPane().setLayout(null);

		JLabel lblBemVindo = new JLabel("Bem vindo ao Reversi Game");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblBemVindo.setIcon(null);
		lblBemVindo.setBounds(135, 77, 567, 136);
		menuFrame.getContentPane().add(lblBemVindo);

		JLabel lblEscolhaSuaOp = new JLabel("O que você deseja fazer?");
		lblEscolhaSuaOp.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEscolhaSuaOp.setBounds(263, 190, 272, 47);
		menuFrame.getContentPane().add(lblEscolhaSuaOp);

		JButton novoJogoButton = new JButton("Novo Jogo");
		novoJogoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGame window = new NewGame();
				window.newGameFrame.setVisible(true);
				menuFrame.dispose();
			}
		});
		novoJogoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		novoJogoButton.setBounds(155, 306, 133, 66);
		menuFrame.getContentPane().add(novoJogoButton);

		JButton loadButton = new JButton("Carregar jogo");
		loadButton.setEnabled( false );
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGame window = new NewGame();
				window.newGameFrame.setVisible(true);
				menuFrame.dispose();
			}
		});
		loadButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loadButton.setBounds(473, 306, 133, 66);
		menuFrame.getContentPane().add(loadButton);

	}
}
