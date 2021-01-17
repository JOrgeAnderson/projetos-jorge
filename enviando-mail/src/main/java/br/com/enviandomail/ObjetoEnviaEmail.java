package br.com.enviandomail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEnviaEmail {

	private String userName = "ja.techjavaweb@gmail.com";
	private String password = "T3chJ4v4w3b";

	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";

	public ObjetoEnviaEmail(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;

	}

	public void enviarEmail(boolean enviohtml) throws Exception {

		/* Olhe as configurações do SMTP do seu emial */

		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");// (autorização)
		properties.put("mail.smtp.starttls", "true");// segurança (autenticação)
		properties.put("mail.smtp.host", "smtp.gmail.com");// Gerenciador dmail Google
		properties.put("mail.smtp.port", "465");// porta do servidor
		properties.put("mail.smtp.socketFactory.port", "465");// Expecífica a porta a ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");/* Classe socket de conexão ao SMTP */

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});

		Address[] toUser = InternetAddress.parse(listaDestinatarios);

		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(userName, nomeRemetente));/* Quem está enviando */
		message.setRecipients(Message.RecipientType.TO, toUser);/* Email de destino */
		message.setSubject(assuntoEmail); /* Assunto do e-mail */

		
		if (enviohtml) {
			message.setContent(textoEmail, "text/html; charset=utf-8");

		} else {

			message.setText(textoEmail);
		}

		Transport.send(message);
	}

	
	public void enviarEmailAnexo(boolean enviohtml) throws Exception {

		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");// (autorização)
		properties.put("mail.smtp.starttls", "true");// segurança (autenticação)
		properties.put("mail.smtp.host", "smtp.gmail.com");// Gerenciador dmail Google
		properties.put("mail.smtp.port", "465");// porta do servidor
		properties.put("mail.smtp.socketFactory.port", "465");// Expecífica a porta a ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/* Classe socket de conexão ao SMTP */

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});

		Address[] toUser = InternetAddress.parse(listaDestinatarios);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetente));/* Quem está enviando */
		message.setRecipients(Message.RecipientType.TO, toUser);/* Email de destino */
		message.setSubject(assuntoEmail); /* Assunto do e-mail */
		
		/* Parte 1 do e-mail que é texto da descrição do e-mail */

		MimeBodyPart corpoEmail = new MimeBodyPart();

		if (enviohtml) {
			corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");

		} else {

			corpoEmail.setText(textoEmail);
		}
		
		List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
		arquivos.add(simuladorDePDF());/*Certificado*/
		arquivos.add(simuladorDePDF());/*Nota Fiscal*/
		arquivos.add(simuladorDePDF());/*documento texto*/
		arquivos.add(simuladorDePDF());/*Imagem*/
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoEmail);
		
		int index = 0;
		for (FileInputStream fileInputStream : arquivos) {

			/* Parte 2 do e-mail que são os anexos em PDF */
			MimeBodyPart anexoEmail = new MimeBodyPart();

			/*
			 * Onde é passado o simuladorDePDF você passa o seu arquivo gravado no banco de
			 * dados
			 */
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
			anexoEmail.setFileName("anexoemail"+ (index+1) +".pdf");
			
			multipart.addBodyPart(anexoEmail);
			
			index ++;
		}

		message.setContent(multipart);

		Transport.send(message);
	}

	/*
	 * 
	 * Esse método simula o PDF ou qualquer arquivo que possa ser enviado por anexo
	 * no email você pode pegar o arquivo no seu banco de dados base 64, byte[],
	 * Stream de arquivos. pode estar em um banco de dados, o em uma pasta.
	 * 
	 * Retorna um PDF em branco com o texto do paragafo de exemplo
	 */

	private FileInputStream simuladorDePDF() throws Exception {

		Document document = new Document();
		File file = new File("Fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com Java Mail, esse texto é do PDF"));
		document.close();

		return new FileInputStream(file);

	}
}
