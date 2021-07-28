package com.feng.android.third_framework.okhttp.upload;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-28 16:35
 * @tips
 * 静态代理
 */
public class ExMultipartBody extends RequestBody {
    private RequestBody mRequestBody;
    private int mCurrentLength;
    private UploadProgressListener mProgressListener;

    public ExMultipartBody(RequestBody mRequestBody,UploadProgressListener progressListener) {
        this.mRequestBody = mRequestBody;
        this.mProgressListener = progressListener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        //静态代理最终还是调用的代理对象的方法
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        Timber.e("监听");
        //1. 拿到总的长度
        long contentLenght = contentLength();
        //2. 获取当前写了多少数据? BufferdSink Sink(okio 就是 io的封装)，就是一个服务器的输出流，我还是不知道写了多少数据

        // 又来一个代理 FowardingSink

        ForwardingSink forwardingSink  = new ForwardingSink(sink) {
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                mCurrentLength += byteCount;
                if(null != mProgressListener){
                    mProgressListener.onProgress(contentLenght,mCurrentLength);
                }
                Timber.e(contentLenght + " : " + mCurrentLength);
                super.write(source, byteCount);
            }
        };

        //转一把
        BufferedSink bufferedSink = Okio.buffer(forwardingSink);
        mRequestBody.writeTo(bufferedSink);
        //刷新，RealConnection 连接池
        bufferedSink.flush();
    }


}
