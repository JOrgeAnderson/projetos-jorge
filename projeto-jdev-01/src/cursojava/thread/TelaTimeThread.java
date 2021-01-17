package cursojava.thread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TelaTimeThread extends JDialog {

	private JPanel jPanel = new JPanel(new GridBagLayout());/* Painel de componentes */

	private JLabel descricaoHora = new JLabel("Time Thread 1");
	private JTextField mostraTempo = new JTextField();

	private JLabel descricaoHora2 = new JLabel("Time Thread 2");
	private JTextField mostraTempo2 = new JTextField();

	private JButton button = new JButton("Start");
	private JButton button2 = new JButton("Stop");

	private Runnable thread1 = new Runnable() {
		@Override
		public void run() {
			while (true) {/* FICA SEMPRE RODANDO */
				mostraTempo
						.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm.ss").format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	private Thread thread1Time;
	
	private Runnable thread2 = new Runnable() {
		@Override
		public void run() {
			while (true) {/* FICA SEMPRE RODANDO */
				mostraTempo2.setText(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
						.format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	private Thread thread2Time;

	public TelaTimeThread() {/* eXECUTA O QUE ESTIVER DENTRO NO MOMENTO DA ABERTURA OU EXECULÇÃO */

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
		mostraTempo.setEditable(false);
		jPanel.add(mostraTempo, gridbagConstraints);

		descricaoHora2.setPreferredSize(new Dimension(200, 25));
		gridbagConstraints.gridy++;// y = 3
		jPanel.add(descricaoHora2, gridbagConstraints);

		mostraTempo2.setPreferredSize(new Dimension(200, 25));
		gridbagConstraints.gridy++;// y = 4
		mostraTempo2.setEditable(false);
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

			@Override
			public void actionPerformed(ActionEvent e) {/* Exuta o clique no botão */

				thread1Time = new Thread(thread1);
				thread1Time.start();
				
				thread2Time = new Thread(thread2);
				thread2Time.start();
				
				button.setEnabled(false);
				button2.setEnabled(true);
			}
			
		});
		
		button2.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				thread1Time.stop();
				thread2Time.stop();
				
				button.setEnabled(true);
				button2.setEnabled(false);
				
			}
		});
		
		button2.setEnabled(false);
		
		add(jPanel, BorderLayout.WEST);
		/* Sempre será o ultimo comando */
		setVisible(true);/* Torna a tela visivel para o usuário */
	}
}
