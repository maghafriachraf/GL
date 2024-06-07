package borne;

import java.sql.Timestamp;

public class ChargeSession {
    private Long id;
    private Long reservationId;
    private Long stationId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String status; // active, completed, cancelled

    public ChargeSession(Long id, Long reservationId, Long stationId, Timestamp startTime, Timestamp endTime, String status) {
        this.id = id;
        this.reservationId = reservationId;
        this.stationId = stationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}