package model;

public class BeanDingdanxiangqing {
	public static final String[] tableTitles={"订单编号","商品编号","数量","价格","折扣","满折编号"};
	public String getCell(int col){
		if(col==0) return "" + this.getDingdanbianhao();
		else if(col==1) return "" + this.getShangpinbianhao();
		else if(col==2) return "" + this.getShuliang();
		else if(col==3) return "" + this.getJiage();
		else if(col==4) return "" + this.getZhekou();
		else if(col==5) return "" + this.getManzhebianhao();
		else return "";
	}
	private int dingdanbianhao;
	private int shangpinbianhao;
	private double shuliang;
	private double jiage;
	private double zhekou;
	private int manzhebianhao;
	
	public int getDingdanbianhao() {
		return dingdanbianhao;
	}
	public void setDingdanbianhao(int dingdanbianhao) {
		this.dingdanbianhao = dingdanbianhao;
	}
	public int getShangpinbianhao() {
		return shangpinbianhao;
	}
	public void setShangpinbianhao(int shangpinbianhao) {
		this.shangpinbianhao = shangpinbianhao;
	}
	public double getShuliang() {
		return shuliang;
	}
	public void setShuliang(double shuliang) {
		this.shuliang = shuliang;
	}
	public double getJiage() {
		return jiage;
	}
	public void setJiage(double jiage) {
		this.jiage = jiage;
	}
	public double getZhekou() {
		return zhekou;
	}
	public void setZhekou(double zhekou) {
		this.zhekou = zhekou;
	}
	public int getManzhebianhao() {
		return manzhebianhao;
	}
	public void setManzhebianhao(int manzhebianhao) {
		this.manzhebianhao = manzhebianhao;
	}
}
