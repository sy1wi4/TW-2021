package zad2;

public class Customer implements Runnable {
    int id;
    Semaphore semaphore;
    public Customer(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            System.out.println("took basket: " + id);
            semaphore.lock();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.unlock();
            System.out.println("returned basket: " + id);
        }
    }
}
