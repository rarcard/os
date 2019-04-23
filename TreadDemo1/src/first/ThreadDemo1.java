package first;

public class ThreadDemo1 {
    public static void main(String[] args) {// 主线程
        ThreadA threadA = new ThreadA("thread_a");//  创建指定名字thread_a的线程
        ThreadB threadB = new ThreadB();// 不指定名字，系统将自动给线程赋一个名字
        threadA.start();// 启动线程thread_a
        threadB.start();// 启动没指定名字的那个线程
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName());
        }

    }
}

class ThreadA extends Thread {// Thread类的子类
    public ThreadA(String name) {// 参数name为线程指定名字
        super(name);
    }

    @Override
    public void run() {// 重写父类的run方法
        for (int i = 0; i < 10; i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ":a");
        }
    }
}

class ThreadB extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ":b");
        }
    }
}

