package handler;

import logger.LogWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XMLHandler implements Handler {
    @Override
    public void handle(File f) {
        long m = System.currentTimeMillis();
        LogWriter logger = new LogWriter();
        double a = (double) (System.currentTimeMillis() - m);

        try {
            logger.log("Time spent to handle XML file: " + a/1000 + " seconds\n" + "Number of lines in file "
                   + f.getName() + ": " + Files.lines(Paths.get(f.getPath())).count() + "\n");
        } catch (IOException e) {
            System.out.println("No XML file found");
            e.printStackTrace();
        }
    }
}
