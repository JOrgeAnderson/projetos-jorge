package br.com.jwebfull.desk.funcao.busca;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.jwebfull.desk.conexaoDAO.ClienteDAO;
import br.com.jwebfull.desk.model.Cliente;

public class TelaHabilitarCliente extends JDialog {

	private static final long serialVersionUID = -6386416160922299711L;

	private JPanel painel = new JPanel(new GridBagLayout());

	private JLabel informa = new JLabel("Informe o id do Usuário");
	private JLabel espaco = new JLabel();
	private JLabel espaco2 = new JLabel();

	private JTextField campoDigita = new JTextField();

	private JButton botaoBuscar = new JButton("Buscar");

	private JButton botaoVoltar = new JButton("Voltar");

	public TelaHabilitarCliente() {

		setTitle("Tela de Habilitação de usuário");
		setSize(new Dimension(400, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = GridBagConstraints.WEST;

		informa.setPreferredSize(new Dimension(190, 25));
		gridBagConstraints.gridy++;
		painel.add(informa, gridBagConstraints);

		campoDigita.setPreferredSize(new Dimension(190, 25));
		gridBagConstraints.gridy++;
		painel.add(campoDigita, gridBagConstraints);

		botaoBuscar.setPreferredSize(new Dimension(100, 25));
		gridBagConstraints.gridy++;
		painel.add(botaoBuscar, gridBagConstraints);

		botaoBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				long id = Long.parseLong(campoDigita.getText());

				ClienteDAO dao = new ClienteDAO();
				try {
					Cliente busca = dao.buscarId(id);
					if (busca.toString().isEmpty() == true) {
						JOptionPane.showMessageDialog(null, "O id " + id + " informado não existe");
					}
					
					int resposta = JOptionPane.showConfirmDialog(null, "Deseja HABILITAR o usuário "+ busca+"?");
					
					if (resposta == 0) {
						dao.habilitarUser(busca.getId());
					}
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		add(painel, BorderLayout.CENTER);
		setVisible(true);
	}

}
