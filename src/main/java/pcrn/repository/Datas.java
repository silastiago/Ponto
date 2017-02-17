package pcrn.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import pcrn.conexao.ConnectionFactory;
import pcrn.interfaces.IData;
import pcrn.model.Data;

public class Datas implements Serializable, IData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Data> listar() {
		List<Data> listaData = null;
		Query query = manager.createQuery("Select c from Data c");
		listaData = query.getResultList();
		return listaData;
	}

	public int listarData(String data) {
		int id_data = 0;
		try{
			Query query = manager.createQuery("Select d.codigo from Data d where d.data = :data ");
			query.setParameter("data", data);
			id_data = (int) query.getSingleResult();

			return id_data;
		}catch (NoResultException nre){
			return 0;
		}
}
	
	@Override
	public Data listarDataConcetion(String data) {
		ConnectionFactory conexao = new ConnectionFactory();
		Connection conn = conexao.getConnection();
		
		Data dataAtual = new Data();
		String sql = "select codigo, data from data where data = '"+ data + "'";
		
		try{
			
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			
			int codigo = Integer.parseInt(rs.getString("codigo"));
			dataAtual.setCodigo(codigo);
			dataAtual.setData(rs.getString("data"));
			
		}
		
		rs.close();
		ps.close();
		conn.close();
		
		}catch (SQLException e) {
			// TODO: handle exception
		}
		
		return dataAtual;
	}
	
	

	public Data porCodigo(int codigo) {
		return manager.find(Data.class, codigo);
	}

	public void salvar(Data data) {
		manager.merge(data);
	}

	public void remover(Data data) {
		//manager.remove(delegacia);
		manager.remove(manager.getReference(Data.class, data.getCodigo()));
	}
}