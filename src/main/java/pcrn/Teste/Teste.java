package pcrn.Teste;

import net.sf.jasperreports.engine.JRException;
import pcrn.util.mail.EmailConfig;
import pcrn.util.report.GerarRelatorio;

public class Teste {

	public static void main(String[] args) {
		
		GerarRelatorio relatorio = new GerarRelatorio();
		String caminhoRelatorio = "";
		
		
		try {
			caminhoRelatorio = relatorio.gerar();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Caminho do relatorio: "+ caminhoRelatorio);
		
		
		EmailConfig email = new EmailConfig();
		email.enviarEmail(caminhoRelatorio);

	}

}
