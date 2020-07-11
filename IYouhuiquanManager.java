package itf;

import java.util.List;

import model.BeanYouhuiquan;
import util.BaseException;

public interface IYouhuiquanManager {
	public List<BeanYouhuiquan> loadAll() throws BaseException;
}
