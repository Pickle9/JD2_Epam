package com.epam.jd12.validator;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineValidator {

    private static final String reg = "^[0-9][.][0-9] [0-9][.][0-9]$";

    public List<String> deleteIncorrectLines(List<String> lines) {

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher;

        Iterator iterator = lines.listIterator();

        while (iterator.hasNext()){

            String line = iterator.next().toString();
            matcher = pattern.matcher(line);

            if (!matcher.matches()) {
                iterator.remove();
            }
        }

        return lines;
    }
}
