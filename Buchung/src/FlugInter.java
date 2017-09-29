import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JComboBox;

public class FlugInter extends JPanel implements ActionListener {
	private JPanel contentPane;

	public FlugInter(FlugModel m) throws SQLException {
		JFrame buchen = new JFrame("Buchen");
		buchen.setAlwaysOnTop(true);
		buchen.setResizable(false);
		buchen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buchen.setBounds(100, 100, 855, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		buchen.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAbreise = new JLabel("Abreise:");
		lblAbreise.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAbreise.setBounds(10, 11, 91, 45);
		contentPane.add(lblAbreise);
		JLabel lblAnkunft = new JLabel("Ankunft:");
		lblAnkunft.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAnkunft.setBounds(10, 206, 91, 45);
		contentPane.add(lblAnkunft);
		
		
		JLabel labelu = new JLabel("Land:");
		labelu.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelu.setBounds(10, 270, 46, 14);
		contentPane.add(labelu);
		
		JLabel lblLand = new JLabel("Land:");
		lblLand.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLand.setBounds(10, 67, 46, 14);
		contentPane.add(lblLand);
		
		
		
		
		m.getLand();
		TreeMap n = new TreeMap(m.getWerte("land"));
		ArrayList<String> l = new ArrayList<String>(n.keySet());
		String[]laender  = new String[l.size()];
		for(int i = 0;i < l.size();i++) {
			laender[i]= l.get(i);
			System.out.println(laender[i]);
		}

		

		JComboBox abLand = new JComboBox(laender);
		abLand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					JLabel lblFlughafen = new JLabel("Flughafen:");
					lblFlughafen.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblFlughafen.setBounds(193, 67, 91, 20);
					contentPane.add(lblFlughafen);
					JComboBox cb = (JComboBox)e.getSource();
					String code = (String)cb.getSelectedItem();
					TreeMap found = new TreeMap( m.findFlughafen((String) n.get(code)));
					ArrayList<String> l = new ArrayList<String>(found.keySet());
					String[]fluege1  = new String[l.size()];
					for(int i = 0;i < l.size();i++) {
						fluege1[i]= l.get(i);	
					}	
					JComboBox abFlug = new JComboBox(fluege1);
					abFlug.setBounds(282, 67, 113, 20);
					contentPane.add(abFlug);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally {
						
					}
				
				}			
			}
			);
		
		//------
		
		TreeMap n2 = new TreeMap(m.getWerte("land"));	
		ArrayList<String> l2 = new ArrayList<String>(n2.keySet());
		String[]laender2  = new String[l2.size()];
		for(int i = 0;i < l2.size();i++) {
			laender2[i]= l2.get(i);
			System.out.println(laender2[i]);
		}
		JComboBox anLand = new JComboBox(laender2);
		anLand.setBounds(70, 270, 113, 20);
		contentPane.add(anLand);
		anLand.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String code2 = (String)cb.getSelectedItem();
				JLabel label = new JLabel("Flughafen:");
				label.setFont(new Font("Tahoma", Font.BOLD, 16));
				label.setBounds(193, 270, 91, 20);
				contentPane.add(label);
				try {
					TreeMap found2 = new TreeMap( m.findFlughafen((String) n2.get(code2)));
					ArrayList<String> l2 = new ArrayList<String>(found2.keySet());
					String[]fluege2  = new String[l2.size()];
					for(int i = 0;i < l2.size();i++) {
						fluege2[i]= l2.get(i);	
					}
					JComboBox anFlug = new JComboBox(fluege2);
					anFlug.setBounds(286, 272, 113, 20);
					contentPane.add(anFlug);
				} catch (SQLException x) {
					// TODO Auto-generated catch block
					x.printStackTrace();
				}
					
			}});
				
			
		
		
		
		abLand.setBounds(66, 65, 113, 20);
		contentPane.add(abLand);

		
	
		
		JButton btnSuche = new JButton("Suche");
		
		btnSuche.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSuche.setBounds(10, 369, 89, 23);
		btnSuche.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PassagierData();
				
			}
		});
		contentPane.add(btnSuche);
		buchen.setVisible(true);
		
	}
	public static void main(String[]args) throws SQLException {
		FlugInter inter = new FlugInter(new FlugModel("root","maman","localhost",3306));
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}


