package ru.sudakovoleg.dal.dao.impl;

import ru.sudakovoleg.dal.dao.MetricDAO;
import ru.sudakovoleg.dal.metric.MetricEntity;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Oleg on 06.05.2016.
 */
public class JdbcMetricDAO implements MetricDAO{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public MetricEntity getMetricById(Long id){
        String sql = "SELECT * FROM metric_type WHERE ID = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            MetricEntity metric = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                metric = new MetricEntity(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getLong("DATA_TYPE_ID")
                );
            }
            rs.close();
            ps.close();
            return metric;
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

        finally{
            if (conn != null){
                try{
                    conn.close();
                } catch(SQLException e){}
            }
        }
    }

}
