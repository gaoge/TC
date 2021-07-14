package com.feng.android.tc.aop;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.feng.android.net.CheckNet;
import com.feng.android.net.NetUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import androidx.fragment.app.Fragment;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-12 16:25
 * @tips 处理切点
 */
@Aspect
public class SectionAspect {
    /**
     * 找到处理的切点
     * * *(..)) 可以处理所有的方法
     */
    @Pointcut("execution(@com.feng.android.net.CheckNet * *(..))")
    public void checkNetBehavior(){

    }

    /**
     * 处理切面
     */
    @Around("checkNetBehavior()")
    public Object checkNet(ProceedingJoinPoint joinPoint) throws  Throwable{
        Log.e("TAG","checkNet");
        //做埋点 日志上传 权限检测 网络检测
        //1. 获取 CheckNet 注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckNet checkNet = signature.getMethod().getAnnotation(CheckNet.class);
        if(null != checkNet){
            //2. 判断有没有网络 怎么样获取Context
            Object object = joinPoint.getThis(); //object可能是 View , Activity,Fragment,当前切点方法所在的类
            Context context = getContext(object);
            if(null != context){
                if(!NetUtil.networkAvailable(context)){
                    Toast.makeText(context,"请检查您的网络",Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

        }

        //3. 没有网络不要往下执行
        return joinPoint.proceed();
    }

    /**
     * 通过对象获取上下文
     * @param object
     * @return
     */
    private Context getContext(Object object) {
        if(object instanceof Activity){
            return (Activity)object;
        }else if(object instanceof Fragment){
            Fragment fragment = (Fragment)object;
            return fragment.getActivity();
        }else if(object instanceof View){
            View view = (View)object;
            return view.getContext();
        }
        return null;
    }
}
