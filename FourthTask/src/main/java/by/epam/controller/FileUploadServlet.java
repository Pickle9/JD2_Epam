package by.epam.controller;

import by.epam.entity.Bank;
import by.epam.parser.BankDOMParser;
import by.epam.parser.BankSAXParser;
import by.epam.parser.BankSTAXParser;
import by.epam.validator.XmlValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static by.epam.controller.ControllerStringEnum.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

@WebServlet(urlPatterns = {"/upload/"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String XSD_SCHEME_FILE_PATH = "data/banks.xsd";
    private static final String UPLOAD_DIR_NAME = "upload";
    private static final String SLASH_SYMBOL = "\\";
    private static final String DOM_PARSER_STRING = "DOM";
    private static final String SAX_PARSER_STRING = "SAX";
    private static final String STAX_PARSER_STRING = "StAX";
    private static final String PARSER_TYPE_STRING = "parserType";
    private static final String BANKS_STRING = "banks";
    private static final String PARSER_STRING = "parser";
    private static final String OUT_STRING = "out";
    private static final String CONTENT_STRING = "content";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String servletPath = request.getServletContext().getRealPath(EMPTY_STRING.getValue());
        String projectPath = EMPTY_STRING.getValue();
        String selectedParser = request.getParameter(PARSER_STRING);

        for (String line : servletPath.split(SLASH_REG_EXP.getValue())) {
            if (line.equalsIgnoreCase(OUT_STRING)) {
                break;
            }
            projectPath = projectPath.concat(line).concat(SLASH_SYMBOL);
        }

//        for (Part part : request.getParts()) {
//
//        }
        Part part = request.getPart(CONTENT_STRING); // todo for

        String fileName = FileNameAction.getFileNameByPart(part);
        if (FileNameAction.isValidFileName(fileName)) {
            String absoluteXmlPath = projectPath + UPLOAD_DIR_NAME + File.separator + fileName;
            String absoluteXsdPath = projectPath + XSD_SCHEME_FILE_PATH;
            part.write(absoluteXmlPath);

            boolean isValidXmlByXsd = XmlValidator.validate(absoluteXmlPath, absoluteXsdPath);
            if (!isValidXmlByXsd) {
                response.sendError(777, "Not valid xml file");
            }

            switch (selectedParser) {
                case DOM_PARSER_STRING: {
                    request.setAttribute(PARSER_TYPE_STRING, DOM_PARSER_STRING);
                    List<Bank> banks = BankDOMParser.parse(absoluteXmlPath);
                    request.setAttribute(BANKS_STRING, banks);
                    break;
                }
                case SAX_PARSER_STRING: {
                    request.setAttribute(PARSER_TYPE_STRING, SAX_PARSER_STRING);
                    List<Bank> banks = BankSAXParser.parse(absoluteXmlPath);
                    request.setAttribute(BANKS_STRING, banks);
                    break;
                }
                case STAX_PARSER_STRING: {
                    request.setAttribute(PARSER_TYPE_STRING, STAX_PARSER_STRING);
                    List<Bank> banks = BankSTAXParser.parse(absoluteXmlPath);
                    request.setAttribute(BANKS_STRING, banks);
                    break;
                }
                default: {
                    response.sendError(778);
                }
            }

            try {
                request.getRequestDispatcher(JSPPathProperty.TITLE_PAGE_PATH).forward(request, response);
            } catch (UnknownHostException e) {
                LOGGER.log(Level.FATAL, e);
                response.sendError(779);
            }
        }
    }
}
