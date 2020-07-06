package itf;

import model.BeanGuanliyuan;
import util.BaseException;

public interface IGuanliyuanManager {
	public BeanGuanliyuan reg(String yuangongbianhao, String yuangongxingming, String denglumima,String querenmima) throws BaseException;
	
	public BeanGuanliyuan login(String yuangongbianhao, String denglumima) throws BaseException;
	
	public void changePwd(BeanGuanliyuan user, String olddenglumima, String newdenglumima,
			String newdenglumima2) throws BaseException;
	
	public BeanGuanliyuan ZengjiaYonghu(String yonghubianhao, String xingming, String xingbie,String mima,String shoujihaoma,String youxiang,String suozaichengshi,String shifouhuiyuan,String huiyuanjiezhishijian) throws BaseException;
	
	public BeanGuanliyuan ShanchuYonghu(String yonghubianhao,String xingming,String mima) throws BaseException;
	
	public BeanGuanliyuan BianjiYonghu(String yonghubianhao, String xingming, String xingbie,String mima,String shoujihaoma,String youxiang,String suozaichengshi,String shifouhuiyuan,String huiyuanjiezhishijian) throws BaseException;
	
	public BeanGuanliyuan ChaxunYonghu(String yonghubianhao, String xingming)throws BaseException;
	
	public BeanGuanliyuan ZengjiaShengxianleibie(String leibiebianhao,String leibiemingcheng,String leibiemiaoshu) throws BaseException;
	
	public BeanGuanliyuan ShanchuShengxianleibie(String leibiebianhao) throws BaseException;
	
	public BeanGuanliyuan BianjiShengxianleibie(String leibiebianhao,String leibiemingcheng,String leibiemiaoshu) throws BaseException;
	
	public BeanGuanliyuan ChaxunShengxianleibie(String leibiebianhao, String leibiemingcheng)throws BaseException;
	
	public BeanGuanliyuan ZengjiaShangpin(String shangpinbianhao, String shangpinmingcheng, String jiage,String huiyuanjia, String shuliang , String guige, String xiangqing, String leibiebianhao)throws BaseException;
}
