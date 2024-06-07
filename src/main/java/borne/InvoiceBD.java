package borne;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceBD {

    public void createInvoice(Invoice invoice) throws SQLException {
        String sql = "INSERT INTO invoices (client_id, amount, issue_date, due_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, invoice.getClientId());
            pstmt.setBigDecimal(2, invoice.getAmount());
            pstmt.setTimestamp(3, invoice.getIssueDate());
            pstmt.setTimestamp(4, invoice.getDueDate());
            pstmt.setString(5, invoice.getStatus());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        invoice.setId(generatedKeys.getLong(1));
                    }
                }
            }
        }
    }

    public Invoice getInvoiceById(Long id) throws SQLException {
        String sql = "SELECT * FROM invoices WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Invoice(
                        rs.getLong("id"),
                        rs.getLong("client_id"),
                        rs.getBigDecimal("amount"),
                        rs.getTimestamp("issue_date"),
                        rs.getTimestamp("due_date"),
                        rs.getString("status")
                    );
                }
            }
        }
        return null;
    }

    public List<Invoice> getAllInvoices() throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices";
        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                invoices.add(new Invoice(
                    rs.getLong("id"),
                    rs.getLong("client_id"),
                    rs.getBigDecimal("amount"),
                    rs.getTimestamp("issue_date"),
                    rs.getTimestamp("due_date"),
                    rs.getString("status")
                ));
            }
        }
        return invoices;
    }

    public void updateInvoice(Invoice invoice) throws SQLException {
        String sql = "UPDATE invoices SET client_id = ?, amount = ?, issue_date = ?, due_date = ?, status = ? WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, invoice.getClientId());
            pstmt.setBigDecimal(2, invoice.getAmount());
            pstmt.setTimestamp(3, invoice.getIssueDate());
            pstmt.setTimestamp(4, invoice.getDueDate());
            pstmt.setString(5, invoice.getStatus());
            pstmt.setLong(6, invoice.getId());

            pstmt.executeUpdate();
        }
    }

    public void deleteInvoice(Long id) throws SQLException {
        String sql = "DELETE FROM invoices WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
}
