package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.Dao;
import model.dao.UserDao;
import model.entities.Commodity;
import model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC implements UserDao {

    private Connection conn;

    public UserDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insert(User obj) {

    }

    @Override
    public void update(User obj) {

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
        return null;
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
