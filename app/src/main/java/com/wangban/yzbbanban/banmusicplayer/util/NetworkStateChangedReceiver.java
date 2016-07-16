package com.wangban.yzbbanban.banmusicplayer.util;

import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

//�û�����������
public class NetworkStateChangedReceiver extends BroadcastReceiver {
//��һ�֣�ʹ�þ�̬����
	//static boolean isOpenMobile=false;
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			ConnectivityManager manager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
			if (activeNetworkInfo == null) {
				MusicApplication.networkIsNone=true;

			} else {
				NetworkInfo mobileNetwork = manager
						.getNetworkInfo(manager.TYPE_MOBILE);
				if (mobileNetwork != null && mobileNetwork.isConnected()) {
					//isOpenMobile=true;
					MusicApplication.networkIsMobile=true;
				}

				NetworkInfo wifiNetworkInfo = manager
						.getNetworkInfo(manager.TYPE_WIFI);
				if (wifiNetworkInfo != null && wifiNetworkInfo.isConnected()) {
					MusicApplication.networkIsWifi=true;

				}
			}
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
	}

}
