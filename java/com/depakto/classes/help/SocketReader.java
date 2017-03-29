package com.depakto.classes.help;

import com.depakto.classes.help.commands.Command;
import com.depakto.tsmobile.ClientActivity;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SocketReader extends Thread {
    public final ClientActivity act;
    private SimpleDateFormat nowBot;
    private boolean stop;
    private TSQuery tsconnect;

    public SocketReader(TSQuery tsconnect, ClientActivity act) {
        super("SocketReader");
        this.tsconnect = tsconnect;
        this.act = act;
        int i = 0;
        while (tsconnect.getIn().ready()) {
            try {
                tsconnect.getIn().readLine();
                i++;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void run() {
        TSQuery tSQuery;
        while (this.tsconnect.getSocket().isConnected() && this.tsconnect.getIn() != null && !this.stop) {
            try {
                if (this.tsconnect.getIn().ready()) {
                    final String line = this.tsconnect.getIn().readLine();
                    if (!line.isEmpty()) {
                        Command c = (Command) this.tsconnect.getCommandList().peek();
                        if (line.startsWith("notify")) {
                            tSQuery = this.tsconnect;
                            TSQuery.log.info("< [1event] " + line);
                            new Thread(new Runnable() {
                                public void run() {
                                    String[] arr = line.split(" ", 2);
                                    try {
                                        SocketReader.this.tsconnect.getEventManager().fireEvent(arr[0], arr[1]);
                                    } catch (Exception ee) {
                                        SocketReader.this.act.finish();
                                        ee.printStackTrace();
                                    }
                                }
                            }).start();
                        } else {
                            if (c != null) {
                                if (c.isSent()) {
                                    tSQuery = this.tsconnect;
                                    TSQuery.log.info("[" + c.getName() + "] < " + line);
                                    if (line.startsWith("error")) {
                                        c.feedError(line.substring("error ".length()));
                                        this.act.last_error_query = c.getError().getMessage();
                                        if (c.getError().getId() != 0) {
                                            TSQuery.log.severe("[ERROR] " + c.getError());
                                            this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                            this.act.saveLogs(this.nowBot.format(Calendar.getInstance().getTime()) + ": [" + c.getName() + "] " + c.getError());
                                        }
                                        c.setAnswered();
                                        this.tsconnect.getCommandList().remove(c);
                                    } else if (!line.isEmpty()) {
                                        c.feed(line);
                                    }
                                }
                            }
                            tSQuery = this.tsconnect;
                            TSQuery.log.info("< " + line);
                        }
                    }
                }
            } catch (IOException e) {
                this.act.finish();
                e.printStackTrace();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e2) {
                this.act.finish();
                e2.printStackTrace();
            }
        }
        tSQuery = this.tsconnect;
        TSQuery.log.severe("SocketReader has stopped!");
    }

    public void finish() {
        this.stop = true;
    }
}
