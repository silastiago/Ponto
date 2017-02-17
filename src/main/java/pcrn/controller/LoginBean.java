package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pcrn.util.FacesUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private HttpServletResponse response;
	
	private String login;

	private static int Aleatorio(int numero){
		Random numeroAleatorio = new Random();
		return numeroAleatorio.nextInt(numero);
	}
	
	public void logar() throws ServletException, IOException {
		ArrayList<String> listaPaginas = new ArrayList<String>();
		listaPaginas.add("pegarDoisUltimosDigitosCPF.xhtml");
		listaPaginas.add("pegarTresPrimeirosDigitosCPF.xhtml");
		listaPaginas.add("pegarTresUltimosDigitosRG.xhtml");
		listaPaginas.add("pegarTresUltimosDigitosRG.xhtml");
		listaPaginas.add("pegarPrimeiroNomePai.xhtml");
	
		String pagina = listaPaginas.get(Aleatorio(5));
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		
		facesContext.responseComplete();
	}

	public void sair() throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_logout");
		dispatcher.forward(request, response);
		
		facesContext.responseComplete();
	}
	
	public void preRender(){
		if ("true".equals(request.getParameter("invalid"))) {
			FacesUtil.addErrorMessage("Usuario ou senha Invalido!!");
		}
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}