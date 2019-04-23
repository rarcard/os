package first;

public class ThreadDemo3 {
    public static void main(String[] args) {// 主线程
        ThreadTarget t = new ThreadTarget();
        Thread thread0 = new Thread(t);// 创建线程
        Thread thread1 = new Thread(t);// 创建线程
        thread0.start();// 启动线程
        thread1.start();// 启动线程
    }
}

class ThreadTarget implements Runnable {// 实现接口Runnable
    int i;

    @Override
    public void run() {
        System.out.println("###" + Thread.currentThread().getName() + "###");
        while (i < 20) {
            System.out.println(Thread.currentThread().getName() + ":  " + i);
            i++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }
}

