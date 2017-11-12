package ua.ali_x.filehashing;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;

public class Worker implements Runnable {
    private Main main;
    private InputStream file;

    public Worker(Main main) {
        this.main = main;
    }

    public void run() {
        while (true) {
            try {
                file = main.takeInputStream();
                main.putHash(DigestUtils.md5Hex(file).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}