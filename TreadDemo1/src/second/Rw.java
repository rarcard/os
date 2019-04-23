package second;

public class Rw {
    public static void main(String[] args) {
        Execute execute = new Execute();
        Thread A = new Number(execute);
        Thread B = new English(execute);
        A.start();
        B.start();
    }
}

class Execute {
    private Boolean flag = false;

    synchronized void printNumber(int number1, int number2) {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        System.out.print(number1 + " " + number2 + " ");
        flag = true;
        notify();
    }

    synchronized void printEnglish(char english) {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.print(english + " ");
        flag = false;
        notify();
    }
}

class Number extends Thread {
    private Execute execute;

    public Number(Execute execute) {
        this.execute = execute;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 52; ) {
            try {
                sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
            }
            execute.printNumber(i, i + 1);
            i += 2;
        }
    }
}

class English extends Thread {
    private Execute execute;

    public English(Execute execute) {
        this.execute = execute;
    }

    @Override
    public void run() {
        for (int i = 0; i < 26; i++) {
            try {
                sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
            }
            execute.printEnglish((char) ('A' + i));
        }
    }
}
