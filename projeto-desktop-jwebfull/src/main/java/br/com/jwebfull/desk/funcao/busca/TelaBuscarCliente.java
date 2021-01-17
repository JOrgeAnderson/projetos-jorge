package br.com.jwebfull.desk.funcao.busca;

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

import br.com.jwebfull.desk.funcao.opacao.TelaOpcao;

public class TelaBuscarCliente extends JDialog{

	private static final long serialVersionUID = 1L;

	
	private JPanel panel = new JPanel(new GridBagLayout());
	
	private JLabel escolha = new JLabel("ESCOLHA A OPÇÃO DE BUSCA");
	
	private JLabel escolhaNome = new JLabel("1 - Buscar pelo Nome");
	
	private JLabel escolhaEmail = new JLabel("2 - Buscar pelo E-mail");
	private JLabel escolhaUsuario = new JLabel("3 - Buscar pelo Usuário");
	private JLabel escolhaCpf = new JLabel("4 - Buscar pelo Cpf");
	
	
	private JTextField campo = new JTextField();
	private JButton botaoentrar = new JButton("Buscar");
	private JLabel espaco = new JLabel();
	private JButton botaoVoltar = new JButton("Voltar");
	
	public TelaBuscarCliente() {
	
		setTitle("Tela de de busca");
		setSize(new Dimension(400, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		
		escolha.setPreferredSize(new Dimension(190, 30));
		gridBagConstraints.gridy++;
		panel.add(escolha, gridBagConstraints);
		
		escolhaNome.setPreferredSize(new Dimension(190, 30));
		gridBagConstraints.gridy++;
		panel.add(escolhaNome, gridBagConstraints);
		
		escolhaEmail.setPreferredSize(new Dimension(190, 30));
		gridBagConstraints.gridy++;
		panel.add(escolhaEmail, gridBagConstraints);
		
		escolhaUsuario.setPreferredSize(new Dimension(190, 30));
		gridBagConstraints.gridy++;
		panel.add(escolhaUsuario, gridBagConstraints);
		
		escolhaCpf.setPreferredSize(new Dimension(190, 30));
		gridBagConstraints.gridy++;
		panel.add(escolhaCpf, gridBagConstraints);
		
		campo.setPreferredSize(new Dimension(190, 20));
		gridBagConstraints.gridy++;
		panel.add(campo, gridBagConstraints);
		
		
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		botaoentrar.setPreferredSize(new Dimension(100, 20));
		gridBagConstraints.gridy++;
		panel.add(botaoentrar, gridBagConstraints);
		
		espaco.setPreferredSize(new Dimension(190, 20));
		gridBagConstraints.gridy++;
		panel.add(espaco, gridBagConstraints);
		
		
		botaoVoltar.setPreferredSize(new Dimension(100, 25));
		gridBagConstraints.gridy++;
		
		panel.add(botaoVoltar, gridBagConstraints);
		
		botaoentrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if (campo.getText().equals("1")) {
					
					@SuppressWarnings("unused")
					TelaBuscarNome telaNome = new TelaBuscarNome();
					setVisible(false);
					dispose();
				}
				
				
			}
		});
		
		botaoVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new TelaOpcao();
				setVisible(false);
				dispose();
				
				
			}
		});
		
		
		add(panel);
		setVisible(true);
	}
	
	
}
