package itf;

import java.util.List;

import model.BeanYonghu;
import util.BaseException;

public interface IYonghuManager {
	public BeanYonghu reg(String yonghubianhao, String xingming, String xingbie,String mima,String querenmima,String shoujihaoma,String youxiang,String suozaichengshi,String shifouhuiyuan,String huiyuanjiezhishijian) throws BaseException;
	
	public BeanYonghu login(String yonghubianhao, String mima) throws BaseException;
	
	public List<BeanYonghu> loadAll() throws BaseException;
	
	public BeanYonghu changePwd(BeanYonghu user, String olddenglumima, String newdenglumima,
			String newdenglumima2) throws BaseException;
	
	public BeanYonghu Bianjigerenxinxi(String olddenglumima, String newdenglumima,
			String newdenglumima2) throws BaseException;
}
