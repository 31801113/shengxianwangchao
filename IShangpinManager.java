package itf;

import java.util.List;

import model.BeanShangpin;
import model.BeanShengxianleibie;
import util.BaseException;

public interface IShangpinManager {
	public List<BeanShangpin> loadShangpin(BeanShengxianleibie shengxianleibie) throws BaseException;
}
