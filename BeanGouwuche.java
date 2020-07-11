package model;

public class BeanGouwuche {
	public static final String[] tableTitles={"购物车编号","用户编号","商品编号","商品名称","用户序号","数量","价格"};
	
	public String getCell(int col){
		if(col==0) return "" + this.getGouwuchebianhao();
		else if(col==1) return "" + this.getYonghubianhao();
		else if(col==2) return "" + this.getShangpinbianhao();
		else if(col==3) return "" + this.getShangpinmingcheng();
		else if(col==4) return "" + this.getYonghuorder();
		else if(col==5) return "" + this.getShuliang();
		else if(col==6) return "" + this.getJiage();
		else return "";
	}
	
	private int gouwuchebianhao;
	private int yonghubianhao;
	private int shangpinbianhao;
	private String shangpinmingcheng;
	private int yonghuorder;
	private double shuliang;
	private double jiage;
	
	public String getShangpinmingcheng() {
		return shangpinmingcheng;
	}
	public void setShangpinmingcheng(String shangpinmingcheng) {
		this.shangpinmingcheng = shangpinmingcheng;
	}
	public double getJiage() {
		return jiage;
	}
	public void setJiage(double jiage) {
		this.jiage = jiage;
	}
	public int getGouwuchebianhao() {
		return gouwuchebianhao;
	}
	public void setGouwuchebianhao(int gouwuchebianhao) {
		this.gouwuchebianhao = gouwuchebianhao;
	}
	public int getYonghubianhao() {
		return yonghubianhao;
	}
	public void setYonghubianhao(int yonghubianhao) {
		this.yonghubianhao = yonghubianhao;
	}
	public int getShangpinbianhao() {
		return shangpinbianhao;
	}
	public void setShangpinbianhao(int shangpinbianhao) {
		this.shangpinbianhao = shangpinbianhao;
	}
	public int getYonghuorder() {
		return yonghuorder;
	}
	public void setYonghuorder(int yonghuorder) {
		this.yonghuorder = yonghuorder;
	}
	public double getShuliang() {
		return shuliang;
	}
	public void setShuliang(double shuliang) {
		this.shuliang = shuliang;
	}
}
