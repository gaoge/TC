package com.feng.android.home;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.drouter.api.core.DRouter;
import com.feng.android.base.BaseActivity;
import com.feng.android.base.BaseApplication;
import com.feng.android.base.exception.ExceptionCrashHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import timber.log.Timber;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-09 12:50
 * @tips
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DRouter.getInstance().action("/insurance/list")
                        .context(HomeActivity.this)
                        .invokeAction();
            }
        });
    }

    private void uploadCrashFile() {
        int i = 2/0;
        File crashFile = ExceptionCrashHandler.getsInstance().getCrashFile();
        if(crashFile.exists()){
            //上传到服务器
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(crashFile));
                char[] buffer = new char[1024];
                int len = 0;
                while((len = inputStreamReader.read(buffer)) != -1){
                    String str = new String(buffer,0,len);
                    Timber.e(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadCrashFile();
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        upgradeFromAndFix();


    }

    private void upgradeFromAndFix() {
        File file = new File(Environment.getExternalStorageDirectory(),"fix.apatch");
        if(file.exists()){
            try {
                //立马生效，不需要重启
//                BaseApplication.mPatchManager.addPatch(file.getAbsolutePath());
                Toast.makeText(this,"修复成功",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this,"修复失败",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
