package br.com.jwebfull.desk.funcao.opacao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.jwebfull.desk.funcao.TelaCadastro;
import br.com.jwebfull.desk.funcao.busca.TelaBuscarCliente;
import br.com.jwebfull.desk.funcao.busca.TelaHabilitarCliente;

public class TelaOpcao extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel painel = new JPanel(new GridBagLayout());

	private JLabel escolha1 = new JLabel("1 - Adicionar Cliente/Usuário");
	private JLabel escolha2 = new JLabel("2 - Buscar Cliente/Usuário");
	private JLabel escolha4 = new JLabel("3 - Hábilitar Cliente/Usuário");
	private JLabel escolha5 = new JLabel("4 - Desabilitar Cliente/Usuário");

	private JTextField campoEscolha = new JTextField();

	private JButton botaoentrar = new JButton("ACESSAR");

	public TelaOpcao() {

		setTitle("Tela de opções");
		setSize(new Dimension(400, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = GridBagConstraints.WEST;

		escolha1.setPreferredSize(new Dimension(190, 30));
		gridBagConstraints.gridy++;
		painel.add(escolha1, gridBagConstraints);

		escolha2.setPreferredSize(new Dimension(190, 30));
		gridBagConstraints.gridy++;
		painel.add(escolha2, gridBagConstraints);

		escolha4.setPreferredSize(new Dimension(190, 30));
		gridBagConstraints.gridy++;
		painel.add(escolha4, gridBagConstraints);

		escolha5.setPreferredSize(new Dimension(190, 30));
		gridBagConstraints.gridy++;
		painel.add(escolha5, gridBagConstraints);

		campoEscolha.setPreferredSize(new Dimension(190, 20));
		gridBagConstraints.gridy++;
		painel.add(campoEscolha, gridBagConstraints);

		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		botaoentrar.setPreferredSize(new Dimension(100, 20));
		gridBagConstraints.gridy++;
		painel.add(botaoentrar, gridBagConstraints);

		botaoentrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (campoEscolha.getText().equals("1")) {
					new TelaCadastro();
					setVisible(false);
					dispose();
				}

				else if (campoEscolha.getText().equals("2")) {

					new TelaBuscarCliente();
					setVisible(false);
					dispose();
				}

				else if (campoEscolha.getText().equals("3")) {


							new TelaHabilitarCliente();
							setVisible(false);
							dispose();

				}

			}
		});

		add(painel, BorderLayout.CENTER);
		setVisible(true);
	}

}
