import controller.HandlerController;
import handler.RemoveHandler;
import watcher.DirectoryWatcher;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/prop"));
        final String path = props.getProperty("PATH");

        Thread thread = new Thread(new HandlerController(path));
        thread.start();

        DirectoryWatcher dw = new DirectoryWatcher(path);
        dw.watch();
    }
}