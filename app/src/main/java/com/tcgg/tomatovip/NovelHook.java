package com.tcgg.tomatovip;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class NovelHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals("com.dragon.read")){
            return;
        }
        XposedHelpers.findAndHookConstructor("com.dragon.read.user.model.VipInfoModel", loadPackageParam.classLoader
                , String.class, String.class, String.class, boolean.class, new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        Object[] args = param.args;
                        String expireTime = (String) args[0];
                        String isVip = (String) args[1];
                        String leftTime = (String) args[2];
                        boolean isAutoCharge = (boolean) args[3];
                        args[0] = "4102415999";
                        args[1] = "1";
                        args[2] = "999999";
                        args[3] = false;
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Object[] args = param.args;
                        String expireTime = (String) args[0];
                        String isVip = (String) args[1];
                        String leftTime = (String) args[2];
                        boolean isAutoCharge = (boolean) args[3];
                    }
                });

        XposedHelpers.findAndHookMethod("com.dragon.read.update.b", loadPackageParam.classLoader, "b", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(false);
            }
        });
    }
}
