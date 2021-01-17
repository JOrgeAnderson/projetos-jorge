package br.com.jdev.cursojava.teste;

import br.com.jdev.cursojava.classes.Aluno;
import br.com.jdev.cursojava.classes.Diretor;
import br.com.jdev.cursojava.classes.Secretario;

public class TestandoClassesFilhas {
	
	public static void main(String[] args) {
		
		Aluno aluno = new Aluno();
		aluno.setNome("Jorge jdev - treinamento");
		aluno.setNomeEscola("Jdev - treinamento");
		aluno.setIdade(16);
		
		Diretor diretor = new Diretor();
		diretor.setRegistroGeral("8845847");
		diretor.setNome("Edigio");
		diretor.setIdade(50);
		
		Secretario secretario = new Secretario();
		secretario.setExperiencia("Administração");
		secretario.setNumeroCpf("555.555.555-90");
		secretario.setIdade(18);
		
		System.out.println(aluno.pessoaMaiorIdade()+ " - "+ aluno.msgMaiorIdade());
		System.out.println(diretor.pessoaMaiorIdade());
		System.out.println(secretario.pessoaMaiorIdade());
	
		
	}
	

}
