package by.epam.fourthtask.controller;

import static by.epam.fourthtask.controller.StringEnum.*;

import javax.servlet.http.Part;

class FileNameSeparator {

    private static final String CONTENT_DISPOSITION_STRING = "content-disposition";
    private static final String EQUALLY_SYMBOL_REG_EXP = "=";
    private static final String QUOTES_SYMBOL_REG_EXP = "\"";

    private FileNameSeparator() {
    }

    static String getFileNameByPart(Part part) {
        String[] s = part.getHeader(CONTENT_DISPOSITION_STRING).split(EQUALLY_SYMBOL_REG_EXP);
        String[] s2 = s[s.length - 1].replaceAll(QUOTES_SYMBOL_REG_EXP, EMPTY_STRING.getValue()).split(SLASH_REG_EXP.getValue());
        return s2[s2.length - 1].replaceAll(QUOTES_SYMBOL_REG_EXP, EMPTY_STRING.getValue());
    }
}
