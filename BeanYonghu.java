package model;

import java.sql.Timestamp;

public class BeanYonghu {
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
