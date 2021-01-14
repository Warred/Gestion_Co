package gestion_co;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

public class Tiers extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 105249027564442010L;

	
	private static int lastIndexArt = -1;
	JTextField nom = new JTextField(15);
	JTextField prenom = new JTextField(15);
	JTextField raisonSocial = new JTextField(15);
	JTextField rue = new JTextField(15);
	JTextField codePostal = new JTextField(15);
	JTextField ville = new JTextField(15);
	JButton addTiers = new JButton("Ajouter");
	JButton editTiers = new JButton("Modifier");
	JButton resetTiers = new JButton("Réinitialiser");
	JTextField recherche;
	JButton searchTiers;
	DefaultTableModel modelTiers = new DefaultTableModel();
	JTable jtableTiers = new JTable(modelTiers);
	JPanel panelTextTiers = new JPanel();
	
	public Tiers() {
		setTitle("Tiers");
 	    setBounds(100,100,1000,500); 
	    
 	    JPanel panelFond = new JPanel();//Un panel est un espace dans lequel on peut placer des composants graphiques
 	    panelFond.setLayout(new GridLayout(2, 1));
 	    
 	    JPanel panelHaut = new JPanel();
 	    panelHaut.setLayout(new GridLayout(1,4)); 	    
 	    JPanel panelLabel = new JPanel();
 	    panelLabel.setLayout(new GridLayout(8,1));    
 	    
 	    JLabel videLabel = new JLabel("");
 		JLabel label_nom = new JLabel("Nom :");
 		JLabel label_prenom = new JLabel("Prénom :");
 		JLabel label_raison_social = new JLabel("Raison social :");
 		JLabel label_rue = new JLabel("Rue :");
 		JLabel label_code = new JLabel("Code postal :");
 		JLabel label_ville = new JLabel("Ville :");
 		
 		JPanel align0 = new JPanel();
 		align0.setLayout(new BorderLayout());
 		align0.add(videLabel, BorderLayout.EAST);
 		panelLabel.add(align0);
 	    JPanel align1 = new JPanel();
 	    align1.setLayout(new BorderLayout());
 	    align1.add(label_nom, BorderLayout.EAST);
 	    panelLabel.add(align1); 	    
 	    JPanel align2 = new JPanel();
	    align2.setLayout(new BorderLayout());
	    align2.add(label_prenom, BorderLayout.EAST);
	    panelLabel.add(align2); 	    
	    JPanel align3 = new JPanel();
 	    align3.setLayout(new BorderLayout());
 	    align3.add(label_raison_social, BorderLayout.EAST);
 	    panelLabel.add(align3); 	    
 	    JPanel align4 = new JPanel();
	    align4.setLayout(new BorderLayout());
	    align4.add(label_rue, BorderLayout.EAST);
	    panelLabel.add(align4);	    
	    JPanel align5 = new JPanel();
	    align5.setLayout(new BorderLayout());
	    align5.add(label_code, BorderLayout.EAST);
	    panelLabel.add(align5);
	    JPanel align6 = new JPanel();
	    align6.setLayout(new BorderLayout());
	    align6.add(label_ville, BorderLayout.EAST);
	    panelLabel.add(align6);
 	     	    
 	    
 	    panelTextTiers.setLayout(new GridLayout(8,1));
 	    JPanel videTextUp = new JPanel();
 	    JLabel videT1 = new JLabel("");
 	    videTextUp.add(videT1);
 	    panelTextTiers.add(videTextUp);
 	    	    
 	    JPanel txt1 = new JPanel();
 	    txt1.add(nom);
 	    panelTextTiers.add(txt1); 	    
 	    JPanel txt2 = new JPanel();
	    txt2.add(prenom);
	    panelTextTiers.add(txt2);	    
	    JPanel txt3 = new JPanel();
 	    txt3.add(raisonSocial);
 	    panelTextTiers.add(txt3); 	    
 	    JPanel txt4 = new JPanel();
	    txt4.add(rue);
	    panelTextTiers.add(txt4);	    
	    JPanel txt5 = new JPanel();
	    txt5.add(codePostal);
	    panelTextTiers.add(txt5);
	    JPanel txt6 = new JPanel();
	    txt6.add(ville);
	    panelTextTiers.add(txt6);
 	    
 	    JPanel panelButton = new JPanel();
	    panelButton.setLayout(new GridLayout(4,1));
	    
	    JPanel vide3 = new JPanel();
	    panelButton.add(vide3);
	    JPanel but1 = new JPanel();
	    but1.add(addTiers);
	    panelButton.add(but1);	    
	    JPanel but2 = new JPanel();
	    but2.add(editTiers);
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
	    
	    JLabel search = new JLabel("Rechercher :");
	    c.gridx = 0;
	    c.gridwidth = 0;
	    c.gridy = 1;
	    panelDroite.add(search, c);
	    
	    recherche = new JTextField(15);
	    c.gridx = 0;
	    c.gridy = 2;
	    panelDroite.add(recherche, c);
	    
	    searchTiers = new JButton("Rechercher");
	    c.gridx = 0;
	    c.gridy = 3;
	    panelDroite.add(searchTiers, c);
	    
	    c.gridx = 0;
	    c.gridy = 4;
	    panelDroite.add(resetTiers, c);
	    
	    JLabel videArtDown1 = new JLabel("");
	    c.gridx = 0;
	    c.gridy = 5;
	    panelDroite.add(videArtDown1, c);
	    
	    JLabel videArtDown2 = new JLabel("");
	    c.gridx = 0;
	    c.gridy = 6;
	    panelDroite.add(videArtDown2, c);
   
	    
	    panelHaut.add(panelLabel);
	    panelHaut.add(panelTextTiers);
	    panelHaut.add(panelButton);
	    panelHaut.add(panelDroite);
	    modelTiers = getModel("tiers");
		jtableTiers.setModel(modelTiers);
		JScrollPane sp = new JScrollPane(jtableTiers);
	    Border bord = BorderFactory.createLineBorder(Color.black, 3);
	    sp.setBorder(bord);
	    panelHaut.setBorder(bord);
	    panelFond.add(panelHaut);
	    panelFond.add(sp);	    
	    			
	    this.add(panelFond);
	    
	    addTiers.addActionListener(this);
	    editTiers.addActionListener(this);
	    searchTiers.addActionListener(this);
	    resetTiers.addActionListener(this);
	    jtableTiers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent event) {
	        	int index = jtableTiers.getSelectedRow();
	        	ArrayList<String> valeurs = new ArrayList<String>();
	            if (index != lastIndexArt && index > -1) {
	            	for (int i = 1; i < modelTiers.getColumnCount(); i++) {
	            		if (jtableTiers.getValueAt(index, i) != null) {
	            			valeurs.add(jtableTiers.getValueAt(index, i).toString());	            			
	            		} else 	valeurs.add("");            			
	            	}
		            
		            nom.setText(valeurs.get(0));
		            prenom.setText(valeurs.get(1));
		            raisonSocial.setText(valeurs.get(2));
		            rue.setText(valeurs.get(3));
		            codePostal.setText(valeurs.get(4));
		            ville.setText(valeurs.get(5));
		            
	            }
	            lastIndexArt = index;
	        }
	    });
	    
	}

	public static void main(String[] args) {
		Tiers f = new Tiers();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent clic) {			
		if (clic.getSource() == addTiers) {			
			if (formulaireFull(panelTextTiers)) {				
				if(insertSQL("tiers", recupText())) {
					modelTiers = getModel("tiers");
					jtableTiers.setModel(modelTiers);
					videText();
				}
			}
		
		} else if (clic.getSource() == searchTiers) {
			String str = recherche.getText().toLowerCase();
			if (!str.isEmpty()) {
				boolean trouve = false;
				String nomTable = "tiers where nom ilike '%" + str + "%' OR prenom ilike '%" + str + "%'";
				nomTable += " OR raison_social ilike '%" + str + "%'";
				String sql = "select * from " + nomTable + " order by code_tiers";
				System.out.println(sql);
				
				for (int i = 0; i < modelTiers.getRowCount(); i++) {
					for (int j = 1; j <= 3; j++) {
						if (modelTiers.getValueAt(i, j) != null) {
							if (modelTiers.getValueAt(i, j).toString().toLowerCase().contains(str)) {
								trouve = true;
							}
						}
					}
				}
				if (trouve) {
					DefaultTableModel modelRech = getModel(nomTable);
					jtableTiers.setModel(modelRech);
				} else{
					JOptionPane.showMessageDialog(this, "Aucun '" + str +"' n'est dans le tableau");
					jtableTiers.setModel(modelTiers);
				}
			} else{
				JOptionPane.showMessageDialog(this, "Entrer un nom dans la recherche");
				jtableTiers.setModel(modelTiers);
			}			
			
		} else if (clic.getSource() == editTiers) {
			if (jtableTiers.getSelectedRow() != -1) {
				if (formulaireFull(panelTextTiers)) {
					if(updateSQL("tiers", recupText(), jtableTiers.getValueAt(jtableTiers.getSelectedRow(), 0).toString())) {
						modelTiers = getModel("tiers");
						jtableTiers.setModel(modelTiers);
						videText();
					}
				} else JOptionPane.showMessageDialog(this, "Remplir tout les renseignements pour ajouter un tiers");
			} else JOptionPane.showMessageDialog(this, "Selectionner un tiers à modifier dans le JTable");
			
		} else if (clic.getSource() == resetTiers) {
			modelTiers = getModel("tiers");
			jtableTiers.setModel(modelTiers);	
		}else JOptionPane.showMessageDialog(this, "Pas encore codé cette fonction");		
	}
		
	private boolean insertSQL(String nomTable, ArrayList<String> valeurs) {
		String sql = "INSERT INTO " + nomTable + " VALUES (default";
		for (String val : valeurs) sql += ",'" + val +"'";
		sql += ");";
		System.out.println(sql);
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/base_gestion_co?currentSchema=schema_gestion_co";
			String user = "postgres";
			String passwd = "bonjour";
			conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
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
		String sql = "UPDATE " + nomTable + " SET  nom = '" + valeurs.get(0) + "', prenom = '" + valeurs.get(1);
		sql += "', raison_social = '" + valeurs.get(2) + "', rue = '" + valeurs.get(3);
		sql += "', cp = '" + valeurs.get(4) + "', ville = '" + valeurs.get(5) + "' ";
		sql += "WHERE code_tiers = " + serial;

		System.out.println(sql);
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/base_gestion_co?currentSchema=schema_gestion_co";
			String user = "postgres";
			String passwd = "bonjour";
			conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			state.execute(sql);
			JOptionPane.showMessageDialog(this, "Modification dans la table " + nomTable + " réussie");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
			return false;
		}
		return true;
	}

	private void videText() {
		for (Component c : panelTextTiers.getComponents()) {
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

	private ArrayList<String> recupText() {
		ArrayList<String> row = new ArrayList<String>();
		for (Component c : panelTextTiers.getComponents()) {
			if (c instanceof JPanel) {
				JPanel p = (JPanel) c;
				for (Component txt : p.getComponents()) {
					if (txt instanceof JTextField) {
						JTextField t = (JTextField) txt;
						if (t.getText().isEmpty()) row.add("");
						else row.add(t.getText());						
					}
				}
			}			
		}		
		return row;
	}

	public boolean formulaireFull(JPanel nomPanel) {
		if ( (!nom.getText().isEmpty() && !prenom.getText().isEmpty() ) || !raisonSocial.getText().isEmpty() ) {
			if (!ville.getText().isEmpty() && !codePostal.getText().isEmpty() && !rue.getText().isEmpty()) {
				return true;
			} else JOptionPane.showMessageDialog(this, "Remplir tout les renseignements pour ajouter un tiers");
		} else JOptionPane.showMessageDialog(this, "Remplir nom/prénom ou raison social pour ajouter un tiers");
		return false;
	}
	
	public DefaultTableModel getModel(String nomTable) {
		DefaultTableModel modelTmp = new DefaultTableModel();
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/base_gestion_co?currentSchema=schema_gestion_co";
			String user = "postgres";
			String passwd = "bonjour";
			conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			
			
			String sql = "select * from " + nomTable + " order by code_tiers";
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

}
