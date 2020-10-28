package handler;

public class StringHandler {
    public String getExtensionByStringHandling(String filename) {
        int index = filename.lastIndexOf('.');
        if(index > 0) {
            return filename.substring(index);
        }
        else return "No extension found";
    }
}
