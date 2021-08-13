package com.feng.android.home;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.drouter.api.core.DRouter;
import com.feng.android.base.BaseActivity;
import com.feng.android.base.exception.ExceptionCrashHandler;
import com.feng.android.base.fixBug.FixDexManager;
import com.feng.android.lib_framework.ui.dialog.DefaultDialog;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import io.reactivex.rxjava3.functions.Consumer;
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

    /**
     * 上传崩溃文件到服务器
     */
    private void uploadCrashFile() {
//        int i = 2/0;
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
//                uploadCrashFile();
//                startActivity(TestActivity.class);
                showDialog();
            }
        });

        findViewById(R.id.fix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                uploadCrashFile();
                fixDexBug();
//                startActivity(TestActivity.class);

            }
        });
    }

    private void showDialog() {
        DefaultDialog dialog = new DefaultDialog.Builder(HomeActivity.this)
                .setContentView(R.layout.dialog_comment)
                .setText(R.id.send,"发送准备")
                .fromBottom(true)
                .fullWidth()
                .create();

        EditText et = dialog.getView(R.id.etComment);
        dialog.setClickListener(R.id.send, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this,"发送消息" + et.getText().toString(),Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Throwable {
                        if (granted) {

                        }
                    }
                });
//        AndFixBug();


    }

    private void fixDexBug() {
        File fixFile = new File(Environment.getExternalStorageDirectory(),"fix.dex");

        if(fixFile.exists()){
            FixDexManager fixDexManager = new FixDexManager(this);
            try {
                fixDexManager.fixDex(fixFile.getAbsolutePath());
                Toast.makeText(this,"修复成功",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this,"修复失败",Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * 加载patch补丁
     */
    private void AndFixBug() {
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
