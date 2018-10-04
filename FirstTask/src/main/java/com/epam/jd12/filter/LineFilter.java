package com.epam.jd12.filter;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineFilter {

    private static final String POINT_REG_EXP = "^\\d\\.\\d\\s\\d\\.\\d$";

    public List<String> deleteIncorrectLines(List<String> lines) {

        Pattern pattern = Pattern.compile(POINT_REG_EXP);
        Matcher matcher;

        Iterator iterator = lines.listIterator();

        while (iterator.hasNext()) {

            String line = iterator.next().toString();
            matcher = pattern.matcher(line);

            if (!matcher.matches()) {
                iterator.remove();
            }
        }

        return lines;
    }
}
