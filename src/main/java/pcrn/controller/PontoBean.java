package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import pcrn.model.Data;
import pcrn.model.Pessoa;
import pcrn.model.Ponto;
import pcrn.repository.filter.PontoFiltro;
import pcrn.security.UsuarioSistema;
import pcrn.service.DataService;
import pcrn.service.PontoService;
import pcrn.util.FacesUtil;


@Named
@ViewScoped
public class PontoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numeroAleatorio;
	private int numeroCadastro = 0;
	private int numeroPergunta = 3;
	private String data;
	private String dataCriacaoDe;
	private String dataCriacaoAte;
	private String criterio;
	private Ponto ponto = new Ponto();
	private Pessoa pessoa;
	private Pessoa pessoaLogada = new Pessoa();
	private List<Ponto> listaPontos = new ArrayList<Ponto>();


	@Inject
	private ExternalContext externalContext;

	@Inject
	private HttpServletRequest request;

	@Inject
	private PontoService pontoService;

	@Inject
	private DataService dataService;
	private PontoFiltro filtro;
	private List<Ponto> pontosFiltrados;

	public PontoBean() {
		filtro = new PontoFiltro();
		pontosFiltrados = new ArrayList<Ponto>();
		pessoa = new Pessoa();
	}

	@PostConstruct
	public void init(){
		data = FacesUtil.retornaDataAtual();
		pessoaLogada = this.getUsuarioLogado().getPessoa();
		numeroAleatorio = Aleatorio();
	}

	public void cadastrarEntrada(){

		String dt = FacesUtil.retornaDataAtual();
		System.out.println(dt);
		int id_data = 0;

		Data data = new Data();
		id_data = dataService.listarData(dt);
		if (id_data == 0) {
			data.setData(dt);
			dataService.salvar(data);
			id_data = dataService.listarData(dt);
		}

		data = dataService.porCodigo(id_data);

		String nome = "";


		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getPessoa().getLogin();
		}


		//Esta linha salva a entidade ponto.
		ponto.setHoraEntrada(FacesUtil.retornaHoraAtual());
		ponto.setData(data);
		ponto.setPessoa(pessoaLogada);
		ponto.setPontoEntrada(true);
		ponto.setPontoSaida(false);
		pontoService.salvar(ponto);

		numeroCadastro = 0;
		numeroPergunta = 3;

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../../../Home.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrarSaida(){

		ponto = pontoService.retornarPonto(pessoaLogada);
		String horaAtual = FacesUtil.retornaHoraAtual();
		System.out.println("Hora Atual: " + horaAtual);
		String horasTrabalhadas = FacesUtil.horasTrabalhadas(ponto.getHoraEntrada(), horaAtual);
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getPessoa().getLogin();
		}

		//Esta linha salva a entidade ponto.
		ponto.setHoraSaida(horaAtual);
		ponto.setPontoSaida(true);
		ponto.setSituacaoPonto("Registro Fechado pelo Usuario");
		ponto.setHorasTrabalhadas(horasTrabalhadas);
		pontoService.salvar(ponto);


		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../../../Home.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}

		return usuario;
	}

	public boolean isRenderizarBotaoCadastrarEntrada(){
		listaPontos = listar();
		if (listaPontos.size() > 0) {
			return false;
		}

		return true;
	}



	public List<Ponto> listar(){
		listaPontos = pontoService.listar(pessoaLogada);

		return listaPontos;
	}

	public void consulta(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Consulta/Pontos.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pesquisar() {
		int id_data_de = dataService.listarData(dataCriacaoDe);
		int id_data_ate = dataService.listarData(dataCriacaoAte);

		filtro.setDataCriacaoDe(id_data_de);
		filtro.setDataCriacaoAte(id_data_ate);

		if (filtro.getPessoa() == null) {
			
			if (criterio.equals("data")) {
				pontosFiltrados = pontoService.listarPontoDataEspecifica(filtro);
			}else if (criterio.equals("datas")) {
				pontosFiltrados = pontoService.listarPontosEntreDatas(filtro);
			}
			
		}else{
			
			if (criterio.equals("data")) {
				pontosFiltrados = pontoService.listarPontoDataEspecificaUsuario(filtro);
			}else if (criterio.equals("datas")) {
				pontosFiltrados = pontoService.listarPontosEntreDatasUsuario(filtro);
			}
		}
	}


	public void compararDoisUltimosDigitosCPF(){

		String doisUltimosDigitosCPF = "";
		String doisUltimosCPFDigitadosPeloUsuario = "";

		doisUltimosCPFDigitadosPeloUsuario  = this.pessoa.getCpf();
		doisUltimosDigitosCPF = FacesUtil.pegarDoisUltimosDigitosCPF(pessoaLogada.getCpf());

		if (doisUltimosDigitosCPF.equals(doisUltimosCPFDigitadosPeloUsuario)) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));
		}
	}


	public void compararTresPrimeirosDigitosCPF(){

		String tresPrimeirosDigitosCPF = "";
		String tresPrimeirosCPFDigitadosPeloUsuario = "";

		tresPrimeirosCPFDigitadosPeloUsuario  = this.pessoa.getCpf();
		tresPrimeirosDigitosCPF = FacesUtil.pegarTresPrimeirosDigitosCPF(pessoaLogada.getCpf());

		if (tresPrimeirosDigitosCPF.equals(tresPrimeirosCPFDigitadosPeloUsuario)) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta -1;
			numeroAleatorio = Aleatorio();
		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}
	}

	public void compararTresPrimeirosDigitosRG(){

		String tresPrimeirosDigitosRG = "";
		String tresPrimeirosRGDigitadosPeloUsuario = "";

		tresPrimeirosRGDigitadosPeloUsuario  = this.pessoa.getRg();
		tresPrimeirosDigitosRG = FacesUtil.pegarTresPrimeirosDigitosRG(pessoaLogada.getRg());		

		if (tresPrimeirosDigitosRG.equals(tresPrimeirosRGDigitadosPeloUsuario)) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}
	}

	public void compararTresUltimosDigitosRG(){

		String tresUltimosDigitosRG = "";
		String tresUltimosRGDigitadosPeloUsuario = "";

		tresUltimosRGDigitadosPeloUsuario  = this.pessoa.getRg();
		tresUltimosDigitosRG = FacesUtil.pegarTresUltimosDigitosRG(pessoaLogada.getRg());

		if (tresUltimosDigitosRG.equals(tresUltimosRGDigitadosPeloUsuario)) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}
	}	

	public void pegarPrimeiroNome(){

		String primeiroNome = "";
		String primeiroNomeDigitadosPeloUsuario = "";

		primeiroNomeDigitadosPeloUsuario  = this.pessoa.getNome();
		primeiroNome = pessoaLogada.getNome();

		if (primeiroNome.toUpperCase().equals(primeiroNomeDigitadosPeloUsuario.toUpperCase())) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public void pegarPrimeiroNomePai(){

		String primeiroNomePai = "";
		String primeiroNomePaiDigitadosPeloUsuario = "";

		primeiroNomePaiDigitadosPeloUsuario  = this.pessoa.getNomePai();
		primeiroNomePai = pessoaLogada.getNomePai();

		if (primeiroNomePai.toUpperCase().equals(primeiroNomePaiDigitadosPeloUsuario.toUpperCase())) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public void pegarPrimeiroNomeMae(){

		String primeiroNomeMae = "";
		String primeiroNomeMaeDigitadosPeloUsuario = "";

		primeiroNomeMaeDigitadosPeloUsuario  = this.pessoa.getNomeMae();
		primeiroNomeMae = pessoaLogada.getNomeMae();

		if (primeiroNomeMae.toUpperCase().equals(primeiroNomeMaeDigitadosPeloUsuario.toUpperCase())) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public void pegarSobreNome(){

		String sobreNome = "";
		String sobreNomeDigitadosPeloUsuario = "";

		sobreNomeDigitadosPeloUsuario  = this.pessoa.getSobreNome();
		sobreNome = pessoaLogada.getSobreNome();

		if (sobreNome.toUpperCase().equals(sobreNomeDigitadosPeloUsuario.toUpperCase())) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public void pegarSobreNomeMae(){

		String sobreNomeMae = "";
		String sobreNomeMaeDigitadosPeloUsuario = "";

		sobreNomeMaeDigitadosPeloUsuario  = this.pessoa.getSobreNomeMae();
		sobreNomeMae = pessoaLogada.getSobreNomeMae();

		if (sobreNomeMae.toUpperCase().equals(sobreNomeMaeDigitadosPeloUsuario.toUpperCase())) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta")); 
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public void pegarSobreNomePai(){

		String sobreNomePai = "";
		String sobreNomePaiDigitadosPeloUsuario = "";

		sobreNomePaiDigitadosPeloUsuario  = this.pessoa.getSobreNomePai();
		sobreNomePai = pessoaLogada.getSobreNomePai();

		if (sobreNomePai.toUpperCase().equals(sobreNomePaiDigitadosPeloUsuario.toUpperCase())) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta")); 
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public void pegarUltimoNome(){

		String ultimoNome = "";
		String ultimoNomeDigitadosPeloUsuario = "";

		ultimoNomeDigitadosPeloUsuario  = this.pessoa.getUltimoNome();
		ultimoNome = pessoaLogada.getUltimoNome();

		if (ultimoNome.toUpperCase().equals(ultimoNomeDigitadosPeloUsuario.toUpperCase())) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta")); 
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public void pegarUltimoNomeMae(){

		String ultimoNomeMae = "";
		String ultimoNomeMaeDigitadosPeloUsuario = "";

		ultimoNomeMaeDigitadosPeloUsuario  = this.pessoa.getUltimoNomeMae();
		ultimoNomeMae = pessoaLogada.getUltimoNomeMae();

		if (ultimoNomeMae.toUpperCase().equals(ultimoNomeMaeDigitadosPeloUsuario.toUpperCase())) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public void pegarUltimoNomePai(){

		String ultimoNomePai = "";
		String ultimoNomePaiDigitadosPeloUsuario = "";

		ultimoNomePaiDigitadosPeloUsuario  = this.pessoa.getUltimoNomePai();
		ultimoNomePai = pessoaLogada.getUltimoNomePai();

		if (ultimoNomePai.toUpperCase().equals(ultimoNomePaiDigitadosPeloUsuario.toUpperCase())) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta")); 
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public void pegarAnoNascimento(){

		String anoNascimento = "";
		String anoNascimentoDigitadosPeloUsuario = "";

		anoNascimentoDigitadosPeloUsuario  = FacesUtil.pegarAnoData(this.pessoa.getDataNascimento());
		anoNascimento = FacesUtil.pegarAnoData(pessoaLogada.getDataNascimento());

		if (anoNascimento.equals(anoNascimentoDigitadosPeloUsuario)) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public void pegarMesNascimento(){

		String mesNascimento = "";
		String mesNascimentoDigitadosPeloUsuario = "";

		mesNascimentoDigitadosPeloUsuario  = FacesUtil.pegarMesData(this.pessoa.getDataNascimento());
		mesNascimento = FacesUtil.pegarMesData(pessoaLogada.getDataNascimento());

		if (mesNascimento.equals(mesNascimentoDigitadosPeloUsuario)) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public void pegarDiaNascimento(){

		String diaNascimento = "";
		String diaNascimentoDigitadosPeloUsuario = "";

		diaNascimentoDigitadosPeloUsuario  = FacesUtil.pegarDiaData(this.pessoa.getDataNascimento());
		diaNascimento = FacesUtil.pegarDiaData(pessoaLogada.getDataNascimento());

		if (diaNascimento.equals(diaNascimentoDigitadosPeloUsuario)) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public void pegarMesInicioEstagio(){

		String mesInicioEstagio = "";
		String mesInicioEstagioDigitadosPeloUsuario = "";

		mesInicioEstagioDigitadosPeloUsuario  = FacesUtil.pegarMesData(this.pessoa.getDataInicioEstagio());
		mesInicioEstagio = FacesUtil.pegarMesData(pessoaLogada.getDataInicioEstagio());


		if (mesInicioEstagio.equals(mesInicioEstagioDigitadosPeloUsuario)) {

			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_INFO, "","Resposta Correta"));
			numeroCadastro = numeroCadastro +1;
			numeroPergunta = numeroPergunta-1;
			numeroAleatorio = Aleatorio();

		}else {
			FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Resposta Errada"));

		}

	}

	public int getNumeroPergunta() {
		return numeroPergunta;
	}

	public void setNumeroPergunta(int numeroPergunta) {
		this.numeroPergunta = numeroPergunta;
	}

	public int getNumeroCadastro() {
		return numeroCadastro;
	}

	public void setNumeroCadastro(int numeroCadastro) {
		this.numeroCadastro = numeroCadastro;
	}

	public int Aleatorio(){
		Random numeroAleatorio = new Random();
		return numeroAleatorio.nextInt(17);
	}

	public int getNumeroAleatorio() {
		return numeroAleatorio;
	}

	public void setNumeroAleatorio(int numeroAleatorio) {
		this.numeroAleatorio = numeroAleatorio;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String tipoCriterio(ValueChangeEvent evento){
		criterio = evento.getNewValue().toString();
		return criterio;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public String getDataCriacaoDe() {
		return dataCriacaoDe;
	}

	public void setDataCriacaoDe(String dataCriacaoDe) {
		this.dataCriacaoDe = dataCriacaoDe;
	}

	public String getDataCriacaoAte() {
		return dataCriacaoAte;
	}

	public void setDataCriacaoAte(String dataCriacaoAte) {
		this.dataCriacaoAte = dataCriacaoAte;
	}

	public PontoFiltro getFiltro() {
		return filtro;
	}

	public void setFiltro(PontoFiltro filtro) {
		this.filtro = filtro;
	}

	public List<Ponto> getPontosFiltrados() {
		return pontosFiltrados;
	}

	public void setPontosFiltrados(List<Ponto> pontosFiltrados) {
		this.pontosFiltrados = pontosFiltrados;
	}

	public List<Ponto> getListaPontos() {
		return listaPontos;
	}

	public void setListaPontos(List<Ponto> listaPontos) {
		this.listaPontos = listaPontos;
	}

	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}