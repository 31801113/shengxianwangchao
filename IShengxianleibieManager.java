package itf;

import java.util.List;

import model.BeanShengxianleibie;
import util.BaseException;

public interface IShengxianleibieManager {
	public List<BeanShengxianleibie> loadAll() throws BaseException;
}
