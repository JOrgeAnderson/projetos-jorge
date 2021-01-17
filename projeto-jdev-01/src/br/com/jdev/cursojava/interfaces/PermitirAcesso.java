package br.com.jdev.cursojava.interfaces;

public interface PermitirAcesso {
	/*
	 * Essa interface será responsável pela autenticação do usuário selecionado
	 */
	public boolean autenticar(String login, String senha);
	public boolean autenticar();

}
