package first;

public class ThreadDemo4_1 {

    public static void main(String[] args) {
        Ticket2 t = new Ticket2();
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

class Ticket2 implements Runnable {
    private int ticketNo = 1;
    private int ticketAvailable = 50;

    public synchronized void saleTicket() {
        if (ticketAvailable > 0) {
            System.out.printf("%s---售出门票No：%04d\n", Thread.currentThread()
                    .getName(), ticketNo++);
            ticketAvailable--;
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            saleTicket();
            if (ticketAvailable <= 0) {
                System.out.println("票已售完！");
                break;
            }
        }
    }
}
