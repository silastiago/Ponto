package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Grupo;
import pcrn.service.GrupoService;


/** Esta � uma Classe concreta que une as implementacoes das interfaces e das paginas xhtml referentes a entidade Grupo.
 *   
 * @author silas
 * @since 18-08-2016
 */

@Named
@ViewScoped
public class GrupoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoService grupoService;
	
	private Grupo grupo = new Grupo();
	private Grupo grupoSelecionado;
	private List<Grupo> listaGrupos = new ArrayList<Grupo>();

	/** Este metodo cadastra um Grupo.
	 */
	public void cadastrar(){
		//Esta linha salva a entidade grupo.
		grupoService.salvar(grupo);

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Consulta/Grupo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editar(){
		//Esta linha salva a entidade grupo.
		grupoService.salvar(grupo);

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Consulta/Grupo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/** Este metodo Remove um grupo.
	 *  @param grupo, Este grupo � o objeto Grupo que voc� ir� remover.
	 */
	public void excluir(){
		//Esta linha remove o grupo.
		grupoService.remover(grupoSelecionado);
		//Atualizar a lista de grupos
		
		grupoSelecionado = null;
		listar();
	}

	/** Este metodo lista todos os grupos cadastrados.
	 * 	@return retorna a lista de todos os grupos cadastradas no sistema.
	 */
	public List<Grupo> listar(){
		//Esta linha lista os grupos e joga em uma lista de grupos.
		listaGrupos = grupoService.listar();
		//Retorna a lista de grupos
		return listaGrupos;
	}	

	public void novo(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Novo/GrupoNovo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void edicao(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Edicao/GrupoEdicao.xhtml?codigo="+grupoSelecionado.getCodigo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}
}