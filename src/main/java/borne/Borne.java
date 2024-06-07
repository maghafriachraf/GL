package borne;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Borne {

    public List<ChargeStation> findAvailableStations() {
        String sql = "SELECT * FROM charge_stations WHERE status = 'available'";
        List<ChargeStation> stations = new ArrayList<>();

        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ChargeStation station = new ChargeStation(
                    rs.getLong("id"),
                    rs.getString("location"),
                    rs.getString("status")
                );
                stations.add(station);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching available charge stations", e);
        }
        return stations;
    }

    public void updateStationStatus(long stationId, String status) {
        String sql = "UPDATE charge_stations SET status = ? WHERE id = ?";

        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setLong(2, stationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating charge station status", e);
        }
    }
}

