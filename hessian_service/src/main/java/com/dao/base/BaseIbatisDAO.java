package com.dao.base;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.dao.DataAccessException;

/**
 * Ibatis型dao基类
 */
public class BaseIbatisDAO extends SqlSessionTemplateDaoSupport {

    private static final Log LOG = LogFactory.getLog(BaseIbatisDAO.class);

    private Integer maxRows = 1001;
    private Integer maxRowsForReport = 50000;
    private String namespaceName;

    public BaseIbatisDAO() {

    }

    public BaseIbatisDAO(String namespaceName) {
        this.namespaceName = namespaceName;

    }

    private String createStatementName(String id) {
        return namespaceName + "." + id;
    }

    protected Object insert(String key, Object object) {
        this.getSqlSession().insert(createStatementName(key), object);
        return object;
    }

    protected Integer update(String key, Object object) {
        if (object != null) {
            return getSqlSession().update(createStatementName(key), object);
        }
        return 0;
    }

    protected Integer delete(String key, Serializable id) {
        if (id != null) {
            return getSqlSession().delete(createStatementName(key), id);
        }
        return 0;
    }

    protected Integer delete(String key, Object object) {
        if (object != null) {
            return getSqlSession().delete(createStatementName(key), object);
        }
        return 0;
    }


    /**
     * 重载一个无参数的get方法，供vst_search使用
     *
     * @param key
     * @return
     * @author wenzhengtao
     */
    @SuppressWarnings({"unchecked"})
    protected <T> T get(String key) {
        return (T) getSqlSession().selectOne(createStatementName(key));
    }

    protected <T> T getOne(String key ,Object params){
        if (params == null)
            return null;
        return getSqlSession().selectOne(key,params);
    }

    protected <T> List<T> getList(String key) {
        return getSqlSession().selectList(createStatementName(key));
    }

    protected <T> List<T> getList(String key, Object params) {
        if (params != null) {
            return getSqlSession().selectList(createStatementName(key), params);
        } else {
            return null;
        }
    }

    // 允许参数传入null
    protected <T> List<T> getListFree(String key, Object params) {
        return getSqlSession().selectList(createStatementName(key), params);
    }

    protected <T> List<T> queryForList(String statementName) throws DataAccessException {
        return queryForList(statementName, null);
    }

    protected <T> List<T> queryForList(final String statementName, final Object parameterObject) throws DataAccessException {
        if (parameterObject != null) {
            List<T> result = getSqlSession().selectList(createStatementName(statementName), parameterObject, new RowBounds(0, maxRows));
            if ((result != null) && (result.size() == maxRows)) {
                LOG.warn("SQL Exception: result size is greater than the max rows " + statementName);
            }
            return result;
        } else {
            return null;
        }
    }

    protected <T> List<T> queryForList(String statementName, Integer skipResults, Integer maxResults) throws DataAccessException {

        if ((maxResults - skipResults) >= maxRows) {
            maxResults = skipResults + maxRows;
            LOG.warn("SQL Exception: result size is greater than the max rows, " + createStatementName(statementName));
        }

        return queryForList(statementName, null, skipResults, maxResults);
    }

    protected <T> List<T> queryForList(final String statementName, final Object parameterObject, final Integer skipResults, final Integer maxResults) throws DataAccessException {

        Integer tempMaxResults = maxResults;
        if ((maxResults - skipResults) >= maxRows) {
            tempMaxResults = skipResults + maxRows;
            LOG.warn("SQL Exception: result size is greater than the max rows, " + createStatementName(statementName));
        }
        return getSqlSession().selectList(createStatementName(statementName), parameterObject, new RowBounds(skipResults, tempMaxResults));
    }

//    // 数据量比较大的报表导出请用这个接口
//    protected <T> List<T> queryForListForReport(String statementName) throws DataAccessException {
//        return queryForListForReport(statementName, null);
//    }
//
//    // 数据量比较大的报表导出请用这个接口
//    protected <T> List<T> queryForListForReport(final String statementName, final Object parameterObject) throws DataAccessException {
//        List<T> result = getSqlSession().selectList(createStatementName(statementName), parameterObject, new RowBounds(0, maxRowsForReport));
//
//        if ((result != null) && (result.size() == maxRowsForReport)) {
//            LOG.warn("SQL Exception: result size is greater than the max rows, " + statementName);
//        }
//        return result;
//    }
//
//    // 数据量比较大的报表导出请用这个接口
//    protected <T> List<T> queryForList(final String statementName, final Object parameterObject, final boolean isForReportExport) throws DataAccessException {
//
//        Integer maxRowsTemp = maxRows;
//        if (isForReportExport) {
//            maxRowsTemp = maxRowsForReport;
//        }
//        List<T> result = getSqlSession().selectList(createStatementName(statementName), parameterObject, new RowBounds(0, maxRowsTemp));
//        if ((result != null) && (result.size() == maxRowsTemp)) {
////            LOG.warn("SQL Exception: result size is greater than the max rows, " + statementName);
//        }
//        return result;
//    }

}
