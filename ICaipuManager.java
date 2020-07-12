package itf;

import java.util.List;

import model.BeanCaipu;
import util.BaseException;

public interface ICaipuManager {
	public List<BeanCaipu> loadAll() throws BaseException;
}
