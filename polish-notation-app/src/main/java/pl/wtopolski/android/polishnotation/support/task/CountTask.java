package pl.wtopolski.android.polishnotation.support.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wtopolski.android.polishnotation.support.model.CountResult;
import pl.wtopolski.android.polishnotation.support.NotationUtil;
import pl.wtopolski.android.polishnotation.support.exception.BracketException;

public class CountTask implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(CountTask.class);

    private String request;
    private CountResult result;
    private CountListener listener;

    public CountTask(String request, CountListener listener) {
        this.request = request;
        this.listener = listener;
    }

    @Override
    public void run() {
        String responsePostfix = null;
        String responsePrefix = null;
        double resultPostfix = 0f;
        double resultPrefix = 0f;

        LOG.debug("Infix: {}", request);

        try {
            responsePostfix = NotationUtil.convertInfixToPostfix(request);
            LOG.debug("Postfix request: {}", responsePostfix);
            resultPostfix = NotationUtil.countFromPostfixNotation(responsePostfix);
            LOG.debug("Count result: {}", resultPostfix);
        } catch (BracketException e) {
            responsePostfix = "";
            LOG.error("Error (postfix): {}", e.getMessage(), e);
        }

        try {
            responsePrefix = NotationUtil.convertInfixToPrefix(request);
            LOG.debug("Prefix request: {}", responsePrefix);
            resultPrefix = NotationUtil.countFromPrefixNotation(responsePrefix);
            LOG.debug("Count result: {}", resultPrefix);
        } catch (BracketException e) {
            responsePrefix = "";
            LOG.error("Error (prefix): {}", e.getMessage(), e);
        }

        if (resultPostfix == resultPrefix) {
            result = new CountResult(request, responsePostfix, responsePrefix, resultPostfix);
        }

        try {
            if (listener != null) {
                listener.onResolve(result);
            }
        } catch (Exception e) {
            LOG.error("Error: {}", e.getMessage(), e);
        }
    }
}
