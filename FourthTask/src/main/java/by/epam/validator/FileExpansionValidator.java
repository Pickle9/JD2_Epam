package by.epam.fourthtask.validator;

public class FileExpansionValidator {

    private static final String POINT_SYMBOL_REG_EXP = "\\.";
    private static final String XML_EXPANSION = "xml";

    public static boolean validate(String fileName) {
        String[] q = fileName.split(POINT_SYMBOL_REG_EXP);
        String expansion = q[q.length - 1];

        return expansion.equalsIgnoreCase(XML_EXPANSION);
    }

}
