package com.epam.jd12.reader;

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

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DEFAULT_DATA_FILE = "data/default_data.txt";

    public List<String> readFile(String filePath) throws ReadDataFileException {

        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {

            filePath = DEFAULT_DATA_FILE;
            LOGGER.log(Level.ERROR, "Data file not found! Used default data file path.");
        }

        Path path = Paths.get(filePath);
        List<String> lines;

        try {
            lines = Files.lines(path).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ReadDataFileException(e);
        }

        return lines;
    }
}
