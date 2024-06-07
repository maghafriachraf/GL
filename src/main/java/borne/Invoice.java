package borne;

import java.math.*;
import java.sql.*;

public class Invoice {
    
        private Long id;
        private Long clientId;
        private BigDecimal amount;
        private Timestamp issueDate;
        private Timestamp dueDate;
        private String status;
        
        public Invoice(Long id, Long clientId, BigDecimal amount, Timestamp issueDate, Timestamp dueDate, String status) {
            this.id = id;
            this.clientId = clientId;
            this.amount = amount;
            this.issueDate = issueDate;
            this.dueDate = dueDate;
            this.status = status;
        }
       
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public Long getClientId() {
            return clientId;
        }
        public void setClientId(Long clientId) {
            this.clientId = clientId;
        }
        public BigDecimal getAmount() {
            return amount;
        }
        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
        public Timestamp getIssueDate() {
            return issueDate;
        }
        public void setIssueDate(Timestamp issueDate) {
            this.issueDate = issueDate;
        }
        public Timestamp getDueDate() {
            return dueDate;
        }
        public void setDueDate(Timestamp dueDate) {
            this.dueDate = dueDate;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        
    
}