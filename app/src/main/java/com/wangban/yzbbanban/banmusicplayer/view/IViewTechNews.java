package com.wangban.yzbbanban.banmusicplayer.view;

import com.wangban.yzbbanban.banmusicplayer.entity.TechDetialContent;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import java.util.*;
/**
 * Created by YZBbanban on 16/7/11.
 */
public interface IViewTechNews {
    void setTechNews(List<TechNews> techNews);
    void setTechNews(TechDetialContent content);
    void showTechNews();
}
