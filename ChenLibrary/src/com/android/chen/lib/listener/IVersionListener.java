package com.android.chen.lib.listener;

import com.android.chen.lib.entity.VersionInfo;

public abstract class IVersionListener {

    // 开启app
    public abstract void startApp();

    // 是否是后台运行（即无加载提示)
    public boolean isBackground() {

	return false;
    }

    // 指定下载路径
    public abstract String downloadPath(VersionInfo version);

    public void backOfFinish(boolean forceup) {

    };// 打算安装之后的操作

    public void failCancel(boolean forceup) {

	// 下载错误后的提示取消
    }
}
