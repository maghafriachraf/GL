package borne;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChargeSessionBD {
	
    private ReservationBD reservationDAO = new ReservationBD();
    private ChargeSessionBD chargeSessionDAO = new ChargeSessionBD();
    private ChargeStationBD chargeStationDAO = new ChargeStationBD();
	
    public void createChargeSession(ChargeSession session) throws SQLException {
        String sql = "INSERT INTO charge_sessions (reservation_id, station_id, start_time, end_time, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, session.getReservationId());
            pstmt.setLong(2, session.getStationId());
            pstmt.setTimestamp(3, session.getStartTime());
            pstmt.setTimestamp(4, session.getEndTime());
            pstmt.setString(5, session.getStatus());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        session.setId(generatedKeys.getLong(1));
                    }
                }
            }
        }
    }

    public ChargeSession getChargeSessionById(Long id) throws SQLException {
        String sql = "SELECT * FROM charge_sessions WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ChargeSession(
                        rs.getLong("id"),
                        rs.getLong("reservation_id"),
                        rs.getLong("station_id"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getString("status")
                    );
                }
            }
        }
        return null;
    }

    public List<ChargeSession> getChargeSessionsByClientId(Long clientId) throws SQLException {
        List<ChargeSession> sessions = new ArrayList<>();
        String sql = "SELECT cs.* FROM charge_sessions cs JOIN reservations r ON cs.reservation_id = r.id WHERE r.client_id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, clientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    sessions.add(new ChargeSession(
                        rs.getLong("id"),
                        rs.getLong("reservation_id"),
                        rs.getLong("station_id"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getString("status")
                    ));
                }
            }
        }
        return sessions;
    }

    public void updateChargeSession(ChargeSession session) throws SQLException {
        String sql = "UPDATE charge_sessions SET reservation_id = ?, station_id = ?, start_time = ?, end_time = ?, status = ? WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, session.getReservationId());
            pstmt.setLong(2, session.getStationId());
            pstmt.setTimestamp(3, session.getStartTime());
            pstmt.setTimestamp(4, session.getEndTime());
            pstmt.setString(5, session.getStatus());
            pstmt.setLong(6, session.getId());

            pstmt.executeUpdate();
        }
    }

    public void deleteChargeSession(Long id) throws SQLException {
        String sql = "DELETE FROM charge_sessions WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
    
    public List<ChargeSession> getEligibleSessionsForExtension() throws SQLException {
        List<ChargeSession> sessions = new ArrayList<>();
        String sql = "SELECT * FROM charge_sessions WHERE status = 'active' AND TIMESTAMPDIFF(MINUTE, NOW(), end_time) <= 15";
        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                sessions.add(new ChargeSession(
                    rs.getLong("id"),
                    rs.getLong("reservation_id"),
                    rs.getLong("station_id"),
                    rs.getTimestamp("start_time"),
                    rs.getTimestamp("end_time"),
                    rs.getString("status")
                ));
            }
        }
        return sessions;
    }
  
}