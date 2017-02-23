package pcrn.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;



/** Esta � uma Classe utilitaria que implementa alguns metodos que servirao para ser reutilizados em algumas classes.
 *   
 * @author silas
 * @since 17-08-2016
 */

public class FacesUtil {

	/** Este metodo captura o atributo da requisicao pelo nome .
	 * 	
	 * 	@param name, Este name e o nome do atributo que voce vai pegar.
	 * 	@return retorna o objeto referente aquele name.
	 */
	public static Object getRequestAttribute(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return request.getAttribute(name);
	}

	public static String md5(String senha) {
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.update(senha.getBytes(),0, senha.length());
		String hash = new BigInteger(1,m.digest()).toString(16);
		return hash;
	}

	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}


	public static String horasTrabalhadas(String horarioEntrada, String horarioSaida) {
		String horasTrabalhadas = "";

		final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat( "HH':'mm" ) ;  

		Calendar entrada = new GregorianCalendar( ) ;  
		try {
			entrada.setTime( DATE_FORMAT.parse( horarioEntrada ) ) ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		Calendar saida = new GregorianCalendar( ) ;  
		try {

			saida.setTime( DATE_FORMAT.parse( horarioSaida ) ) ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		int horas = saida.get( Calendar.HOUR_OF_DAY ) - entrada.get( Calendar.HOUR_OF_DAY ) ;  
		int minutos = saida.get( Calendar.MINUTE ) - entrada.get( Calendar.MINUTE ) ;  

		if ( horas < 0 ) {  
			horas += 24 ;  
		}  

		if (minutos < 0 ) {
			minutos += 60;
			horas -= 1;
		}

		horasTrabalhadas = String.format( "%02d:%02d", horas, minutos ) ;

		return horasTrabalhadas;
	}

	public static String retornaDataAtual(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String retornaHoraAtual(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String pegarDoisUltimosDigitosCPF(String cpf){
		String[] cpfTemp = cpf.split("-");
		String doisUltimosDigitosCPF = cpfTemp[1];
		return doisUltimosDigitosCPF;
	}

	public static String pegarTresPrimeirosDigitosCPF(String cpf){
		String[] cpfTemp = cpf.split("\\.");
		String doisUltimosDigitosCPF = cpfTemp[0];

		return doisUltimosDigitosCPF;
	}

	public static String pegarTresUltimosDigitosRG(String rg){
		String[] rgTemp = rg.split("\\.");
		String tresUltimosDigitosRG = rgTemp[2];
		return tresUltimosDigitosRG;
	}

	public static String pegarTresPrimeirosDigitosRG(String rg){
		String[] rgTemp = rg.split("\\.");
		String tresUltimosDigitosRG = rgTemp[0];
		return tresUltimosDigitosRG;
	}

	public static String pegarAnoData(Date data){
		SimpleDateFormat formatoData = new SimpleDateFormat("yyyy");
		String dataFormatada = formatoData.format(data);
		return dataFormatada;
	}

	public static String pegarMesData(Date data){
		SimpleDateFormat formatoData = new SimpleDateFormat("MM");
		String dataFormatada = formatoData.format(data);
		return dataFormatada;
	}

	public static String pegarDiaData(Date data){
		SimpleDateFormat formatoData = new SimpleDateFormat("dd");
		String dataFormatada = formatoData.format(data);
		return dataFormatada;
	}

}