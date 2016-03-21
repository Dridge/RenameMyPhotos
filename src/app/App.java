package app;

import java.io.IOException;import java.lang.String;

public class App {
    public void run(String s) {


        FileHandler fh;
        try {
            fh = new FileHandler("");

            fh.renameDscFiles();
            fh.renameVidFiles();
            fh.renameImgFiles();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}