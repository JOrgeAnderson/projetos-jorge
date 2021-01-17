package br.com.jdev.cursojava.classes;

import br.com.jdev.cursojava.interfaces.PermitirAcesso;

public class Secretario extends Pessoa implements PermitirAcesso{
	
	private String Registro;
	private String NivelCargo;
	private String experiencia;
	
	private String login;
	private String senha;
	
	public Secretario() {
	}
	
	public Secretario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRegistro() {
		return Registro;
	}
	public void setRegistro(String registro) {
		Registro = registro;
	}
	public String getNivelCargo() {
		return NivelCargo;
	}
	public void setNivelCargo(String nivelCargo) {
		NivelCargo = nivelCargo;
	}
	public String getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}
	
	@Override
	public String toString() {
		return "Secretario [Registro=" + Registro + ", NivelCargo=" + NivelCargo + ", experiencia=" + experiencia
				+ ", nome=" + nome + ", idade=" + idade + ", dataNascimento=" + dataNascimento + ", registroGeral="
				+ registroGeral + ", numeroCpf=" + numeroCpf + ", nomeMae=" + nomeMae + ", nomePai=" + nomePai + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NivelCargo == null) ? 0 : NivelCargo.hashCode());
		result = prime * result + ((Registro == null) ? 0 : Registro.hashCode());
		result = prime * result + ((experiencia == null) ? 0 : experiencia.hashCode());
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
		Secretario other = (Secretario) obj;
		if (NivelCargo == null) {
			if (other.NivelCargo != null)
				return false;
		} else if (!NivelCargo.equals(other.NivelCargo))
			return false;
		if (Registro == null) {
			if (other.Registro != null)
				return false;
		} else if (!Registro.equals(other.Registro))
			return false;
		if (experiencia == null) {
			if (other.experiencia != null)
				return false;
		} else if (!experiencia.equals(other.experiencia))
			return false;
		return true;
	}

	@Override
	public boolean autenticar(String login, String senha) {
		this.login = login;
		this.senha = senha;
		return autenticar();
	}
	@Override
	public boolean autenticar() {

		return login.equals("admin") && senha.equals("admin");
	}

	
}
