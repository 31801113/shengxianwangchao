package starter;

import control.GuanliyuanManager;
import control.ShangpinManager;
import control.ShangpindingdanManager;
import control.ShengxianleibieManager;
import control.YonghuManager;
import itf.IGuanliyuanManager;
import itf.IShangpinManager;
import itf.IShangpindingdanManager;
import itf.IShengxianleibieManager;
import itf.IYonghuManager;

public class Util {
	public static IGuanliyuanManager guanliyuanManager = new GuanliyuanManager();
	public static IYonghuManager yonghuManager = new YonghuManager();
	public static IShangpindingdanManager ShangpindingdanManager = new ShangpindingdanManager();
	public static IShengxianleibieManager shengxianleibieManager = new ShengxianleibieManager();
	public static IShangpinManager shangpinManager = new ShangpinManager();
}
