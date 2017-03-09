package pcrn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Pessoa;
import pcrn.model.Ponto;
import pcrn.repository.Pontos;
import pcrn.repository.filter.PontoFiltro;
import pcrn.util.jpa.Transactional;

public class PontoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pontos pontos;

	@Transactional
	public void salvar(Ponto ponto) {
		pontos.salvar(ponto);
	}
	
	@Transactional
	public List<Ponto> listar(Pessoa pessoa, int codigo_data){
		return pontos.listar(pessoa, codigo_data);
	}

	@Transactional
	public List<Ponto> listarPontoAberto(Pessoa pessoa){
		return pontos.listarPontoAberto(pessoa);
	}
	
	
	@Transactional
	public List<Ponto> listarPontosEntreDatas(PontoFiltro filtro){
		return pontos.listarPontosEntreDatas(filtro);
	}
	
	@Transactional
	public List<Ponto> listarPontoDataEspecifica(PontoFiltro filtro){
		return pontos.listarPontoDataEspecifica(filtro);
	}
	
	@Transactional
	public List<Ponto> listarPontosEntreDatasUsuario(PontoFiltro filtro){
		return pontos.listarPontosEntreDatasUsuario(filtro);
	}
	
	@Transactional
	public List<Ponto> listarPontoDataEspecificaUsuario(PontoFiltro filtro){
		return pontos.listarPontoDataEspecificaUsuario(filtro);
	}
	
	@Transactional
	public Ponto retornarPonto(Pessoa pessoa){
		return pontos.retornaPonto(pessoa);
	}
}
