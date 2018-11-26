package by.epam.controller;

import static by.epam.controller.ControllerStringEnum.*;

import javax.servlet.http.Part;

class FileNameAction {

    private static final String CONTENT_DISPOSITION_STRING = "content-disposition";
    private static final String EQUALLY_SYMBOL_REG_EXP = "=";
    private static final String QUOTES_SYMBOL_REG_EXP = "\"";
    private static final String POINT_SYMBOL_REG_EXP = "\\.";
    private static final String XML_EXPANSION_STRING = "xml";

    private FileNameAction() {}

    static String getFileNameByPart(Part part) {
        String[] s = part.getHeader(CONTENT_DISPOSITION_STRING).split(EQUALLY_SYMBOL_REG_EXP);
        String[] s2 = s[s.length - 1].replaceAll(QUOTES_SYMBOL_REG_EXP, EMPTY_STRING.getValue()).split(SLASH_REG_EXP.getValue());
        return s2[s2.length - 1].replaceAll(QUOTES_SYMBOL_REG_EXP, EMPTY_STRING.getValue());
    }

    static boolean isValidFileName(String fileName) {
        String[] q = fileName.split(POINT_SYMBOL_REG_EXP);
        String extension = q[q.length - 1];

        return extension.equalsIgnoreCase(XML_EXPANSION_STRING);
    }
}
