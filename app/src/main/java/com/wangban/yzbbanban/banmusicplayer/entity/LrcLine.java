package com.wangban.yzbbanban.banmusicplayer.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ����һ�������
 */
public class LrcLine {
	private String time; // ʱ��
	private String content; // ����
	private static SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

	public LrcLine(String time, String content) {
		super();
		this.time = time;
		this.content = content;
	}

	public LrcLine() {
		// TODO Auto-generated constructor stub
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * �жϲ���time������ʱ����time�����Ƿ�һ��
	 * @param time  ����ֵ
	 */
	public boolean equalsTime(int time){
		//t :   03:30
		String t = sdf.format(new Date(time));
		// time: 03:30.33
		if(this.time.startsWith(t)){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "["+time+"]"+content+"\n";
	}
}
