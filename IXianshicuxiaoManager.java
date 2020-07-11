package itf;

import java.util.List;

import model.BeanXianshicuxiao;
import util.BaseException;

public interface IXianshicuxiaoManager {
	public List<BeanXianshicuxiao> loadAll() throws BaseException;
}
