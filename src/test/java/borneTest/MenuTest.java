package borneTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import borne.Menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

@DisplayName("Tests pour la classe Menu")
public class MenuTest {

    @DisplayName("Test de validation de l'email avec un format valide")
    @Test
    public void testValidateEmailValid() {
        String email = "test@example.com";
        Menu.validateEmail(email);
        // Si aucune exception n'est lancée, le test est réussi
    }

    @DisplayName("Test de validation de l'email avec un format invalide")
    @Test
    public void testValidateEmailInvalid() {
        String email = "test@.com";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Menu.validateEmail(email);
        });
        assertEquals("L'adresse email est invalide.", exception.getMessage());
    }

    @DisplayName("Test de validation du numéro de mobile avec un format valide")
    @Test
    public void testValidateMobileNumberValid() {
        String mobileNumber = "0123456789";
        Menu.validateMobileNumber(mobileNumber);
        // Si aucune exception n'est lancée, le test est réussi
    }

    @DisplayName("Test de validation du numéro de mobile avec un format invalide")
    @Test
    public void testValidateMobileNumberInvalid() {
        String mobileNumber = "01234";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Menu.validateMobileNumber(mobileNumber);
        });
        assertEquals("Le numéro de mobile doit contenir 10 chiffres.", exception.getMessage());
    }

    @DisplayName("Test du choix validé avec une entrée valide")
    @Test
    public void testGetValidatedChoiceValid() {
        Scanner scanner = new Scanner("3\n");
        int choice = Menu.getValidatedChoice(scanner);
        assertEquals(3, choice);
    }

    @DisplayName("Test du choix validé avec une entrée invalide")
    @Test
    public void testGetValidatedChoiceInvalid() {
        Scanner scanner = new Scanner("10\n5\n");
        int choice = Menu.getValidatedChoice(scanner);
        assertEquals(5, choice);
    }

    @DisplayName("Test de récupération d'une entrée valide")
    @Test
    public void testGetValidatedInput() {
        Scanner scanner = new Scanner("Valid Input\n");
        String input = Menu.getValidatedInput(scanner);
        assertEquals("Valid Input", input);
    }

    @DisplayName("Test de récupération d'une entrée vide")
    @Test
    public void testGetValidatedInputEmpty() {
        Scanner scanner = new Scanner("\nValid Input\n");
        String input = Menu.getValidatedInput(scanner);
        assertEquals("Valid Input", input);
    }

    @DisplayName("Test d'inscription du client avec des entrées valides")
    @Test
    public void testRegisterClientValid() {
        Scanner scanner = new Scanner("John\nDoe\n123 Main St\njohn.doe@example.com\n0123456789\n");
        Menu.registerClient(scanner);
        // Si aucune exception n'est lancée, le test est réussi
    }

    @DisplayName("Test d'inscription du client avec une adresse email invalide")
    @Test
    public void testRegisterClientInvalidEmail() {
        Scanner scanner = new Scanner("John\nDoe\n123 Main St\ninvalid-email\n0123456789\n");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Menu.registerClient(scanner);
        });
        assertEquals("L'adresse email est invalide.", exception.getMessage());
    }

    @DisplayName("Test d'inscription du client avec un numéro de mobile invalide")
    @Test
    public void testRegisterClientInvalidMobileNumber() {
        Scanner scanner = new Scanner("John\nDoe\n123 Main St\njohn.doe@example.com\n01234\n");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Menu.registerClient(scanner);
        });
        assertEquals("Le numéro de mobile doit contenir 10 chiffres.", exception.getMessage());
    }

    // Ajoutez d'autres tests pour les autres fonctionnalités ici
}
