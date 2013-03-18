package petter.bjelm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;

public class GUI extends JFrame {

	private JPanel panel;
	private int i;
	private int lastRow=0;
	private int rowX=0;
	private int rowY=0;
	private int setButtonsPerRows=9;
	private int buttonCounter=0;
	private ArrayList<String> anArray;
	private ArrayList<JButton> anArrayTwo;
	private GetCategory data;
	private JButton[] buttons;
	private JComboBox comboBox;

	private int buttonWidth=120;
	private int buttonHeight=120;	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public GUI() {
		super("Add component on JFrame at runtime");

		anArray = new ArrayList<String>();
		anArrayTwo = new ArrayList<JButton>();

		try {
			data = new GetCategory("","");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ParserConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SAXException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (TransformerException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JButton button = new JButton("Skaldjur");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				rowY=0;
				lastRow=setButtonsPerRows;
				rowX=30;
				buttonCounter=0;
				data.setCategory("Skaldjur");
				
				// buttons = new JButton[0];

				if(buttons != null){
					for (i = 0; i < buttons.length; i++) {

						panel.remove(buttons[i]);
						panel.revalidate();
						panel.repaint();

					}
				}


				try {
					anArray = data.getArray();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				buttons = new JButton[anArray.size()];
				
				for (i = 0; i < buttons.length; i++) {
					
					buttons[i] = new JButton(anArray.get(i));
	
					
					
					if (i==lastRow){
						rowY=rowY+buttonHeight;
						lastRow=i+setButtonsPerRows;
						System.out.println(rowY+" Y");

						
					}else{
						
						
					}
					
					System.out.println(buttonCounter);
					if(buttonCounter==setButtonsPerRows){
						buttonCounter=0;
						
						buttons[i].setBounds((buttonWidth*buttonCounter+10), rowY, buttonWidth, buttonHeight);
					}else{
							
						buttons[i].setBounds((buttonWidth*buttonCounter+10), rowY, buttonWidth, buttonHeight);
					}
					
					buttonCounter++;
					System.out.println(rowX);
					//buttons[i].setBounds((150*buttonCounter+10), rowY, buttonWidth, buttonWidth);
					
					buttons[i].setActionCommand(anArray.get(i));
					
					buttons[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String choice = e.getActionCommand();
							JOptionPane.showMessageDialog(null,
									"You have clicked: " + choice);
						}
					});

					panel.add(buttons[i]);
					panel.revalidate();
					validate();
					panel.repaint();
				}

			}

		});
		
		String[] patternExamples = {
		         "Januari",
		         "Februari",
		         "Mars",
		         "April",
		         "Maj",
		         "Juni",
		         "Juli",
		         "Augusti",
		         "September",
		         "Oktober",
		         "November",
		         "December",
		};
		
		comboBox = new JComboBox(patternExamples);
		comboBox.setSelectedIndex(2);
		comboBox.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   
				  int index = comboBox.getSelectedIndex();
				  
				  if(index == 0){
					  
					  data.setDate("01");
					  
				  }else if(index == 1){
					  
					  data.setDate("02");
					  
				  }else if(index == 2){
					  
					  data.setDate("03");
					  
				  }else if(index == 3){
					  
					  data.setDate("04");
					  
				  }else if(index == 4){
					  
					  data.setDate("05");
					  
				  }else if(index == 5){
					  
					  data.setDate("06");
					  
				  }else if(index == 6){
					  
					  data.setDate("07");
					  
				  }else if(index == 7){
					  
					  data.setDate("08");
					  
				  }else if(index == 8){
					  
					  data.setDate("09");
					  
				  }else if(index == 9){
					  
					  data.setDate("10");
					  
				  }else if(index == 10){
					  
					  data.setDate("11");
					  
				  }else if(index == 11){
					  
					  data.setDate("12");
					  
				  }
				   
				   
			   }
			 });
		
		JButton buttonTwo = new JButton("Grönsaker");
	
		panel = new JPanel();
		panel.setBorder(null);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(buttonTwo, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
							.addGap(22))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(comboBox, 0, 996, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonTwo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
					.addContainerGap())
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setAutoCreateGaps(true);
		gl_panel.setAutoCreateContainerGaps(true);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 736, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 539, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	
		buttonTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rowY=0;
				lastRow=setButtonsPerRows;
				rowX=30;
				buttonCounter=0;
				data.setCategory("Gr%C3%B6nsaker");
				
				// buttons = new JButton[0];

				if(buttons != null){
					for (i = 0; i < buttons.length; i++) {

						panel.remove(buttons[i]);
						panel.revalidate();
						panel.repaint();

					}
				}


				try {
					anArray = data.getArray();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

				buttons = new JButton[anArray.size()];
				
				for (i = 0; i < buttons.length; i++) {
					
					buttons[i] = new JButton(anArray.get(i));
	
					
					
					if (i==lastRow){
						rowY=rowY+buttonHeight;
						lastRow=i+setButtonsPerRows;
						System.out.println(rowY+" Y");

						
					}else{
						
						
					}
					
					System.out.println(buttonCounter);
					if(buttonCounter==setButtonsPerRows){
						buttonCounter=0;
						
						buttons[i].setBounds((buttonWidth*buttonCounter+10), rowY, buttonWidth, buttonHeight);
					}else{
							
						buttons[i].setBounds((buttonWidth*buttonCounter+10), rowY, buttonWidth, buttonHeight);
					}
					
					buttonCounter++;
					System.out.println(rowX);
					//buttons[i].setBounds((150*buttonCounter+10), rowY, buttonWidth, buttonWidth);
					
					buttons[i].setActionCommand(anArray.get(i));
					
					buttons[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String choice = e.getActionCommand();
							JOptionPane.showMessageDialog(null,
									"You have clicked: " + choice);
						}
					});

					panel.add(buttons[i]);
					panel.revalidate();
					validate();
					panel.repaint();
				}

			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(1124, 850);
		setVisible(true);
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
}
