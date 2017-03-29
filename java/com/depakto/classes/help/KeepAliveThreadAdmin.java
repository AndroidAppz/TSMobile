package com.depakto.classes.help;

import com.depakto.classes.help.commands.CWhoAmI;

public class KeepAliveThreadAdmin extends Thread {
    private static int SLEEP = 60000;
    private Thread thread = Thread.currentThread();
    private TSQueryAdmin tsq;
    private SocketWriterAdmin writer;

    public KeepAliveThreadAdmin(TSQueryAdmin tsq, SocketWriterAdmin socketWriter) {
        super("Keep alive");
        this.tsq = tsq;
        this.writer = socketWriter;
    }

    public void run() {
        while (this.tsq.getSocket().isConnected() && this.tsq.getIn() != null) {
            long idleTime = this.writer.getIdleTime();
            Thread thread;
            if (idleTime >= ((long) SLEEP)) {
                Runtime.getRuntime().gc();
                System.gc();
                this.tsq.doCommand(new CWhoAmI());
                try {
                    thread = this.thread;
                    Thread.sleep((long) SLEEP);
                } catch (InterruptedException e) {
                    Runtime.getRuntime().gc();
                    System.gc();
                }
            } else {
                try {
                    thread = this.thread;
                    Thread.sleep(((long) SLEEP) - idleTime);
                } catch (InterruptedException e2) {
                }
            }
        }
        Runtime.getRuntime().gc();
        System.gc();
    }
}
