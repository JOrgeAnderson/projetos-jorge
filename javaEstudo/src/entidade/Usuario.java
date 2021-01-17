package entidade;

public class Usuario {

	private Long id;
	private String login;
	private String senha;
	private String imagem;
	private String tipofile;
	
	public String getTipofile() {
		return tipofile;
	}

	public void setTipofile(String tipofile) {
		this.tipofile = tipofile;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
