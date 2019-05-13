/**
 * 项目名称:91营销云
 * 文件名：BaseJdbcDao.java
 * author:Administrator
 * 版本信息：
 * 日期：2015-12-14
 * Copyright 颢云科技 2015 版权所有
 */
package com.britecloud.marketingcloud.core.dao.jdbc;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.britecloud.marketingcloud.consants.pay.CommonConstants;
import com.britecloud.marketingcloud.dao.impl.pay.AccessConfDaoImpl;
import org.apache.commons.lang.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.lob.LobHandler;

import com.britecloud.marketingcloud.utils.SqlUtils;

public class BaseJdbcDao extends NamedParameterJdbcDaoSupport implements OracleSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        this.setDataSource(dataSource);
    }

    private LobHandler defaultLobHandler;

    private DataFieldMaxValueIncrementer dataFieldMaxValueIncrementer;

    public DataFieldMaxValueIncrementer getDataFieldMaxValueIncrementer() {
        return dataFieldMaxValueIncrementer;
    }

    public void setDataFieldMaxValueIncrementer(DataFieldMaxValueIncrementer dataFieldmaxValueIncrementer) {
        this.dataFieldMaxValueIncrementer = dataFieldmaxValueIncrementer;
    }

    public LobHandler getDefaultLobHandler() {
        return defaultLobHandler;
    }

    public void setDefaultLobHandler(LobHandler defaultLobHandler) {
        this.defaultLobHandler = defaultLobHandler;
    }

    public Long getNextLongID() {
        return new Long(getDataFieldMaxValueIncrementer().nextLongValue());
    }

    public String getPaginationString(String sql, Integer start, Integer limit) {
        if (this instanceof DB2Support) {
            return DB2Utils.getPaginationString(sql, start, limit);
        } else if (this instanceof OracleSupport) {
            return OracleUtils.getPaginationString(sql, start, limit);
        } else if (this instanceof SQLServerSupport) {
            return SQLServerUtils.getPaginationString(sql, start, limit);
        } else if (this instanceof MySqlSupport) {
            return MySqlUtils.getPaginationString(sql, start, limit);
        }
        return null;
    }

    public String getRemotePaginationString(String sql, Integer start, Integer limit, String sort, String dir) {
        if (sort != null && dir != null) {
            //判断去除原排序字段
            int sortIndex = sql.lastIndexOf("ORDER BY");
            if (sortIndex == -1)
                sortIndex = sql.lastIndexOf("order by");
            if (sortIndex != -1) {
                String sortStr = sql.substring(sortIndex);
                if (sortStr.indexOf("FROM ") == -1 && sortStr.indexOf("AND ") == -1) {
                    sql = sql.substring(0, sortIndex) + " order by " + sort + " " + dir;
                } else {
                    sql = "select * from (" + sql + ") as name order by " + sort + " " + dir;
                }
            } else {
                sql = sql + " order by " + sort + " " + dir;
            }

        }
        if (start != null && limit != null) {
            sql = getPaginationString(sql, start, limit);
        }
        return sql;
    }

    public String getTotalCountString(String sql) {
        return "SELECT COUNT(*) FROM (" + sql + ") CDD ";
    }

    public String loadSQL(String sqlId) {

        String simpleClassName = ClassUtils.getShortClassName(this.getClass().getName());
        String className =
                this.getClass().getPackage().getName().replace('.', '/') + "/sql/" + simpleClassName
                        + ".sql";
        String sql = SqlUtils.loadSQL(className, sqlId);
        if (sql == null) {
            simpleClassName = ClassUtils.getShortClassName(this.getClass().getSuperclass().getName());
            className =
                    this.getClass().getSuperclass().getPackage().getName().replace('.', '/') + "/sql/" + simpleClassName
                            + ".sql";
            sql = SqlUtils.loadSQL(className, sqlId);
        }
        return sql;
    }

    public String loadSQL(String sqlId, String packageName) {
        String suffix = ".sql";
        char classSeparator = '.';
        char catalogSeparator = '/';
        String simpleClassName = ClassUtils.getShortClassName(this.getClass().getName());
        String classPath = this.getClass().getPackage().getName();
        classPath = classPath.substring(0, classPath.lastIndexOf(classSeparator));
        String sqlSuffix = "/sql/" + packageName + "/" + simpleClassName + suffix;
        String className = classPath.replace(classSeparator, catalogSeparator) + sqlSuffix;

        String sql = SqlUtils.loadSQL(className, sqlId);
        if (sql == null) {
            simpleClassName = ClassUtils.getShortClassName(this.getClass().getSuperclass().getName());
            sqlSuffix = "/sql/" + packageName + "/" + simpleClassName + suffix;
            classPath = this.getClass().getSuperclass().getPackage().getName();
            classPath = classPath.substring(0, classPath.lastIndexOf(classSeparator));
            className = classPath.replace(classSeparator, catalogSeparator) + sqlSuffix;
            sql = SqlUtils.loadSQL(className, sqlId);
        }
        return sql;
    }

    public String loadSQL(String sqlId, Map parameters) {
        String simpleClassName = ClassUtils.getShortClassName(this.getClass().getName());
        String className =
                this.getClass().getPackage().getName().replace('.', '/') + "/sql/" + simpleClassName
                        + ".sql";
        String sql = SqlUtils.loadSQL(className, sqlId, parameters);
        if (sql == null) {
            simpleClassName = ClassUtils.getShortClassName(this.getClass().getSuperclass().getName());
            className =
                    this.getClass().getSuperclass().getPackage().getName().replace('.', '/') + "/sql/" + simpleClassName
                            + ".sql";
            sql = SqlUtils.loadSQL(className, sqlId, parameters);
        }
        return SqlUtils.loadSQL(className, sqlId, parameters);
    }

    /**
     * @param sqlId
     * @param parameters
     * @param packageName 包名
     * @return
     */
    public String loadSQL(String sqlId, Map parameters, String packageName) {
        String simpleClassName = ClassUtils.getShortClassName(this.getClass().getName());
        String classPath = this.getClass().getPackage().getName();
        classPath = classPath.substring(0, classPath.lastIndexOf("."));
        String className =
                classPath.replace('.', '/') + "/sql/" + packageName + "/" + simpleClassName
                        + ".sql";
        String sql = SqlUtils.loadSQL(className, sqlId, parameters);
        if (sql == null) {
            simpleClassName = ClassUtils.getShortClassName(this.getClass().getSuperclass().getName());
            classPath = this.getClass().getSuperclass().getPackage().getName();
            classPath = classPath.substring(0, classPath.lastIndexOf("."));
            className =
                    classPath.replace('.', '/') + "/sql/" + packageName + "/" + simpleClassName
                            + ".sql";
            sql = SqlUtils.loadSQL(className, sqlId, parameters);
        }
        return SqlUtils.loadSQL(className, sqlId, parameters);
    }


    public String getMaxCode(String table, String columnPrefix, int level, Long parentId) {
        String sql = null;
        if (parentId == null || parentId.longValue() == 0) {
            sql =
                    "select max(" + columnPrefix + "_CODE) from " + table + " where " + columnPrefix + "_LEVEL=" + level
                            + " and " + columnPrefix + "_PARE_ID is null";
        } else {
            sql =
                    "select max(" + columnPrefix + "_CODE) from " + table + " where " + columnPrefix + "_LEVEL=" + level
                            + " and " + columnPrefix + "_PARE_ID=" + parentId;
        }

        return (String) getJdbcTemplate().queryForObject(sql, String.class);
    }
}
