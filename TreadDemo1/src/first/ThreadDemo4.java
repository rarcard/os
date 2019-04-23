package first;

public class ThreadDemo4 {
    public static void main(String[] args) {
        Ticket t = new Ticket();
        Thread t1 = new Thread(t, "窗口一");
        Thread t2 = new Thread(t, "窗口二");
        Thread t3 = new Thread(t, "窗口三");
        Thread t4 = new Thread(t, "窗口四");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class Ticket implements Runnable {
    private int ticketNo = 1;
    private int ticketAvailable = 50;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ticketAvailable <= 0) {
                System.out.println("票已售完！");
                break;
            }
            System.out.printf("%s---售出门票No：%04d\n",Thread.currentThread().getName(),ticketNo++);
            ticketAvailable--;
        }
    }
}

