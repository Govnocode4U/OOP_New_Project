//package org.saloon_management.dao.impl;
//
//import org.saloon_management.dao.ClientDao;
//import org.saloon_management.system_models.Client;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//@Repository
//public class ClientDaoImpl implements ClientDao {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public void add(Client client) {
//        String sql = "INSERT INTO clients (full_name, phone, email) VALUES (?, ?, ?)";
//        jdbcTemplate.update(sql, client.getFullName(), client.getPhone(), client.getEmail());
//    }
//
//    public void update(Client client) throws SQLException {
//        String sql = "UPDATE clients SET full_name = ?, phone = ?, email = ? WHERE id = ?";
//        jdbcTemplate.update(sql, client.getFullName(), client.getPhone(), client.getEmail(), client.getId());
//    }
//
//
//    public void delete(Client client) {
//        String sql = "DELETE FROM clients WHERE id = ?";
//        jdbcTemplate.update(sql, client.getId());
//    }
//
//    public List<Client> getAll() {
//        String sql = "SELECT * FROM clients";
//        return jdbcTemplate.query(sql, new ClientRowMapper());
//    }
//
//    public Client getById(int id) {
//        String sql = "SELECT * FROM clients WHERE id = ?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ClientRowMapper());
//    }
//}
//
//class ClientRowMapper implements RowMapper<Client> {
//    @Override
//    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
//        String fullName = resultSet.getString("full_name");
//        String phone = resultSet.getString("phone");
//        String email = resultSet.getString("email");
//        return new Client(fullName, phone, email);
//    }
//}
//
