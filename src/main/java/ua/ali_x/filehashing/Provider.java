package ua.ali_x.filehashing;

import java.io.*;

public class Provider implements Runnable {
    private Main main;
    private String fileName;
    private InputStream fin;
    private OutputStream fout;
    private byte[] hash;

    public Provider(Main main, String fileName) {
        this.main = main;
        this.fileName = fileName;
    }

    public void run() {
        try {
            fin = new FileInputStream(fileName);
            main.putInputStream(fin);
            hash = main.takeHash();
            fout = new FileOutputStream(fileName, true);
            fout.write(hash);
            fout.close();
            fin.close();
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}