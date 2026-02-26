package bibliotheque;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    
    public static Connection seConnecter() {
        Connection connexion = null;
        try {
            String url = "jdbc:mysql://localhost:3306/registre_bibliotheque";
            String utilisateur = "root";
            String motDePasse = "";
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Succès : Connecté à la base de données !");
            
        } catch (Exception e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
        return connexion;
    }
    
    public static void main(String[] args) {
        seConnecter();
    }
}