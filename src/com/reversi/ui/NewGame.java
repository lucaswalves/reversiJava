package com.reversi.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewGame {

	JFrame newGameFrame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGame window = new NewGame();
					window.newGameFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		newGameFrame = new JFrame();
		newGameFrame.setFont(new Font("Constantia", Font.PLAIN, 12));
		newGameFrame.setTitle("Modalidade");
		newGameFrame.setBounds(100, 100, 802, 573);
		newGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newGameFrame.getContentPane().setLayout(null);

		JLabel lblBemVindo = new JLabel("Escolhe seu modo de jogo");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblBemVindo.setIcon(null);
		lblBemVindo.setBounds(135, 77, 567, 136);
		newGameFrame.getContentPane().add(lblBemVindo);

		JLabel lblEscolhaSuaOp = new JLabel("O que você deseja fazer?");
		lblEscolhaSuaOp.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEscolhaSuaOp.setBounds(263, 190, 272, 47);
		newGameFrame.getContentPane().add(lblEscolhaSuaOp);

		JButton humaHumaButton = new JButton("Humano X Humano");
		humaHumaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGame window = new NewGame();
				window.newGameFrame.setVisible(true);
				newGameFrame.dispose();
			}
		});
		humaHumaButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		humaHumaButton.setBounds(140, 306, 200, 66);
		newGameFrame.getContentPane().add(humaHumaButton);

		JButton humaPCButton = new JButton("Humano X Computador");
		humaPCButton.setEnabled( false );
		humaPCButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGame window = new NewGame();
				window.newGameFrame.setVisible(true);
				newGameFrame.dispose();
			}
		});
		humaPCButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		humaPCButton.setBounds(460, 306, 200, 66);
		newGameFrame.getContentPane().add(humaPCButton);

	}
}
