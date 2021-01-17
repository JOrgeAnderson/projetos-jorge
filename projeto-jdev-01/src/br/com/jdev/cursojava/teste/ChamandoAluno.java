package br.com.jdev.cursojava.teste;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.jdev.cursojava.classes.Aluno;
import br.com.jdev.cursojava.classes.Diretor;
import br.com.jdev.cursojava.classes.Disciplina;
import br.com.jdev.cursojava.classesauxiliares.FuncaoAutenticacao;
import jdev.constante.StatusAluno;

public class ChamandoAluno {

	public static void main(String[] args) {

		try {
			
//			lerArquivo();

			String login = JOptionPane.showInputDialog("informe o login");
			String senha = JOptionPane.showInputDialog("informe a senha");

			if (new FuncaoAutenticacao(new Diretor(login, senha)).autenticar()) {

				List<Aluno> alunos = new ArrayList<Aluno>();

				HashMap<String, List<Aluno>> maps = new HashMap<String, List<Aluno>>();

				for (int qtd = 0; qtd < 1; qtd++) {

					String nome = JOptionPane.showInputDialog("Qual nome do aluno " + (qtd + 1) + " ?");
					String idade = JOptionPane.showInputDialog("Qual a idade?");
//		String dataNascimento = JOptionPane.showInputDialog("Infome sua data de Nascimento:");
//		String rg = JOptionPane.showInputDialog("Registro Geral?");
//		String cpf = JOptionPane.showInputDialog("Qual é o CPF?");
//		String mae = JOptionPane.showInputDialog("Qual é o nome da mãe?");
//		String pai = JOptionPane.showInputDialog("Qual é o nome do pai?");

					Aluno aluno = new Aluno();

					aluno.setNome(nome);
					aluno.setIdade(Integer.parseInt(idade));
//		aluno.setDataNascimento(dataNascimento);
//		aluno.setRegistroGeral(rg);
//		aluno.setNumeroCpf(cpf);
//		aluno.setNomeMae(mae);
//		aluno.setNomePai(pai);
					aluno.setDataMatricula(LocalDate.now());

					for (int pos = 1; pos < 2; pos++) {
						String nomeDisciplina = JOptionPane.showInputDialog("Nome da disciplina " + pos + "?");
						String notaDisciplina = JOptionPane.showInputDialog("Nota da disciplina " + pos + "?");

						Disciplina disciplina = new Disciplina();
						disciplina.setDisciplina(nomeDisciplina);
						disciplina.setNota(Double.parseDouble(notaDisciplina));

						aluno.getDisciplinas().add(disciplina);
					}

					int escolha = JOptionPane.showConfirmDialog(null, "Deseja Remover alguma disciplina");

					if (escolha == 0) {

						int continuarRemover = 0;
						int posicao = 1;
						
						while (continuarRemover == 0) {
							
							try {
							String disciplinaRemover = JOptionPane.showInputDialog("Qual a disciplina 1, 2, 3 ou 4?");
							aluno.getDisciplinas().remove(Integer.parseInt(disciplinaRemover) - posicao);
							posicao++;
							
							}catch (IndexOutOfBoundsException e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "Erro na lista de notas.");
								break;
							}
//							int confirmar = JOptionPane.showConfirmDialog(null, "Deseja apagar a disciplina "
//									+ aluno.getDisciplinas().get(Integer.parseInt(disciplinaRemover) - posicao));
//							if (confirmar == 0) {
//							}

							continuarRemover = JOptionPane.showConfirmDialog(null, "Continuar a remover?");

						}
					}

					alunos.add(aluno);
				}

				maps.put(StatusAluno.APROVADO, new ArrayList<Aluno>());
				maps.put(StatusAluno.REPROVADO, new ArrayList<Aluno>());
				maps.put(StatusAluno.RECUPERACAO, new ArrayList<Aluno>());

				for (Aluno aluno : alunos) {

					if (aluno.getResultado2().equalsIgnoreCase(StatusAluno.APROVADO)) {
						maps.get(StatusAluno.APROVADO).add(aluno);
					} else if (aluno.getResultado2().equalsIgnoreCase(StatusAluno.RECUPERACAO)) {
						maps.get(StatusAluno.RECUPERACAO).add(aluno);
					} else {
						maps.get(StatusAluno.REPROVADO).add(aluno);
					}

				}

				System.out.println("---------------------- LISTA DOS APROVADOS -----------------------");
				for (Aluno aluno : maps.get(StatusAluno.APROVADO)) {
					System.out.println("Aluno: " + aluno.getNome() + "\nResultado: " + aluno.getResultado2()
							+ " Com a média de = " + aluno.getMediaNota());
				}

				System.out.println("---------------------- LISTA DOS REPROVADOS -----------------------");
				for (Aluno aluno : maps.get(StatusAluno.REPROVADO)) {
					System.out.println("Aluno: " + aluno.getNome() + "\nResultado: " + aluno.getResultado2()
							+ " Com a média de = " + aluno.getMediaNota());
				}

				System.out.println("---------------------- LISTA DE RECUPERAÇÃO -----------------------");
				for (Aluno aluno : maps.get(StatusAluno.RECUPERACAO)) {
					System.out.println("Aluno: " + aluno.getNome() + "\nResultado: " + aluno.getResultado2()
							+ " Com a média de = " + aluno.getMediaNota());
				}

			} else {
				JOptionPane.showMessageDialog(null, "Login ou senha incorreto!");
			}
		} catch (Exception e) {
			StringBuilder saida = new StringBuilder();

			e.printStackTrace();/* imprime erro no console */

			System.out.println("Mensagem: " + e.getMessage());

			for (int pos = 0; pos < e.getStackTrace().length; pos++) {
				saida.append("\nClasse de erro: " + e.getStackTrace()[pos].getClassName());
				saida.append("\nMétodo de erro: " + e.getStackTrace()[pos].getMethodName());
				saida.append("\nLinha de erro: " + e.getStackTrace()[pos].getLineNumber());
				saida.append("\nClass Exception: " + e.getClass().getName());
			}

		
		} finally {/* Sempre é executado ocorrendo erros ou não */
			JOptionPane.showMessageDialog(null, "Obrigado por ensina Java.");
		}
	}

	public static void lerArquivo() throws FileNotFoundException {

			File file = new File("c://lines.txt");
			@SuppressWarnings({ "unused", "resource" })
			Scanner scanner = new Scanner(file);

	}

}
