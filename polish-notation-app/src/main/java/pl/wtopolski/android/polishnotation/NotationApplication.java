package pl.wtopolski.android.polishnotation;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wtopolski.android.polishnotation.support.model.CountResult;
import pl.wtopolski.android.polishnotation.support.task.CountListener;
import pl.wtopolski.android.polishnotation.support.task.CountTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotationApplication extends Application implements CountListener, Handler.Callback {
    private static final Logger LOG = LoggerFactory.getLogger(CountTask.class);

    private CountResult result;
    private ExecutorService executor;
    private Handler guiHandler;
    private CountListener externalListener;

    static {
        System.loadLibrary("polish-notation-app");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        guiHandler = new Handler(this);
        executor = Executors.newSingleThreadExecutor();
    }

    public void makeRequest(String request) {
        executor.submit(new CountTask(request, this));
    }

    @Override
    public void onResolve(CountResult result) {
        Message msg = new Message();
        msg.obj = result;
        guiHandler.sendMessage(msg);
    }

    @Override
    public boolean handleMessage(Message message) {
        result = (CountResult) message.obj;
        try {
            if (externalListener != null) {
                externalListener.onResolve(result);
            }
        } catch (Exception e) {
            LOG.error("Count Error", e.getMessage(), e);
        }
        return true;
    }

    public void setListener(CountListener listener) {
        this.externalListener = listener;
    }
}
