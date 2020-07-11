package itf;

import java.util.List;

import model.BeanManzhe;
import util.BaseException;

public interface IManzheManager {
	public List<BeanManzhe> loadAll() throws BaseException;
}
