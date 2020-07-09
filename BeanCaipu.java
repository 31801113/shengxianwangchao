package model;

public class BeanCaipu {
	public static final String[] tableTitles={"²ËÆ×±àºÅ","²ËÆ×Ãû³Æ","²ËÆ×ÓÃÁÏ","²½Öè","Í¼Æ¬"};
	public String getCell(int col){
		if(col==0) return "" + this.getCaipubianhao();
		else if(col==1) return "" + this.getCaipumingcheng();
		else if(col==2) return "" + this.getCaipuyongliao();
		else if(col==3) return "" + this.getBuzhou();
		else if(col==4) return "" + this.getTupian();
		else return "";
	}
	private int caipubianhao;
	private String caipumingcheng;
	private String caipuyongliao;
	private String buzhou;
	private String tupian;
	public int getCaipubianhao() {
		return caipubianhao;
	}
	public void setCaipubianhao(int caipubianhao) {
		this.caipubianhao = caipubianhao;
	}
	public String getCaipumingcheng() {
		return caipumingcheng;
	}
	public void setCaipumingcheng(String caipumingcheng) {
		this.caipumingcheng = caipumingcheng;
	}
	public String getCaipuyongliao() {
		return caipuyongliao;
	}
	public void setCaipuyongliao(String caipuyongliao) {
		this.caipuyongliao = caipuyongliao;
	}
	public String getBuzhou() {
		return buzhou;
	}
	public void setBuzhou(String buzhou) {
		this.buzhou = buzhou;
	}
	public String getTupian() {
		return tupian;
	}
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
}
