package cursojava.thread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TelaThreadLogin extends JDialog {

	private JPanel jPanel = new JPanel(new GridBagLayout());/* Painel de componentes */

	private JLabel descricaoHora = new JLabel("Nome");
	private JTextField mostraTempo = new JTextField();

	private JLabel descricaoHora2 = new JLabel("E-mail");
	private JTextField mostraTempo2 = new JTextField();

	private JButton button = new JButton("Gerar Lote");
	private JButton button2 = new JButton("Stop");
	
	private ImplementacaoFilaThread filaThread = new ImplementacaoFilaThread();

	public TelaThreadLogin() {/* eXECUTA O QUE ESTIVER DENTRO NO MOMENTO DA ABERTURA OU EXECULÇÃO */

		setTitle("Minha tela de time com thread");
		setSize(new Dimension(240, 240));/* Largura e Altura */
		setLocationRelativeTo(null);/* Centralizar a tela */
		setResizable(false);/* Para não permitir o aumento de tela */
		/* Primeira parte concluida */

		GridBagConstraints gridbagConstraints = new GridBagConstraints();/* Controlador de componentes */
		gridbagConstraints.gridx = 0;
		gridbagConstraints.gridy = 0;
		gridbagConstraints.gridwidth = 2;// LARGURA EM 2
		gridbagConstraints.insets = new Insets(5, 10, 5, 5);
		gridbagConstraints.anchor = GridBagConstraints.WEST;// alinhar a esquerda

		descricaoHora.setPreferredSize(new Dimension(200, 25));/* tamanho que vai ficar na tela */
		jPanel.add(descricaoHora, gridbagConstraints);

		mostraTempo.setPreferredSize(new Dimension(200, 25));
		gridbagConstraints.gridy++;// y = 2
		jPanel.add(mostraTempo, gridbagConstraints);

		descricaoHora2.setPreferredSize(new Dimension(200, 25));
		gridbagConstraints.gridy++;// y = 3
		jPanel.add(descricaoHora2, gridbagConstraints);

		mostraTempo2.setPreferredSize(new Dimension(200, 25));
		gridbagConstraints.gridy++;// y = 4
		jPanel.add(mostraTempo2, gridbagConstraints);

		// *************************************************************************

		gridbagConstraints.gridwidth = 1;// VOLTANDO A LARGURA PRA 1

		button.setPreferredSize(new Dimension(92, 25));
		gridbagConstraints.gridy++;// y = 5
		jPanel.add(button, gridbagConstraints);
		

		button2.setPreferredSize(new Dimension(92, 25));
		gridbagConstraints.gridx++;// x = 1
		jPanel.add(button2, gridbagConstraints);

		button.addActionListener(new ActionListener() {

			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {/* Exuta o clique no botão */
				
				
				
				for(int qtd = 0; qtd < 100; qtd++) {/*Simulando 100 envios em massa*/
				
				ObjetoFilaThread fila = new ObjetoFilaThread();
				fila.setNome(mostraTempo.getText());
				fila.setEmail(mostraTempo2.getText()+ "- "+ qtd);
				
				filaThread.add(fila);
					
				if(filaThread == null) {
					filaThread =  new ImplementacaoFilaThread();
					filaThread.start();
					qtd = 0;
				}
				}
			}
		});
		
		button2.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				filaThread.stop();
				filaThread = null;
			}
		});
		
		filaThread.start();
		add(jPanel, BorderLayout.WEST);
		/* Sempre será o ultimo comando */
		setVisible(true);/* Torna a tela visivel para o usuário */
	}
}
