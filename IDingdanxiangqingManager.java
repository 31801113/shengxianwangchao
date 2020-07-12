package itf;

import java.util.List;

import model.BeanDingdanxiangqing;
import model.BeanShangpindingdan;
import util.BaseException;

public interface IDingdanxiangqingManager {
	public List<BeanDingdanxiangqing> loadDingdanxiangqing(BeanShangpindingdan shangpindingdan) throws BaseException;
}
