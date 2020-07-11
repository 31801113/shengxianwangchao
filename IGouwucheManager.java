package itf;

import java.util.List;

import model.BeanGouwuche;
import util.BaseException;

public interface IGouwucheManager {
	public List<BeanGouwuche> loadAll() throws BaseException;
}
