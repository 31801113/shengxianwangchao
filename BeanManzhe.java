package model;

import java.sql.Timestamp;

public class BeanManzhe {
	public static final String[] tableTitles={"满折编号","内容","适用商品数量","折扣","起始日期","结束日期"};
	public String getCell(int col){
		if(col==0) return "" + this.getManzhebianhao();
		else if(col==1) return "" + this.getNeirong();
		else if(col==2) return "" + this.getShiyongshangpinshuliang();
		else if(col==3) return "" + this.getZhekou();
		else if(col==4) return "" + this.getQishiriqi();
		else if(col==5) return "" + this.getJieshuriqi();
		else return "";
	}
	private int manzhebianhao;
	private String neirong;
	private double shiyongshangpinshuliang;
	private double zhekou;
	private Timestamp qishiriqi;
	private Timestamp jieshuriqi;
	
	public int getManzhebianhao() {
		return manzhebianhao;
	}
	public void setManzhebianhao(int manzhebianhao) {
		this.manzhebianhao = manzhebianhao;
	}
	public String getNeirong() {
		return neirong;
	}
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}
	public double getShiyongshangpinshuliang() {
		return shiyongshangpinshuliang;
	}
	public void setShiyongshangpinshuliang(double shiyongshangpinshuliang) {
		this.shiyongshangpinshuliang = shiyongshangpinshuliang;
	}
	public double getZhekou() {
		return zhekou;
	}
	public void setZhekou(double zhekou) {
		this.zhekou = zhekou;
	}
	public Timestamp getQishiriqi() {
		return qishiriqi;
	}
	public void setQishiriqi(Timestamp qishiriqi) {
		this.qishiriqi = qishiriqi;
	}
	public Timestamp getJieshuriqi() {
		return jieshuriqi;
	}
	public void setJieshuriqi(Timestamp jieshuriqi) {
		this.jieshuriqi = jieshuriqi;
	}
}
