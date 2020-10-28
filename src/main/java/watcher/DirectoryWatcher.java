package watcher;

import controller.HandlerController;
import handler.StringHandler;
import logger.LogWriter;

import java.io.IOException;
import java.nio.file.*;

public class DirectoryWatcher {
    private WatchService watchService;
    private Path PATH;

    public DirectoryWatcher(String path) {
        try {
            this.watchService = FileSystems.getDefault().newWatchService();
            this.PATH = Paths.get(path);
        } catch (Exception e) {
            System.out.println("Wrong directory path...");
            e.printStackTrace();
        }
    }

    public WatchService getWatchService() {
        return watchService;
    }

    public Path getPath() {
        return PATH;
    }

    public void watch() throws InterruptedException {
        WatchKey key;
        LogWriter logger = new LogWriter();
        StringHandler sh = new StringHandler();
        try {
            getPath().register(getWatchService(),
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Waiting for changes...");
        while ((key = getWatchService().take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                logger.log( "File " + event.context().toString() + " was affected with kind " + event.kind() + " with extension "
                        + sh.getExtensionByStringHandling(event.context().toString()) + "\n");
            }
            key.reset();
        }
    }
}
