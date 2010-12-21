package com.github.jskeys.encpad;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class EncpadServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String action = req.getParameter("action");
		if (action.equals("save")) {
			this.save(req, resp);
		}
	}

	private void save(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

}
