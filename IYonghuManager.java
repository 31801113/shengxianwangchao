package itf;

import model.BeanYonghu;
import util.BaseException;

public interface IYonghuManager {
	public BeanYonghu reg(String yonghubianhao, String xingming, String xingbie,String mima,String querenmima,String shoujihaoma,String youxiang,String suozaichengshi,String shifouhuiyuan,String huiyuanjiezhishijian) throws BaseException;
	public BeanYonghu login(String yonghubianhao, String mima) throws BaseException;
}
