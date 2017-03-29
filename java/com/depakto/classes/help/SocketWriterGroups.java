package com.depakto.classes.help;

import com.depakto.classes.help.commands.Command;

public class SocketWriterGroups extends Thread {
    private int floodRate;
    private long lastCommand = System.currentTimeMillis();
    private boolean stop;
    private TSQueryGroups tsconnect;

    public SocketWriterGroups(TSQueryGroups tsconnect, int floodRate) {
        super("SocketWriter");
        this.tsconnect = tsconnect;
        if (floodRate > 50) {
            this.floodRate = floodRate;
        } else {
            this.floodRate = 50;
        }
    }

    public void run() {
        while (this.tsconnect.getSocket().isConnected() && this.tsconnect.getOut() != null && !this.stop) {
            Command c = (Command) this.tsconnect.getCommandList().peek();
            if (!(c == null || c.isSent())) {
                String msg = c.toString();
                TSQuery.log.info("> " + msg);
                this.tsconnect.getOut().println(msg);
                this.lastCommand = System.currentTimeMillis();
                c.setSent();
            }
            try {
                Thread.sleep((long) this.floodRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        TSQuery.log.severe("SocketWriter has stopped!");
    }

    public long getIdleTime() {
        return System.currentTimeMillis() - this.lastCommand;
    }

    public void finish() {
        this.stop = true;
    }
}
