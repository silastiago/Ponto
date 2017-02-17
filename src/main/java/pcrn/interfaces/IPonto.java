package pcrn.interfaces;

import java.util.List;

import pcrn.model.Pessoa;
import pcrn.model.Ponto;
import pcrn.repository.filter.PontoFiltro;

public interface IPonto {
	
	public Ponto retornaPonto(Pessoa pessoa);
	public List<Ponto> listar(Pessoa pessoa);
	public List<Ponto> listarPontosEntreDatas(PontoFiltro filtro);
	public List<Ponto> listarPontoDataEspecifica(PontoFiltro filtro);
	public List<Ponto> listarPontosEntreDatasUsuario(PontoFiltro filtro);
	public List<Ponto> listarPontoDataEspecificaUsuario(PontoFiltro filtro);
	public Ponto porCodigo(int codigo);
	public void salvar(Ponto ponto);
	public void remover(Ponto ponto);
	
}
