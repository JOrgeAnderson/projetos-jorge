package br.com.jdev.cursojava.excecao;

public class ExcecaoProcessarNota extends Exception{


	private static final long serialVersionUID = 39969723191381523L;
	

	public ExcecaoProcessarNota(String msg) {
		
		super("Vish, um erro no processamento do arquivo ao processar as notas"+ msg);
	}

}
