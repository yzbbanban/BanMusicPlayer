package com.wangban.yzbbanban.banmusicplayer.model.impl;

import android.os.AsyncTask;

import com.wangban.yzbbanban.banmusicplayer.entity.LrcLine;
import com.wangban.yzbbanban.banmusicplayer.model.IModelLrc;
import com.wangban.yzbbanban.banmusicplayer.model.IMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.util.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by YZBbanban on 16/7/2.
 */
public class ModelLrcImpl implements IModelLrc {

    @Override
    public void getLrc(final String lrcUrl, final IMusicCallback callback) {
        new AsyncTask<String, String, List<LrcLine>>() {
            //异步发送http请求
            protected List<LrcLine> doInBackground(String... params) {
                //下载歌词
                try {
                    InputStream is = HttpUtil.get(lrcUrl);
                    //按行读取输入流
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    List<LrcLine> lines = new ArrayList<LrcLine>();
                    while ((line = reader.readLine()) != null) {
                        //line 可能是:
                        //line 可能是:   [00:04.52]词曲：G.E.M. 邓紫棋
                        //line 可能是:   [ti:画]
                        if ("".equals(line)) {
                            continue;
                        }
                        if (line.contains("]")) {
                            String time = line.substring(1, line.indexOf("]"));
                            String content = line.substring(line.indexOf("]") + 1);
                            LrcLine l = new LrcLine(time, content);
                            lines.add(l);
                        }else {
                            return null;
                        }
                    }
                    return lines;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            //主线程中执行   调用回调方法  返回list
            protected void onPostExecute(java.util.List<LrcLine> result) {
                if (result != null) {
                    callback.findAllMusic(result);
                } else {
                    return;
                }
            }
        }.execute();
    }
}
