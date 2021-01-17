package br.com.jdev.cursojava.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jdev.constante.StatusAluno;

public class Aluno extends Pessoa{

	
	private LocalDate dataMatricula;
	private String nomeEscola;
	private String serieMatriculado;
	private List<Disciplina> disciplinas = new ArrayList<>();

	public Aluno() {

	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		super.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		super.idade = idade;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		super.dataNascimento = dataNascimento;
	}

	public String getRegistroGeral() {
		return registroGeral;
	}

	public void setRegistroGeral(String registroGeral) {
		super.registroGeral = registroGeral;
	}

	public String getNumeroCpf() {
		return numeroCpf;
	}

	public void setNumeroCpf(String numeroCpf) {
		super.numeroCpf = numeroCpf;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		super.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		super.nomePai = nomePai;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(LocalDate dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	
	public String getNomeEscola() {
		return nomeEscola;
	}

	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}

	public String getSerieMatriculado() {
		return serieMatriculado;
	}

	public void setSerieMatriculado(String serieMatriculado) {
		this.serieMatriculado = serieMatriculado;
	}

	public double getMediaNota() {
		double somaNotas = 0.0;
		
		for (Disciplina disciplina : disciplinas) {
			somaNotas += disciplina.getNota();
		}
		
		return somaNotas / disciplinas.size();

	}
	

	public boolean getResultado() {

		if (getMediaNota() >= 70) {
			return true;
		}
		return false;
	}
	public String getResultado2() {
		
		double media = this.getMediaNota();
		
		if (media >= 50) {
			if(media >= 70) {
				return StatusAluno.APROVADO;
			}else {
				return StatusAluno.RECUPERACAO;
			}
		}
		return StatusAluno.REPROVADO;
	}
	
	

	@Override
	public String toString() {
		return "Aluno [nome=" + nome + "\nidade=" + idade + "\ndataNascimento=" + dataNascimento + "\nregistroGeral="
				+ registroGeral + "\nnumeroCpf=" + numeroCpf + "\nnomeMae=" + nomeMae + "\nnomePai=" + nomePai
				+ "\ndataMatricula=" + dataMatricula + "\ndisciplinas=" + disciplinas + "]\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public boolean pessoaMaiorIdade() {
		
		
		return idade >=21;
	}
	
	public String msgMaiorIdade() {
		return this.pessoaMaiorIdade() ? "Obaa aluno é maior de idade" : "Vish, o aluno é menor de idade";
	}
}