package com.wangban.yzbbanban.banmusicplayer.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;

import android.util.Log;

public class ExceptionUtil {
	public static void handleException(Throwable ex) {
		if (MusicApplication.ISRELEASE) {
			String info = ex.getMessage();
			Log.i("uncaughtException", "info=" + info);

			StringWriter stringWriter = new StringWriter();

			PrintWriter printWriter = new PrintWriter(stringWriter);
			ex.printStackTrace(printWriter);
			info = stringWriter.toString();
			Log.i("uncaughtException", "info=" + info);
		} else {
			ex.printStackTrace();
		}
	}
}
