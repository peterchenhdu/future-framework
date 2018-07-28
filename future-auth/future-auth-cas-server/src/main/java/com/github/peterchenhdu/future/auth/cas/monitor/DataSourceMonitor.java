/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.monitor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Monitors a data source that describes a single connection or connection pool to a database.
 *
 * @author Marvin S. Addison
 * @since 3.5.1
 */
public class DataSourceMonitor extends AbstractPoolMonitor {

    @NotNull
    private final JdbcTemplate jdbcTemplate;

    @NotNull
    private String validationQuery;


    /**
     * Creates a new instance that monitors the given data source.
     *
     * @param dataSource Data source to monitor.
     */
    public DataSourceMonitor(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * Sets the validation query used to monitor the data source. The validation query should return
     * at least one result; otherwise results are ignored.
     *
     * @param query Validation query that should be as efficient as possible.
     */
    public void setValidationQuery(final String query) {
        this.validationQuery = query;
    }


    @Override
    protected StatusCode checkPool() throws Exception {
        return this.jdbcTemplate.query(this.validationQuery, new ResultSetExtractor<StatusCode>() {
            public StatusCode extractData(final ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return StatusCode.OK;
                }
                return StatusCode.WARN;
            }
        });
    }


    @Override
    protected int getIdleCount() {
        return PoolStatus.UNKNOWN_COUNT;
    }


    @Override
    protected int getActiveCount() {
        return PoolStatus.UNKNOWN_COUNT;
    }
}
