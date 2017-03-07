package pcrn.util.mail;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.outjected.email.api.ContentDisposition;
import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;
import com.outjected.email.impl.SimpleMailConfig;

import pcrn.util.FacesUtil;

public class EmailConfig {

	public SessionConfig mailConfig() {
		Properties props = new Properties();
		try {
			props.load(getClass().getResourceAsStream("/mail.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleMailConfig configuracaoEmailServidorSmtp = new SimpleMailConfig();
		configuracaoEmailServidorSmtp.setServerHost(props.getProperty("mail.server.host"));
		configuracaoEmailServidorSmtp.setServerPort(Integer.parseInt(props.getProperty("mail.server.port")));
		configuracaoEmailServidorSmtp.setEnableSsl(Boolean.parseBoolean(props.getProperty("mail.enable.ssl")));
		//configuracaoEmailServidorSmtp.setEnableTls(Boolean.parseBoolean(props.getProperty("mail.enable.tls")));
		configuracaoEmailServidorSmtp.setAuth(Boolean.parseBoolean(props.getProperty("mail.auth")));
		configuracaoEmailServidorSmtp.setUsername(props.getProperty("mail.username"));
		configuracaoEmailServidorSmtp.setPassword(props.getProperty("mail.password"));
		return configuracaoEmailServidorSmtp;
	}

	public MailMessage novaMensagem() {
		return new MailMessageImpl(mailConfig());
	}

	public void enviarEmail(String caminhoRelatorio) {
		MailMessage message = novaMensagem();
		System.out.println("Enviando o Email");
		
		File file = new File(caminhoRelatorio);
		String dataAtual = FacesUtil.retornaDataAtual();
		
		message.to("estagiospcrn@gmail.com")
				.subject("Relatorio dos Estagiários")
				.bodyHtml("<strong> Segue em anexo o relatorio de Ponto dos estagiários do Dia: "+ dataAtual+"</strong>")
				.addAttachment(ContentDisposition.ATTACHMENT, file)
				.send();
		
		System.out.println("Email Enviado");

	}

}
