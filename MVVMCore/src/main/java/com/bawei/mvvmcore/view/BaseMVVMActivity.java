package com.bawei.mvvmcore.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.bawei.mvvmcore.R;
import com.bawei.mvvmcore.exception.MVVMNormalExcepation;
import com.bawei.mvvmcore.viewmodel.BaseViewModel;
import com.bawei.untils.MsgUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseMVVMActivity<VM extends BaseViewModel,Binding extends ViewDataBinding> extends BaseActivity {
    protected VM mViewModel;
    protected Binding mBinding;

    /**
     * 存放ui上所需要的数据源(页面变量)
     */
    private HashMap<Integer,Object> mMap = new HashMap<>();

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {

        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        //要设置数据绑定的生命周期拥有者，否则livedata的属性变更无法更新ui内容
        mBinding.setLifecycleOwner(this);
        mViewModel = createViewModel();


        super.onCreate(savedInstanceState);


        prepareSetVars(mMap);
        setVars(mBinding,mMap);
    }

    /**
     * 将设置的数据源绑定到dataBinding
     * @param mBinding
     * @param mMap
     */
    private void setVars(Binding mBinding, HashMap<Integer, Object> mMap) {
        if (mMap==null||mMap.size()==0){
            try {
                throw  new MVVMNormalExcepation("please set variables...");
            } catch (MVVMNormalExcepation mvvmNormalExcepation) {
                mvvmNormalExcepation.printStackTrace();
            }
        }

        for (Map.Entry<Integer,Object> entry:mMap.entrySet()){
            mBinding.setVariable(entry.getKey(),entry.getValue());
        }
    }

    /**
     * 项目父类map添加变量
     * @param mMap
     */

    protected abstract void prepareSetVars(HashMap<Integer, Object> mMap);

    /**
     * 创建viewModel
     * @return
     */
    protected abstract VM createViewModel();

    /**
     * 设置布局ID
     * @return
     */
    protected abstract int getLayoutId();
}
