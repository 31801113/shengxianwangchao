package model;

public class BeanGuanliyuan {
	public static BeanGuanliyuan currentLoginGuanliyuan=null;
	private int yuangongbianhao;
	private String yuangongxingming;
	private String denglumima;
	
	public int getYuangongbianhao() {
		return yuangongbianhao;
	}
	public void setYuangongbianhao(int yuangongbianhao) {
		this.yuangongbianhao = yuangongbianhao;
	}
	public String getYuangongxingming() {
		return yuangongxingming;
	}
	public void setYuangongxingming(String yuangongxingming) {
		this.yuangongxingming = yuangongxingming;
	}
	public String getDenglumima() {
		return denglumima;
	}
	public void setDenglumima(String denglumima) {
		this.denglumima = denglumima;
	}
}
