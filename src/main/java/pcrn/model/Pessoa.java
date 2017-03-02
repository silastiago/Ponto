package pcrn.model;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** Esta Classe que possui os metodos de acesso getter e setters que representa um usuario do sistema, 
 * e tamb�m possui o mapeamento relacional das tabelas via hibernate, da entidade Pessoa.
*   
* @author silas
*
*/

@Entity
@Table
public class Pessoa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private String login;
	private String senha;
	private String matricula;
	private String cpf;
	private String rg;
	private String nome;
	private String sobreNome;
	private String ultimoNome;
	private Date dataNascimento;
	private Date dataInicioEstagio;
	private String nomePai;
	private String sobreNomePai;
	private String ultimoNomePai;
	private String nomeMae;
	private String sobreNomeMae;
	private String ultimoNomeMae;
	
	private List<Grupo> grupos;
	private Delegacia delegacia;

	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	@NotEmpty(message = "Login deve ser informado")
	@Column(name="login")
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login.toUpperCase();
	}
	
	@NotEmpty(message = "Senha deve ser informada")
	@Column(name="senha")
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}	
	
	@NotEmpty(message = "Matricula deve ser informada")
	@Column(name="matricula")
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	@NotEmpty(message = "CPF deve ser informado")
	@Column(name="cpf")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@NotEmpty(message = "RG deve ser informado")
	@Column(name="rg")
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	@NotEmpty(message = "Nome deve ser informado")
	@Column(name = "nome_pessoa")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name="sobre_nome")
	public String getSobreNome() {
		return sobreNome;
	}
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	
	@NotEmpty(message = "Ultimo nome deve ser informado")
	@Column(name="ultimo_nome")
	public String getUltimoNome() {
		return ultimoNome;
	}
	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="data_nascimento")
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Column(name="data_inicio_estagio")
	public Date getDataInicioEstagio() {
		return dataInicioEstagio;
	}
	public void setDataInicioEstagio(Date dataInicioEstagio) {
		this.dataInicioEstagio = dataInicioEstagio;
	}
	@NotEmpty(message = "Nome do pai deve ser informado")
	@Column(name="nome_pai")
	public String getNomePai() {
		return nomePai;
	}
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	
	@Column(name="sobre_nome_pai")
	public String getSobreNomePai() {
		return sobreNomePai;
	}
	public void setSobreNomePai(String sobreNomePai) {
		this.sobreNomePai = sobreNomePai;
	}
	
	@NotEmpty(message = "Ultimo nome do pai deve ser informado")
	@Column(name="ultimo_nome_pai")
	public String getUltimoNomePai() {
		return ultimoNomePai;
	}
	public void setUltimoNomePai(String ultimoNomePai) {
		this.ultimoNomePai = ultimoNomePai;
	}
	
	@NotEmpty(message = "Nome da mae deve ser informado")
	@Column(name="nome_mae")
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	
	@Column(name="sobre_nome_mae")
	public String getSobreNomeMae() {
		return sobreNomeMae;
	}
	public void setSobreNomeMae(String sobreNomeMae) {
		this.sobreNomeMae = sobreNomeMae;
	}
	
	@NotEmpty(message = "Ultimonome da mae deve ser informado")
	@Column(name="ultimo_nome_mae")
	public String getUltimoNomeMae() {
		return ultimoNomeMae;
	}
	public void setUltimoNomeMae(String ultimoNomeMae) {
		this.ultimoNomeMae = ultimoNomeMae;
	}
	@NotNull(message = "Grupo deve ser informado")
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name="codigo_pessoa"),
			inverseJoinColumns = @JoinColumn(name = "codigo_grupo"))
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	
	@ManyToOne
	@JoinColumn(name="codigo_delegacia", referencedColumnName="codigo")
	public Delegacia getDelegacia() {
		return delegacia;
	}
	public void setDelegacia(Delegacia delegacia) {
		this.delegacia = delegacia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

}