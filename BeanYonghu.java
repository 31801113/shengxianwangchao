package model;

import java.sql.Timestamp;

public class BeanYonghu {
	public static final String[] tableTitles={"用户编号","姓名","性别","密码","手机号码","邮箱","所在城市","注册时间","是否会员","会员截止日期"};
	public String getCell(int col){
		if(col==0) return "" + this.getYonghubianhao();
		else if(col==1) return "" + this.getXingming();
		else if(col==2) return "" + this.getXingbie();
		else if(col==3) return "" + this.getMima();
		else if(col==4) return "" + this.getShoujihaoma();
		else if(col==5) return "" + this.getYouxiang();
		else if(col==6) return "" + this.getSuozaichengshi();
		else if(col==7) return "" + this.getZhuceshijian();
		else if(col==8) return "" + this.getShifouhuiyuan();
		else if(col==9) return "" + this.getHuiyuanjiezhiriqi();
		else return "";
	}
	public static BeanYonghu currentLoginYonghu = null;
	private int yonghubianhao;
	private String xingming;
	private String xingbie;
	private String mima;
	private String shoujihaoma;
	private String youxiang;
	private String suozaichengshi;
	private Timestamp zhuceshijian;
	private int shifouhuiyuan;
	private Timestamp huiyuanjiezhiriqi;
	
	public int getYonghubianhao() {
		return yonghubianhao;
	}
	public void setYonghubianhao(int yonghubianhao) {
		this.yonghubianhao = yonghubianhao;
	}
	public String getXingming() {
		return xingming;
	}
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	public String getXingbie() {
		return xingbie;
	}
	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}
	public String getMima() {
		return mima;
	}
	public void setMima(String mima) {
		this.mima = mima;
	}
	public String getShoujihaoma() {
		return shoujihaoma;
	}
	public void setShoujihaoma(String shoujihaoma) {
		this.shoujihaoma = shoujihaoma;
	}
	public String getYouxiang() {
		return youxiang;
	}
	public void setYouxiang(String youxiang) {
		this.youxiang = youxiang;
	}
	public String getSuozaichengshi() {
		return suozaichengshi;
	}
	public void setSuozaichengshi(String suozaichengshi) {
		this.suozaichengshi = suozaichengshi;
	}
	public Timestamp getZhuceshijian() {
		return zhuceshijian;
	}
	public void setZhuceshijian(Timestamp zhuceshijian) {
		this.zhuceshijian = zhuceshijian;
	}
	public int getShifouhuiyuan() {
		return shifouhuiyuan;
	}
	public void setShifouhuiyuan(int shifouhuiyuan) {
		this.shifouhuiyuan = shifouhuiyuan;
	}
	public Timestamp getHuiyuanjiezhiriqi() {
		return huiyuanjiezhiriqi;
	}
	public void setHuiyuanjiezhiriqi(Timestamp huiyuanjiezhiriqi) {
		this.huiyuanjiezhiriqi = huiyuanjiezhiriqi;
	}
}
