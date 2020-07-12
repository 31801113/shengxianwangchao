package itf;

import java.util.List;

import model.BeanGouwuche;
import model.BeanShangpin;
import model.BeanYonghu;
import util.BaseException;

public interface IYonghuManager {
	public BeanYonghu reg(String yonghubianhao, String xingming, String xingbie,String mima,String querenmima,String shoujihaoma,String youxiang,String suozaichengshi,String shifouhuiyuan,String huiyuanjiezhishijian) throws BaseException;
	
	public BeanYonghu login(String yonghubianhao, String mima) throws BaseException;
	
	public List<BeanYonghu> loadAll() throws BaseException;
	
	public BeanYonghu changePwd(BeanYonghu user, String olddenglumima, String newdenglumima,
			String newdenglumima2) throws BaseException;
	
	public BeanYonghu Bianjigerenxinxi(String xingming, String xingbie,String shoujihao,String youxiang,String suozaichengshi) throws BaseException;
	
	public BeanYonghu Bianjipeisongdizhi(String sheng, String shi,String qu,String dizhi,String lianxiren,String dianhua) throws BaseException;
	
	public BeanYonghu Jiarugouwuche(BeanShangpin shangpin, String shuliang) throws BaseException;
	
	public BeanYonghu Quxiaogoumai(BeanGouwuche Gouwuche) throws BaseException;
	
	public BeanYonghu Fukuangoumai(List<BeanGouwuche> allGouwuche,String songdashijian) throws BaseException;
	
	public BeanYonghu Zengjiapingjia(String shangpinbianhao,String pingjianeirong,String xingji,String zhaopian) throws BaseException;
	
	public BeanYonghu Bianjipingjia(String shangpinbianhao,String pingjianeirong,String xingji,String zhaopian) throws BaseException;
	
	public BeanYonghu Shanchupingjia(String shangpinbianhao) throws BaseException;
	
	public BeanYonghu Xiugaishuliang(BeanGouwuche Gouwuche,String shuliang) throws BaseException;
	
	public BeanYonghu Chengweihuiyuan(BeanYonghu user) throws BaseException;
}
