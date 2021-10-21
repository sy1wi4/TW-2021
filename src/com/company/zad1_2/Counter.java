package com.company.zad1_2;

public class Counter {
    private int i;

    public Counter(int i) {
        this.i = i;
    }

    public synchronized void increment(){
        this.i += 1;
    }

    public synchronized void decrement(){
        this.i -= 1;
    }

    public void print(){
        System.out.println(i);
    }
}
