package com.bawei.mvvmcore.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.bawei.mvvmcore.repository.BaseRepository;

public abstract class BaseViewModel<Repo extends BaseRepository> extends ViewModel implements LifecycleObserver {

    protected Repo mRepository;
    protected LifecycleOwner owner;

    public BaseViewModel(LifecycleOwner _owner){
        mRepository = createRepository();
        _owner.getLifecycle().addObserver(this);
        this.owner = _owner;
    }

    /**
     * 创建具体的数据仓库
     * @return
     */
    protected abstract Repo createRepository();


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void activityOnCreate(){
        initRes();
    }

    /**
     * 初始化资源
     */
    protected abstract void initRes();

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void activityOnStop(){
        releaseRes();
    }

    /***
     * 释放资源
     */
    protected abstract void releaseRes();


}
