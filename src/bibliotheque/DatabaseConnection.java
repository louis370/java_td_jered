package bibliotheque; // Assure-toi que ce nom correspond à ton package !

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    
    public static Connection seConnecter() {
        Connection connexion = null;
        try {
            // L'adresse de ta base de données, l'utilisateur (root par défaut) et le mot de passe (vide par défaut sur XAMPP/WAMP)
            String url = "jdbc:mysql://localhost:3306/registre_bibliotheque";
            String utilisateur = "root";
            String motDePasse = "";
            
            // Chargement du pilote
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Établissement de la connexion
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Succès : Connecté à la base de données !");
            
        } catch (Exception e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
        return connexion;
    }
    
    // Petite méthode pour tester directement en exécutant ce fichier
    public static void main(String[] args) {
        seConnecter();
    }
}