package pcrn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.util.jpa.Transactional;
import pcrn.model.Grupo;
import pcrn.repository.Grupos;

public class GrupoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Grupos grupos;
	
	@Transactional
	public void salvar(Grupo grupo){
		grupos.salvar(grupo);
	}

	@Transactional
	public Grupo porCodigo(Integer codigo) {
		return grupos.porCodigo(codigo);
	}
	
	@Transactional
	public void remover(Grupo grupo) {
		grupos.remover(grupo);
	}
	
	@Transactional
	public List<Grupo> listar() {
		return grupos.listar();
	}
	
	
}
