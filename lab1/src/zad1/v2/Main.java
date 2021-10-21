package zad1.v2;

// NOT synchronized
// version2: via extending Thread class

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter(0);
        AddThread a = new AddThread(c);
        SubtractThread s = new SubtractThread(c);

        a.start();
        s.start();

        a.join();
        s.join();

        c.print();
    }
}
