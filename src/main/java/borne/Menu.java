package borne;

import java.util.*;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = getValidatedChoice(scanner);

            try {
                switch (choice) {
                    case 1:
                        registerClient(scanner);
                        break;
                    case 2:
                        handleLicensePlate(scanner);
                        break;
                    case 3:
                        makeReservation(scanner);
                        break;
                    case 4:
                        handleReservationNumber(scanner);
                        break;
                    case 5:
                        showAllClients();
                        break;
                    case 6:
                        showAllReservations();
                        break;
                    case 7:
                        System.out.println("Au revoir!");
                        return;
                    default:
                        System.out.println("Option invalide, veuillez réessayer.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Inscription du client");
        System.out.println("2. Entrer le numéro de plaque d'immatriculation");
        System.out.println("3. Faire une réservation");
        System.out.println("4. Entrer le numéro de réservation");
        System.out.println("5. Afficher tous les clients");
        System.out.println("6. Afficher toutes les réservations");
        System.out.println("7. Quitter");
        System.out.print("Choisissez une option: ");
    }

    public static int getValidatedChoice(Scanner scanner) {
        int choice = -1;
        while (choice == -1) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 7) {
                    System.out.println("Option invalide, veuillez entrer un nombre entre 1 et 7.");
                    choice = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide, veuillez entrer un nombre entier.");
            }
        }
        return choice;
    }
    public static void makeReservation(Scanner scanner) {
    	
    }

    public static void registerClient(Scanner scanner) {
        try {
            System.out.print("Entrez votre prénom: ");
            String firstName = getValidatedInput(scanner);
            System.out.print("Entrez votre nom: ");
            String lastName = getValidatedInput(scanner);
            System.out.print("Entrez votre adresse: ");
            String address = getValidatedInput(scanner);
            System.out.print("Entrez votre adresse email: ");
            String email = getValidatedInput(scanner);
            validateEmail(email);
            System.out.print("Entrez votre numéro de mobile: ");
            String mobileNumber = getValidatedInput(scanner);
            validateMobileNumber(mobileNumber);

            // Sauvegarder les informations du client
            System.out.println("Client enregistré avec succès!");

        } catch (Exception e) {
            System.out.println("Erreur lors de l'inscription : " + e.getMessage());
        }
    }

    public static String getValidatedInput(Scanner scanner) {
        String input = null;
        while (input == null || input.trim().isEmpty()) {
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("L'entrée ne peut pas être vide, veuillez réessayer:");
            }
        }
        return input;
    }

    static void handleLicensePlate(Scanner scanner) {
        // Implémentation de la gestion du numéro de plaque d'immatriculation
    }

    static void handleReservationNumber(Scanner scanner) {
        // Implémentation de la gestion du numéro de réservation
    }

    public static void validateEmail(String email) {
        // Regex pour valider un format d'email standard
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (email == null || !email.matches(emailRegex)) {
            throw new IllegalArgumentException("L'adresse email est invalide.");
        }
    }

    public static void validateMobileNumber(String mobileNumber) {
        if (!mobileNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Le numéro de mobile doit contenir 10 chiffres.");
        }
    }

    public static void validateDebitCardNumber(String cardNumber) {
        // Implémentation de la validation du numéro de carte de débit
    }

    static void showAllClients() {
    	
        // Implémentation de l'affichage de tous les clients
    }

    static void showAllReservations() {
        // Implémentation de l'affichage de toutes les réservations
    }
}
