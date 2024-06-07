package borne;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class InvoiceService {

    private InvoiceBD invoiceDAO = new InvoiceBD();
    private ClientBD clientDAO = new ClientBD(); // Assuming there is a ClientBD class to manage client data
    private ChargeSessionBD chargeSessionDAO = new ChargeSessionBD(); // Assuming there is a ChargeSessionBD class to manage charge session data
    private ReservationBD reservationDAO = new ReservationBD(); // Assuming there is a ReservationBD class to manage reservation data

    public void generateMonthlyInvoices() throws SQLException {
        List<Client> clients = getClients();
        for (Client client : clients) {
            BigDecimal totalAmount = calculateTotalAmount(client);
            Invoice invoice = new Invoice(null, client.getId(), totalAmount, new Timestamp(System.currentTimeMillis()), calculateDueDate(), "pending");
            invoiceDAO.createInvoice(invoice);
            sendInvoiceToClient(client, invoice);
        }
    }

    private BigDecimal calculateTotalAmount(Client client) throws SQLException {
        BigDecimal totalAmount = BigDecimal.ZERO;

        // Calculate total charge sessions cost
        List<ChargeSession> chargeSessions = chargeSessionDAO.getChargeSessionsByClientId(client.getId());
        for (ChargeSession session : chargeSessions) {
            BigDecimal sessionCost = calculateSessionCost(session);
            totalAmount = totalAmount.add(sessionCost);
        }

        // Calculate total reservation fees
        List<Reservation> reservations = reservationDAO.getReservationsByClientId(client.getId());
        for (Reservation reservation : reservations) {
            BigDecimal reservationFee = calculateReservationFee(reservation);
            totalAmount = totalAmount.add(reservationFee);
        }

        return totalAmount;
    }

    private BigDecimal calculateSessionCost(ChargeSession session) {
        // Calculate the cost of a charge session
        long durationInMinutes = (session.getEndTime().getTime() - session.getStartTime().getTime()) / 60000;
        long intervalsOf20Minutes = durationInMinutes / 20;
        BigDecimal ratePerInterval = new BigDecimal("10.00"); // 10€ per 20 minutes
        return ratePerInterval.multiply(new BigDecimal(intervalsOf20Minutes));
    }

    private BigDecimal calculateReservationFee(Reservation reservation) {
        // Logic to calculate the reservation fee
        BigDecimal reservationFee = new BigDecimal("5.00"); // Example fixed fee
        return reservationFee;
    }

    private Timestamp calculateDueDate() {
        // Logic to calculate the due date
        long thirtyDaysInMillis = 30L * 24 * 60 * 60 * 1000;
        return new Timestamp(System.currentTimeMillis() + thirtyDaysInMillis);
    }

    private void sendInvoiceToClient(Client client, Invoice invoice) {
     
        System.out.println("Sending invoice to client: " + client.getEmail());
        System.out.println("Invoice ID: " + invoice.getId());
        System.out.println("Total Amount: " + invoice.getAmount());
    }

    private List<Client> getClients() throws SQLException {
        // Logic to get all clients from the database
        return clientDAO.getAllClients();
    }
}