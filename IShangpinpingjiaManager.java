package itf;

import java.util.List;

import model.BeanShangpinpingjia;
import util.BaseException;

public interface IShangpinpingjiaManager {
	public List<BeanShangpinpingjia> loadAll() throws BaseException;
}
