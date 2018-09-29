package com.gmail.michzuerch.TeachersAssistant.servlet;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.report.css.ReportCSSImage;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.service.ReportCSSImageService;
import org.apache.fop.servlet.ServletContextURIResolver;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.URIResolver;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "ReportCSSImageServlet", urlPatterns = "/reportcssimage/*")
public class ReportCSSImageServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ReportCSSImageServlet.class.getName());
    protected URIResolver uriResolver;

    @Autowired
    ReportCSSImageService service;

    @Override
    public void init() throws ServletException {
        super.init();
        this.uriResolver = new ServletContextURIResolver(getServletContext());
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] parameterValues = request.getParameterValues("id");
        if (parameterValues.length == 0) {
            LOGGER.warning("Fehler, Requestparameter íd nicht vorhanden");
            throw new IOException("Fehler, Requestparameter id nicht vorhanden");
        }
        Long id = Long.valueOf(parameterValues[0]);
        ReportCSSImage reportCSSImage = service.findBy(id).get();
        if (reportCSSImage == null) {
            LOGGER.warning("Artikelbild nicht gefunden, id: " + id);
            throw new IOException("Artikelbild nicht gefunden, id: " + id);
        }
        // @todo Mimetype für CSS suchen
        response.setContentType(reportCSSImage.getMimeType());
        response.setContentLength(reportCSSImage.getImage().length);
        response.getOutputStream().write(reportCSSImage.getImage());
        response.getOutputStream().flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }

}
