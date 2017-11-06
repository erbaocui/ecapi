package com.cn.dao.impl;
import java.util.List;
import java.util.Map;

import com.cn.dao.BaseDao;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


@SuppressWarnings("rawtypes")
public class BaseDaoImpl extends SqlSessionDaoSupport implements BaseDao {

    //使用sqlSessionFactory
    @Autowired
    private  SqlSessionFactory sqlSessionFactory;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
    {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public void addObject(String statementName, Object obj)
            throws DataAccessException {
        getSqlSession().insert(statementName, obj);
    }

    @Override
    public int deleteObject(String statementName, String objId)
            throws DataAccessException {
        return getSqlSession().delete(statementName, objId);
    }

    @Override
    public int deleteForMap(String statementName,Map<String, Object> map) throws DataAccessException {
        return getSqlSession().delete(statementName, map);
    }

    @Override
    public Object findObject(String statementName, String objId)
            throws DataAccessException {
        return getSqlSession().selectOne(statementName, objId);
    }

    @Override
    public Object findObject(String statementName,Object entity)
            throws DataAccessException {
        return getSqlSession().selectOne(statementName, entity);
    }

    @Override
    public int updateObject(String statementName, Object obj)
            throws DataAccessException {
        return getSqlSession().update(statementName, obj);
    }

    @Override
    public int updateObjectState(String statementName, Map<String, Object> map)
            throws DataAccessException {
        return getSqlSession().update(statementName, map);
    }

    @Override
    public int getObjectCount(String statementName, String filter) {
        Long obj = getSqlSession().selectOne(statementName, filter);
        return obj.intValue();
    }

    @Override
    public int getObjectCount(String statementName, Map<String,Object> map) {
        Long obj = getSqlSession().selectOne(statementName, map);
        return obj.intValue();
    }

    @Override
    public Object findObject(String statementName, Map<String, Object> map) {
        return getSqlSession().selectOne(statementName, map);
    }

    @Override
    public List listByPage(String statementName, Map<String, Object> map,
                           int skipResults, int pageSize) {
        return getSqlSession().selectList(statementName, map, new RowBounds(skipResults, pageSize));
    }

    @Override
    public List listByPage(String statementName, String filter, int skipResults, int pageSize) {
        return getSqlSession().selectList(statementName, filter, new RowBounds(skipResults, pageSize));
    }

    @Override
    public List listByPage(String statementName, Object entity, int skipResults, int pageSize) {
        return getSqlSession().selectList(statementName, entity, new RowBounds(skipResults, pageSize));
    }

    @Override
    public List list(String statementName, String filter) {
        return getSqlSession().selectList(statementName, filter);
    }




    @Override
    public List list(String statementName, Map<String, Object> map) {
        return getSqlSession().selectList(statementName, map);
    }

    @Override
    public List list(String statementName, Object entity) {
        return getSqlSession().selectList(statementName, entity);
    }
}