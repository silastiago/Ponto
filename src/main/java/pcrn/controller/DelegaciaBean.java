package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Delegacia;
import pcrn.service.DelegaciaService;


@Named
@ViewScoped
public class DelegaciaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DelegaciaService delegaciaService;
	private Delegacia delegacia = new Delegacia();
	private Delegacia delegaciaSelecionada;
	private List<Delegacia> listaDelegacia = new ArrayList<Delegacia>();
	
	public void cadastrar(){
		//Esta linha salva a entidade grupo.
		delegaciaService.salvar(delegacia);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			fc.getExternalContext().redirect("../Consulta/Delegacia.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editar(){
		//Esta linha salva a entidade grupo.
		delegaciaService.salvar(delegacia);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			fc.getExternalContext().redirect("../Consulta/Delegacia.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir(){
		//Esta linha salva a entidade grupo.
		delegaciaService.remover(delegaciaSelecionada);		
		delegaciaSelecionada = null;
		listar();
	}
	
	public List<Delegacia> listar(){
		//Esta linha lista os grupos e joga em uma lista de grupos.
		listaDelegacia = delegaciaService.listar();
		//Retorna a lista de grupos
		return listaDelegacia;
	}	
	
	public void novo(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Novo/DelegaciaNovo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edicao(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Edicao/DelegaciaEdicao.xhtml?codigo="+delegaciaSelecionada.getCodigo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Delegacia getDelegaciaSelecionada() {
		return delegaciaSelecionada;
	}

	public void setDelegaciaSelecionada(Delegacia delegaciaSelecionada) {
		this.delegaciaSelecionada = delegaciaSelecionada;
	}

	public Delegacia getDelegacia() {
		return delegacia;
	}

	public void setDelegacia(Delegacia delegacia) {
		this.delegacia = delegacia;
	}

	public List<Delegacia> getListaDelegacia() {
		return listaDelegacia;
	}

	public void setListaDelegacia(List<Delegacia> listaDelegacia) {
		this.listaDelegacia = listaDelegacia;
	}
}