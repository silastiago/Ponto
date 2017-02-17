package pcrn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import pcrn.model.Ponto;

public class FechaPontosAbertos implements Job {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		System.out.println("Verificando pontos abertos");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ipsPU");
	    EntityManager manager = factory.createEntityManager();
	    List<Ponto> listaPontosAbertos = new ArrayList<Ponto>();
		Query query = manager.createQuery("from Ponto where ponto_saida = FALSE");
		listaPontosAbertos = query.getResultList();
		
		for (int i = 0; i < listaPontosAbertos.size(); i++) {
			Ponto ponto = new Ponto();
			ponto = listaPontosAbertos.get(i);
			System.out.println("Codigo: "+ ponto.getCodigo());
			ponto.setSituacaoPonto("Registro Fechado pelo Sistema");
			ponto.setPontoSaida(true);
			manager.getTransaction().begin();
			manager.merge(ponto);
			manager.getTransaction().commit();
		}		
		
			
		}

	}
