package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.Dao;
import model.dao.UserDao;
import model.entities.Commodity;
import model.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoJDBC implements UserDao {

    private Connection conn;

    public UserDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insert(User obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO user " +
                            "(Id, Name, UserType, Email, TimeCreated, CommodityId) " +
                            "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getId());
            st.setString(2, obj.getName());
            st.setString(3, obj.getUserType());
            st.setString(4, obj.getEmail());
            st.setDate(5, new java.sql.Date(obj.getTimeCreated().getTime()));
            st.setString(6, obj.getCommodity().getId());
            int affectRows = st.executeUpdate();
            if (affectRows == 0) {
                throw new DbException("Unexpected error! No affected rows!");

            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(User obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE user " +
                            "SET Name = ?, UserType = ?, Email = ?, CommodityId = ? " +
                            "WHERE Id = ?");
            st.setString(1, obj.getName());
            st.setString(2, obj.getUserType());
            st.setString(3, obj.getEmail());
            st.setString(4, obj.getCommodity().getId());
            st.setString(5, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public User findById(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT u.*, c.Name AS CommodityName, c.Domain AS CommodityDomain " +
                            "FROM abira.user AS u " +
                            "INNER JOIN commodity c " +
                            "ON c.Id = u.CommodityId " +
                            "WHERE u.Id = ?");
            st.setString(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Commodity commodity = instantiateCommodity(rs);
                User user = instantiateUser(rs, commodity);
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private User instantiateUser(ResultSet rs, Commodity commodity) throws SQLException {
        User user = new User();
        user.setId(rs.getString("Id"));
        user.setName(rs.getString("Name"));
        user.setUserType(rs.getString("UserType"));
        user.setEmail(rs.getString("Email"));
        user.setTimeCreated(rs.getDate("TimeCreated"));
        user.setCommodity(commodity);
        return user;
    }

    private Commodity instantiateCommodity(ResultSet rs) throws SQLException {
        Commodity commodity = new Commodity();
        commodity.setId(rs.getString("CommodityId"));
        commodity.setName(rs.getString("CommodityName"));
        commodity.setDomain(rs.getString("CommodityDomain"));
        return commodity;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT u.*, c.Name AS CommodityName, c.Domain AS CommodityDomain " +
                            "FROM abira.user AS u " +
                            "INNER JOIN commodity c " +
                            "ON c.Id = u.CommodityId " +
                            "ORDER BY Name");
            rs = st.executeQuery();
            Map<String, Commodity> map = new HashMap<>();
            while (rs.next()) {
                Commodity com = map.get(rs.getString("CommodityId"));
                if (com == null) {
                    com = instantiateCommodity(rs);
                    map.put(rs.getString("CommodityId"), com);
                }
                User user = instantiateUser(rs, com);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return list;
    }

    // in case the user does not specify a domain, we will return the default one (custom)
    @Override
    public List<User> findByCommodity(Commodity commodity) {
        List<User> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT u.*, c.Name AS CommodityName, c.Domain AS CommodityDomain " +
                            "FROM abira.user AS u " +
                            "INNER JOIN commodity c " +
                            "ON c.Id = u.CommodityId " +
                            "WHERE CommodityId = ? AND c.Domain = ? " +
                            "ORDER BY Name");
            st.setString(1, commodity.getId());
            String domain = (commodity.getDomain() != null && !commodity.getDomain().trim().equals("")) ? commodity.getDomain() : "custom";
            st.setString(2, domain);

            rs = st.executeQuery();
            while (rs.next()) {
                Commodity dbCommodity = instantiateCommodity(rs);
                list.add(instantiateUser(rs, dbCommodity));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return list;
    }
}
