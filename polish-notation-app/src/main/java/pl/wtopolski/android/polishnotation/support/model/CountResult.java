package pl.wtopolski.android.polishnotation.support.model;

public class CountResult {
    private String request;
    private String postfix;
    private String prefix;
    private double result;

    public CountResult() {
    }

    public CountResult(String request, String postfix, String prefix, double result) {
        this.request = request;
        this.postfix = postfix;
        this.prefix = prefix;
        this.result = result;
    }

    public String getRequest() {
        return request;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
