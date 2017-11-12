package ua.ali_x.filehashing;

public class App {

    public static void main(String[] args) {
        Main main = new Main();
        (new Thread(new Worker(main))).start();
        for (String arg : args) {
            (new Thread(new Provider(main, arg))).start();
        }
    }

}
