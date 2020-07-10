package model;

public class BeanShengxianleibie {
	public static final String[] tableTitles={"类别编号","类别名称","类别描述"};
	public String getCell(int col){
		if(col==0) return "" + this.getLeibiebianhao();
		else if(col==1) return "" + this.getLeibiemingcheng();
		else if(col==2) return "" + this.getLeibiemiaoshu();
		else return "";
	}
	private int leibiebianhao;
	private String leibiemingcheng;
	private String leibiemiaoshu;
	
	public int getLeibiebianhao() {
		return leibiebianhao;
	}
	public void setLeibiebianhao(int leibiebianhao) {
		this.leibiebianhao = leibiebianhao;
	}
	public String getLeibiemingcheng() {
		return leibiemingcheng;
	}
	public void setLeibiemingcheng(String leibiemingcheng) {
		this.leibiemingcheng = leibiemingcheng;
	}
	public String getLeibiemiaoshu() {
		return leibiemiaoshu;
	}
	public void setLeibiemiaoshu(String leibiemiaoshu) {
		this.leibiemiaoshu = leibiemiaoshu;
	}
	
}
