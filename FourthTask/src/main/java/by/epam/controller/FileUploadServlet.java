package by.epam.fourthtask.controller;

import by.epam.fourthtask.entity.Bank;
import by.epam.fourthtask.validator.FileExpansionValidator;
import by.epam.fourthtask.validator.XmlByXsdValidator;
import by.epam.fourthtask.parser.BankDOMParser;
import by.epam.fourthtask.parser.BankSAXParser;
import by.epam.fourthtask.parser.BankSTAXParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.fourthtask.controller.StringEnum.*;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/upload/"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String XSD_FILE_PATH = "data" + File.separator + "banks.xsd";
    private static final String UPLOAD_DIR = "upload";
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
        String realPath = request.getServletContext().getRealPath(EMPTY_STRING.getValue());
        String projectDirPath = realPath.split(OUT_STRING)[0];
        String selectedParser = request.getParameter(PARSER_STRING);
        String uploadDirPath = projectDirPath + File.separator + UPLOAD_DIR;

        File fileSaveDir = new File(uploadDirPath);
        if (!fileSaveDir.exists()) {
            LOGGER.log(Level.INFO, "\"upload\" directory was created.");
            fileSaveDir.mkdirs();
        }

        List<Bank> result = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (!part.getName().equalsIgnoreCase(CONTENT_STRING)) {
                continue;
            }

            String fileName = FileNameSeparator.getFileNameByPart(part);

            if (!FileExpansionValidator.validate(fileName)) {
                LOGGER.log(Level.WARN, "Incorrect expansion validation with filename:", fileName);
                throw new ServletException("There isn't a xml file.");
            }

            String absoluteXmlPath = projectDirPath + UPLOAD_DIR + File.separator + fileName;
            String absoluteXsdPath = projectDirPath + XSD_FILE_PATH;
            part.write(absoluteXmlPath);

            if (!XmlByXsdValidator.validate(absoluteXmlPath, absoluteXsdPath)) {
                LOGGER.log(Level.WARN, "Unsuccessful validation trying with this file:", absoluteXmlPath);
                throw new ServletException("Not valid xml file.");
            }

            switch (selectedParser) {
                case DOM_PARSER_STRING: {
                    request.setAttribute(PARSER_TYPE_STRING, DOM_PARSER_STRING);
                    result.addAll(BankDOMParser.parse(absoluteXmlPath));
                    break;
                }
                case SAX_PARSER_STRING: {
                    request.setAttribute(PARSER_TYPE_STRING, SAX_PARSER_STRING);
                    result.addAll(BankSAXParser.parse(absoluteXmlPath));
                    break;
                }
                case STAX_PARSER_STRING: {
                    request.setAttribute(PARSER_TYPE_STRING, STAX_PARSER_STRING);
                    result.addAll(BankSTAXParser.parse(absoluteXmlPath));
                    break;
                }
                default: {
                    LOGGER.log(Level.ERROR, "Default block was called with value:", selectedParser);
                    throw new ServletException("Unknown parser name.");
                }
            }
        }

        request.setAttribute(BANKS_STRING, result);
        try {
            request.getRequestDispatcher(JSPPath.TITLE_PAGE_PATH).forward(request, response);
        } catch (UnknownHostException e) {
            LOGGER.log(Level.FATAL, e);
            throw new ServletException("Unknown error.");
        }
    }
}
