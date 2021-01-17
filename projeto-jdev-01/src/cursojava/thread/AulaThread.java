package cursojava.thread;

import javax.swing.JOptionPane;

public class AulaThread {

	public static void main(String[] args) throws InterruptedException {

		/* Thread processando em paralelo do envio de email */
		Thread threadEmail = new Thread(thread1);
		threadEmail.start();

		/* Thread processando em paralelo de envio de nota fiscal */
		Thread threadNFCE = new Thread(thread2);
		threadNFCE.start();

		System.out.println("CHEGOU O FINAL DO C�GIGO DE TESTE");

		JOptionPane.showMessageDialog(null, "Sistema continua executando para o usu�rio");
	}

	private static Runnable thread2 = new Runnable() {

		@Override
		public void run() {
			/* C�digo de rotina */
			for (int pos = 0; pos < 10; pos++) {

				/*
				 * Quero executar essa envio com um tempo de parada, ou com um tempo determinado
				 */
				System.out.println("Executando um envio de nota fiscal");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} /* D� um tempo para cada processo */
			}

			/* Fim do c�digo paralelo */

		}
	};

	private static Runnable thread1 = new Runnable() {

		@Override
		public void run() {

			/* C�digo de rotina */
			for (int pos = 0; pos < 10; pos++) {

				/*
				 * Quero executar essa envio com um tempo de parada, ou com um tempo determinado
				 */
				System.out.println("Executando um envio de e-mail");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} /* D� um tempo para cada processo */
			}

			/* Fim do c�digo paralelo */

		}
	};
}