package gestion_co;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
import static javax.swing.JOptionPane.*;

public class gestion_commande {
	JTextField zone_texte1;
	JTable table, table2;
	static DefaultTableModel model;
	DefaultTableModel model2;
	JButton bouton_afficher_commande, bouton_creer_commnde, bouton_supprimer_commande, bouton_valider_recherche;
	static  int num_ligne = 0;
	static  String num_cde = "";
	static String date_cde ="";
	static String type_cde="";
	static String num_tiers="";
	static String statut_cde="";
	static Connection conn = null;
	static String nom_tiers;
	static String prenom_tiers;
	static String raison_tiers;
	static String adresse_tiers;
	static String cp_tiers;
	static String ville_tiers;
	int recherche_id_tiers;
	int recherche_id_pdt;
	static String passwd;

	JComboBox choix_article = new JComboBox();
	JComboBox choix_type_cde = new JComboBox();
	JComboBox choix_tiers = new JComboBox();
	JComboBox choix_statut_cde = new JComboBox();
	ArrayList<Object> article = new ArrayList<Object>();
	ArrayList<Object> tiers = new ArrayList<Object>();
	ArrayList<Object> type_de_cde = new ArrayList<Object>();
	ArrayList<Object> statut_de_cde = new ArrayList<Object>();
	JLabel filtre1 = new JLabel("Filtre Produits");
	JLabel filtre2 = new JLabel("Filtre Tiers");
	JLabel filtre3 = new JLabel("Filtre Type de Cde");
	JLabel filtre4 = new JLabel("Filtre Statut de Cde");
	
	
	
	
	

