package second;


public class ReaderAndWriter {
    public static void main(String[] args) {
        Work work = new Work();
        Thread a = new Reader1(work, "一");
        Thread b = new Reader1(work, "二");
        Thread c = new Reader1(work, "三");
        a.start();
        c.start();
        b.start();

        Thread d = new Writer1(work, "一");
        Thread e = new Writer1(work, "二");
        Thread f = new Writer1(work, "三");
        Thread g = new Writer1(work, "四");
        d.start();
        e.start();
        f.start();
        g.start();
    }
}

class Work {
    private int buffer[] = new int[10];
    private int in = 0;
    private int out = 0;
    private int empty = 10;

    private int target = 1;//计数
    private int target2 = 1;//计数消费者线程

    synchronized void producer(String name) {
        while (empty <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        while (target > 50) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        buffer[in] = target;
        System.out.println("生产者" + name + "存入了产品：" + buffer[in]);
        in = (in + 1) % 10;
        target++;
        empty--;


        notify();
    }

    synchronized void consumer(String name) {
        while (empty == 10) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        while (target2 > 50) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("          " + "消费者" + name + "获得了产品：" + buffer[out]);
        buffer[out] = 0;
        out = (out + 1) % 10;

        target2++;
        empty++;
        notify();
    }


}

class Reader1 extends Thread {
    private Work work;

    public Reader1(Work work, String name) {
        this.work = work;
        this.setName(name);
    }

    @Override
    public void run() {

        while (true) {
            try {
                sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
            }


            work.producer(Thread.currentThread().getName());


        }

    }
}

class Writer1 extends Thread {
    private Work work;

    public Writer1(Work work, String name) {
        this.work = work;
        this.setName(name);
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
            }

            work.consumer(Thread.currentThread().getName());

        }
    }
}
