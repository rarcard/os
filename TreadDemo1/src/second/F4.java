package second;

public class F4 {
    public static void main(String[] args) {
    ThreadA Ta=new ThreadA("Ta");
    ThreadA Tb=new ThreadA("Tb");
    Ta.start();
    Tb.start();
    Thread Tc=new Thread(new ThreadC());
    Thread Td=new Thread(new ThreadC());
    Tc.start();
    Td.start();
    }
}

class ThreadA extends Thread {
    public ThreadA(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ":第" + i + "次输出");
        }
    }
}



class ThreadC implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ":第" + i + "次输出");
        }
    }
}

