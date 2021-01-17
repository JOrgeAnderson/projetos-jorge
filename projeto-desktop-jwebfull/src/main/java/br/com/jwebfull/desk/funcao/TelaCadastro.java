package br.com.jwebfull.desk.funcao;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.jwebfull.desk.conexaoDAO.ClienteDAO;
import br.com.jwebfull.desk.funcao.opacao.TelaOpcao;
import br.com.jwebfull.desk.model.Cliente;

public class TelaCadastro extends JDialog{

	private static final long serialVersionUID = -6845393719472348274L;
	
	private JPanel painel = new JPanel(new GridBagLayout());

	private JButton botao = new JButton("Salvar");
	
	private JLabel espaco = new JLabel();
	
	private JButton botaoVoltar = new JButton("Voltar");

	private JLabel nome = new JLabel("Nome");
	private JTextField campoNome = new JTextField();

	private JLabel email = new JLabel("E-mail");
	private JTextField campoEmail = new JTextField();
	
	private JLabel cpf = new JLabel("CPF");
	private JTextField campoCpf = new JTextField();
	
	private JLabel usuario = new JLabel("Usuário");
	private JTextField campoUsuario = new JTextField();
	
	private JLabel senha = new JLabel("Senha");
	private JPasswordField campoSenha = new JPasswordField();
	
	private JLabel senha1 = new JLabel("Confirmar Senha");
	private JPasswordField campoSenha1 = new JPasswordField();
	
	public TelaCadastro() {
		
		setTitle("Tela de Cadastramento de Clientes");
		setSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	GridBagConstraints constraints = new GridBagConstraints();
	constraints.gridx = 0;
	constraints.gridy = 0;

//	constraints.insets = new Insets(5, 10, 5, 5);
//	constraints.insets = new Insets(10, 10, 5, 5);
	constraints.anchor = GridBagConstraints.CENTER;
		
	nome.setPreferredSize(new Dimension(50, 30));
	painel.add(nome, constraints);
	campoNome.setPreferredSize(new Dimension(250, 25));
	constraints.gridy ++;
	painel.add(campoNome, constraints);
	

	email.setPreferredSize(new Dimension(50, 30));
	constraints.gridy ++;
	painel.add(email, constraints);
	campoEmail.setPreferredSize(new Dimension(250, 30));
	constraints.gridy ++;
	painel.add(campoEmail, constraints);
	
	
	cpf.setPreferredSize(new Dimension(40, 30));
	constraints.gridy ++;
	painel.add(cpf, constraints);
	campoCpf.setPreferredSize(new Dimension(250, 30));
	constraints.gridy ++;
	painel.add(campoCpf, constraints);
	
	
	usuario.setPreferredSize(new Dimension(50, 30));
	constraints.gridy ++;
	painel.add(usuario, constraints);
	campoUsuario.setPreferredSize(new Dimension(250, 30));
	constraints.gridy ++;
	painel.add(campoUsuario, constraints);
		
	
	senha.setPreferredSize(new Dimension(50, 30));
	constraints.gridy ++;
	painel.add(senha, constraints);
	campoSenha.setPreferredSize(new Dimension(250, 30));
	constraints.gridy ++;
	painel.add(campoSenha, constraints);
	
	
	senha1.setPreferredSize(new Dimension(110, 30));
	constraints.gridy ++;
	painel.add(senha1, constraints);
	campoSenha1.setPreferredSize(new Dimension(250, 30));
	constraints.gridy ++;
	painel.add(campoSenha1, constraints);
	
	botao.setPreferredSize(new Dimension(100, 20));
	constraints.gridy ++;
	painel.add(botao, constraints);
	
	espaco.setPreferredSize(new Dimension(100, 20));
	constraints.gridy ++;
	painel.add(espaco, constraints);
	
	botaoVoltar.setPreferredSize(new Dimension(100, 20));
	constraints.gridy ++;
	painel.add(botaoVoltar, constraints);
	
	botao.addActionListener(new ActionListener() {
		
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(!campoSenha1.getText().equals(campoSenha.getText())) {
				
				JOptionPane.showMessageDialog(null, "AS SENHAS NÃO SE IGUALAM.");
				
			}else {
			
				ClienteDAO dao = new ClienteDAO();
				Cliente cliente = new Cliente();
				
				cliente.setNome(campoNome.getText());
				cliente.setEmail(campoEmail.getText());
				cliente.setCpf(campoCpf.getText());
				cliente.setUsuario(campoUsuario.getText());
				cliente.setSenha(campoSenha1.getText());
				cliente.setStatus("ATIVO");
				
				dao.salvarCliente(cliente);
				
			JOptionPane.showMessageDialog(null, "SALVO COM SUCESSO! \n"+cliente);
			setVisible(false);
			dispose();
				new TelaCadastro();
				
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
	
		add(painel, BorderLayout.CENTER);
		setVisible(true);
	}
}