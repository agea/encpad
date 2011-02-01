package com.github.encpad;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.ByteArrayDataSource;

@SuppressWarnings("serial")
public class EncpadServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String action = req.getParameter("action");
		try {
			if (action.equals("save")) {
				this.save(req, resp);
			}
			if (action.equals("email")) {
				this.email(req, resp);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void email(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		Message msg = new MimeMessage(session);
		MimeMultipart mp = new MimeMultipart();

		MimeBodyPart txtPart = new MimeBodyPart();
		txtPart.setContent(
				"You can find your encrypted notes attached to this email.",
				"text/plain");
		mp.addBodyPart(txtPart);

		msg.setSubject("Your encrypted notes");
		msg.setFrom(new InternetAddress("noreply@encpad.appspotmail.com",
				"Encrypted Notepad"));
		msg.addRecipient(Message.RecipientType.TO,
				new InternetAddress(req.getParameter("save_email")));

		MimeBodyPart attachmentPart = new MimeBodyPart();
		attachmentPart.setFileName("encpad.html");
		attachmentPart.setDisposition(Part.ATTACHMENT);
		DataSource src = new ByteArrayDataSource(this.prepareHtml(req)
				.getBytes(), "text/html");
		DataHandler handler = new DataHandler(src);
		attachmentPart.setDataHandler(handler);
		mp.addBodyPart(attachmentPart);

		msg.setContent(mp);
		Transport.send(msg);

		resp.sendRedirect("/");

	}

	private String prepareHtml(HttpServletRequest req) throws IOException {
		String cypher = req.getParameter("save_cypher");
		InputStream is = this.getClass().getResourceAsStream(
				"savetemplate.html");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int r;
		while ((r = is.read(buffer)) != -1) {
			baos.write(buffer, 0, r);
		}
		String html = new String(baos.toByteArray());
		html = html.replaceAll("@@cypher@@", cypher);
		return html;
	}

	private void save(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		String html = this.prepareHtml(req);
		resp.setHeader("Content-Disposition", "attachment");
		resp.setHeader("Content-Type", "octet/stream");
		resp.getWriter().write(html);
	}

}
