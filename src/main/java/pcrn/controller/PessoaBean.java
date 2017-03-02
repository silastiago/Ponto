package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import pcrn.model.Grupo;
import pcrn.model.Pessoa;
import pcrn.repository.Grupos;
import pcrn.security.UsuarioSistema;
import pcrn.service.PessoaService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class PessoaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ExternalContext externalContext;

	@Inject
	private PessoaService pessoaService;

	private Pessoa pessoa = new Pessoa();
	private Pessoa pessoaSelecionada;
	private Pessoa pessoaLogada = new Pessoa();
	private List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	private List<Grupo> listaGrupos;

	@Inject
	private Grupos grupos;

	@PostConstruct
	public void inicializar(){
		listaGrupos = grupos.listar();
		pessoaLogada = this.getUsuarioLogado().getPessoa();
	}


	public void cadastrar() {

		String senha = this.pessoa.getSenha();
		this.pessoa.setSenha(FacesUtil.md5(senha));
		pessoaService.salvar(pessoa);

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Consulta/Pessoa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editar() {

		pessoaService.salvar(pessoa);

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Consulta/Pessoa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void alterarPropriaSenha() {

		String senha = this.pessoa.getSenha();
		pessoa = this.getUsuarioLogado().getPessoa();
		this.pessoa.setSenha(FacesUtil.md5(senha));
		pessoaService.salvar(pessoa);

		FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Senha alterada com sucesso"));
	}

	public void alterarSenha() {

		String senha = this.pessoa.getSenha();
		this.pessoa.setSenha(FacesUtil.md5(senha));
		pessoaService.salvar(pessoa);
		
		FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Senha alterada com sucesso"));
	}
	
	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	}	

	public void excluir(){
		pessoaService.remover(pessoaSelecionada);
		pessoaSelecionada = null;
		listar();
	}

	public List<Pessoa> listar(){
		//Esta linha lista os tipos e joga em uma lista de tipos.
		listaPessoas = pessoaService.listar();
		//retorna a lista de tipos.
		return listaPessoas;
	}


	public void novo(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Novo/PessoaNovo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public void alteracaoSenha(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Edicao/PessoaSenha.xhtml?codigo="+pessoaSelecionada.getCodigo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edicao(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Edicao/PessoaEdicao.xhtml?codigo="+pessoaSelecionada.getCodigo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}



	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	public Pessoa getPessoaLogada() {
		return pessoaLogada;
	}



	public void setPessoaLogada(Pessoa pessoaLogada) {
		this.pessoaLogada = pessoaLogada;
	}



	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) throws CloneNotSupportedException {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
}