package com.amoy.pod.support.base;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
public interface BaseMapper<T> {

    int save(T t);

    int save(Map<String, Object> map);

    int saveBatch(List<T> list);

    int update(T t);

    int update(Map<String, Object> map);

    int delete(Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] ids);

    T queryObject(Object id);

    List<T> queryList(Map<String, Object> map);

    List<T> queryListByBean(T t);

    List<T> queryList(Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();
}
