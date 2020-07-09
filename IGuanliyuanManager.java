package itf;

import model.BeanGuanliyuan;
import util.BaseException;

public interface IGuanliyuanManager {
	public BeanGuanliyuan reg(String yuangongbianhao, String yuangongxingming, String denglumima,String querenmima) throws BaseException;
	
	public BeanGuanliyuan login(String yuangongbianhao, String denglumima) throws BaseException;
	
	public BeanGuanliyuan changePwd(BeanGuanliyuan user, String olddenglumima, String newdenglumima,
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
	
	public BeanGuanliyuan ShanchuShangpin(String shangpinbianhao, String shangpinmingcheng)throws BaseException;
	
	public BeanGuanliyuan BianjiShangpin(String shangpinbianhao, String shangpinmingcheng, String jiage,String huiyuanjia, String shuliang , String guige, String xiangqing, String leibiebianhao)throws BaseException;
	
	public BeanGuanliyuan ChaxunShangpin(String shangpinbianhao, String shangpinmingcheng)throws BaseException;
	
	public BeanGuanliyuan ZengjiaYouhuiquan(String youhuiquanbianhao, String neirong,String shiyongjine,String jianmianjine,String qishiriqi,String jiezhiriqi)throws BaseException;
	
	public BeanGuanliyuan ShanchuYouhuiquan(String youhuiquanbianhao)throws BaseException;
	
	public BeanGuanliyuan BianjiYouhuiquan(String youhuiquanbianhao, String neirong,String shiyongjine,String jianmianjine,String qishiriqi,String jieshuriqi)throws BaseException;
	
	public BeanGuanliyuan ChaxunYouhuiquan(String youhuiquanbianhao)throws BaseException;
	
	public BeanGuanliyuan ZengjiaManzhe(String manzhebianhao, String neirong,String shiyongshangpinshuliang,String zhekou,String qishiriqi,String jieshuriqi)throws BaseException;
	
	public BeanGuanliyuan ShanchuManzhe(String manzhebianhao)throws BaseException;
	
	public BeanGuanliyuan BianjiManzhe(String manzhebianhao, String neirong,String shiyongshangpinshuliang,String zhekou,String qishiriqi,String jieshuriqi)throws BaseException;
	
	public BeanGuanliyuan ChaxunManzhe(String manzhebianhao)throws BaseException;
	
	public BeanGuanliyuan ZengjiaXianshicuxiao(String cuxiaobianhao, String shangpinbianhao,String cuxiaojiage,String cuxiaoshuliang,String qishiriqi,String jieshuriqi)throws BaseException;
	
	public BeanGuanliyuan ShanchuXianshicuxiao(String cuxiaobianhao, String shangpinbianhao)throws BaseException;
	
	public BeanGuanliyuan BianjiXianshicuxiao(String cuxiaobianhao, String shangpinbianhao,String cuxiaojiage,String cuxiaoshuliang,String qishiriqi,String jieshuriqi)throws BaseException;
	
	public BeanGuanliyuan ChaxunXianshicuxiao(String cuxiaobianhao, String shangpinbianhao)throws BaseException;
	
	public BeanGuanliyuan ZengjiaCaipu(String caipubianhao,String caipumingcheng,String caipuyongliao,String buzhou,String tupian)throws BaseException;
	
	public BeanGuanliyuan ShanchuCaipu(String caipubianhao,String caipumingcheng)throws BaseException;
	
	public BeanGuanliyuan BainjiCaipu(String caipubianhao,String caipumingcheng,String caipuyongliao,String buzhou,String tupian)throws BaseException;
	
	public BeanGuanliyuan ChaxunCaipu(String caipubianhao,String caipumingcheng)throws BaseException;
	
	public BeanGuanliyuan ZengjiaCaigou(String caigoudanbianhao,String shicaibianhao,String shuliang,String zhuangtai)throws BaseException;
	
	public BeanGuanliyuan ShanchuCaigou(String caigoudanbianhao,String shicaibianhao)throws BaseException;
	
	public BeanGuanliyuan BianjiCaigou(String caigoudanbianhao,String shicaibianhao,String shuliang,String zhuangtai)throws BaseException;
	
	public BeanGuanliyuan ChaxunCaigou(String caigoudanbianhao,String shicaibianhao)throws BaseException;
}
