package model;

import java.sql.Timestamp;

public class BeanShangpinpingjia {
	public static final String[] tableTitles={"商品编号","评价用户编号","评价内容","评价日期","星级","照片"};
	public String getCell(int col){
		if(col==0) return "" + this.getShangpinbianhao();
		else if(col==1) return "" + this.getPingjiayonghubianhao();
		else if(col==2) return "" + this.getPingjianeirong();
		else if(col==3) return "" + this.getPingjiariqi();
		else if(col==4) return "" + this.getXingji();
		else if(col==5) return "" + this.getZhaopian();
		else return "";
	}
	private int shangpinbianhao;
	private int pingjiayonghubianhao;
	private String pingjianeirong;
	private Timestamp pingjiariqi;
	private int xingji;
	private String zhaopian;
	
	
	public int getShangpinbianhao() {
		return shangpinbianhao;
	}
	public void setShangpinbianhao(int shangpinbianhao) {
		this.shangpinbianhao = shangpinbianhao;
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
