import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Contact;

import java.util.Collections;
import java.util.Comparator;

public class App {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        afficherMenu();
        while (true) {
            String choix = scan.nextLine();
            switch (choix) {
                case "1":
                    ajouterContact();
                    break;
                case "2":
                    listerContact();
                    break;
                case "3":
                    listerContact();
                    modifierContact();
                    break;
                case "4":
                    listerContact();
                    supprimerContact();
                    break;
                case "5":
                    trierContacts();
                    break;
                case "6":
                    trierContactsParDateNaissance();
                    break;
                case "7":
                    rechercherContactParNom();
                    break;
                case "q":
                    scan.close();
                    return;
                default:
                    System.out.println("Boulet!!!!");
                    break;
            }
            afficherMenu();
        }
    }
    private static void supprimerContact() {
        System.out.println("Saisissez le nom du contact à supprimer :");
        String nom = scan.nextLine();

        try {
            ArrayList<Contact> liste = Contact.lister();

            Contact contactASupprimer = null;
            for (Contact contact : liste) {
                if (contact.getNom().equals(nom)) {
                    contactASupprimer = contact;
                    break;
                }
            }

            if (contactASupprimer != null) {
                liste.remove(contactASupprimer);
                Contact.enregistrer2(liste);
                System.out.println("Contact supprimé.");
            } else {
                System.out.println("Contact introuvable.");
            }
        } catch (IOException e) {
            System.out.println("Erreur à la suppression du contact");
        }
    }
    private static void listerContact() {
        // Contact c = new Contact();
        try {
            ArrayList<Contact> liste = Contact.lister();

            for (Contact contact : liste) {
                System.out.println(contact.getPrenom() + " " + contact.getNom());
            }
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier");
        }

    }

    private static void ajouterContact() {

        Contact c = new Contact();
        System.out.println("Saisir le nom:");
        c.setNom(scan.nextLine());
        System.out.println("Saisir le prénom:");
        c.setPrenom(scan.nextLine());

        do {
            try {
                System.out.println("Saisir le téléphone:");
                c.setNumero(scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        do {
            try {
                System.out.println("Saisir le mail:");
                c.setMail(scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        do {
            try {
                System.out.println("Saisir la date de naissance:");
                c.setDateNaissance(scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("Error, try again!");
            }
        } while (true);

        try {
            c.enregistrer();
            System.out.println("Contact enregistré.");
        } catch (IOException e) {
            System.out.println("Erreur à l'enregistrement");
        }

    }


    private static void modifierContact() {
        System.out.println("Saisissez le nom du contact à modifier :");
        String nom = scan.nextLine();

        try {
            ArrayList<Contact> liste = Contact.lister();

            Contact contactAModifier = null;
            for (Contact contact : liste) {
                if (contact.getNom().equals(nom)) {
                    contactAModifier = contact;
                    break;
                }
            }

            if (contactAModifier != null) {
                System.out.println("Saisissez le nouveau nom du contact :");
                contactAModifier.setNom(scan.nextLine());
                System.out.println("Saisissez le nouveau prénom du contact :");
                contactAModifier.setPrenom(scan.nextLine());

                do {
                    try {
                        System.out.println("Saisissez le nouveau numéro de téléphone du contact :");
                        contactAModifier.setNumero(scan.nextLine());
                        break;
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                    }
                } while (true);

                do {
                    try {
                        System.out.println("Saisissez le nouveau mail du contact :");
                        contactAModifier.setMail(scan.nextLine());
                        break;
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                    }
                } while (true);

                do {
                    try {
                        System.out.println("Saisissez la nouvelle date de naissance du contact :");
                        contactAModifier.setDateNaissance(scan.nextLine());
                        break;
                    } catch (ParseException e) {
                        System.out.println("Error, try again!");
                    }
                } while (true);

                Contact.enregistrer2(liste);
                System.out.println("Le contact a été modifié avec succès.");
            } else {
                System.out.println("Le contact n'a pas été trouvé dans la liste.");
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture/écriture du fichier des contacts.");
        }
    }

    private static void trierContactsParDateNaissance() {
        try {
            ArrayList<Contact> liste = Contact.lister();

            Collections.sort(liste, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c1.getDateNaissance().compareTo(c2.getDateNaissance());
                }
            });

            for (Contact contact : liste) {
                System.out.println(contact.getNom() + " " + contact.getPrenom() + " - Date de naissance : " + contact.getDateNaissance());
            }
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier");
        }
    }

    private static void trierContacts() {
        try {
            ArrayList<Contact> liste = Contact.lister();

            Collections.sort(liste, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c1.getNom().compareTo(c2.getNom());
                }
            });

            for (Contact contact : liste) {
                System.out.println(contact.getNom() + " " + contact.getPrenom());
            }
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier");
        }
    }

    private static void rechercherContactParNom() {
        System.out.println("Saisissez le nom du contact à rechercher :");
        String nom = scan.nextLine();

        try {
            ArrayList<Contact> liste = Contact.lister();

            Contact contactTrouve = null;
            for (Contact contact : liste) {
                if (contact.getNom().equals(nom)) {
                    contactTrouve = contact;
                    break;
                }
            }

            if (contactTrouve != null) {
                System.out.println("Nom : " + contactTrouve.getNom());
                System.out.println("Prénom : " + contactTrouve.getPrenom());
                System.out.println("Téléphone : " + contactTrouve.getNumero());
                System.out.println("Mail : " + contactTrouve.getMail());
                System.out.println("Date de naissance : " + contactTrouve.getDateNaissance());
            } else {
                System.out.println("Aucun contact trouvé avec ce nom");
            }
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier");
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void afficherMenu() {
        // 1
        /*
         * System.out.println("-- MENU --");
         * System.out.println("1- Ajouter un contact");
         * System.out.println("2- Lister les contacts");
         * System.out.println("q- Quitter");
         */
        // 2
        ArrayList<String> menus = new ArrayList<>();
        menus.add(ANSI_YELLOW + "\n⸺⸺⸺⸺ MENU ⸺⸺⸺⸺\n" + ANSI_RESET);
        menus.add(ANSI_GREEN + "1 ➥ Ajouter un contact" + ANSI_RESET);
        menus.add(ANSI_GREEN + "2 ➥ Lister les contacts" + ANSI_RESET);
        menus.add(ANSI_GREEN + "3 ➥ Modifier un contact" + ANSI_RESET);
        menus.add(ANSI_GREEN + "4 ➥ Supprimer un contact" + ANSI_RESET);
        menus.add(ANSI_GREEN + "5 ➥ Trier les contacts" + ANSI_RESET);
        menus.add(ANSI_GREEN + "6 ➥ Trier les contacts par date" + ANSI_RESET);
        menus.add(ANSI_GREEN + "7 ➥ Rechercher les contacts via le nom" + ANSI_RESET);

        menus.add(ANSI_RED + "\nq ➥ Quitter" + ANSI_RESET);
        menus.add(ANSI_YELLOW + "\n⸺⸺⸺⸺⸺⸺⸺⸺⸺⸺\n" + ANSI_RESET);
        for (String s : menus) {
            System.out.println(s);
        }
    }
}

