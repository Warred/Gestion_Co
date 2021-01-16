package gestion_co;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class detail_commande extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9089674941204792262L;
	DefaultTableModel model2 = new DefaultTableModel();
    JTable table2 = new JTable(model2);
    JScrollPane sp = new JScrollPane(table2);
	
    JButton bouton_ajouter_ligne =new JButton("Ajouter Ligne");
	JButton bouton_supprimer_ligne =new JButton("Supprimer Ligne");
	JButton bouton_modifier_ligne =new JButton("Modifier Ligne");
	JButton bouton_valider_ajout =new JButton("Valider ajout");
	JButton bouton_valider_modif =new JButton("Valider modif");
	JButton bouton_quitter_sans_valider =new JButton("Quitter sans valider");
	JButton bouton_valider_commande =new JButton("Valider commande");

	JLabel num_cde = new JLabel("CDE N° :");
	JLabel date_cde = new JLabel("DATE :");
	JLabel type_cde = new JLabel("TYPE :");
	JLabel id_tiers = new JLabel("ID :");
	JLabel nom = new JLabel("NOM :");
	JLabel prenom = new JLabel("PRENOM :");
	JLabel raison_social = new JLabel("RAISON SOCIALE :");
	JLabel adresse = new JLabel("ADRESSE :");
	JLabel cp = new JLabel("CP :");
	JLabel ville = new JLabel("VILLE :");
	JLabel ref_article = new JLabel("Réf. Article");
	JLabel qte_souhaitee = new JLabel("Qté souhaitée");
	JLabel px_unit = new JLabel("Prix unit.");
	JLabel chx_art = new JLabel("Sélectionner article");
	JLabel stk_dispo = new JLabel("Stock dispo.");
	JLabel label_statut_cde = new JLabel("Statut Cde.");
	JLabel label_total_ht = new JLabel("Total HT");
	JLabel label_tva = new JLabel("TVA");
	JLabel label_total_ttc = new JLabel("Total TTC");
	
	JTextField val_total_ht = new JTextField ();
	JTextField val_tva = new JTextField ();
	JTextField val_total_ttc = new JTextField ();
	
	JTextField val_num_cde = new JTextField ();
	JTextField val_date_cde = new JTextField ();
	JTextField val_type_cde = new JTextField ();
	JTextField val_id_tiers = new JTextField ();
	JTextField val_nom = new JTextField ();
	JTextField val_prenom = new JTextField ();
	JTextField val_raison = new JTextField ();
	JTextField val_adresse = new JTextField ();
	JTextField val_cp = new JTextField ();
	JTextField val_ville = new JTextField ();
	JTextField val_qte_cde = new JTextField ();
	JTextField val_ref_art = new JTextField ();
	JTextField val_pu = new JTextField ();
	JTextField val_stk_dispo = new JTextField ();
	JTextField val_statut_cde = new JTextField ();

	
	String id_ligne;
	
	JComboBox choix_article = new JComboBox();


	ArrayList<Object> article = new ArrayList<Object>();


	
	public detail_commande()  {
		JFrame f = new JFrame("Détail de commande");
		JPanel panel1 = new JPanel();

		panel1.setLayout(new GridBagLayout());
		GridBagConstraints gc2 = new GridBagConstraints();
		gc2.fill = GridBagConstraints.BOTH;
		gc2.weightx = 6;
		gc2.weighty = 10;
		gc2.insets = new Insets(5, 5, 5, 5);		
		
		//1ere ligne
		gc2.gridx = 0;
		gc2.gridy = 0;
		panel1.add(num_cde, gc2);
		gc2.gridx = 1;
		gc2.gridy = 0;
		val_num_cde.setText(gestion_commande.num_cde);
		val_num_cde.setEditable(false);
		panel1.add(val_num_cde, gc2);
		gc2.gridx = 2;
		gc2.gridy = 0;
		panel1.add(date_cde, gc2);

		gc2.gridx = 3;
		gc2.gridy = 0;
		val_date_cde.setText(gestion_commande.date_cde);
		val_date_cde.setEditable(false);
		panel1.add(val_date_cde, gc2);		
		gc2.gridx = 4;
		gc2.gridy = 0;
		panel1.add(type_cde, gc2);
		gc2.gridx = 5;
		gc2.gridy = 0;
		val_type_cde.setText(gestion_commande.type_cde);
		val_type_cde.setEditable(false);
		panel1.add(val_type_cde, gc2);
		
		//2eme ligne
		gc2.gridx = 0;
		gc2.gridy = 1;
		panel1.add(id_tiers, gc2);
		gc2.gridx = 1;
		gc2.gridy = 1;
		val_id_tiers.setText(gestion_commande.num_tiers);
		panel1.add(val_id_tiers, gc2);
		val_id_tiers.setEditable(false);
		gc2.gridx = 2;
		gc2.gridy = 1;
		panel1.add(nom, gc2);
		gc2.gridx = 3;
		gc2.gridy = 1;

		val_nom.setText(gestion_commande.nom_tiers);
		val_nom.setEditable(false);
		panel1.add(val_nom, gc2);
		
		gc2.gridx = 4;
		gc2.gridy = 1;
		panel1.add(prenom, gc2);
		gc2.gridx = 5;
		gc2.gridy = 1;
		val_prenom.setText(gestion_commande.prenom_tiers);
		val_prenom.setEditable(false);
		panel1.add(val_prenom, gc2);
	
		//3eme ligne
		gc2.gridx = 0;
		gc2.gridy = 2;
		panel1.add(raison_social, gc2);
		gc2.gridwidth = 3;
		gc2.gridx = 1;
		gc2.gridy = 2;
		val_raison.setText(gestion_commande.raison_tiers);
		val_raison.setEditable(false);
		panel1.add(val_raison, gc2);	
		gc2.gridwidth = 1;

		//4eme ligne
		gc2.gridx = 0;
		gc2.gridy = 3;
		panel1.add(adresse, gc2);
		gc2.gridwidth = 3;
		gc2.gridx = 1;
		gc2.gridy = 3;
		val_adresse.setText(gestion_commande.adresse_tiers);
		val_adresse.setEditable(false);
		panel1.add(val_adresse, gc2);
		gc2.gridwidth = 1;

		//5eme ligne	
		gc2.gridx = 0;
		gc2.gridy = 4;
		panel1.add(cp, gc2);
		gc2.gridx = 1;
		gc2.gridy = 4;
		val_cp.setText(gestion_commande.cp_tiers);
		val_cp.setEditable(false);
		panel1.add(val_cp, gc2);
		gc2.gridx = 2;
		gc2.gridy = 4;
		panel1.add(ville, gc2);
		gc2.gridx = 3;
		gc2.gridy = 4;
		panel1.add(val_ville, gc2);	
		val_ville.setText(gestion_commande.ville_tiers);
		val_ville.setEditable(false);
		
		gc2.gridx = 4;
		gc2.gridy = 3;
		panel1.add(label_statut_cde, gc2);
		gc2.gridx = 5;
		gc2.gridy = 3;
		panel1.add(val_statut_cde, gc2);	
		val_statut_cde.setText(gestion_commande.statut_cde);
		val_statut_cde.setEditable(false);
		
		//affichage des boutons
		gc2.gridx = 4;
		gc2.gridy = 4;
		gc2.gridwidth = 2;
		panel1.add(bouton_valider_commande, gc2);	
		 if (val_statut_cde.getText().contentEquals("VALIDEE")) {
			 bouton_ajouter_ligne.setVisible(false);
			 bouton_modifier_ligne.setVisible(false);
			 bouton_supprimer_ligne.setVisible(false);
			 bouton_valider_commande.setVisible(false);
		 } else {
			 bouton_valider_commande.setVisible(true);
		 }
		

		gc2.gridx = 0;
		gc2.gridy = 5;
		panel1.add(bouton_ajouter_ligne, gc2);
		gc2.gridx = 2;
		gc2.gridy = 5;
		panel1.add(bouton_modifier_ligne, gc2);
		gc2.gridx = 4;
		gc2.gridy = 5;
		panel1.add(bouton_supprimer_ligne, gc2);
		ajouter_ligne();
		
		gc2.gridwidth = 1;
		gc2.gridx = 1;
		gc2.gridy = 6;
		panel1.add(ref_article, gc2);
		ref_article.setVisible(false);
		gc2.gridx = 1;
		gc2.gridy = 7;
		panel1.add(val_ref_art, gc2);
		val_ref_art.setEditable(false);
		val_ref_art.setVisible(false);
		gc2.gridx = 0;
		gc2.gridy = 6;
		panel1.add(chx_art, gc2);
		chx_art.setVisible(false);
		gc2.gridx = 0;
		gc2.gridy = 7;
		panel1.add(choix_article, gc2);
		choix_article.setVisible(false);
		gc2.gridx = 2;
		gc2.gridy = 6;
		panel1.add(qte_souhaitee, gc2);
		qte_souhaitee.setVisible(false);
		gc2.gridx = 2;
		gc2.gridy = 7;
		panel1.add(val_qte_cde, gc2);
		val_qte_cde.setText("0");
		val_qte_cde.setVisible(false);
		gc2.gridx = 3;
		gc2.gridy = 6;
		panel1.add(stk_dispo, gc2);
		stk_dispo.setVisible(false);
		gc2.gridx = 3;
		gc2.gridy = 7;
		panel1.add(val_stk_dispo, gc2);
		val_stk_dispo.setVisible(false);
		val_stk_dispo.setEditable(false);
		gc2.gridx = 4;
		gc2.gridy = 6;
		panel1.add(px_unit, gc2);
		px_unit.setVisible(false);
		gc2.gridx = 4;
		gc2.gridy = 7;
		panel1.add(val_pu, gc2);
		val_pu.setEditable(false);
		val_pu.setVisible(false);
		gc2.gridx = 5;
		gc2.gridy = 7;
		panel1.add(bouton_valider_ajout, gc2);
		bouton_valider_ajout.setVisible(false);
		gc2.gridx = 5;
		gc2.gridy = 7;
		panel1.add(bouton_valider_modif, gc2);
		bouton_valider_modif.setVisible(false);
		
		gc2.gridx = 5;
		gc2.gridy = 8;
		panel1.add(bouton_quitter_sans_valider, gc2);
		bouton_quitter_sans_valider.setVisible(false);
		calcul_montants();
		gc2.gridx = 3;
		gc2.gridy = 9;
		panel1.add(label_total_ht, gc2);
		gc2.gridx = 3;
		gc2.gridy = 10;
		panel1.add(val_total_ht, gc2);
		gc2.gridx = 4;
		gc2.gridy = 9;
		panel1.add(label_tva, gc2);
		gc2.gridx = 4;
		gc2.gridy = 10;
		panel1.add(val_tva, gc2);
		gc2.gridx = 5;
		gc2.gridy = 9;
		panel1.add(label_total_ttc, gc2);
		gc2.gridx = 5;
		gc2.gridy = 10;
		panel1.add(val_total_ttc, gc2);
		charger_donnees();
		
		sp.setPreferredSize(new Dimension(300, 300));

		/* Définition de la fenêtre */
		f.setSize(1000, 600);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		f.setLayout(new BorderLayout());
		f.getContentPane().add(panel1, BorderLayout.NORTH);
		f.getContentPane().add(sp, BorderLayout.SOUTH);
		
		
		bouton_ajouter_ligne.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (val_statut_cde.getText().contentEquals("VALIDEE")) {
						showMessageDialog(null, "Vous ne pouvez pas modifier une commande validée.", "Erreur", ERROR_MESSAGE);
					} else {
					choix_article.setVisible(true);
					choix_article.setSelectedIndex(0);
					val_qte_cde.setVisible(true);
					val_qte_cde.setText(null);
					bouton_valider_ajout.setVisible(true);
					bouton_quitter_sans_valider.setVisible(true);
					val_ref_art.setText(null);
					ref_article.setVisible(true);
					val_ref_art.setVisible(true);
					qte_souhaitee.setVisible(true);
					val_pu.setText(null);
					val_pu.setVisible(true);
					px_unit.setVisible(true);
					chx_art.setVisible(true);
					stk_dispo.setVisible(true);
					val_stk_dispo.setText(null);
					val_stk_dispo.setVisible(true);
					sp.setPreferredSize(new Dimension(300,200));
					}
				}
		});
		 
		bouton_modifier_ligne.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int num_ligne = table2.getSelectedRow();
					
					if (num_ligne ==-1) {
						showMessageDialog(null, "Vous devez sélectionner au moins une ligne", "Erreur", ERROR_MESSAGE);
					} else if  (val_statut_cde.getText().contentEquals("VALIDEE")) {
						showMessageDialog(null, "Vous ne pouvez pas modifier une commande validée.", "Erreur", ERROR_MESSAGE);
					} else {
						id_ligne = table2.getValueAt(num_ligne,0).toString();
						String ref_cde = table2.getValueAt(num_ligne,1).toString();
						String ref_art = table2.getValueAt(num_ligne,2).toString();
						int ref_art2 = Integer.parseInt(ref_art);
						String lib_art = table2.getValueAt(num_ligne,3).toString();
						String pv_art = table2.getValueAt(num_ligne,4).toString();
						String qt_art = table2.getValueAt(num_ligne,5).toString();
						chx_art.setVisible(true);
						choix_article.setSelectedIndex(ref_art2);
						choix_article.setVisible(true);
						ref_article.setVisible(true);
						val_ref_art.setVisible(true);
						val_ref_art.setText(ref_art);
						px_unit.setVisible(true);
						val_pu.setText(pv_art);
						val_pu.setVisible(true);
						qte_souhaitee.setVisible(true);
						val_qte_cde.setVisible(true);
						val_qte_cde.setText(qt_art);
						stk_dispo.setVisible(true);
						val_stk_dispo.setVisible(true);
						bouton_valider_modif.setVisible(true);
						bouton_quitter_sans_valider.setVisible(true);
						sp.setPreferredSize(new Dimension(300, 200));

					}	
					}	
		});

		 
		
		
		
		 bouton_supprimer_ligne.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestion_commande.connection();
					PreparedStatement state2;
					int ligne_selctionnee = table2.getSelectedRow();
					
					if (val_statut_cde.getText().contentEquals("VALIDEE")) {
						showMessageDialog(null, "Vous ne pouvez pas modifier une commande validée.", "Erreur", ERROR_MESSAGE);
					} else if (ligne_selctionnee ==-1) {
						showMessageDialog(null, "Vous devez sélectionner au moins une ligne", "Erreur", ERROR_MESSAGE);
					} 
					
					else {
						int id_ligne = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(),0).toString());
						try {
							state2 = gestion_commande.conn.prepareStatement("DELETE FROM ligne_de_commande WHERE id_ligne_commande = ?");
							state2.setInt(1, id_ligne);
							int result2 = state2.executeUpdate();
							update_stock_transitionnel();
							maj_model2();
							calcul_montants();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
					}
				}
		});
		 

		 bouton_valider_ajout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(val_ref_art.getText().isEmpty()) {
						showMessageDialog(null, "Vous devez sélectionner au moins un article.", "Erreur", ERROR_MESSAGE);
					} else {
						
						gestion_commande.connection();
						PreparedStatement state2;
						int num_cde = Integer.parseInt(gestion_commande.num_cde);
						int ref_art = Integer.parseInt(val_ref_art.getText());
						String lib_art = choix_article.getSelectedItem().toString();
						float px_art = Float.parseFloat(val_pu.getText());
						int qte_art = Integer.parseInt(val_qte_cde.getText());
						
						if ( val_type_cde.getText().equals("VENTE")
								&& (qte_art> Integer.parseInt(val_stk_dispo.getText()) || qte_art <=0 || val_qte_cde.getText() ==null) ) {
							showMessageDialog(null, "La quantité doit être comprise entre 1 et la quantité en stock disponible.", "Erreur", ERROR_MESSAGE);
						} else {
						try {
						state2 = gestion_commande.conn.prepareStatement("INSERT INTO ligne_de_commande VALUES (default, ?, ?, ?, ?, ?)");
						state2.setInt(1, num_cde);
						state2.setInt(2, ref_art);
						state2.setString(3, lib_art);
						state2.setFloat(4, px_art);
						state2.setInt(5, qte_art);
						int result2 = state2.executeUpdate();
						update_stock_transitionnel();
						maj_model2();
						
						choix_article.setVisible(false);
						val_qte_cde.setVisible(false);
						bouton_valider_ajout.setVisible(false);
						bouton_quitter_sans_valider.setVisible(false);
						ref_article.setVisible(false);
						val_ref_art.setVisible(false);
						qte_souhaitee.setVisible(false);
						val_pu.setVisible(false);
						px_unit.setVisible(false);
						chx_art.setVisible(false);
						stk_dispo.setVisible(false);
						val_stk_dispo.setVisible(false);
						calcul_montants();
						sp.setPreferredSize(new Dimension(300, 300));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
				}
		});
		 
		 bouton_valider_modif.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestion_commande.connection();
					PreparedStatement state2;
					
					int id_ligne2 = Integer.parseInt(id_ligne);
					int num_cde = Integer.parseInt(gestion_commande.num_cde);
					int ref_art = Integer.parseInt(val_ref_art.getText());
					String lib_art = choix_article.getSelectedItem().toString();
					float px_art = Float.parseFloat(val_pu.getText());
					int qte_art = Integer.parseInt(val_qte_cde.getText());
					try {
					state2 = gestion_commande.conn.prepareStatement("UPDATE ligne_de_commande SET ref_commande = ?,"
							+ "ref_article = ?,"
							+ "lib_article = ?,"
							+ "px_vente = ?,"
							+ "qte_commande = ?"
							+ " where id_ligne_commande = ?");
					state2.setInt(1, num_cde);
					state2.setInt(2, ref_art);
					state2.setString(3, lib_art);
					state2.setFloat(4, px_art );
					state2.setInt(5, qte_art);
					state2.setInt(6, id_ligne2 );
					int result2 = state2.executeUpdate();
					maj_model2();
					update_stock_transitionnel();
					
					choix_article.setVisible(false);
					val_qte_cde.setVisible(false);
					bouton_modifier_ligne.setVisible(true);
					ref_article.setVisible(false);
					val_ref_art.setVisible(false);
					qte_souhaitee.setVisible(false);
					val_pu.setVisible(false);
					px_unit.setVisible(false);
					chx_art.setVisible(false);
					stk_dispo.setVisible(false);
					val_stk_dispo.setVisible(false);
					bouton_valider_modif.setVisible(false);
					bouton_quitter_sans_valider.setVisible(false);
					
					calcul_montants();
					sp.setPreferredSize(new Dimension(300, 300));
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		});
		 
		 bouton_quitter_sans_valider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					choix_article.setSelectedIndex(0);
					choix_article.setVisible(false);
					val_qte_cde.setVisible(false);
					bouton_valider_ajout.setVisible(false);
					bouton_valider_modif.setVisible(false);
					bouton_quitter_sans_valider.setVisible(false);
					ref_article.setVisible(false);
					val_ref_art.setVisible(false);
					val_ref_art.setText(null);
					qte_souhaitee.setVisible(false);
					val_pu.setVisible(false);
					px_unit.setVisible(false);
					val_pu.setText(null);
					chx_art.setVisible(false);
					stk_dispo.setVisible(false);
					val_stk_dispo.setText(null);
					val_stk_dispo.setVisible(false);
					sp.setPreferredSize(new Dimension(300, 300));

				}
		});
		 
		 bouton_valider_commande.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestion_commande.connection();
					PreparedStatement state2;
				
					int num_cde = Integer.parseInt(gestion_commande.num_cde);
					try {
					state2 = gestion_commande.conn.prepareStatement("UPDATE commande SET statut_commande = 'VALIDEE' WHERE ref_commande = ?;");
					state2.setInt(1, num_cde);
					int result2 = state2.executeUpdate();
					update_stock_dispo(num_cde);
					update_stock_transitionnel();
					
					val_statut_cde.setText("VALIDEE");
					bouton_valider_commande.setVisible(false);
					bouton_ajouter_ligne.setVisible(false);
					bouton_modifier_ligne.setVisible(false);
					bouton_supprimer_ligne.setVisible(false);
					
					gestion_commande.model.setColumnCount(0);
					gestion_commande.model.setRowCount(0);
					gestion_commande.afficher_table();
					sp.setPreferredSize(new Dimension(300, 300));
					
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		});
		 
		 choix_article.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestion_commande.connection();
					PreparedStatement state2;

					try {
					state2 = gestion_commande.conn.prepareStatement("select ref_article, px_vente, stock_dispo from article where lib_article = ?");
					String libelle = choix_article.getSelectedItem().toString();
					state2.setString(1, libelle);
					ResultSet result2 = state2.executeQuery();
					while (result2.next()) {
						val_ref_art.setText(result2.getString(1));
						val_pu.setText(result2.getString(2));
						val_stk_dispo.setText( result2.getString(3));
					}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		});
		 
	}



	
	public void charger_donnees () {
		gestion_commande.connection();
		PreparedStatement state2;
		int num_cde2= Integer.parseInt(gestion_commande.num_cde);
		try {
		state2 = gestion_commande.conn.prepareStatement("select * from ligne_de_commande where ref_commande = ?");
		state2.setInt(1, num_cde2);
		ResultSet result2 = state2.executeQuery();
		ResultSetMetaData resultMeta2 = result2.getMetaData();
			for (int i = 1; i <= resultMeta2.getColumnCount(); i++) {
				model2.addColumn(resultMeta2.getColumnName(i).toUpperCase());
			}
			while (result2.next()) {
				ArrayList<Object> o2 = new ArrayList<Object>();
				for (int i=1; i <=resultMeta2.getColumnCount(); i++) {
					o2.add(result2.getString(i));    
				}
				model2.insertRow(model2.getRowCount(), o2.toArray());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public void calcul_montants () {
		gestion_commande.connection();
		PreparedStatement state2;
		int num_cde2= Integer.parseInt(val_num_cde.getText().toString());
		float montant_ht;
		float montant_tva;
		float montant_ttc;
		try {
		state2 = gestion_commande.conn.prepareStatement("SELECT SUM(qte_commande*px_vente) from ligne_de_commande where ref_commande = ?");
		state2.setInt(1, num_cde2);
		ResultSet result2 = state2.executeQuery();
		
		 while(result2.next()) {
			 montant_ht=result2.getFloat(1);
			 val_total_ht.setText(montant_ht+"");
			 montant_tva=montant_ht/5;
			 val_tva.setText(montant_tva+"");
			 montant_ttc = montant_tva+montant_ht;
			 val_total_ttc.setText(montant_ttc+"");
			 }		 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void maj_model2 () {
		model2.setColumnCount(0);
		model2.setRowCount(0);
		charger_donnees();
	}	
	
		@SuppressWarnings("unchecked")
		public void ajouter_ligne () {

			try {
			Statement state = gestion_commande.conn.createStatement();
			String req = ("select lib_article from article order by ref_article;");
			ResultSet result = state.executeQuery(req);
			ResultSetMetaData resultMeta = result.getMetaData();
			article.clear();
			article.add("");
			while (result.next()) {
				for (int i=1; i <=resultMeta.getColumnCount(); i++) {
					article.add(result.getString(i));
				}				
			}
			DefaultComboBoxModel test = new DefaultComboBoxModel(article.toArray());
			choix_article.setModel(test);
			choix_article.setSelectedIndex(0);			

			result.close();
			state.close();
			} catch (Exception e) {
			e.printStackTrace();
			}					
		}
	
		public void update_stock_transitionnel() {
			String sql = "UPDATE article " + 
					"set stock_transitionnel = 0; " + 
					"UPDATE article " + 
					"SET stock_transitionnel = stock_transitionnel - ligne_de_commande.qte_commande " + 
					"from ligne_de_commande, commande " + 
					"where article.ref_article = ligne_de_commande.ref_article " + 
					"AND ligne_de_commande.ref_commande = commande.ref_commande " + 
					"and statut_commande = 'EN-COURS' " + 
					"and type_commande = 'VENTE'; " + 
					"UPDATE article " + 
					"SET stock_transitionnel = stock_transitionnel + ligne_de_commande.qte_commande " + 
					"from ligne_de_commande, commande " + 
					"where article.ref_article = ligne_de_commande.ref_article " + 
					"AND ligne_de_commande.ref_commande = commande.ref_commande " + 
					"and statut_commande = 'EN-COURS' " + 
					"and type_commande = 'ACHAT';";
			try {
				Statement stock_trans_update = gestion_commande.conn.createStatement();
				stock_trans_update.execute(sql);
				System.out.println("stock_transitionnel mis à jour");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		public void update_stock_dispo(int num_comm) {
			String signe;
			String type;
			String qte;
			String refArt;
			
			try {				
				Statement state = gestion_commande.conn.createStatement();
				ResultSet commande = state.executeQuery("select * from commande where ref_commande = " + num_comm);
				commande.next();
				type = commande.getObject("type_commande").toString();
				if (type.equals("ACHAT")) signe = "+";
				else signe = "-";
				
				String req_ligne_co = "select * from ligne_de_commande where " +
						 "ref_commande in (select ref_commande from commande where ref_commande = " + num_comm + ");";
				ResultSet ligne_commande = state.executeQuery(req_ligne_co);
				
				while (ligne_commande.next()) {					
					refArt = ligne_commande.getObject("ref_article").toString();
					qte = ligne_commande.getObject("qte_commande").toString();
					String sql = "UPDATE article " + 
							"SET stock_dispo = stock_dispo " + signe + " " + qte + 
							" where article.ref_article = " + refArt;
					state.execute(sql);
					System.out.println(sql);					
				}
				System.out.println("stock_dispo mis à jour");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	
	
	
	

}
