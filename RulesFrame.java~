import javax.swing.*;
import java.io.*;
//import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesFrame extends JFrame implements ActionListener{
	private JPanel topPanel;
	private JScrollPane scrollPane;
	private JButton ok;
	private JList lst;
	private String RulesArray[];
	private java.util.List<String> mRules;
		public RulesFrame(java.util.List<String> Rules){
			mRules = Rules;			
			setTitle("Select Rules to Add(Ctrl+Select for multiple)");
			setSize(500,500);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			topPanel = new JPanel();
			topPanel.setLayout(new BorderLayout());
			getContentPane().add(topPanel);
			RulesArray = new String[Rules.size()];
			lst = new JList(Rules.toArray(RulesArray));
			scrollPane = new JScrollPane(lst);
			topPanel.add(scrollPane, BorderLayout.CENTER);
			
			ok = new JButton("Ok");
			ok.setPreferredSize(new Dimension(50, 50));
			ok.addActionListener(this);
			topPanel.add(ok, BorderLayout.SOUTH);
		}
	public static void main(String args[]){

	}
	public void actionPerformed(ActionEvent e){
		int[] selectedIx = this.lst.getSelectedIndices();
		for(int i=0; i<selectedIx.length; i++)  
			SEAndroidAutomation.rulesByUser.add(RulesArray[selectedIx[i]]);
			//System.out.println("Selected Rule : "+mRules[i]);
		this.dispose();
		SEAndroidAutomation.afterOkClick();
	}
}
