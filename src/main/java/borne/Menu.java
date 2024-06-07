package borne;

import java.util.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;

public class Menu {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
 
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Inscription du client");
            System.out.println("2. Entrer le numéro de plaque d'immatriculation");
            System.out.println("3. Faire une reservation");
            System.out.println("4. Entrer le numéro de réservation");
            System.out.println("5. Afficher tous les clients");
            System.out.println("6. Afficher toutes les réservations");
            System.out.println("7. Quitter");
            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

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

    static void makeReservation(Scanner scanner) {
      
    }

    static void registerClient(Scanner scanner) {
        System.out.print("Entrez votre prénom: ");
        String firstName = scanner.nextLine();
        System.out.print("Entrez votre nom: ");
        String lastName = scanner.nextLine();
        System.out.print("Entrez votre adresse: ");
        String address = scanner.nextLine();
        System.out.print("Entrez votre numéro de mobile: ");
        String mobileNumber = scanner.nextLine();
        validateMobileNumber(mobileNumber);

       

       
    }

    static void handleLicensePlate(Scanner scanner) {
    
    }

    static void handleReservationNumber(Scanner scanner) {

    }

    public static void validateEmail(String email) {
    
    }

    public static void validateMobileNumber(String mobileNumber) {
     
    }

    public static void validateDebitCardNumber(String cardNumber) {
    
    }

    static void showAllClients() {
      
    }

    static void showAllReservations() {
   
    }
}