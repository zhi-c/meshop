package org.example;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet(){
        ThreadLocal tl = new ThreadLocal();

        new Thread(()->{
            tl.set("666");
            System.out.println(Thread.currentThread().getName()+" :"+tl.get());
        },"蓝色").start();
        new Thread(()->{
            tl.set("777");
            System.out.println(Thread.currentThread().getName()+" :"+tl.get());
        },"红色").start();
    }
}
