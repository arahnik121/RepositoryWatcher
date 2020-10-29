package controller;

import handler.*;

import java.io.File;
import java.nio.file.Path;

public class HandlerController implements Runnable {
    StringHandler sh = new StringHandler();
    private final File[] fList;
    private final String fileName;

    public HandlerController(String dir, String filePath) {
        File folder = new File(dir);
        this.fList = folder.listFiles();
        this.fileName = filePath;
    }

    public void chooseHandler() {
        for (File file : fList) {
            if (file.getName().equals(fileName)) {
                Handler handler;
                if (file.exists() && sh.getExtensionByStringHandling(file.getName()).equals(".json")) {
                    handler = new JSONHandler();
                    handler.handle(file);
                } else if (file.exists() && sh.getExtensionByStringHandling(file.getName()).equals(".xml")) {
                    handler = new XMLHandler();
                    handler.handle(file);
                } else if (file.exists() && sh.getExtensionByStringHandling(file.getName()).equals(".DS_Store")) {
                    return;
                } else {
                    handler = new RemoveHandler();
                    handler.handle(file);
                }
            }
        }
    }

    @Override
    public void run() {
        chooseHandler();
    }
}
