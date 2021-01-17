package br.com.jwebfull.desk.funcao.busca;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.jwebfull.desk.conexaoDAO.ClienteDAO;
import br.com.jwebfull.desk.model.Cliente;

public class TelaBuscarNome extends JDialog{

	private static final long serialVersionUID = -7733592414890196978L;


	private JPanel painel = new JPanel(new GridBagLayout());

	private JLabel nome = new JLabel("Informe o nome: ");
	
	private JLabel espaco = new JLabel();
	private JLabel espaco2 = new JLabel();
	
	private JTextField campoDigita = new JTextField();

	private JButton botaoBuscar = new JButton("Buscar");
	
	private JButton botaoVoltar = new JButton("Voltar");
	
	public TelaBuscarNome() {
	
		
		setTitle("Buscar pelo nome");
		setSize(new Dimension(300, 300));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		
		
		nome.setPreferredSize(new Dimension(100, 25));
		gridBagConstraints.gridy++;
		painel.add(nome, gridBagConstraints);
		
		campoDigita.setPreferredSize(new Dimension(190, 25));
		gridBagConstraints.gridy++;
		painel.add(campoDigita, gridBagConstraints);
		
		espaco.setPreferredSize(new Dimension(190, 30));
		gridBagConstraints.gridy++;
		painel.add(espaco, gridBagConstraints);
		
		botaoBuscar.setPreferredSize(new Dimension(100, 20));
		gridBagConstraints.gridy++;
		painel.add(botaoBuscar, gridBagConstraints);
		
		espaco2.setPreferredSize(new Dimension(190, 30));
		gridBagConstraints.gridy++;
		painel.add(espaco2, gridBagConstraints);
		
		botaoVoltar.setPreferredSize(new Dimension(100, 20));
		gridBagConstraints.gridy++;
		painel.add(botaoVoltar, gridBagConstraints);
		
		botaoBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(campoDigita.getText().isEmpty()) {//Verifica se o campo está vazio ou não
					
					JOptionPane.showMessageDialog(null, "Informe um nome válido. EX: Pedro");
						setVisible(false);
						dispose();
						new TelaBuscarNome();
				}
				
					
					ClienteDAO dao = new ClienteDAO();
					
					try {
						List<Cliente> list = dao.BuscarNome(campoDigita.getText());
						for (Cliente cliente : list) {
							System.out.println(cliente.getNome());
							System.out.println(cliente.getEmail());
							System.out.println(cliente.getCpf());
							System.out.println(cliente.getUsuario());
							System.out.println("---------------------------------------------");
							
						}
						JOptionPane.showMessageDialog(null, list);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
				}
		});
		
		botaoVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new TelaBuscarCliente();
				setVisible(false);
				dispose();
				
			}
		});
		
		
		add(painel);
		setVisible(true);
	}
	
	
}
