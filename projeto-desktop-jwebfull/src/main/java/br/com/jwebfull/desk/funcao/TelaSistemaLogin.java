package br.com.jwebfull.desk.funcao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.jwebfull.desk.funcao.opacao.TelaOpcao;
import br.com.jwebfull.desk.model.Admin;

public class TelaSistemaLogin extends JDialog {

	private static final long serialVersionUID = 8178960331030715994L;

	private JPanel panel = new JPanel(new GridBagLayout());

	private JLabel usuario = new JLabel("usuario");
	private JTextField campoUsuario = new JTextField();

	 
	private JLabel senha = new JLabel("senha");
	private JPasswordField campoSenha = new JPasswordField();
//	private JTextField campoSenha = new JTextField();

	private JButton botaoentrar = new JButton("ENTRAR");

	public TelaSistemaLogin() {

		setTitle("Tela de login Admin");
		setSize(new Dimension(400, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 0, 255));

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
//		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(10, 10, 5, 5);
//		gridBagConstraints.anchor = GridBagConstraints.WEST;

		usuario.setPreferredSize(new Dimension(50, 30));
		panel.add(usuario, gridBagConstraints);

		campoUsuario.setPreferredSize(new Dimension(250, 25));
		gridBagConstraints.gridy++;
		panel.add(campoUsuario, gridBagConstraints);

		senha.setPreferredSize(new Dimension(50, 30));
		gridBagConstraints.gridy++;
		panel.add(senha, gridBagConstraints);

		campoSenha.setPreferredSize(new Dimension(250, 25));
		gridBagConstraints.gridy++;
		panel.add(campoSenha, gridBagConstraints);

		botaoentrar.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridy++;
		panel.add(botaoentrar, gridBagConstraints);

		botaoentrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Admin admin = new Admin();
				admin.setUser(campoUsuario.getText());
				admin.setSenha(new String (campoSenha.getPassword()));
//				int tentativas = 0;

				if (admin.getUser().equals("admin") && admin.getSenha().equals("@12345")) {

					@SuppressWarnings("unused")
					TelaOpcao opcao = new TelaOpcao();
					setVisible(false);
					dispose();
				}else {

					int escolha = JOptionPane.showConfirmDialog(null, "SENHA OU USUÁRIO INCORRETO, Continuar?");

				if (escolha == 0) {
						setVisible(false);
						dispose();
						new TelaSistemaLogin();

					}
				if(escolha == 1) {
					setVisible(false);
					dispose();
				}
				
				}

//				if (tentativas == 3) {
//					JOptionPane.showMessageDialog(null, "N�mero de tentaivas expirou");
//
//				}
			}
		});
		add(panel);
		setVisible(true);
	}

}
