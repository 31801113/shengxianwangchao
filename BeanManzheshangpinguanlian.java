package model;

import java.sql.Timestamp;

public class BeanManzheshangpinguanlian {
	private int manzhebianhao;
	private int shangpinbianhao;
	private Timestamp qishiriqi;
	private Timestamp jieshuriqi;
	
	public int getManzhebianhao() {
		return manzhebianhao;
	}
	public void setManzhebianhao(int manzhebianhao) {
		this.manzhebianhao = manzhebianhao;
	}
	public int getShangpinbianhao() {
		return shangpinbianhao;
	}
	public void setShangpinbianhao(int shangpinbianhao) {
		this.shangpinbianhao = shangpinbianhao;
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
