package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabableView;

public class TestJFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1209646065885814522L;
	JTextField libelle = new JTextField(15);
	JTextField pxAchat = new JTextField(15);
	JTextField pxVente = new JTextField(15);
	JTextField stockTrans = new JTextField(15);
	JTextField stockDispo = new JTextField(15);
	JButton addArticle = new JButton("Ajouter");
	JButton editArticle = new JButton("Modifier");
	JButton searchArticle = new JButton("Rechercher");
	JTextField recherche;
	JButton buttonOK;
	DefaultTableModel modelArt = new DefaultTableModel();
	JTable jtableArt = new JTable(modelArt);
	JPanel panelTextArticle = new JPanel();
	
	public TestJFrame() {
		setTitle("Gestion Commerciale V1");
 	    setBounds(100,100,1000,500); 
 	    
 	    //
 	    JTabbedPane tab = new JTabbedPane();
 	    JPanel panelComm = new JPanel();
 	    JPanel panelArt = new JPanel();
 	    JPanel panelTiers = new JPanel();
 	    JPanel panelStock = new JPanel();
	    
 	    JPanel panelFond = new JPanel();//Un panel est un espace dans lequel on peut placer des composants graphiques
 	    panelFond.setLayout(new GridLayout(2, 1));
 	    
 	    JPanel panelHaut = new JPanel();
 	    panelHaut.setLayout(new GridLayout(1,4)); 	    
 	    JPanel panelLabel = new JPanel();
 	    panelLabel.setLayout(new GridLayout(7,1));    

 		JLabel label_lib_article = new JLabel("Libellé :");
 		JLabel label_px_achat = new JLabel("Prix d'achat :");
 		JLabel label_px_vente = new JLabel("Prix de vente :");
 		JLabel label_stock_transitionnel = new JLabel("Stock transitionnel :");
 		JLabel label_stock_dispo = new JLabel("Stock disponible :");
 		
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
	    align4.add(label_stock_transitionnel, BorderLayout.EAST);
	    panelLabel.add(align4);	    
	    JPanel align5 = new JPanel();
	    align5.setLayout(new BorderLayout());
	    align5.add(label_stock_dispo, BorderLayout.EAST);
	    panelLabel.add(align5);
 	     	    
 	    
 	    panelTextArticle.setLayout(new GridLayout(7,1));
 	    	    
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
	    txt4.add(stockTrans);
	    panelTextArticle.add(txt4);	    
	    JPanel txt5 = new JPanel();
	    txt5.add(stockDispo);
	    panelTextArticle.add(txt5);
 	    
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
	    
	    JLabel search = new JLabel("Rechercher par nom :");
	    c.gridx = 0;
	    c.gridwidth = 0;
	    c.gridy = 0;
	    panelDroite.add(search, c);
	    
	    recherche = new JTextField(15);
	    c.gridx = 0;
	    c.gridy = 1;
	    panelDroite.add(recherche, c);
	    
	    buttonOK = new JButton("Ok");
	    c.gridx = 0;
	    c.gridy = 3;
	    panelDroite.add(buttonOK, c);
	    JLabel vide5 = new JLabel("");
	    c.gridx = 0;
	    c.gridy = 5;
	    panelDroite.add(vide5, c);	    
	    
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
	    
	    tab.add("Articles", panelFond);
	    tab.add("Commandes", panelComm);
	    
	    tab.add("Tiers", panelTiers);
	    tab.add("Stock", panelStock);
	    
	    			
	    this.add(tab);
	    
	    addArticle.addActionListener(this);
	    editArticle.addActionListener(this);
	    buttonOK.addActionListener(this); 	   
	    
	}

	public static void main(String[] args) {
		TestJFrame f = new TestJFrame();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent clic) {			
		if (clic.getSource() == addArticle) {			
			if (formulaireOK(panelTextArticle)) {
				
				ArrayList<String> list = recupText(panelTextArticle);
				insertSQL("article", list);
				int nbValeur = list.size()+1;
				String[] row = new String[nbValeur];
				row[0] = "default";
				for (int i = 1; i < nbValeur; i++) {
					row[i] = list.get(i-1);
				}
				modelArt.addRow(row);

			} else JOptionPane.showMessageDialog(this, "Remplir tout les renseignements pour ajouter un article");
		} else JOptionPane.showMessageDialog(this, "Pas encore codé cette fonction");
		
	}
		
	private void insertSQL(String nomTable, ArrayList<String> valeurs) {
		String sql = "INSERT INTO " + nomTable + " VALUES (default";
		for (String val : valeurs) {
			sql += ",'" + val +"'";
		}
		sql += ");";
		System.out.println(sql);
		
		
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
		return row;		
	}

	public boolean formulaireOK(JPanel nomPanel) {
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
		DefaultTableModel modelTmp = new DefaultTableModel();
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/base_gestion_co?currentSchema=schema_gestion_co";
			String user = "postgres";
			String passwd = "bonjour";
			conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String sql = "select * from " + nomTable;
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
					if (!result.wasNull()) row[i-1] = result.getObject(i).toString();
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
