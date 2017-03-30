package pcrn.util.report;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import pcrn.conexao.ConnectionFactory;
import pcrn.model.Data;
import pcrn.repository.Datas;
import pcrn.util.FacesUtil;

public class GerarRelatorio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	public String gerar() throws JRException{
		Datas dataService = new Datas();
		Data data = new Data();
		
		String dataAtual = "";
		dataAtual = FacesUtil.retornaDataAtual();
		System.out.println("Data Atual: " + dataAtual);
		
		data = dataService.listarDataConcetion(FacesUtil.retornaDataAtual());
		
		ConnectionFactory conexao = new ConnectionFactory();
		Connection conn = conexao.getConnection();
		//String caminhoRelatorio = "C:/Users/Sinf02/git/Ponto/src/main/resources/relatorios/";
		String caminhoRelatorio = "/opt/tomcat/webapps/Ponto/WEB-INF/classes/relatorios/Servidor/";
		
		
		System.out.println("Codigo data: "+  data.getCodigo());
		
		
		JasperReport report = JasperCompileManager
				.compileReport(caminhoRelatorio+"Ponto.jrxml");
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo_data", data.getCodigo());
		
		JasperPrint print = JasperFillManager.fillReport(report,
				parametros, conn);
		
		JasperExportManager.exportReportToPdfFile(print, caminhoRelatorio+"Ponto.pdf");
		
		System.out.println("Relatório gerado.");
		
		return caminhoRelatorio+"Ponto.pdf";
		
	}	
}
