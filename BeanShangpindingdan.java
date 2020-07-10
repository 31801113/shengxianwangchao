package model;

import java.sql.Timestamp;

public class BeanShangpindingdan {
	public static final String[] tableTitles={"订单编号","原始金额","结算金额","使用优惠券编号","要求送达时间","配送地址编号","订单状态"};
	public String getCell(int col){
		if(col==0) return "" + this.getDingdanbianhao();
		else if(col==1) return "" + this.getYuanshijine();
		else if(col==2) return "" + this.getJiesuanjine();
		else if(col==3) return "" + this.getShiyongyouhuiquanbianhao();
		else if(col==4) return "" + this.getYaoqiusongdashijian();
		else if(col==5) return "" + this.getPeisongdizhibianhao();
		else if(col==6) return "" + this.getDingdanzhuangtai();
		else return "";
	}
	private int dingdanbianhao;
	private int yonghubianhao;
	private double yuanshijine;
	private double jiesuanjine;
	private int shiyongyouhuiquanbianhao;
	private Timestamp yaoqiusongdashijian;
	private int peisongdizhibianhao;
	private String dingdanzhuangtai;
	
	public int getDingdanbianhao() {
		return dingdanbianhao;
	}
	public void setDingdanbianhao(int dingdanbianhao) {
		this.dingdanbianhao = dingdanbianhao;
	}
	public int getYonghubianhao() {
		return yonghubianhao;
	}
	public void setYonghubianhao(int yonghubianhao) {
		this.yonghubianhao = yonghubianhao;
	}
	public double getYuanshijine() {
		return yuanshijine;
	}
	public void setYuanshijine(double yuanshijine) {
		this.yuanshijine = yuanshijine;
	}
	public double getJiesuanjine() {
		return jiesuanjine;
	}
	public void setJiesuanjine(double jiesuanjine) {
		this.jiesuanjine = jiesuanjine;
	}
	public int getShiyongyouhuiquanbianhao() {
		return shiyongyouhuiquanbianhao;
	}
	public void setShiyongyouhuiquanbianhao(int shiyongyouhuiquanbianhao) {
		this.shiyongyouhuiquanbianhao = shiyongyouhuiquanbianhao;
	}
	public Timestamp getYaoqiusongdashijian() {
		return yaoqiusongdashijian;
	}
	public void setYaoqiusongdashijian(Timestamp yaoqiusongdashijian) {
		this.yaoqiusongdashijian = yaoqiusongdashijian;
	}
	public int getPeisongdizhibianhao() {
		return peisongdizhibianhao;
	}
	public void setPeisongdizhibianhao(int peisongdizhibianhao) {
		this.peisongdizhibianhao = peisongdizhibianhao;
	}
	public String getDingdanzhuangtai() {
		return dingdanzhuangtai;
	}
	public void setDingdanzhuangtai(String dingdanzhuangtai) {
		this.dingdanzhuangtai = dingdanzhuangtai;
	}
	
}
