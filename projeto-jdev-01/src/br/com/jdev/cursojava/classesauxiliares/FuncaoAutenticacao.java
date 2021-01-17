package br.com.jdev.cursojava.classesauxiliares;

import br.com.jdev.cursojava.interfaces.PermitirAcesso;

public class FuncaoAutenticacao {

	private PermitirAcesso permitirAcesso;
	
	
	public boolean autenticar() {
		return permitirAcesso.autenticar();
	}
	
	
	public FuncaoAutenticacao(PermitirAcesso acesso) {
		this.permitirAcesso = acesso;
		
	}
}
