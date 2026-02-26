/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bibliotheque;

/**
 *
 * @author Kalala
 */
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class MenuPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuPrincipal.class.getName());

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        this.setTitle("DEFABooK");
        this.setLocationRelativeTo(null);
        afficherLivres();
        afficherLecteurs();
        chargerListes();
        afficherEmprunts();
    }

    public void afficherLivres() {
    String[] colonnes = {"ID", "Titre", "Auteur", "Catégorie", "Disponible"};
    DefaultTableModel modele = new DefaultTableModel(null, colonnes);
    
    try {
        Connection con = DatabaseConnection.seConnecter();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM livres");
        
        while (rs.next()) {
            Object[] ligne = {
                rs.getInt("id_livre"),
                rs.getString("titre"),
                rs.getString("auteur"),
                rs.getString("categorie"),
                rs.getBoolean("disponible") ? "Oui" : "Sorti"
            };
            modele.addRow(ligne);
        }
        
        tableLivres.setModel(modele);
        chargerListes();
        
    } catch (Exception e) {
        System.out.println("Erreur d'affichage : " + e.getMessage());
        }
    }
        public void afficherLecteurs() {
        String[] colonnes = {"ID", "Nom Complet", "Téléphone"};
        DefaultTableModel modele = new DefaultTableModel(null, colonnes);

        try {
            java.sql.Connection con = DatabaseConnection.seConnecter();
            java.sql.Statement st = con.createStatement();
            java.sql.ResultSet rs = st.executeQuery("SELECT * FROM lecteurs");

            while (rs.next()) {
                Object[] ligne = {
                    rs.getInt("id_lecteur"),
                    rs.getString("nom_complet"),
                    rs.getString("telephone")
                };
                modele.addRow(ligne);
            }
            tableLecteurs.setModel(modele);
            chargerListes();

        } catch (Exception e) {
            System.out.println("Erreur lecteurs : " + e.getMessage());
        }
    }
                public void chargerListes() {
            comboLivres.removeAllItems();
            comboLecteurs.removeAllItems();

            try {
                java.sql.Connection con = DatabaseConnection.seConnecter();
                java.sql.Statement st = con.createStatement();

                java.sql.ResultSet rsLivres = st.executeQuery("SELECT id_livre, titre FROM livres WHERE disponible = true");
                while(rsLivres.next()) {
                    comboLivres.addItem(rsLivres.getInt("id_livre") + " - " + rsLivres.getString("titre"));
                }

                java.sql.ResultSet rsLecteurs = st.executeQuery("SELECT id_lecteur, nom_complet FROM lecteurs");
                while(rsLecteurs.next()) {
                    comboLecteurs.addItem(rsLecteurs.getInt("id_lecteur") + " - " + rsLecteurs.getString("nom_complet"));
                }

                txtDateEmprunt.setText(java.time.LocalDate.now().toString());
                
                txtDateRetour.setText(java.time.LocalDate.now().plusDays(14).toString());

            } catch (Exception e) {
                System.out.println("Erreur chargement des listes : " + e.getMessage());
            }
        }
                public void afficherEmprunts() {
                    String[] colonnes = {"N° Emprunt", "Livre", "Lecteur", "Date Emprunt", "Retour Prévu", "Statut", "Amende"};
                    javax.swing.table.DefaultTableModel modele = new javax.swing.table.DefaultTableModel(null, colonnes);

                    try {
                        java.sql.Connection con = DatabaseConnection.seConnecter();
                        java.sql.Statement st = con.createStatement();

                        String sql = "SELECT e.id_emprunt, l.titre, m.nom_complet, e.date_emprunt, e.date_retour_prevue, e.statut " +
                                     "FROM emprunts e " +
                                     "JOIN livres l ON e.id_livre = l.id_livre " +
                                     "JOIN lecteurs m ON e.id_lecteur = m.id_lecteur";

                        java.sql.ResultSet rs = st.executeQuery(sql);

                        java.time.LocalDate dateActuelle = java.time.LocalDate.now();

                        while (rs.next()) {
                            String statut = rs.getString("statut");
                            String datePrevueStr = rs.getString("date_retour_prevue");

                            long amende = 0;

                            if (statut.equals("En cours") && datePrevueStr != null) {
                                java.time.LocalDate datePrevue = java.time.LocalDate.parse(datePrevueStr);

                                if (dateActuelle.isAfter(datePrevue)) {
                                    long joursRetard = java.time.temporal.ChronoUnit.DAYS.between(datePrevue, dateActuelle);
                                    amende = joursRetard * 500;
                                }
                            }

                            Object[] ligne = {
                                rs.getInt("id_emprunt"),
                                rs.getString("titre"),
                                rs.getString("nom_complet"),
                                rs.getString("date_emprunt"),
                                datePrevueStr,
                                statut,
                                (amende > 0) ? amende + " FC" : "0 FC"
                            };
                            modele.addRow(ligne);
                        }
                        tableEmprunts.setModel(modele);

                    } catch (Exception e) {
                        System.out.println("Erreur affichage emprunts avec amende : " + e.getMessage());
                    }
                }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLivres = new javax.swing.JTable();
        txtTitre = new javax.swing.JTextField();
        txtAuteur = new javax.swing.JTextField();
        txtCategorie = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAjouter = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableLecteurs = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelephone = new javax.swing.JTextField();
        btnAjouter1 = new javax.swing.JButton();
        btnModifier1 = new javax.swing.JButton();
        btnSupprimer1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableEmprunts = new javax.swing.JTable();
        comboLivres = new javax.swing.JComboBox<>();
        comboLecteurs = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtDateEmprunt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDateRetour = new javax.swing.JTextField();
        btnEmprunter = new javax.swing.JButton();
        btnRetourner = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableLivres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Titre", "Auteur", "Disponibilité"
            }
        ));
        tableLivres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLivresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableLivres);

        txtAuteur.addActionListener(this::txtAuteurActionPerformed);

        jLabel1.setText("Titre");

        jLabel2.setText("Auteur");

        jLabel3.setText("Categorie");

        btnAjouter.setBackground(new java.awt.Color(0, 204, 0));
        btnAjouter.setText("Ajouter le livre");
        btnAjouter.addActionListener(this::btnAjouterActionPerformed);

        btnSupprimer.setBackground(new java.awt.Color(255, 0, 0));
        btnSupprimer.setText("Supprimer le livre");
        btnSupprimer.addActionListener(this::btnSupprimerActionPerformed);

        btnModifier.setBackground(new java.awt.Color(255, 255, 0));
        btnModifier.setText("Modifier");
        btnModifier.addActionListener(this::btnModifierActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTitre)
                    .addComponent(txtAuteur)
                    .addComponent(txtCategorie)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(btnAjouter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSupprimer, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(btnModifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(1, 1, 1)
                        .addComponent(txtAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(1, 1, 1)
                        .addComponent(txtCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAjouter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSupprimer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModifier))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 200, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Gestion des Livres", jPanel1);

        tableLecteurs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableLecteurs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLecteursMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableLecteurs);

        jLabel4.setText("Nom Complet");

        jLabel5.setText("Numéro");

        btnAjouter1.setBackground(new java.awt.Color(0, 204, 0));
        btnAjouter1.setText("Ajouter");
        btnAjouter1.addActionListener(this::btnAjouter1ActionPerformed);

        btnModifier1.setBackground(new java.awt.Color(255, 255, 0));
        btnModifier1.setText("Modifier");
        btnModifier1.addActionListener(this::btnModifier1ActionPerformed);

        btnSupprimer1.setBackground(new java.awt.Color(255, 0, 0));
        btnSupprimer1.setText("Supprimer");
        btnSupprimer1.addActionListener(this::btnSupprimer1ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtNom)
                    .addComponent(txtTelephone)
                    .addComponent(btnAjouter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSupprimer1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(btnModifier1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAjouter1)
                        .addGap(18, 18, 18)
                        .addComponent(btnSupprimer1)
                        .addGap(18, 18, 18)
                        .addComponent(btnModifier1)))
                .addContainerGap(218, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lecteurs", jPanel2);

        tableEmprunts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tableEmprunts);

        comboLivres.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboLecteurs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Date d'emprunt");

        jLabel7.setText("Date de retour");

        btnEmprunter.setBackground(new java.awt.Color(255, 255, 0));
        btnEmprunter.setText("Ajouter Emprunt");
        btnEmprunter.addActionListener(this::btnEmprunterActionPerformed);

        btnRetourner.setBackground(new java.awt.Color(0, 204, 0));
        btnRetourner.setText("Livre retourné");
        btnRetourner.addActionListener(this::btnRetournerActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(btnEmprunter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRetourner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDateRetour)
                    .addComponent(txtDateEmprunt)
                    .addComponent(comboLecteurs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboLivres, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(comboLivres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboLecteurs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDateEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDateRetour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEmprunter)
                        .addGap(18, 18, 18)
                        .addComponent(btnRetourner)))
                .addContainerGap(203, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Registre des Emprunts", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAuteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAuteurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAuteurActionPerformed

    private void btnAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterActionPerformed
        String titre = txtTitre.getText();
        String auteur = txtAuteur.getText();
        String categorie = txtCategorie.getText();

        if (titre.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Le titre du livre est obligatoire !", "Erreur", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            java.sql.Connection con = DatabaseConnection.seConnecter();

            String sql = "INSERT INTO livres (titre, auteur, categorie) VALUES (?, ?, ?)";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, titre);
            pst.setString(2, auteur);
            pst.setString(3, categorie);

            pst.executeUpdate();

            javax.swing.JOptionPane.showMessageDialog(this, "Le livre a été ajouté avec succès !");
            
            afficherLivres();

            txtTitre.setText("");
            txtAuteur.setText("");
            txtCategorie.setText("");

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout : " + e.getMessage(), "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnAjouterActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed

    int ligneSelectionnee = tableLivres.getSelectedRow();

    if (ligneSelectionnee == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Veuillez sélectionner un livre dans le tableau !");
        return;
    }


    String id = tableLivres.getValueAt(ligneSelectionnee, 0).toString();

 
    int confirmation = javax.swing.JOptionPane.showConfirmDialog(this, 
        "Voulez-vous vraiment supprimer ce livre ?", "Confirmation", javax.swing.JOptionPane.YES_NO_OPTION);

    if (confirmation == javax.swing.JOptionPane.YES_OPTION) {
        try {
            java.sql.Connection con = DatabaseConnection.seConnecter();
            String sql = "DELETE FROM livres WHERE id_livre = ?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();

            javax.swing.JOptionPane.showMessageDialog(this, "Livre supprimé !");
            afficherLivres();

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage());
        }
    }
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void tableLivresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLivresMouseClicked
        int ligne = tableLivres.getSelectedRow();
        txtTitre.setText(tableLivres.getValueAt(ligne, 1).toString());
        txtAuteur.setText(tableLivres.getValueAt(ligne, 2).toString());
        txtCategorie.setText(tableLivres.getValueAt(ligne, 3).toString());
    }//GEN-LAST:event_tableLivresMouseClicked

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        int ligne = tableLivres.getSelectedRow();
        if (ligne == -1) return;

        String id = tableLivres.getValueAt(ligne, 0).toString();

        try {
            java.sql.Connection con = DatabaseConnection.seConnecter();
            String sql = "UPDATE livres SET titre=?, auteur=?, categorie=? WHERE id_livre=?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtTitre.getText());
            pst.setString(2, txtAuteur.getText());
            pst.setString(3, txtCategorie.getText());
            pst.setString(4, id);

            pst.executeUpdate();
            afficherLivres();
            javax.swing.JOptionPane.showMessageDialog(this, "Mise à jour réussie !");
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }//GEN-LAST:event_btnModifierActionPerformed

    private void btnAjouter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouter1ActionPerformed
        try {
            java.sql.Connection con = DatabaseConnection.seConnecter();
            String sql = "INSERT INTO lecteurs (nom_complet, telephone) VALUES (?, ?)";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, txtNom.getText());
            pst.setString(2, txtTelephone.getText());

            pst.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(this, "Lecteur enregistré !");

            txtNom.setText("");
            txtTelephone.setText("");
            afficherLecteurs();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAjouter1ActionPerformed

    private void btnEmprunterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmprunterActionPerformed
        // TODO add your handling code here:
        try {
            if (comboLivres.getSelectedItem() == null || comboLecteurs.getSelectedItem() == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Veuillez sélectionner un livre et un lecteur !");
                return;
            }

            String livreSelectionne = comboLivres.getSelectedItem().toString();
            String lecteurSelectionne = comboLecteurs.getSelectedItem().toString();

            int idLivre = Integer.parseInt(livreSelectionne.split(" - ")[0]);
            int idLecteur = Integer.parseInt(lecteurSelectionne.split(" - ")[0]);

            String dateEmprunt = txtDateEmprunt.getText();
            String dateRetour = txtDateRetour.getText();

            java.sql.Connection con = DatabaseConnection.seConnecter();

            String sqlEmprunt = "INSERT INTO emprunts (id_livre, id_lecteur, date_emprunt, date_retour_prevue, statut) VALUES (?, ?, ?, ?, 'En cours')";
            java.sql.PreparedStatement pst1 = con.prepareStatement(sqlEmprunt);
            pst1.setInt(1, idLivre);
            pst1.setInt(2, idLecteur);
            pst1.setString(3, dateEmprunt);
            pst1.setString(4, dateRetour);
            pst1.executeUpdate();

            String sqlLivre = "UPDATE livres SET disponible = false WHERE id_livre = ?";
            java.sql.PreparedStatement pst2 = con.prepareStatement(sqlLivre);
            pst2.setInt(1, idLivre);
            pst2.executeUpdate();

            javax.swing.JOptionPane.showMessageDialog(this, "Emprunt validé avec succès !");

            afficherEmprunts();
            afficherLivres();
            chargerListes();

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erreur lors de l'emprunt : " + e.getMessage());
        }
    }//GEN-LAST:event_btnEmprunterActionPerformed

    private void btnSupprimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimer1ActionPerformed
        // TODO add your handling code here:
        int ligne = tableLecteurs.getSelectedRow();
        if (ligne == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Sélectionnez un lecteur à supprimer.");
            return;
        }

        String id = tableLecteurs.getValueAt(ligne, 0).toString();
        int confirmation = javax.swing.JOptionPane.showConfirmDialog(this, "Supprimer ce lecteur ?", "Attention", javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirmation == javax.swing.JOptionPane.YES_OPTION) {
            try {
                java.sql.Connection con = DatabaseConnection.seConnecter();
                String sql = "DELETE FROM lecteurs WHERE id_lecteur = ?";
                java.sql.PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, id);
                pst.executeUpdate();

                afficherLecteurs();
                txtNom.setText("");
                txtTelephone.setText("");
                javax.swing.JOptionPane.showMessageDialog(this, "Lecteur supprimé.");
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Erreur (Vérifiez si ce lecteur n'a pas un emprunt en cours) : " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnSupprimer1ActionPerformed

    private void btnModifier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifier1ActionPerformed
        // TODO add your handling code here:
        int ligne = tableLecteurs.getSelectedRow();
        if (ligne == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Sélectionnez un lecteur à modifier.");
            return;
        }

        String id = tableLecteurs.getValueAt(ligne, 0).toString();

        try {
            java.sql.Connection con = DatabaseConnection.seConnecter();
            String sql = "UPDATE lecteurs SET nom_complet=?, telephone=? WHERE id_lecteur=?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, txtNom.getText());
            pst.setString(2, txtTelephone.getText());
            pst.setString(3, id);

            pst.executeUpdate();
            afficherLecteurs();
            javax.swing.JOptionPane.showMessageDialog(this, "Infos lecteur mises à jour !");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage());
        }
    }//GEN-LAST:event_btnModifier1ActionPerformed

    private void tableLecteursMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLecteursMouseClicked
        // TODO add your handling code here:
        int ligne = tableLecteurs.getSelectedRow();
        txtNom.setText(tableLecteurs.getValueAt(ligne, 1).toString());
        txtTelephone.setText(tableLecteurs.getValueAt(ligne, 2).toString());
    }//GEN-LAST:event_tableLecteursMouseClicked

    private void btnRetournerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetournerActionPerformed
        // TODO add your handling code here:
        try {
            int ligne = tableEmprunts.getSelectedRow();
            if (ligne == -1) {
                javax.swing.JOptionPane.showMessageDialog(this, "Sélectionnez l'emprunt à clôturer !");
                return;
            }

            String idEmprunt = tableEmprunts.getValueAt(ligne, 0).toString();
            String titreLivre = tableEmprunts.getValueAt(ligne, 1).toString();
            String amendeTexte = tableEmprunts.getValueAt(ligne, 6).toString();
            
            if (!amendeTexte.equals("0 FC")) {
                int choix = javax.swing.JOptionPane.showConfirmDialog(this, 
                    "Attention ! Ce lecteur est en retard et doit une amende de : " + amendeTexte + ".\n\nLe lecteur a-t-il payé cette somme ?", 
                    "Règlement de l'amende", 
                    javax.swing.JOptionPane.YES_NO_OPTION, 
                    javax.swing.JOptionPane.WARNING_MESSAGE);

                if (choix != javax.swing.JOptionPane.YES_OPTION) {
                    return; 
                }
            }
            java.sql.Connection con = DatabaseConnection.seConnecter();

            String sqlUpdateEmprunt = "UPDATE emprunts SET statut = 'Rendu' WHERE id_emprunt = ?";
            java.sql.PreparedStatement pst1 = con.prepareStatement(sqlUpdateEmprunt);
            pst1.setString(1, idEmprunt);
            pst1.executeUpdate();

            String sqlUpdateLivre = "UPDATE livres SET disponible = true WHERE titre = ?";
            java.sql.PreparedStatement pst2 = con.prepareStatement(sqlUpdateLivre);
            pst2.setString(1, titreLivre);
            pst2.executeUpdate();

            javax.swing.JOptionPane.showMessageDialog(this, "Le livre '" + titreLivre + "' a été rendu !");

            afficherEmprunts(); 
            afficherLivres();   
            chargerListes();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erreur lors du retour : " + e.getMessage());
        }
    }//GEN-LAST:event_btnRetournerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouter;
    private javax.swing.JButton btnAjouter1;
    private javax.swing.JButton btnEmprunter;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnModifier1;
    private javax.swing.JButton btnRetourner;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton btnSupprimer1;
    private javax.swing.JComboBox<String> comboLecteurs;
    private javax.swing.JComboBox<String> comboLivres;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tableEmprunts;
    private javax.swing.JTable tableLecteurs;
    private javax.swing.JTable tableLivres;
    private javax.swing.JTextField txtAuteur;
    private javax.swing.JTextField txtCategorie;
    private javax.swing.JTextField txtDateEmprunt;
    private javax.swing.JTextField txtDateRetour;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtTelephone;
    private javax.swing.JTextField txtTitre;
    // End of variables declaration//GEN-END:variables
}
