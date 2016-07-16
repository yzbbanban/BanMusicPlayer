package com.wangban.yzbbanban.banmusicplayer.util;

import android.util.Log;

import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.TechDetialContent;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZBbanban on 16/6/9.
 */
public class JsoupUtil implements Consts {

    /**
     * 解析网页的科技新闻数据
     * @param url
     * @return
     * @throws IOException
     */
    public static List<TechNews> downTechNews(String url) throws IOException {
        List<TechNews> techNewses = new ArrayList<TechNews>();
        Document doc = Jsoup.connect(url).get();
        Elements e1 = doc.select(".clearfix").select(".content");
        Elements e2 = e1.first().getElementsByClass("archive");
        for (int i = 0; i < e2.size(); i++) {
            Elements c = e2.get(i).getElementsByClass("main");
            Elements a1 = c.first().getElementsByTag("a");
            String detialPath = a1.first().attr("href");
            String title = a1.first().attr("title");
            Elements img = a1.first().getElementsByTag("img");
            String imagePath = img.attr("src");
            Elements p = c.get(0).getElementsByTag("p");
            String detial = p.text();
//            LogUtil.logInfo(TAG,message);
            String detialContent = detial.substring(0, detial.length() - 5);
            TechNews techNews = new TechNews();
            techNews.setTitle(title);
            techNews.setDetialPath(detialPath);
            techNews.setImagePath(imagePath);
            techNews.setDetialContent(detialContent);
            techNewses.add(techNews);
        }
//        LogUtil.logInfo(TAG, "jsoupechNews: " + techNewses.get(0).getImagePath());

        return techNewses;
    }

    /**
     * 解析新闻详情页
     * @param url
     * @return
     * @throws IOException
     */
    public static TechDetialContent downDetialTechNews(String url) throws IOException {
        TechDetialContent techDetialContent = new TechDetialContent();
        Document doc = Jsoup.connect(url).get();
        Elements e1 = doc.select(".clearfix").select(".content");
        Elements e2 = e1.first().getElementsByClass("archive");
        Elements c = e2.get(0).getElementsByClass("main");
        Elements img = c.first().getElementsByTag("img");
        String content = c.text();
        String imagePath = img.attr("src");
        techDetialContent.setImagePath(imagePath);
        techDetialContent.setContent(content);

        return techDetialContent;
    }


}
