package pcrn.repository.filter;

import java.io.Serializable;

import pcrn.model.Pessoa;

public class PontoFiltro implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	private int dataCriacaoDe;
	private int dataCriacaoAte;
	private Pessoa pessoa;
	
	public int getDataCriacaoDe() {
		return dataCriacaoDe;
	}
	public void setDataCriacaoDe(int dataCriacaoDe) {
		this.dataCriacaoDe = dataCriacaoDe;
	}
	public int getDataCriacaoAte() {
		return dataCriacaoAte;
	}
	public void setDataCriacaoAte(int dataCriacaoAte) {
		this.dataCriacaoAte = dataCriacaoAte;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}