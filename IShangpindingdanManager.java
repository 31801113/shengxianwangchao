package itf;

import java.util.List;

import model.BeanShangpindingdan;
import util.BaseException;

public interface IShangpindingdanManager {
	public List<BeanShangpindingdan> loadAll() throws BaseException;
}
