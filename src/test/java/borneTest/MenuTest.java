package borneTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import borne.*;

import java.util.Optional;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

class MenuTest {

    private ClientBD mockClientBD;
    private ReservationBD mockReservationDAO;
    private ChargeStationBD mockChargeStationDAO;

    @BeforeEach
    void setUp() {
        mockClientBD = Mockito.mock(ClientBD.class);
        mockReservationDAO = Mockito.mock(ReservationBD.class);
        mockChargeStationDAO = Mockito.mock(ChargeStationBD.class);
        Menu.clientBD = mockClientBD;
        Menu.reservationDAO = mockReservationDAO;
        Menu.chargeStationDAO = mockChargeStationDAO;
    }

    @Test
    void testValidateEmail_Valid() {
        assertDoesNotThrow(() -> Menu.validateEmail("test@example.com"));
    }

    @Test
    void testValidateEmail_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> Menu.validateEmail("invalid-email"));
    }

    @Test
    void testValidateMobileNumber_Valid() {
        assertDoesNotThrow(() -> Menu.validateMobileNumber("0123456789"));
        assertDoesNotThrow(() -> Menu.validateMobileNumber("+33123456789"));
        assertDoesNotThrow(() -> Menu.validateMobileNumber("0033123456789"));
    }

    @Test
    void testValidateMobileNumber_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> Menu.validateMobileNumber("12345"));
    }

    @Test
    void testValidateDebitCardNumber_Valid() {
        assertDoesNotThrow(() -> Menu.validateDebitCardNumber("1234567812345678"));
    }

    @Test
    void testValidateDebitCardNumber_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> Menu.validateDebitCardNumber("1234"));
    }

    @Test
    void testRegisterClient_ValidInput() {
        Scanner scanner = new Scanner("John\nDoe\n123 Street\n0123456789\njohn.doe@example.com\n1234567812345678\nABC123,DEF456\n");
        
        Mockito.doNothing().when(mockClientBD).save(Mockito.any(Client.class));

        assertDoesNotThrow(() -> Menu.registerClient(scanner));
    }

    @Test
    void testRegisterClient_InvalidEmail() {
        Scanner scanner = new Scanner("John\nDoe\n123 Street\n0123456789\ninvalid-email\njohn.doe@example.com\n1234567812345678\nABC123,DEF456\n");

        assertThrows(IllegalArgumentException.class, () -> Menu.registerClient(scanner));
    }

    @Test
    void testMakeReservation_ClientNotFound() {
        Scanner scanner = new Scanner("nonexistent@example.com\n");
        
        Mockito.when(mockClientBD.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> Menu.makeReservation(scanner));
    }

  
}
