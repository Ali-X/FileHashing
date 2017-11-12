package ua.ali_x.filehashing;

import java.io.InputStream;

public class Main {
    private byte[] hash;
    private InputStream file;
    private boolean emptyHash = true;
    private boolean emptyFile = true;

    public synchronized byte[] takeHash() {
        while (emptyHash) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        emptyHash = true;
        notifyAll();
        return hash;
    }

    public synchronized void putInputStream(InputStream file) {
        while (!emptyFile) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        emptyFile = false;
        this.file = file;
        notifyAll();
    }

    public synchronized InputStream takeInputStream() {
        while (emptyFile) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        emptyFile = true;
        notifyAll();
        return file;
    }

    public synchronized void putHash(byte[] hash) {
        while (!emptyHash) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        emptyHash = false;
        this.hash = hash;
        notifyAll();
    }
}