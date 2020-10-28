package logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogWriter {
    private final Logger logger;
    private FileHandler fh;

    public LogWriter() {
        this.logger = Logger.getLogger("MyLog");
        try {
            this.fh = new FileHandler("LogFile.log");
        } catch (IOException e) {
            System.out.println("No such directory or file...");
        }
    }

    public void log(String logMessage) {
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.info(logMessage);
    }
}