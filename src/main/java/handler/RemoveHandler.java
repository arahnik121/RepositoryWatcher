package handler;

import java.io.File;

public class RemoveHandler implements Handler {

    public void handle(File f) {
        f.delete();
    }
}
