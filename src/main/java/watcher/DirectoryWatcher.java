package watcher;

import controller.HandlerController;
import handler.StringHandler;
import logger.LogWriter;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        ExecutorService executorService = Executors.newFixedThreadPool(1);

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

                String kind = event.kind().toString();
                if (kind.equals("OVERFLOW")) {
                    break;
                }

                logger.log( "File " + event.context().toString() + " was affected with kind " + event.kind() + " with extension "
                        + sh.getExtensionByStringHandling(event.context().toString()) + "\n");

                String fileName = event.context().toString();
                if (event.kind().toString().equals("ENTRY_CREATE")) {
                    executorService.submit(new HandlerController(PATH.toString(), fileName));
                    System.out.println("Task submitted");
                }
            }
            key.reset();
        }
        executorService.shutdown();
    }
}
