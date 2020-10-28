package handler;

import java.io.File;

public class RemoveHandler implements Handler {

    public void handle(File f) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        f.delete();
    }
}
