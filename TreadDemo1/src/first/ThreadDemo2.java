package first;

public class ThreadDemo2 {
    public static void main(String[] args) {// 主线程
        // 创建指定名字为thread_c的线程，其中将实现了Runnable接口的类ThreadTarget1的一个实例传递给Thread()
        Thread threadC = new Thread(new ThreadTarget1(), "thread_c");
        Thread threadD = new Thread(new ThreadTarget2());// 创建没指定名字的线程，系统将会给线程赋一个名字
        threadC.start();// 启动线程thread_c
        threadD.start();// 启动没指定名字的那个线程
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName());
        }

    }
}

class ThreadTarget1 implements Runnable {// 实现接口Runnable
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ":c");
        }
    }
}

class ThreadTarget2 implements Runnable {// 实现接口Runnable
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ":d");
        }
    }
}

