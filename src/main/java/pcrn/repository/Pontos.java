package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.IPonto;
import pcrn.model.Pessoa;
import pcrn.model.Ponto;
import pcrn.repository.filter.PontoFiltro;

public class Pontos implements Serializable, IPonto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	@Override
	public Ponto retornaPonto(Pessoa pessoa) {
		Ponto ponto = new Ponto();
		Query query = manager.createQuery("from Ponto where codigo_pessoa = :codigo_pessoa and ponto_entrada = TRUE and ponto_saida = FALSE");
		query.setParameter("codigo_pessoa", pessoa.getCodigo());
		ponto = (Ponto) query.getSingleResult();
		return ponto;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ponto> listar(Pessoa pessoa) {
		List<Ponto> listaPonto = new ArrayList<Ponto>();
		Query query = manager.createQuery("from Ponto where codigo_pessoa = :codigo_pessoa and ponto_entrada = TRUE and ponto_saida = FALSE");
		query.setParameter("codigo_pessoa", pessoa.getCodigo());
		listaPonto = query.getResultList();
		return listaPonto;
	}

	@SuppressWarnings("unchecked")
	public List<Ponto> listarPontosEntreDatas(PontoFiltro filtro) {
		List<Ponto> listaPonto = new ArrayList<Ponto>();
		Query query = manager.createQuery("from Ponto where codigo_data BETWEEN :dataCriacaoDe and  :dataCriacaoAte order by codigo_data asc");
		query.setParameter("dataCriacaoDe", filtro.getDataCriacaoDe());
		query.setParameter("dataCriacaoAte", filtro.getDataCriacaoAte());
		listaPonto = query.getResultList();
		return listaPonto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ponto> listarPontoDataEspecifica(PontoFiltro filtro) {
		List<Ponto> listaPonto = new ArrayList<Ponto>();
		Query query = manager.createQuery("from Ponto where codigo_data = :dataCriacaoDe");
		query.setParameter("dataCriacaoDe", filtro.getDataCriacaoDe());
		listaPonto = query.getResultList();
		return listaPonto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ponto> listarPontosEntreDatasUsuario(PontoFiltro filtro) {
		List<Ponto> listaPonto = new ArrayList<Ponto>();
		Query query = manager.createQuery("from Ponto where codigo_pessoa = :codigo_pessoa and codigo_data BETWEEN :dataCriacaoDe and  :dataCriacaoAte order by codigo_data asc");
		query.setParameter("codigo_pessoa", filtro.getPessoa().getCodigo());
		query.setParameter("dataCriacaoDe", filtro.getDataCriacaoDe());
		query.setParameter("dataCriacaoAte", filtro.getDataCriacaoAte());
		listaPonto = query.getResultList();
		return listaPonto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ponto> listarPontoDataEspecificaUsuario(PontoFiltro filtro) {
		List<Ponto> listaPonto = new ArrayList<Ponto>();
		Query query = manager.createQuery("from Ponto where codigo_pessoa = :codigo_pessoa and codigo_data = :dataCriacaoDe");
		query.setParameter("codigo_pessoa", filtro.getPessoa().getCodigo());
		query.setParameter("dataCriacaoDe", filtro.getDataCriacaoDe());
		listaPonto = query.getResultList();
		return listaPonto;
	}	
	
	@Override
	public Ponto porCodigo(int codigo) {
		return manager.find(Ponto.class, codigo);
	}

	@Override
	public void salvar(Ponto ponto) {
		manager.merge(ponto);		
	}

	@Override
	public void remover(Ponto ponto) {
		// TODO Auto-generated method stub
		
	}

}
