package pcrn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.util.jpa.Transactional;
import pcrn.model.Delegacia;
import pcrn.repository.Delegacias;

public class DelegaciaService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Delegacias delegacias;

	@Transactional
	public List<Delegacia> listar() {
		return delegacias.listar();
	}
	
	@Transactional
	public Delegacia porCodigo(Integer codigo) {
		return delegacias.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Delegacia delegacia) {
		delegacias.salvar(delegacia);
	}
	
	@Transactional
	public void remover(Delegacia delegacia) {
		delegacias.remover(delegacia);
	}
	
}