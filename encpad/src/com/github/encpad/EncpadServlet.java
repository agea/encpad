package com.github.encpad;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String msgBody = "the body!";

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("encpad@encpad.appspot.com"));
		msg.addRecipient(Message.RecipientType.TO,
				new InternetAddress(req.getParameter("save_email")));
		msg.setSubject("the subject");
		msg.setText(msgBody);
		Transport.send(msg);

	}

	private void save(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
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
		resp.setHeader("Content-Disposition", "attachment");
		resp.setHeader("Content-Type", "octet/stream");
		resp.getWriter().write(html);
	}

}
