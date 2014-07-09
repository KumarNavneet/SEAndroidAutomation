import javax.swing.*;
import java.io.*;
import java.awt.*;
public class RulesFrame extends JFrame{
	private JPanel topPanel;
	private JButton ok;
	private JList lst;
		public RulesFrame(String []Rules){
			setTitle("Select Rules to add");
			setSize(400,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			topPanel = new JPanel();
			topPanel.setLayout(new BorderLayout());
			getContentPane().add(topPanel);

			lst = new JList(Rules);
			topPanel.add(lst, BorderLayout.CENTER);
			
			ok = new JButton("Ok");
			ok.setPreferredSize(new Dimension(50, 50));
			topPanel.add(ok, BorderLayout.SOUTH);
		}
	public static void main(String args[]){

	}
}
