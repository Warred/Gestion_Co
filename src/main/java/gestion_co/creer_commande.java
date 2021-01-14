package gestion_co;
import static javax.swing.JOptionPane.*;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class creer_commande extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9089674941204792262L;
	DefaultTableModel model_creer_cde = new DefaultTableModel();
    JTable table_creer_cde = new JTable(model_creer_cde);
	
    JButton bouton_ajouter_ligne =new JButton("Ajouter Ligne");
	JButton bouton_supprimer_ligne =new JButton("Supprimer Ligne");
	JButton bouton_modifier_ligne =new JButton("Modifier Ligne");
	JButton bouton_valider_ajout =new JButton("Valider ajout");
	JButton bouton_valider_modif =new JButton("Valider modif");
	JButton bouton_quitter_sans_valid =new JButton("Quitter sans valider");
	JButton bouton_valider_entete_cde =new JButton("Valider en-tête de commande");
	JButton bouton_valider_commande =new JButton("Valider commande");
	JLabel num_cde = new JLabel("CDE N° :"); // ok
	JLabel date_cde = new JLabel("DATE :");
	JLabel type_cde = new JLabel("TYPE :");
	JLabel id_tiers = new JLabel("TIERS :");
	JLabel adresse = new JLabel("ADRESSE :");
	JLabel cp = new JLabel("CP :");
	JLabel ville = new JLabel("VILLE :");
	JLabel ref_article = new JLabel("Réf. Article");
	JLabel qte_souhaitee = new JLabel("Qté souhaitée");
	JLabel px_unit = new JLabel("Prix unit.");
	JLabel chx_art = new JLabel("Sélectionner article");
	JLabel stk_dispo = new JLabel("Stock dispo.");
	JLabel label_statut_cde = new JLabel("Statut Cde.");
	JTextField val_num_cde = new JTextField ();
	JTextField val_date_cde = new JTextField ();
	JTextField val_type_cde = new JTextField ();
	JTextField val_qte_cde = new JTextField ();
	JTextField val_ref_art = new JTextField ();
	JTextField val_pu = new JTextField ();
	JTextField val_stk_dispo = new JTextField ();
	
	JTextField val_code_tiers = new JTextField ();
	JTextField val_nom = new JTextField ();
	JTextField val_prenom = new JTextField ();
	JTextField val_raison_social = new JTextField ();
	JTextField val_adresse = new JTextField ();
	JTextField val_cp = new JTextField ();
	JTextField val_ville = new JTextField ();
	JTextField val_tiers = new JTextField ();
	JTextField val_type = new JTextField ();
	JTextField val_statut_cde = new JTextField ();
	String id_ligne;
	
	JComboBox choix_article = new JComboBox();
	JComboBox choix_type_cde = new JComboBox();
	JComboBox choix_tiers = new JComboBox();
	JComboBox combo_remise = new JComboBox();
	ArrayList<Object> article = new ArrayList<Object>();
	ArrayList<Object> tiers = new ArrayList<Object>();
	ArrayList<Object> type_de_cde = new ArrayList<Object>();
	ArrayList<Object> array_remise = new ArrayList<Object>();
	
	public creer_commande()  {
		JFrame f = new JFrame("Création commande");
		JPanel panel1 = new JPanel();

		panel1.setLayout(new GridBagLayout());
		GridBagConstraints gc2 = new GridBagConstraints();
		gc2.fill = GridBagConstraints.BOTH;
		gc2.weightx = 6;
		gc2.weighty = 8;
		gc2.insets = new Insets(5, 5, 5, 5);
		
		//1ere ligne
		combo_type_cde();
		gc2.gridx = 0;
		gc2.gridy = 0;
		panel1.add(num_cde, gc2);
		gc2.gridx = 1;
		gc2.gridy = 0;
		val_num_cde.setText("default");
		val_num_cde.setEditable(false);
		panel1.add(val_num_cde, gc2);
		gc2.gridx = 2;
		gc2.gridy = 0;
		panel1.add(date_cde, gc2);
		gc2.gridx = 3;
		gc2.gridy = 0;
		val_date_cde.setText(aujourdhui().toString());
		panel1.add(val_date_cde, gc2);		
		gc2.gridx = 4;
		gc2.gridy = 0;
		panel1.add(type_cde, gc2);
		gc2.gridx = 5;
		gc2.gridy = 0;
		panel1.add(choix_type_cde, gc2);
		gc2.gridx = 5;
		gc2.gridy = 0;
		panel1.add(val_type, gc2);
		val_type.setVisible(false);
		
		
		//2eme ligne
		combo_tiers();
		gc2.gridx = 0;
		gc2.gridy = 1;
		panel1.add(id_tiers, gc2);
		gc2.gridx = 1;
		gc2.gridy = 1;
		gc2.gridwidth = 3;
		panel1.add(choix_tiers, gc2);
		gc2.gridx = 1;
		gc2.gridy = 1;
		gc2.gridwidth = 3;
		panel1.add(val_tiers, gc2);
		val_tiers.setVisible(false);

		//3eme ligne
		gc2.gridx = 0;
		gc2.gridy = 2;
		panel1.add(adresse, gc2);
		gc2.gridx = 1;
		gc2.gridy = 2;
		gc2.gridwidth = 3;
		val_adresse.setEditable(false);
		panel1.add(val_adresse, gc2);
		gc2.gridwidth = 1;

		//4eme ligne	
		gc2.gridx = 0;
		gc2.gridy = 3;
		panel1.add(cp, gc2);
		gc2.gridx = 1;
		gc2.gridy = 3;
		val_cp.setEditable(false);
		panel1.add(val_cp, gc2);
		gc2.gridx = 2;
		gc2.gridy = 3;
		panel1.add(ville, gc2);
		gc2.gridx = 3;
		gc2.gridy = 3;
		panel1.add(val_ville, gc2);	
		val_ville.setEditable(false);
		
		//affichage des boutons
		gc2.gridwidth = 2;
		gc2.gridx = 0;
		gc2.gridy = 4;
		panel1.add(bouton_ajouter_ligne, gc2);
		bouton_ajouter_ligne.setVisible(false);
		gc2.gridwidth = 2;
		gc2.gridx = 2;
		gc2.gridy = 4;
		panel1.add(bouton_valider_entete_cde, gc2);
		gc2.gridx = 2;
		gc2.gridy = 4;
		panel1.add(bouton_modifier_ligne, gc2);
		bouton_modifier_ligne.setVisible(false);
		gc2.gridx = 4;
		gc2.gridy = 4;
		panel1.add(bouton_supprimer_ligne, gc2);
		bouton_supprimer_ligne.setVisible(false);
		
		gc2.gridx = 5;
		gc2.gridy = 2;
		gc2.gridwidth = 1;
		panel1.add(val_statut_cde, gc2);
		val_statut_cde.setEditable(false);
		gc2.gridx = 4;
		gc2.gridy = 2;
		gc2.gridwidth = 1;
		panel1.add(label_statut_cde, gc2);
	
		
		
		
		
		gc2.gridx = 4;
		gc2.gridy = 3;
		gc2.gridwidth = 2;
		panel1.add(bouton_valider_commande, gc2);
		bouton_valider_commande.setVisible(false);
		
				
		
		
		combo_article();

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
		panel1.add(bouton_quitter_sans_valid, gc2);
		bouton_quitter_sans_valid.setVisible(false);
		
		
		
		f.setLayout(new BorderLayout());
		f.getContentPane().add(panel1, BorderLayout.NORTH);
		f.getContentPane().add(new JScrollPane(table_creer_cde), BorderLayout.SOUTH);

		/* Définition de la fenêtre */
		f.setSize(1000, 800);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		
		bouton_ajouter_ligne.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					choix_article.setVisible(true);
					val_qte_cde.setVisible(true);
					val_qte_cde.setText(null);
					bouton_valider_ajout.setVisible(true);
					ref_article.setVisible(true);
					val_ref_art.setText(null);
					val_ref_art.setVisible(true);
					
					qte_souhaitee.setVisible(true);
					val_pu.setVisible(true);
					px_unit.setVisible(true);
					chx_art.setVisible(true);
					stk_dispo.setVisible(true);
					val_stk_dispo.setText(null);
					val_stk_dispo.setVisible(true);
					bouton_quitter_sans_valid.setVisible(true);
				
				}
		});
		 
		bouton_modifier_ligne.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int num_ligne = table_creer_cde.getSelectedRow();
					
					if (num_ligne ==-1) {
						showMessageDialog(null, "Vous devez sélectionner au moins une ligne", "Erreur", ERROR_MESSAGE);
					}else {
						id_ligne = table_creer_cde.getValueAt(num_ligne,0).toString();
						String ref_cde = table_creer_cde.getValueAt(num_ligne,1).toString();
						String ref_art = table_creer_cde.getValueAt(num_ligne,2).toString();
						int ref_art2 = Integer.parseInt(ref_art);
						String lib_art = table_creer_cde.getValueAt(num_ligne,3).toString();
						String pv_art = table_creer_cde.getValueAt(num_ligne,4).toString();
						String qt_art = table_creer_cde.getValueAt(num_ligne,5).toString();
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
						bouton_quitter_sans_valid.setVisible(true);
					}	
					}	
		});

		 bouton_supprimer_ligne.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestion_commande.connection();
					PreparedStatement state2;
					int ligne_selctionnee = table_creer_cde.getSelectedRow();
					if (ligne_selctionnee ==-1) {
						showMessageDialog(null, "Vous devez sélectionner au moins une ligne", "Erreur", ERROR_MESSAGE);
					} else {
						int id_ligne = Integer.parseInt(table_creer_cde.getValueAt(table_creer_cde.getSelectedRow(),0).toString());
						try {
							System.out.println(id_ligne);
							state2 = gestion_commande.conn.prepareStatement("DELETE FROM ligne_de_commande WHERE id_ligne_commande = ?");
							state2.setInt(1, id_ligne);
							int result2 = state2.executeUpdate();
							
							update_stock_transitionnel();
							
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						model_creer_cde.setColumnCount(0);
						model_creer_cde.setRowCount(0);
						charger_donnees();
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
						int num_cde = Integer.parseInt(val_num_cde.getText());
						int ref_art = Integer.parseInt(val_ref_art.getText());
						String lib_art = choix_article.getSelectedItem().toString();
						float px_art = Float.parseFloat(val_pu.getText());
						int qte_art = Integer.parseInt(val_qte_cde.getText());
						System.out.println(val_type.getText());
						if ( val_type.getText().equals("VENTE") && 
								(qte_art> Integer.parseInt(val_stk_dispo.getText()) || qte_art <=0 || val_qte_cde.getText() ==null) ) {
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
						
						choix_article.setVisible(false);
						val_qte_cde.setVisible(false);
						bouton_valider_ajout.setVisible(false);
						ref_article.setVisible(false);
						val_ref_art.setVisible(false);
						qte_souhaitee.setVisible(false);
						qte_souhaitee.setText("");
						val_pu.setVisible(false);
						px_unit.setVisible(false);
						chx_art.setVisible(false);
						stk_dispo.setVisible(false);
						val_stk_dispo.setVisible(false);
						model_creer_cde.setColumnCount(0);
						model_creer_cde.setRowCount(0);
						charger_donnees();
						bouton_quitter_sans_valid.setVisible(false);
						
						
						
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
					
					update_stock_transitionnel();
					
					choix_article.setVisible(false);
					val_qte_cde.setVisible(false);
					bouton_valider_modif.setVisible(false);
					ref_article.setVisible(false);
					val_ref_art.setVisible(false);
					qte_souhaitee.setVisible(false);
					val_pu.setVisible(false);
					px_unit.setVisible(false);
					chx_art.setVisible(false);
					stk_dispo.setVisible(false);
					val_stk_dispo.setVisible(false);
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		});
		 
		 bouton_quitter_sans_valid.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
		
					choix_article.setVisible(false);
					val_qte_cde.setVisible(false);
					bouton_valider_ajout.setVisible(false);
					bouton_valider_modif.setVisible(false);
					bouton_quitter_sans_valid.setVisible(false);
					bouton_valider_entete_cde.setVisible(false);
					ref_article.setVisible(false);
					val_ref_art.setVisible(false);
					qte_souhaitee.setVisible(false);
					val_pu.setVisible(false);
					px_unit.setVisible(false);
					chx_art.setVisible(false);
					stk_dispo.setVisible(false);
					val_stk_dispo.setVisible(false);
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
		 
		 choix_tiers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestion_commande.connection();
					PreparedStatement state2;
					try {
						String tiers = choix_tiers.getSelectedItem().toString();
						String separateur = " ";
				        String code_tiers[] = tiers.split(separateur);
						//state2.setInt(1, Integer.parseInt(code_tiers[1]));
				
					state2 = gestion_commande.conn.prepareStatement("select * from tiers where code_tiers = ?");
					state2.setInt(1, Integer.parseInt(code_tiers[1]));
					ResultSet result2 = state2.executeQuery();
					while (result2.next()) {					
						 val_code_tiers.setText(result2.getString(1));
						 val_nom.setText(result2.getString(2));
						 val_prenom.setText(result2.getString(3)); 
						 val_raison_social.setText(result2.getString(4)); 
						 val_adresse.setText(result2.getString(5));
						 val_cp.setText(result2.getString(6));
						 val_ville.setText(result2.getString(7));
					}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		});
		 
		 bouton_valider_commande.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int reponse=showConfirmDialog(null,"Si vous validez la commande, vous le pourrez plus la modifier !", "Attention !!", YES_NO_OPTION);
					if (reponse == JOptionPane.YES_OPTION) {
						gestion_commande.connection();
						PreparedStatement state2;
					
						int num_cde = Integer.parseInt(val_num_cde.getText());
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
						
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
		});
		 
		 bouton_valider_entete_cde.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestion_commande.connection();
					PreparedStatement state2;
					PreparedStatement state3;
					LocalDate cde1 =  aujourdhui();
					String cde2 = choix_type_cde.getSelectedItem().toString();
					int cde3;

					if (choix_tiers.getSelectedItem().toString()!="" && choix_type_cde.getSelectedItem().toString()!="") {
							cde3 = Integer.parseInt(val_code_tiers.getText());
							try {
								state2 = gestion_commande.conn.prepareStatement("INSERT INTO COMMANDE VALUES (default, ? , ?, ?, 'EN-COURS')");
								state2.setDate(1, java.sql.Date.valueOf(cde1.toString()));
								state2.setString(2, cde2);
								state2.setInt(3, cde3);
								int result2 = state2.executeUpdate();

								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							int cde;
							try {
								state3 = gestion_commande.conn.prepareStatement("select ref_commande from commande order by ref_commande desc limit 1;");
								ResultSet result3 = state3.executeQuery();
								 while(result3.next()) {
									cde=result3.getInt(1);
									val_num_cde.setText(cde+"");
								 }
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							bouton_ajouter_ligne.setVisible(true);
							bouton_modifier_ligne.setVisible(true);
							bouton_supprimer_ligne.setVisible(true);
							bouton_valider_entete_cde.setVisible(false);
							val_tiers.setText(choix_tiers.getSelectedItem().toString());
							val_type.setText(choix_type_cde.getSelectedItem().toString());
							choix_tiers.setVisible(false);
							val_tiers.setEditable(false);	
							val_tiers.setVisible(true);
							val_date_cde.setEditable(false);
							choix_type_cde.setVisible(false);
							val_type.setVisible(true);
							val_statut_cde.setText("EN-COURS");
							val_type.setEditable(false);
	
							bouton_valider_commande.setVisible(true);
							gestion_commande.model.setColumnCount(0);
							gestion_commande.model.setRowCount(0);
							gestion_commande.afficher_table();
					} else {
						showMessageDialog(null, "Vous devez selectionner au moins un type de commande et un tiers.", "Erreur", ERROR_MESSAGE);
						
					}
					
					
				}
		});
		
		 
		 
	}

	
		public LocalDate aujourdhui() {
			//return (Date)LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			return LocalDate.now();
		}
	
		@SuppressWarnings("unchecked")
		public void combo_article () {
			gestion_commande.connection();
			try {
			Statement state = gestion_commande.conn.createStatement();
			String req = ("select lib_article from article;");
			ResultSet result = state.executeQuery(req);
			ResultSetMetaData resultMeta = result.getMetaData();
			article.add("");
			while (result.next()) {
				for (int i=1; i <=resultMeta.getColumnCount(); i++) {
					article.add(result.getString(i));
				}				
			}
			choix_article = new JComboBox(article.toArray());
			choix_article.setSelectedIndex(0);
			

			result.close();
			state.close();
			} catch (Exception e) {
			e.printStackTrace();
			}					
		}
	
		@SuppressWarnings("unchecked")
		public void combo_tiers () {
			gestion_commande.connection();
			try {
			Statement state = gestion_commande.conn.createStatement();
			String req = ("select code_tiers, nom, prenom, raison_social from tiers;");
			ResultSet result = state.executeQuery(req);
			ResultSetMetaData resultMeta = result.getMetaData();
			tiers.add("");
			while (result.next()) {
				String nom ="";
				for (int i=1; i <=resultMeta.getColumnCount(); i++) {
					if (result.getString(i)!= null) {
						nom = nom+" "+result.getString(i);
					}
					
				}	tiers.add(nom);			
			}
			choix_tiers = new JComboBox(tiers.toArray());
			choix_tiers.setSelectedIndex(0);
			

			result.close();
			state.close();
			} catch (Exception e) {
			e.printStackTrace();
			}					
		}
	
		@SuppressWarnings("unchecked")
		public void combo_type_cde () {
			type_de_cde.add("");
			type_de_cde.add("ACHAT");	
			type_de_cde.add("VENTE");	
			choix_type_cde = new JComboBox(type_de_cde.toArray());
			choix_type_cde.setSelectedIndex(0);					
		}
	
		@SuppressWarnings("unchecked")
		public void combo_remise () {
			array_remise.add("");
			array_remise.add("1%");	
			array_remise.add("3%");
			
			choix_type_cde = new JComboBox(type_de_cde.toArray());
			choix_type_cde.setSelectedIndex(0);					
		}
		

		public void charger_donnees () {
			gestion_commande.connection();
			PreparedStatement state2;
			int num_cde2= Integer.parseInt(val_num_cde.getText());
			try {
			state2 = gestion_commande.conn.prepareStatement("select * from ligne_de_commande where ref_commande = ?");
			state2.setInt(1, num_cde2);
			ResultSet result2 = state2.executeQuery();
			ResultSetMetaData resultMeta2 = result2.getMetaData();
				for (int i = 1; i <= resultMeta2.getColumnCount(); i++) {
					model_creer_cde.addColumn(resultMeta2.getColumnName(i).toUpperCase());
				}
				while (result2.next()) {
					ArrayList<Object> o2 = new ArrayList<Object>();
					for (int i=1; i <=resultMeta2.getColumnCount(); i++) {
						o2.add(result2.getString(i));    
					}
					model_creer_cde.insertRow(model_creer_cde.getRowCount(), o2.toArray());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
