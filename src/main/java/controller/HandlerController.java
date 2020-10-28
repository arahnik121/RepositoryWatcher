package controller;

import handler.*;

import java.io.File;

public class HandlerController implements Runnable {
    StringHandler sh = new StringHandler();
    private final File[] fList;

    public HandlerController(String dir) {
        File folder = new File(dir);
        this.fList = folder.listFiles();
    }

    public void chooseHandler() {
        for (File file : fList) {
            Handler handler;
            File f = file;
            if (f.exists() && sh.getExtensionByStringHandling(f.getName()).equals(".json")) {
                handler = new JSONHandler();
                handler.handle(f);
            } else if (f.exists() && sh.getExtensionByStringHandling(f.getName()).equals(".xml")) {
                handler = new XMLHandler();
                handler.handle(f);
            } else {
                handler = new RemoveHandler();
                handler.handle(f);
            }

        }
    }

    @Override
    public void run() {
        while (true) {
            chooseHandler();
        }
    }
}