	public gestion_commande() {
		JFrame f = new JFrame("Gestion des commandes");
		JPanel panel1 = new JPanel();
		passwd = (String) JOptionPane.showInputDialog(f, "Entrez votre mot de passe");

		model = new DefaultTableModel();
	    table = new JTable(model);

	    
	    bouton_afficher_commande =new JButton("Afficher commande");
	    bouton_creer_commnde =new JButton("Créer commande");
	    bouton_supprimer_commande =new JButton("Supprimer commande");
		bouton_valider_recherche =new JButton("Valider recherche");

		panel1.setLayout(new GridBagLayout());
		GridBagConstraints gc2 = new GridBagConstraints();
		gc2.weightx = 4;
		gc2.weighty = 4;
		gc2.insets = new Insets(5, 5, 5, 5);
		combo_statut_cde();
		combo_type_cde();
		combo_choix_tiers();
		combo_choix_article();

		
		gc2.gridx = 0;
		gc2.gridy = 0;
		panel1.add(filtre1, gc2);
		gc2.gridx = 1;
		gc2.gridy = 0;
		panel1.add(filtre2, gc2);
		gc2.gridx = 2;
		gc2.gridy = 0;
		panel1.add(filtre3, gc2);
		gc2.gridx = 3;
		gc2.gridy = 0;
		panel1.add(filtre4, gc2);
		
		gc2.gridx = 0;
		gc2.gridy = 1;
		panel1.add(choix_article, gc2);
		gc2.gridx = 1;
		gc2.gridy = 1;
		panel1.add(choix_tiers, gc2);
		gc2.gridx = 2;
		gc2.gridy = 1;
		panel1.add(choix_type_cde, gc2);
		gc2.gridx = 3;
		gc2.gridy = 1;
		panel1.add(choix_statut_cde, gc2);
		
		
		
		gc2.gridx = 1;
		gc2.gridy = 2;
		panel1.add(bouton_valider_recherche, gc2);
		
		gc2.gridx = 0;
		gc2.gridy = 3;
		panel1.add(bouton_afficher_commande, gc2);
		gc2.gridx = 1;
		gc2.gridy = 3;
		panel1.add(bouton_creer_commnde, gc2);
		gc2.gridx = 2;
		gc2.gridy = 3;
		panel1.add(bouton_supprimer_commande, gc2);
		
		f.setLayout(new BorderLayout());
		f.getContentPane().add(panel1, BorderLayout.NORTH);
		f.getContentPane().add(new JScrollPane(table), BorderLayout.SOUTH);

		/* Définition de la fenêtre */
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 800);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		bouton_afficher_commande.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					num_ligne = table.getSelectedRow();
					if (num_ligne==-1) {
						showMessageDialog(null, "Vous devez sélectionner au moins une ligne", "Erreur", ERROR_MESSAGE);
					} else {
						num_cde = table.getValueAt(num_ligne,0).toString();
						date_cde = table.getValueAt(num_ligne, 1).toString();
						type_cde = table.getValueAt(num_ligne, 2).toString();
						num_tiers = table.getValueAt(num_ligne, 3).toString();
						statut_cde = table.getValueAt(num_ligne, 4).toString();
						renvoie_tiers();
						detail_commande c = new detail_commande();
					}
			
				}
		});

		bouton_creer_commnde.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						creer_commande cc1 = new creer_commande();
					}
		});
		  
		bouton_supprimer_commande.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					num_ligne = table.getSelectedRow();
					statut_cde = table.getValueAt(num_ligne, 4).toString();
					if (num_ligne==-1) {
						showMessageDialog(null, "Vous devez sélectionner au moins une ligne", "Erreur", ERROR_MESSAGE);
					} else if (statut_cde.contentEquals("VALIDEE")) {
							showMessageDialog(null, "Vous ne pouvez pas modifier une commande validée.", "Erreur", ERROR_MESSAGE);
					} else {	
						
					num_cde = table.getValueAt(num_ligne,0).toString();
					supprimer_cde ();
					model.setColumnCount(0);
					model.setRowCount(0);
					afficher_table();
				}
				}
		});
		  
		  
		  bouton_valider_recherche.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			gestion_commande.connection();
			Statement state2;
			
			String sql = "SELECT commande.ref_commande, date_commande, type_commande, commande.code_tiers, statut_commande"
				+ "  FROM commande join ligne_de_commande "
				+ "on commande.ref_commande = ligne_de_commande.ref_commande WHERE ";
			
			int ref_produit=0;
	        if (choix_article.getSelectedIndex() != 0) {
				String article = choix_article.getSelectedItem().toString();
			    String code_produit [] = article.split(" ");
				ref_produit=Integer.parseInt(code_produit[1]);
	        } 
	        int ref_tiers=0;
	        if (choix_tiers.getSelectedIndex() != 0) {
				String tiers = choix_tiers.getSelectedItem().toString();
				String code_tiers[] = tiers.split(" ");
				ref_tiers=Integer.parseInt(code_tiers[1]);
	        }

			String ref_type=choix_type_cde.getSelectedItem().toString();
			String ref_statut=choix_statut_cde.getSelectedItem().toString();
			
			try {
				state2 = conn.createStatement();
				if (!ref_type.equals("")) {
					sql += "commande.type_commande = '" + ref_type + "'";
				} else sql += "commande.type_commande IS NOT NULL ";

				if (ref_tiers != 0) {
					sql +=  " AND commande.code_tiers = " + ref_tiers;
				} else sql += " AND commande.code_tiers != 0 ";

				if (!ref_statut.equals("")) {
					sql += " AND commande.statut_commande = '" + ref_statut +"'";
				} else sql += " AND commande.statut_commande IS NOT NULL " ;

				if (ref_produit!= 0) {
					sql += " AND ligne_de_commande.ref_article = " + ref_produit;
				} else sql += " AND ligne_de_commande.ref_article != 0";
				sql += " group by commande.ref_commande order by commande.ref_commande";
				
				ResultSet result2 = state2.executeQuery(sql);
				ResultSetMetaData resultMeta = result2.getMetaData();
				DefaultTableModel newModel = new DefaultTableModel();
				// les entetes du tableau
				for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
					newModel.addColumn(resultMeta.getColumnName(i).toUpperCase());
				}
				//les donnees du tableau
				while (result2.next()) {
					ArrayList<Object> o = new ArrayList<Object>();
					for (int i=1; i <=resultMeta.getColumnCount(); i++) {
						o.add(result2.getString(i));    
					}
					newModel.insertRow(newModel.getRowCount(), o.toArray());
				}
				table.setModel(newModel);
				result2.close();
				state2.close();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
});
		  
		  
		  
		  
	}

	public static void connection() {
		try {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5432/base_gestion_co?currentSchema=schema_gestion_co";
		String user = "postgres";
		//String passwd = "Maelys06.";
		conn = DriverManager.getConnection(url, user, passwd);
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void renvoie_tiers () {
		connection();
		PreparedStatement state1;
		
		try {
			state1 = conn.prepareStatement("select * from tiers where code_tiers = ?");
			int num_tiers2= Integer.parseInt(num_tiers);
			state1.setInt(1, num_tiers2);
			ResultSet result1 = state1.executeQuery();
			while (result1.next()) {
				nom_tiers = result1.getString("nom");
				prenom_tiers = result1.getString("prenom");
				raison_tiers = result1.getString("raison_social");
				adresse_tiers = result1.getString("rue");
				cp_tiers = result1.getString("cp");
				ville_tiers = result1.getString("ville");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void supprimer_cde () {
		connection();
		PreparedStatement state3;
		PreparedStatement state4;
		try {
			state3 = conn.prepareStatement("DELETE FROM ligne_de_commande WHERE ref_commande = ?");
			int num_cde3= Integer.parseInt(num_cde);
			state3.setInt(1, num_cde3);
			int result3 = state3.executeUpdate();
			state4 = conn.prepareStatement("DELETE FROM commande WHERE ref_commande = ?");
			num_cde3= Integer.parseInt(num_cde);
			state4.setInt(1, num_cde3);
			int result4 = state4.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void afficher_table() {
		connection();
		try {
		Statement state = conn.createStatement();
		String req = ("select * from commande;");
		ResultSet result = state.executeQuery(req);
		ResultSetMetaData resultMeta = result.getMetaData();

		// les entetes du tableau
		for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
			model.addColumn(resultMeta.getColumnName(i).toUpperCase());
		}
		//les donnees du tableau
		while (result.next()) {
			ArrayList<Object> o = new ArrayList<Object>();
			for (int i=1; i <=resultMeta.getColumnCount(); i++) {
				o.add(result.getString(i));    
			}
			model.insertRow(model.getRowCount(), o.toArray());
		}
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
	public void combo_statut_cde () {
		statut_de_cde.add("");
		statut_de_cde.add("EN-COURS");	
		statut_de_cde.add("VALIDEE");	
		choix_statut_cde = new JComboBox(statut_de_cde.toArray());
		choix_statut_cde.setSelectedIndex(0);					
	}
	
	@SuppressWarnings("unchecked")
	public void combo_choix_tiers () {
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
	public void combo_choix_article () {
		gestion_commande.connection();
		try {
		Statement state = gestion_commande.conn.createStatement();
		String req = ("select ref_article, lib_article from article;");
		ResultSet result = state.executeQuery(req);
		ResultSetMetaData resultMeta = result.getMetaData();
		article.add("");
		while (result.next()) {
			String produits ="";
			for (int i=1; i <=resultMeta.getColumnCount(); i++) {
				if (result.getString(i)!= null) {
					produits = produits+" "+result.getString(i);
				}
				
			}	article.add(produits);			
		}
		choix_article = new JComboBox(article.toArray());
		choix_article.setSelectedIndex(0);
		result.close();
		state.close();
		} catch (Exception e) {
		e.printStackTrace();
		}					
	}
	
	
	
	public static void main(String[] args) {
		  gestion_commande g = new gestion_commande();
		  g.afficher_table();
	}
}