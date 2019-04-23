package first;

public class ThreadDemo5 {
    public static void main(String[] args) {
        Buffer buf = new Buffer();
        Computer cp = new Computer(buf);
        Printer op = new Printer(buf);
        cp.start();
        op.start();
    }
}

class Buffer {
    private int number;
    private boolean fullFlag = false;

    synchronized void put(int number) {
        while (fullFlag) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.number = number;
        fullFlag = true;
        notify();
    }

    synchronized void print() {
        while (!fullFlag) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("打印缓冲中取得的结果：" + number);
        fullFlag = false;
        notify();
    }
}

class Computer extends Thread {
    private Buffer buf;

    Computer(Buffer buf) {
        this.buf = buf;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
            }
            buf.put(i);
        }
    }
}

class Printer extends Thread {
    private Buffer buf;

    Printer(Buffer buf) {
        this.buf = buf;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
            }
            buf.print();
        }
    }
}
