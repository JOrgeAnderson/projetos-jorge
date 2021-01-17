package br.com.enviandomail;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	
	@Test
	public void  testeEmail() {
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("Olá, <br/><br/>");
		stringBuilderTextoEmail.append("<h2>Você está recebendo o acesso ao curso Java</h2> <br/><br/>");
		stringBuilderTextoEmail.append("Para ter acesso, clique no botão abaixo<br/><br/>");
		stringBuilderTextoEmail.append("<b>Login:</b> jthech@hjaus.com<br/>");
		stringBuilderTextoEmail.append("<b>Senha:</b> 15262656538<br/><br/>");
		stringBuilderTextoEmail.append("<a target=\"_blank\" href = \"http://projetojavaweb.com/certificado-aluno/login\" style=\"color: #2525a7; padding: 12px 25px; text-align: center; text-decoration: none; display: inline-block; border-radius: 30px; font-size: 20px; font-family: courier; border: 3px solid green; background-color: #99DA39;\">Acessar Portal do Aluno</a><br/><br/>");
		
		stringBuilderTextoEmail.append("<span style=\"font-size: 8px;\">Ass. JA.T3CH LTDA.</span>");
		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("anderson.ribeiro56@gmail.com", 
															"Jorge - JA.T3CH", 
															"Testando e-mail com Java", 
															stringBuilderTextoEmail.toString());
		
		try {
			enviaEmail.enviarEmailAnexo(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
