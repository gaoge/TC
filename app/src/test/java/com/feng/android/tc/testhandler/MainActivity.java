package com.feng.android.tc.testhandler;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 14:13
 * @tips
 */
public class MainActivity extends Activity {
    private TextView mTextView;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mTextView.setText((CharSequence) msg.obj);
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate 执行了");
        mTextView = findViewById(R.id.text_view);

        new Thread(){
            @Override
            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Looper.prepare();
                Handler handler = new Handler();
                Message message = new Message();
                message.obj = "后台数据";
                mHandler.sendMessage(message);
//                mTextView.setText("后台数据");
            }
        }.start();

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume 执行了");
    }
}
