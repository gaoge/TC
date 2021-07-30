package com.feng.android.third_framework.okhttp.download.v2.db;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-30 10:37
 * @tips
 */
public class DownloadEntity {
    private String url;
    private long start;
    private long end;
    private long progress;
    private int threadId;
    private long contentLength;

    public DownloadEntity( long start, long end, String url,long progress, int threadId, long contentLength) {
        this.url = url;
        this.start = start;
        this.end = end;
        this.progress = progress;
        this.threadId = threadId;
        this.contentLength = contentLength;
    }

    public int getThreadId() {
        return threadId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }
}
