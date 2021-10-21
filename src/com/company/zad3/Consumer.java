package com.company.zad3;

public class Consumer implements Runnable{
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i = 0;  i < 10;   i++) {
            String message = buffer.take();
        }

    }
}

