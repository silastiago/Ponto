package pcrn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import pcrn.interfaces.IDelegacia;
import pcrn.model.Delegacia;

public class Delegacias implements Serializable, IDelegacia{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Delegacia> listar() {
		List<Delegacia> listaDelegacia = null;
		Query query = manager.createQuery("Select d from Delegacia d");
		listaDelegacia = query.getResultList();
		return listaDelegacia;
	}

	public Delegacia porCodigo(int codigo) {	
		return manager.find(Delegacia.class, codigo);
	}

	public void salvar(Delegacia delegacia) {
		manager.merge(delegacia);		
	}

	public void remover(Delegacia delegacia) {
		//manager.remove(delegacia);
		manager.remove(manager.getReference(Delegacia.class, delegacia.getCodigo()));
	}

}
