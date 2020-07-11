package model;

import java.sql.Timestamp;

public class BeanXianshicuxiao {
	public static final String[] tableTitles={"促销编号","商品编号","促销价格","促销数量","起始日期","结束日期"};
	public String getCell(int col){
		if(col==0) return "" + this.getCuxiaobianhao();
		else if(col==1) return "" + this.getShangpinbianhao();
		else if(col==2) return "" + this.getCuxiaojiage();
		else if(col==3) return "" + this.getCuxiaoshuliang();
		else if(col==4) return "" + this.getQishiriqi();
		else if(col==5) return "" + this.getJieshuriqi();
		else return "";
	}
	private int cuxiaobianhao;
	private int shangpinbianhao;
	private double cuxiaojiage;
	private double cuxiaoshuliang;
	private Timestamp qishiriqi;
	private Timestamp jieshuriqi;
	
	public int getCuxiaobianhao() {
		return cuxiaobianhao;
	}
	public void setCuxiaobianhao(int cuxiaobianhao) {
		this.cuxiaobianhao = cuxiaobianhao;
	}
	public int getShangpinbianhao() {
		return shangpinbianhao;
	}
	public void setShangpinbianhao(int shangpinbianhao) {
		this.shangpinbianhao = shangpinbianhao;
	}
	public double getCuxiaojiage() {
		return cuxiaojiage;
	}
	public void setCuxiaojiage(double cuxiaojiage) {
		this.cuxiaojiage = cuxiaojiage;
	}
	public double getCuxiaoshuliang() {
		return cuxiaoshuliang;
	}
	public void setCuxiaoshuliang(double cuxiaoshuliang) {
		this.cuxiaoshuliang = cuxiaoshuliang;
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
