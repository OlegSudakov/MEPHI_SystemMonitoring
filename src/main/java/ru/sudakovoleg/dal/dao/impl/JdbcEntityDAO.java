package ru.sudakovoleg.dal.dao.impl;

import ru.sudakovoleg.dal.dao.EntityDAO;
import ru.sudakovoleg.dal.measure.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Oleg on 05.05.2016.
 */
public class JdbcEntityDAO implements EntityDAO {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ArrayList<BooleanMeasureEntity> getLastNBooleans(Long id, int num) {

        String sql = "SELECT * FROM boolean_measure WHERE METRIC_ID = ? ORDER BY TIME DESC LIMIT ?";
        Connection conn = null;
        ArrayList<BooleanMeasureEntity> result = new ArrayList<BooleanMeasureEntity>();

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setInt(2, num);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                BooleanMeasureEntity measure = new BooleanMeasureEntity(rs.getLong("METRIC_ID"), rs.getTimestamp("TIME"),
                        rs.getBoolean("VALUE"));
                result.add(measure);
            }
            rs.close();
            ps.close();
            return result;
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

        finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch(SQLException e) {};
            }
        }
    }

    public ArrayList<DoubleMeasureEntity> getLastNDoubles(Long id, int num) {

        String sql = "SELECT * FROM double_measure WHERE METRIC_ID = ? ORDER BY TIME DESC LIMIT ?";
        Connection conn = null;
        ArrayList<DoubleMeasureEntity> result = new ArrayList<DoubleMeasureEntity>();

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setInt(2, num);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DoubleMeasureEntity measure = new DoubleMeasureEntity(rs.getLong("METRIC_ID"), rs.getTimestamp("TIME"),
                        rs.getDouble("VALUE"));
                result.add(measure);
            }
            rs.close();
            ps.close();
            return result;
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

        finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch(SQLException e) {};
            }
        }
    }

    public ArrayList<FloatMeasureEntity> getLastNFloats(Long id, int num) {

        String sql = "SELECT * FROM float_measure WHERE METRIC_ID = ? ORDER BY TIME DESC LIMIT ?";
        Connection conn = null;
        ArrayList<FloatMeasureEntity> result = new ArrayList<FloatMeasureEntity>();

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setInt(2, num);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                FloatMeasureEntity measure = new FloatMeasureEntity(rs.getLong("METRIC_ID"), rs.getTimestamp("TIME"),
                        rs.getFloat("VALUE"));
                result.add(measure);
            }
            rs.close();
            ps.close();
            return result;
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

        finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch(SQLException e) {};
            }
        }
    }

    public ArrayList<IntegerMeasureEntity> getLastNIntegers(Long id, int num) {

        String sql = "SELECT * FROM integer_measure WHERE METRIC_ID = ? ORDER BY TIME DESC LIMIT ?";
        Connection conn = null;
        ArrayList<IntegerMeasureEntity> result = new ArrayList<IntegerMeasureEntity>();

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setInt(2, num);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                IntegerMeasureEntity measure = new IntegerMeasureEntity(rs.getLong("METRIC_ID"), rs.getTimestamp("TIME"),
                        rs.getInt("VALUE"));
                result.add(measure);
            }
            rs.close();
            ps.close();
            return result;
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

        finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch(SQLException e) {};
            }
        }
    }

    public ArrayList<LongMeasureEntity> getLastNLongs(Long id, int num) {

        String sql = "SELECT * FROM long_measure WHERE METRIC_ID = ? ORDER BY TIME DESC LIMIT ?";
        Connection conn = null;
        ArrayList<LongMeasureEntity> result = new ArrayList<LongMeasureEntity>();

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setInt(2, num);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LongMeasureEntity measure = new LongMeasureEntity(rs.getLong("METRIC_ID"), rs.getTimestamp("TIME"),
                        rs.getLong("VALUE"));
                result.add(measure);
            }
            rs.close();
            ps.close();
            return result;
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

        finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch(SQLException e) {};
            }
        }
    }

    public ArrayList<BooleanMeasureEntity> getBooleanMeasuresFromPeriod(Long id, Timestamp start, Timestamp end){

        String sql = "SELECT * FROM boolean_measure WHERE TIME > ? and TIME < ? ORDER BY TIME";
        Connection conn = null;
        ArrayList<BooleanMeasureEntity> result = new ArrayList<BooleanMeasureEntity>();

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, start);
            ps.setTimestamp(2, end);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                BooleanMeasureEntity measure = new BooleanMeasureEntity(rs.getLong("METRIC_ID"), rs.getTimestamp("TIME"),
                        rs.getBoolean("VALUE"));
                result.add(measure);
            }
            rs.close();
            ps.close();
            return result;
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

        finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch(SQLException e) {};
            }
        }
    };

    public ArrayList<DoubleMeasureEntity> getDoubleMeasuresFromPeriod(Long id, Timestamp start, Timestamp end){

        String sql = "SELECT * FROM double_measure WHERE TIME > ? and TIME < ? ORDER BY TIME";
        Connection conn = null;
        ArrayList<DoubleMeasureEntity> result = new ArrayList<DoubleMeasureEntity>();

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, start);
            ps.setTimestamp(2, end);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DoubleMeasureEntity measure = new DoubleMeasureEntity(rs.getLong("METRIC_ID"), rs.getTimestamp("TIME"),
                        rs.getDouble("VALUE"));
                result.add(measure);
            }
            rs.close();
            ps.close();
            return result;
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

        finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch(SQLException e) {};
            }
        }
    };

    public ArrayList<FloatMeasureEntity> getFloatMeasuresFromPeriod(Long id, Timestamp start, Timestamp end){

        String sql = "SELECT * FROM float_measure WHERE TIME > ? and TIME < ? ORDER BY TIME";
        Connection conn = null;
        ArrayList<FloatMeasureEntity> result = new ArrayList<FloatMeasureEntity>();

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, start);
            ps.setTimestamp(2, end);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                FloatMeasureEntity measure = new FloatMeasureEntity(rs.getLong("METRIC_ID"), rs.getTimestamp("TIME"),
                        rs.getFloat("VALUE"));
                result.add(measure);
            }
            rs.close();
            ps.close();
            return result;
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

        finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch(SQLException e) {};
            }
        }
    };

    public ArrayList<IntegerMeasureEntity> getIntegerMeasuresFromPeriod(Long id, Timestamp start, Timestamp end){

        String sql = "SELECT * FROM integer_measure WHERE TIME > ? and TIME < ? ORDER BY TIME";
        Connection conn = null;
        ArrayList<IntegerMeasureEntity> result = new ArrayList<IntegerMeasureEntity>();

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, start);
            ps.setTimestamp(2, end);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                IntegerMeasureEntity measure = new IntegerMeasureEntity(rs.getLong("METRIC_ID"), rs.getTimestamp("TIME"),
                        rs.getInt("VALUE"));
                result.add(measure);
            }
            rs.close();
            ps.close();
            return result;
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

        finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch(SQLException e) {};
            }
        }
    };

    public ArrayList<LongMeasureEntity> getLongMeasuresFromPeriod(Long id, Timestamp start, Timestamp end){

        String sql = "SELECT * FROM long_measure WHERE TIME > ? and TIME < ? ORDER BY TIME";
        Connection conn = null;
        ArrayList<LongMeasureEntity> result = new ArrayList<LongMeasureEntity>();

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, start);
            ps.setTimestamp(2, end);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LongMeasureEntity measure = new LongMeasureEntity(rs.getLong("METRIC_ID"), rs.getTimestamp("TIME"),
                        rs.getLong("VALUE"));
                result.add(measure);
            }
            rs.close();
            ps.close();
            return result;
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

        finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch(SQLException e) {};
            }
        }
    };

}
