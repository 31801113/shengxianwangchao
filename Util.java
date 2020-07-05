package starter;

import control.GuanliyuanManager;
import control.YonghuManager;
import itf.IGuanliyuanManager;
import itf.IYonghuManager;

public class Util {
	public static IGuanliyuanManager guanliyuanManager = new GuanliyuanManager();
	public static IYonghuManager yonghuManager = new YonghuManager();
}
