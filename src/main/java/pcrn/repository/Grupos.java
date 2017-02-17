package pcrn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.IGrupo;
import pcrn.model.Grupo;

public class Grupos implements Serializable, IGrupo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Grupo> listar() {
		List<Grupo> listaGrupo = null;
		Query query = manager.createQuery("Select c from Grupo c");
		listaGrupo = query.getResultList();
		return listaGrupo;
	}

	public Grupo porCodigo(Integer codigo) {
		Grupo grupo = manager.find(Grupo.class, codigo);
		return grupo;
	}

	public void salvar(Grupo grupo) {
		manager.merge(grupo);
	}

	public void remover(Grupo grupo) {
		manager.remove(manager.getReference(Grupo.class, grupo.getCodigo()));
	}
}
