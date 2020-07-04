package model;

import java.sql.Timestamp;

public class BeanShangpinpingjia {
	private int shangpinbgianhao;
	private int pingjiayonghubianhao;
	private String pingjianeirong;
	private Timestamp pingjiariqi;
	private int xingji;
	private String zhaopian;
	
	public int getShangpinbgianhao() {
		return shangpinbgianhao;
	}
	public void setShangpinbgianhao(int shangpinbgianhao) {
		this.shangpinbgianhao = shangpinbgianhao;
	}
	public int getPingjiayonghubianhao() {
		return pingjiayonghubianhao;
	}
	public void setPingjiayonghubianhao(int pingjiayonghubianhao) {
		this.pingjiayonghubianhao = pingjiayonghubianhao;
	}
	public String getPingjianeirong() {
		return pingjianeirong;
	}
	public void setPingjianeirong(String pingjianeirong) {
		this.pingjianeirong = pingjianeirong;
	}
	public Timestamp getPingjiariqi() {
		return pingjiariqi;
	}
	public void setPingjiariqi(Timestamp pingjiariqi) {
		this.pingjiariqi = pingjiariqi;
	}
	public int getXingji() {
		return xingji;
	}
	public void setXingji(int xingji) {
		this.xingji = xingji;
	}
	public String getZhaopian() {
		return zhaopian;
	}
	public void setZhaopian(String zhaopian) {
		this.zhaopian = zhaopian;
	}
}
