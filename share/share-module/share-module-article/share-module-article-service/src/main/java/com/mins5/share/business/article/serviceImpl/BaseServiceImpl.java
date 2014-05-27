package com.mins5.share.business.article.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.mins5.share.business.article.service.BaseService;
import com.mins5.share.common.dao.CrudDao;
import com.mins5.share.common.domain.DomainObject;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;
import com.mins5.share.spring.util.SpringContextUtil;

/**
 * service层实现类基类，提供基本的增删改方法
 * 
 * @param <T> entity
 * @param <C> 对应DAO接口
 * @author zhanglin
 * @since 2014年5月27日
 */

@Service
public class BaseServiceImpl<T extends DomainObject> implements BaseService<T> {

	private static final Log log = LogFactory.getLog(BaseServiceImpl.class);

	/**
	 * 约定(dao名称=实体+Dao) ArticleDao = Article+Dao 否则不能满足
	 * 
	 * @param t
	 * @author zhanglin
	 * @since 2014年5月27日
	 */
	@SuppressWarnings("rawtypes")
	private CrudDao getCurrentDao(T t) {
		CrudDao currentDao = (CrudDao) SpringContextUtil.getBean(firstCharLower(t.getClass().getSimpleName()));
		if (currentDao == null) {
			throw new NullPointerException("从Spring容器中查找Dao实例失败，请检查实体及对应Dao命名是否符合约定！");
		}
		return currentDao;
	}

	/**
	 * 格式化实体名称首字母小写 （spring中注册的dao实例第一个字母小写）
	 * 
	 * @param entityName
	 * @author zhanglin
	 * @since 2014年5月27日
	 */
	private String firstCharLower(String entityName) {
		char[] chars = new char[1];
		chars[0] = entityName.charAt(0);
		String temp = new String(chars);
		if (chars[0] >= 'A' && chars[0] <= 'Z') {
			return entityName.replaceFirst(temp, temp.toLowerCase()) + "Dao";
		} else {
			return entityName + "Dao";
		}
	}

	/**
	 * 通用实体检测类，可子类重写实现具体逻辑
	 * 
	 * @param t
	 * @author zhanglin
	 * @since 2014年5月26日
	 */
	protected boolean checkDomain(T t) {
		boolean result = true;
		if (t == null) {
			result = false;
		}
		return result;

	}

	@SuppressWarnings("unchecked")
	@Override
	public ReturnData<T> save(T t) {
		ReturnData<T> returnData = new ReturnData<T>();
		try {
			if (!checkDomain(t)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}

			if (getCurrentDao(t) != null) {
				int count = getCurrentDao(t).save(t);
				if (count != 1) {
					returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
					return returnData;
				}
				returnData.setResultData(t);
				returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
			}
		} catch (Exception e) {
			log.error("保存实体：[" + t.getClass().getName() + "] 异常：[" + e.toString() + "]");
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	/**
	 * 逻辑删除，只是修改状态
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReturnData<VOID> deleteById(T t) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if (t == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			getCurrentDao(t).update(t);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("删除实体:[" + t.getClass().getName() + "] 异常：[" + e.toString() + "]");
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReturnData<T> update(T t) {
		ReturnData<T> returnData = new ReturnData<T>();
		try {
			if (!checkDomain(t)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			getCurrentDao(t).update(t);
			returnData.setResultData(t);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("更新实体:[" + t.getClass().getName() + "] 异常：[" + e.toString() + "]");
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReturnData<T> findById(long id, T t) {
		ReturnData<T> returnData = new ReturnData<T>();
		try {
			if (id < 0) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			T entity = (T) (getCurrentDao(t).findById(id));
			returnData.setResultData(entity);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("根据ID查询实体:[" + t.getClass().getName() + "] 异常：[" + e.toString() + "]");
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

}
