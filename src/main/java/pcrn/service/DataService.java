package pcrn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Data;
import pcrn.repository.Datas;
import pcrn.util.jpa.Transactional;

public class DataService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Datas datas;

	@Transactional
	public List<Data> listar() {
		return datas.listar();
	}
	
	@Transactional
	public int listarData(String data) {
		return datas.listarData(data);
	}
	
	@Transactional
	public Data porCodigo(Integer codigo) {
		return datas.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Data data) {
		datas.salvar(data);
	}
	
	@Transactional
	public void remover(Data data) {
		datas.remover(data);
	}
	
}