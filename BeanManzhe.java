package model;

import java.sql.Timestamp;

public class BeanManzhe {
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
