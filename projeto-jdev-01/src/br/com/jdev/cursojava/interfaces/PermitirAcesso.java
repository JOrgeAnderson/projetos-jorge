package br.com.jdev.cursojava.interfaces;

public interface PermitirAcesso {
	/*
	 * Essa interface ser� respons�vel pela autentica��o do usu�rio selecionado
	 */
	public boolean autenticar(String login, String senha);
	public boolean autenticar();

}
