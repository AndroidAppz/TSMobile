package com.depakto.classes.help;

import com.depakto.classes.help.api.Callback;
import com.depakto.classes.help.commands.Command;
import com.depakto.classes.help.log.LogHandler;
import com.depakto.tsmobile.ServerGroupsActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

public class TSQueryGroups {
    public static final Logger log = Logger.getLogger(TSQueryGroups.class.getName());
    private TSApiGroups bot;
    private ConcurrentLinkedQueue<Command> commandList = new ConcurrentLinkedQueue();
    private TSConfig config;
    private EventManager eventManager = new EventManager();
    public LogHandler hanlder1;
    private BufferedReader in;
    private final LogHandler logPomoc;
    private PrintWriter out;
    private Socket socket;
    private SocketReaderGroups socketReader;
    private SocketWriterGroups socketWriter;

    public enum FloodRate {
        DEFAULT(350),
        MAX(650),
        UNLIMITED(0);
        
        private int ms;

        private FloodRate(int ms) {
            this.ms = ms;
        }

        public int getMs() {
            return this.ms;
        }
    }

    public TSQueryGroups(TSConfig config) {
        log.setUseParentHandlers(false);
        this.logPomoc = new LogHandler(config.getDebugToFile());
        log.addHandler(this.logPomoc);
        log.setLevel(config.getDebugLevel());
        this.config = config;
    }

    private Runnable keepProces(final TSQueryGroups tsQuery, final SocketWriterGroups socketWriter) {
        return new Runnable() {
            public void run() {
                new KeepAliveThreadGroups(tsQuery, socketWriter).start();
            }
        };
    }

    public TSQueryGroups connect(ServerGroupsActivity act) {
        try {
            this.socket = new Socket(this.config.getHost(), this.config.getQueryPort());
            if (this.socket.isConnected()) {
                this.out = new PrintWriter(this.socket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                this.socketReader = new SocketReaderGroups(this, act);
                this.socketReader.start();
                this.socketWriter = new SocketWriterGroups(this, this.config.getFloodRate().getMs());
                this.socketWriter.start();
                try {
                    act.executor2.execute(new KeepAliveThreadGroups(this, this.socketWriter));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e2) {
            exit();
        } catch (UnknownError e3) {
            exit();
            e3.printStackTrace();
        }
        TSApiGroups api = getApi();
        if (!(this.config.getUsername() == null || this.config.getPassword() == null)) {
            api.login(this.config.getUsername(), this.config.getPassword());
        }
        return this;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public PrintWriter getOut() {
        return this.out;
    }

    public BufferedReader getIn() {
        return this.in;
    }

    public boolean doCommand(Command c) {
        this.commandList.offer(c);
        long start = System.currentTimeMillis();
        while (!c.isAnswered() && System.currentTimeMillis() - start < 4000) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
        if (c.isAnswered()) {
            return true;
        }
        log.severe("Command " + c.getName() + " is not answered in time.");
        this.commandList.remove(c);
        return false;
    }

    public void doCommandAsync(Command c) {
        doCommandAsync(c, null);
    }

    public void doCommandAsync(final Command c, final Callback callback) {
        new Thread(new Runnable() {
            public void run() {
                TSQueryGroups.this.doCommand(c);
                if (callback != null) {
                    callback.handle();
                }
            }
        }).start();
    }

    public void exit() {
        if (this.out != null) {
            this.out.close();
            this.out = null;
        }
        if (this.in != null) {
            try {
                this.in.close();
                this.in = null;
            } catch (IOException e) {
            }
        }
        if (this.socket != null) {
            try {
                this.socket.shutdownInput();
                this.socket.shutdownOutput();
                this.socket.close();
                this.socket = null;
            } catch (IOException e2) {
            }
        }
        if (this.socketReader != null) {
            this.socketReader.finish();
            this.socketReader = null;
        }
        if (this.socketWriter != null) {
            this.socketWriter.finish();
            this.socketWriter = null;
        }
        log.removeHandler(this.logPomoc);
        this.commandList.clear();
    }

    public ConcurrentLinkedQueue<Command> getCommandList() {
        return this.commandList;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public TSApiGroups getApi() {
        if (this.bot == null) {
            this.bot = new TSApiGroups(this);
        }
        return this.bot;
    }
}
