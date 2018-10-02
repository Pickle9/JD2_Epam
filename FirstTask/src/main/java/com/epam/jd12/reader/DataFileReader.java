package com.epam.jd12.reader;

import com.epam.jd12.exception.DataFileException;
import com.epam.jd12.exception.DataFileNotFoundException;
import com.epam.jd12.exception.ReadDataFileException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DataFileReader {

    private static final Logger log = LogManager.getLogger();

    public List<String> readFile(String filePath) throws DataFileException {

        if(!new File(filePath).exists()) {
            log.log(Level.ERROR, "File not found");
            throw new DataFileNotFoundException();
        }

        Path path = Paths.get(filePath);

        List<String> lines;

        try {
            lines = Files.lines(path).collect(Collectors.toList());
        } catch (IOException e) {
            log.log(Level.ERROR, "Read file error");
            throw new ReadDataFileException(e);
        }

        return lines;

    }

}
