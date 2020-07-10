package model;

public class BeanShangpin {
	public static final String[] tableTitles={"商品编号","商品名称","价格","会员价","数量","规格","详情","类别编号"};
	public String getCell(int col){
		if(col==0) return "" + this.getShangpinbianhao();
		else if(col==1) return "" + this.getShangpinmingcheng();
		else if(col==2) return "" + this.getJiage();
		else if(col==3) return "" + this.getHuiyuanjia();
		else if(col==4) return "" + this.getShuliang();
		else if(col==5) return "" + this.getGuige();
		else if(col==6) return "" + this.getXiangqing();
		else if(col==7) return "" + this.getLeibiebianhao();
		else return "";
	}
	private int shangpinbianhao;
	private String shangpinmingcheng;
	private double jiage;
	private double huiyuanjia;
	private double shuliang;
	private String guige;
	private String xiangqing;
	private int leibiebianhao;
	
	public int getLeibiebianhao() {
		return leibiebianhao;
	}
	public void setLeibiebianhao(int leibiebianhao) {
		this.leibiebianhao = leibiebianhao;
	}
	public int getShangpinbianhao() {
		return shangpinbianhao;
	}
	public void setShangpinbianhao(int shangpinbianhao) {
		this.shangpinbianhao = shangpinbianhao;
	}
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
	public double getHuiyuanjia() {
		return huiyuanjia;
	}
	public void setHuiyuanjia(double huiyuanjia) {
		this.huiyuanjia = huiyuanjia;
	}
	public double getShuliang() {
		return shuliang;
	}
	public void setShuliang(double shuliang) {
		this.shuliang = shuliang;
	}
	public String getGuige() {
		return guige;
	}
	public void setGuige(String guige) {
		this.guige = guige;
	}
	public String getXiangqing() {
		return xiangqing;
	}
	public void setXiangqing(String xiangqing) {
		this.xiangqing = xiangqing;
	}
}
