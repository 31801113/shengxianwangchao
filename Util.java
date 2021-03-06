package starter;

import control.GuanliyuanManager;
import control.ManzheManager;
import control.ShangpinManager;
import control.ShangpindingdanManager;
import control.ShangpinpingjiaManager;
import control.ShengxianleibieManager;
import control.XianshicuxiaoManager;
import control.YonghuManager;
import control.YouhuiquanManager;
import control.CaipuManager;
import control.DingdanxiangqingManager;
import control.GouwucheManager;
import itf.ICaipuManager;
import itf.IDingdanxiangqingManager;
import itf.IGouwucheManager;
import itf.IGuanliyuanManager;
import itf.IManzheManager;
import itf.IShangpinManager;
import itf.IShangpindingdanManager;
import itf.IShangpinpingjiaManager;
import itf.IShengxianleibieManager;
import itf.IXianshicuxiaoManager;
import itf.IYonghuManager;
import itf.IYouhuiquanManager;

public class Util {
	public static IGuanliyuanManager guanliyuanManager = new GuanliyuanManager();
	public static IYonghuManager yonghuManager = new YonghuManager();
	public static IShangpindingdanManager ShangpindingdanManager = new ShangpindingdanManager();
	public static IShengxianleibieManager shengxianleibieManager = new ShengxianleibieManager();
	public static IShangpinManager shangpinManager = new ShangpinManager();
	public static IGouwucheManager gouwucheManager = new GouwucheManager();
	public static IYouhuiquanManager youhuiquanManager = new YouhuiquanManager();
	public static IManzheManager manzheManager = new ManzheManager();
    public static IXianshicuxiaoManager xianshicuxiaoManager = new XianshicuxiaoManager();
    public static IShangpinpingjiaManager shangpinpingjiaManager = new ShangpinpingjiaManager();
    public static ICaipuManager caipuManager = new CaipuManager();
    public static IDingdanxiangqingManager dingdanxiangqingManager = new DingdanxiangqingManager();
}
