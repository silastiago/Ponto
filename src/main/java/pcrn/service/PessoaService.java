package pcrn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.util.jpa.Transactional;
import pcrn.model.Pessoa;
import pcrn.repository.Pessoas;

public class PessoaService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoas pessoas;

	@Transactional
	public boolean login(Pessoa pessoa) {
		return pessoas.login(pessoa);
	}
	
	@Transactional
	public void logout() {
		pessoas.logout();
	}
	
	@Transactional
	public List<Pessoa> listar() {
		return pessoas.listar();
	}
	
	@Transactional
	public Pessoa porCodigo(Integer codigo) {
		return pessoas.porCodigo(codigo);
	}

	@Transactional
	public void salvar(Pessoa pessoa) {
		pessoas.salvar(pessoa);
	}
	
	
	@Transactional
	public void remover(Pessoa pessoa) {
		pessoas.remover(pessoa);
	}
}