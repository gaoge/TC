package com.feng.android.insurance;

import com.feng.android.insurance.model.Member;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 17:56
 * @tips
 */
public class DatabaseManager {

    private static volatile DatabaseManager mInstance;

    private Observable<Member,Observer<Member>> mObserverable;

    private DatabaseManager(){
        mObserverable = new Observable<>();

    }
    public static DatabaseManager getInstance(){
        if(null == mInstance){
            synchronized (DatabaseManager.class){
                if(null == mInstance){
                    mInstance = new DatabaseManager();
                }
            }
        }
        return mInstance;
    }

    public void insert(Member member){
        mObserverable.addUpdate(member);
    }

    public void register(Observer<Member> observer){
        mObserverable.register(observer);
    }

    public void unregister(Observer<Member> observer){
        mObserverable.unregister(observer);
    }
}
