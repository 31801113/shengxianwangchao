package model;

import java.sql.Timestamp;

public class BeanYouhuiquan {
	public static final String[] tableTitles={"优惠券编号","内容","使用金额","减免金额","起始日期","结束日期"};
	public String getCell(int col){
		if(col==0) return "" + this.getYouhuiquanbianhao();
		else if(col==1) return "" + this.getNeirong();
		else if(col==2) return "" + this.getShiyongjine();
		else if(col==3) return "" + this.getJianmianjine();
		else if(col==4) return "" + this.getQishishijian();
		else if(col==5) return "" + this.getJieshuriqi();
		else return "";
	}
	private int youhuiquanbianhao;
	private String neirong;
	private double shiyongjine;
	private double jianmianjine;
	private Timestamp qishishijian;
	private Timestamp jieshuriqi;
	
	public int getYouhuiquanbianhao() {
		return youhuiquanbianhao;
	}
	public void setYouhuiquanbianhao(int youhuiquanbianhao) {
		this.youhuiquanbianhao = youhuiquanbianhao;
	}
	public String getNeirong() {
		return neirong;
	}
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}
	public double getShiyongjine() {
		return shiyongjine;
	}
	public void setShiyongjine(double shiyongjine) {
		this.shiyongjine = shiyongjine;
	}
	public double getJianmianjine() {
		return jianmianjine;
	}
	public void setJianmianjine(double jianmianjine) {
		this.jianmianjine = jianmianjine;
	}
	public Timestamp getQishishijian() {
		return qishishijian;
	}
	public void setQishishijian(Timestamp qishishijian) {
		this.qishishijian = qishishijian;
	}
	public Timestamp getJieshuriqi() {
		return jieshuriqi;
	}
	public void setJieshuriqi(Timestamp jieshuriqi) {
		this.jieshuriqi = jieshuriqi;
	}
	
	
}
