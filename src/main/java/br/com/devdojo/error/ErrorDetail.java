package br.com.devdojo.error;

public class ErrorDetail {
    private String titlr;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;

    public String getTitlr() {
        return titlr;
    }

    public void setTitlr(String titlr) {
        this.titlr = titlr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
