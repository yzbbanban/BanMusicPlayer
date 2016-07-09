package com.wangban.yzbbanban.banmusicplayer.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by YZBbanban on 16/7/9.
 */
public class ToastUtil {
    public static void showToast(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
