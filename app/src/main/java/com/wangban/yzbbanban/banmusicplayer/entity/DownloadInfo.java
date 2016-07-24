package com.wangban.yzbbanban.banmusicplayer.entity;

import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.util.ToastUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by YZBbanban on 16/7/22.
 */
public class DownloadInfo implements Serializable {
    private List<DownloadDoc> docs = new ArrayList<DownloadDoc>();

    public DownloadInfo() {
    }

    public DownloadInfo(List<DownloadDoc> downloadDocs) {
        this.docs = downloadDocs;
    }

    public List<DownloadDoc> getDownloadDocs() {
        return docs;
    }

    public void setDownloadDocs(List<DownloadDoc> downloadDocs) {
        this.docs = downloadDocs;
    }

    public List<DownloadDoc> downloadItems(DownloadDoc downloadDoc) {
        if (docs.size() > 1) {
            for (int i = 0; i < docs.size(); i++) {
                DownloadDoc doc = docs.get(i);
                if (downloadDoc.equals(doc)) {
                    ToastUtil.showToast(MusicApplication.getContext().getApplicationContext(), "对不起已在下载列表");

                }
            }
        }
        DownloadDoc doc = new DownloadDoc();
        docs.add(downloadDoc);
        saveDownloadDoc();
        return docs;
    }

    public void saveDownloadDoc() {

        try {
            File file = new File(MusicApplication.getContext().getCacheDir(), "DOC.INFO");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

            oos.writeObject(this);
            oos.flush();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public DownloadInfo readDownloadDocs() {
        try {
            File file = new File(MusicApplication.getContext().getCacheDir(), "DOC.INFO");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            DownloadInfo downloadInfo = (DownloadInfo) ois.readObject();
            if (downloadInfo == null) {
                return new DownloadInfo();
            }
            return downloadInfo;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new DownloadInfo();
    }

    public void deleteData(int position) {
        if (docs.size() > position) {
            docs.remove(position);
            saveDownloadDoc();
        }
    }
}
