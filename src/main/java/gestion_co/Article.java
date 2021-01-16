package gestion_co;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Article extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int lastIndexArt = -1;
	JTextField libelle = new JTextField(15);
	JTextField pxAchat = new JTextField(15);
	JTextField pxVente = new JTextField(15);
	JTextField stockDispo = new JTextField(15);
	JButton addArticle = new JButton("Ajouter");
	JButton editArticle = new JButton("Modifier");
	JButton resetArticle = new JButton("Réinitialiser");
	JTextField recherche;
	JButton searchArticle;
	DefaultTableModel modelArt = new DefaultTableModel();
	JTable jtableArt = new JTable(modelArt);
	JPanel panelTextArticle = new JPanel();
	
	public Article() {
		JFrame f = new JFrame("Articles");
		f.setSize(1000, 500);

 	    JPanel panelFond = new JPanel();//Un panel est un espace dans lequel on peut placer des composants graphiques
 	    panelFond.setLayout(new GridLayout(2, 1));
 	    
 	    JPanel panelHaut = new JPanel();
 	    panelHaut.setLayout(new GridLayout(1,4)); 	    
 	    JPanel panelLabel = new JPanel();
 	    panelLabel.setLayout(new GridLayout(7,1));    
 	    
 	    JLabel videLabel = new JLabel("");
 	    JLabel videlabel2 = new JLabel("");
 		JLabel label_lib_article = new JLabel("Libellé :");
 		JLabel label_px_achat = new JLabel("Prix d'achat :");
 		JLabel label_px_vente = new JLabel("Prix de vente :");
 		JLabel label_stock_dispo = new JLabel("Stock disponible :");
 		
 		JPanel align = new JPanel();
	    align.setLayout(new BorderLayout());
	    align.add(videlabel2, BorderLayout.EAST);
	    panelLabel.add(align);
 		JPanel align0 = new JPanel();
 		align0.setLayout(new BorderLayout());
 		align0.add(videLabel, BorderLayout.EAST);
 		panelLabel.add(align0);
 	    JPanel align1 = new JPanel();
 	    align1.setLayout(new BorderLayout());
 	    align1.add(label_lib_article, BorderLayout.EAST);
 	    panelLabel.add(align1); 	    
 	    JPanel align2 = new JPanel();
	    align2.setLayout(new BorderLayout());
	    align2.add(label_px_achat, BorderLayout.EAST);
	    panelLabel.add(align2); 	    
	    JPanel align3 = new JPanel();
 	    align3.setLayout(new BorderLayout());
 	    align3.add(label_px_vente, BorderLayout.EAST);
 	    panelLabel.add(align3); 	    
 	    JPanel align4 = new JPanel();
	    align4.setLayout(new BorderLayout());
	    align4.add(label_stock_dispo, BorderLayout.EAST);
	    panelLabel.add(align4); 	     	    
 	    
 	    panelTextArticle.setLayout(new GridLayout(7,1));
 	    JPanel videTextUp = new JPanel();
 	    JLabel videT1 = new JLabel("");
 	    videTextUp.add(videT1);
 	    panelTextArticle.add(videTextUp);
 	    
 	    JPanel txt = new JPanel();
	    txt.add(new JLabel(""));
	    panelTextArticle.add(txt);
 	    JPanel txt1 = new JPanel();
 	    txt1.add(libelle);
 	    panelTextArticle.add(txt1); 	    
 	    JPanel txt2 = new JPanel();
	    txt2.add(pxAchat);
	    panelTextArticle.add(txt2);	    
	    JPanel txt3 = new JPanel();
 	    txt3.add(pxVente);
 	    panelTextArticle.add(txt3); 	    	    
	    JPanel txt4 = new JPanel();
	    txt4.add(stockDispo);
	    panelTextArticle.add(txt4);
 	    
 	    JPanel panelButton = new JPanel();
	    panelButton.setLayout(new GridLayout(4,1));
	    
	    JPanel vide3 = new JPanel();
	    panelButton.add(vide3);
	    JPanel but1 = new JPanel();
	    but1.add(addArticle);
	    panelButton.add(but1);	    
	    JPanel but2 = new JPanel();
	    but2.add(editArticle);
	    panelButton.add(but2);
	    JPanel vide4 = new JPanel();
	    panelButton.add(vide4);
	    
	    JPanel panelDroite = new JPanel(new GridBagLayout());
	    
	    GridBagConstraints c = new GridBagConstraints();
	    c.weightx = 1;
	    c.weighty = 0.2;
	    c.fill = GridBagConstraints.NONE;
	    
	    JLabel videArtUp = new JLabel("");
	    c.gridx = 0;
	    c.gridy = 0;
	    panelDroite.add(videArtUp, c);	
	    
	    JLabel search = new JLabel("Rechercher par nom :");
	    c.gridx = 0;
	    c.gridwidth = 0;
	    c.gridy = 1;
	    panelDroite.add(search, c);
	    
	    recherche = new JTextField(15);
	    c.gridx = 0;
	    c.gridy = 2;
	    panelDroite.add(recherche, c);
	    
	    searchArticle = new JButton("Rechercher");
	    c.gridx = 0;
	    c.gridy = 3;
	    panelDroite.add(searchArticle, c);
	    
	    c.gridx = 0;
	    c.gridy = 4;
	    panelDroite.add(resetArticle, c);
	    
	    JLabel videArtDown1 = new JLabel("");
	    c.gridx = 0;
	    c.gridy = 5;
	    panelDroite.add(videArtDown1, c);
	    
	    JLabel videArtDown2 = new JLabel("");
	    c.gridx = 0;
	    c.gridy = 6;
	    panelDroite.add(videArtDown2, c);
   
	    
	    panelHaut.add(panelLabel);
	    panelHaut.add(panelTextArticle);
	    panelHaut.add(panelButton);
	    panelHaut.add(panelDroite);
	    
	    modelArt = getModel("article");
		jtableArt.setModel(modelArt);
		JScrollPane sp = new JScrollPane(jtableArt);
		
	    Border bord = BorderFactory.createLineBorder(Color.black, 3);
	    sp.setBorder(bord);
	    
	    panelFond.add(panelHaut);
	    panelFond.add(sp);	    
	    			
	    f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	    f.add(panelFond);
	    
	    addArticle.addActionListener(this);
	    editArticle.addActionListener(this);
	    searchArticle.addActionListener(this);
	    resetArticle.addActionListener(this);
	    jtableArt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent event) {
	        	int index = jtableArt.getSelectedRow();
	            if (index != lastIndexArt && index > -1) {
	                libelle.setText(jtableArt.getValueAt(index, 1).toString());
	                pxAchat.setText(jtableArt.getValueAt(index, 2).toString());
	                pxVente.setText(jtableArt.getValueAt(index, 3).toString());
	                stockDispo.setText(jtableArt.getValueAt(index, 5).toString());
	            }
	            lastIndexArt = index;
	        }
	    });
	}	    

	public void actionPerformed(ActionEvent clic) {
		// TODO Auto-generated method stub
		if (clic.getSource() == addArticle) {			
			if (formulaireFull(panelTextArticle)) {				
				if(insertSQL("article", recupText(panelTextArticle))) {
					modelArt = getModel("article");
					jtableArt.setModel(modelArt);
					videText(panelTextArticle);
					gestion_commande.combo_choix_article();
				}
			} else JOptionPane.showMessageDialog(this, "Remplir tout les renseignements pour ajouter un article");
		
		} else if (clic.getSource() == searchArticle) {
			String str = recherche.getText().toLowerCase();
			if (!str.isEmpty()) {
				boolean trouve = false;
				String nomTable = "article where lib_article ilike '%" + str + "%'";
				
				for (int i = 0; i < modelArt.getRowCount(); i++) {
					if (modelArt.getValueAt(i, 1).toString().toLowerCase().contains(str)) {						
						trouve = true;
					}
				}
				if (trouve) {
					DefaultTableModel modelRech = getModel(nomTable);
					jtableArt.setModel(modelRech);
				} else{
					JOptionPane.showMessageDialog(this, "Le nom '" + str +"' n'est pas dans le tableau");
					jtableArt.setModel(modelArt);
				}
			} else{
				JOptionPane.showMessageDialog(this, "Entrer un nom dans la recherche");
				jtableArt.setModel(modelArt);
			}			
			
		} else if (clic.getSource() == editArticle) {
			if (jtableArt.getSelectedRow() != -1) {
				if (formulaireFull(panelTextArticle)) {
					if(updateSQL("article", recupText(panelTextArticle), jtableArt.getValueAt(jtableArt.getSelectedRow(), 0).toString())) {
						modelArt = getModel("article");
						jtableArt.setModel(modelArt);
						videText(panelTextArticle);
						gestion_commande.combo_choix_article();
					}
				} else JOptionPane.showMessageDialog(this, "Remplir tout les renseignements pour ajouter un article");
			} else JOptionPane.showMessageDialog(this, "Selectionner un article à modifier dans le JTable");
		} else if (clic.getSource() == resetArticle) {
			modelArt = getModel("article");
			jtableArt.setModel(modelArt);	
		}
	}
	
	private boolean insertSQL(String nomTable, ArrayList<String> valeurs) {
		String sql = "INSERT INTO " + nomTable + " VALUES (default";
		for (String val : valeurs) sql += ",'" + val +"'";
		sql += ");";
		System.out.println(sql);
		try {
			Statement state = gestion_commande.conn.createStatement();
			state.execute(sql);
			JOptionPane.showMessageDialog(this, "Ajout dans la table " + nomTable + " réussi");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
			return false;
		}
		return true;
	}

	private boolean updateSQL(String nomTable, ArrayList<String> valeurs, String serial) {
		String sql = "UPDATE " + nomTable + " SET  lib_article = '" + valeurs.get(0) + "', px_achat = '" + valeurs.get(1);
		sql += "', px_vente = '" + valeurs.get(2) + "', stock_transitionnel = '" + valeurs.get(3);
		sql += "', stock_dispo = '" + valeurs.get(4) + "' ";
		sql += "WHERE ref_article = " + serial;

		System.out.println(sql);
		try {
			Statement state = gestion_commande.conn.createStatement();
			state.execute(sql);
			JOptionPane.showMessageDialog(this, "Modification dans la table " + nomTable + " réussie");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
			return false;
		}
		return true;
	}

	private void videText(JPanel nomPanel) {
		for (Component c : nomPanel.getComponents()) {
			if (c instanceof JPanel) {
				JPanel p = (JPanel) c;
				for (Component txt : p.getComponents()) {
					if (txt instanceof JTextField) {
						JTextField t = (JTextField) txt;
						t.setText("");
					}
				}
			}
		}		
	}

	private ArrayList<String> recupText(JPanel nomPanel) {
		ArrayList<String> row = new ArrayList<String>();
		for (Component c : nomPanel.getComponents()) {
			if (c instanceof JPanel) {
				JPanel p = (JPanel) c;
				for (Component txt : p.getComponents()) {
					if (txt instanceof JTextField) {
						JTextField t = (JTextField) txt;
						row.add(t.getText());						
					}
				}
			}			
		}
		row.add("" + row.get(3));
		return row;		
	}

	public boolean formulaireFull(JPanel nomPanel) {
		boolean isFull = true;
		for (Component c : nomPanel.getComponents()) {
			if (c instanceof JPanel) {
				JPanel p = (JPanel) c;
				for (Component txt : p.getComponents()) {
					if (txt instanceof JTextField) {
						JTextField t = (JTextField) txt;						
						if (t.getText().isEmpty()) isFull = false;
					}
				}
			}			
		}
		return isFull;		
	}
	
	public DefaultTableModel getModel(String nomTable) {
		update_stock_transitionnel();
		DefaultTableModel modelTmp = new DefaultTableModel();
		try {			
			Statement state = gestion_commande.conn.createStatement();
			String colonnes = "ref_article as \"Référence\","
					+ " lib_article as \"Libellé\", px_achat as \"Prix d'achat\","
					+ " px_vente as \"Prix de vente\", stock_transitionnel as \"Stock en transit\","
					+ " stock_dispo as \"Stock disponible\"";
			String sql = "select " + colonnes + " from " + nomTable + " order by ref_article";
			System.out.println(sql);
			ResultSet result = state.executeQuery(sql);
			ResultSetMetaData resultMeta = result.getMetaData();			
			
			int nbCol = resultMeta.getColumnCount();
			String[] nomDesColonnes = new String[nbCol];
			
			for (int i = 0; i < nbCol; i++) {
				nomDesColonnes[i]= resultMeta.getColumnName(i+1).toUpperCase();				    
			}
			
			modelTmp = new DefaultTableModel(nomDesColonnes, 0);
			
			while (result.next()) {
				String[] row = new String[nbCol];
				
				for (int i = 1; i <= nbCol; i++) {
					result.getObject(i);
					if (!result.wasNull()) {						
						row[i-1] = result.getObject(i).toString();
					}
				}
				modelTmp.addRow(row);
			}				
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		return modelTmp;
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
}
