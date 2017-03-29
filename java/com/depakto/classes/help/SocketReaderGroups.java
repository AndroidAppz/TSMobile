package com.depakto.classes.help;

import com.depakto.classes.help.commands.Command;
import com.depakto.tsmobile.ServerGroupsActivity;
import java.io.IOException;

public class SocketReaderGroups extends Thread {
    public final ServerGroupsActivity act;
    private boolean stop;
    private TSQueryGroups tsconnect;

    public SocketReaderGroups(TSQueryGroups tsconnect, ServerGroupsActivity act) {
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
        while (this.tsconnect.getSocket().isConnected() && this.tsconnect.getIn() != null && !this.stop) {
            try {
                if (this.tsconnect.getIn().ready()) {
                    final String line = this.tsconnect.getIn().readLine();
                    if (!line.isEmpty()) {
                        Command c = (Command) this.tsconnect.getCommandList().peek();
                        if (line.startsWith("notify")) {
                            TSQuery.log.info("< [1event] " + line);
                            new Thread(new Runnable() {
                                public void run() {
                                    String[] arr = line.split(" ", 2);
                                    SocketReaderGroups.this.tsconnect.getEventManager().fireEvent(arr[0], arr[1]);
                                }
                            }).start();
                        } else {
                            if (c != null) {
                                if (c.isSent()) {
                                    TSQuery.log.info("[" + c.getName() + "] < " + line);
                                    if (line.startsWith("error")) {
                                        c.feedError(line.substring("error ".length()));
                                        this.act.last_error_query = c.getError().getMessage();
                                        if (c.getError().getId() != 0) {
                                            TSQuery.log.severe("[ERROR] " + c.getError());
                                        }
                                        c.setAnswered();
                                        this.tsconnect.getCommandList().remove(c);
                                    } else if (!line.isEmpty()) {
                                        c.feed(line);
                                    }
                                }
                            }
                            TSQuery.log.info("< " + line);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        TSQuery.log.severe("SocketReader has stopped!");
    }

    public void finish() {
        this.stop = true;
    }
}
