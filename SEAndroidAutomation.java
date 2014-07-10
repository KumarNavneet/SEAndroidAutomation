import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SEAndroidAutomation extends JFrame{
	private static JPanel topPanel;
	private static JButton ok;
	private static JList lst;
	public static List<String> rulesByUser = new ArrayList<>();
	public static void main(String args[]){
		String dirName = args[0];
		int i=0;
		String mkdirCmd[] = {"mkdir", dirName};
		//String cdDirCmd[] = {"cd", dirName};
		String sepolicyPullCmd[] = {"adb", "pull","sepolicy","./"+dirName+"/origSePolicy"};
		String bugReportCmd[] = {"adb", "bugreport"};
		String ruleCmd[] = {"audit2allow", "-i"+"audit.log","-o"+"./"+dirName+"/Rules"};// -p ./"+dirName+"/origSepolicy"};
		String permDomainCmd[] = {"seinfo", "--permissive", "mySepolicy"};//"./"+dirName+"/origSepolicy"};
		List<String> rules = new ArrayList<>();
		List<String> permissiveDomains = new ArrayList<>();
		String temp = "";
		BufferedReader br = null;
		File f = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try{	
			Runtime rt = Runtime.getRuntime();
		
			ProcessBuilder pb = new ProcessBuilder(mkdirCmd);
			pb.redirectErrorStream(true);
			Process pr = pb.start();
			pr.waitFor();

			pb = new ProcessBuilder(sepolicyPullCmd);
			pb.redirectErrorStream(true);
			pr = pb.start();
			pr.waitFor();		
		
			//pb = new ProcessBuilder(cdDirCmd);
			//pr = pb.start();
			//pr.waitFor();
			pb = new ProcessBuilder(permDomainCmd);
			pb.redirectErrorStream(true);
			pr = pb.start();
			pr.waitFor();
			br = new BufferedReader(new InputStreamReader(pr.getInputStream()));			
			temp = br.readLine();
			while(temp != null){
					System.out.println("Permissive Domain : "+temp);
					permissiveDomains.add(temp);
					temp = br.readLine();
			}
			br.close();			
			
			//pb = new ProcessBuilder(bugReportCmd);
			//pb.redirectErrorStream(true);
			//pr = pb.start();
			//pr.waitFor();
			/*System.out.println("Starting the BugReport....Please wait!!!");
			pr = Runtime.getRuntime().exec("adb bugreport");
			br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			f = new File("./"+dirName+"/audit.log");
			fw = new FileWriter(f.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
			String temp = br.readLine();
			while(temp != null){
					temp += "\n";
					bw.write(temp);
					temp = br.readLine();
			}
			br.close();
			bw.close();
			System.out.println("Done Writing the Bugreport to ./"+dirName+"audit.log");
			*/
			pb = new ProcessBuilder(ruleCmd);
			pb.redirectErrorStream(true);
			pr = pb.start();
			pr.waitFor();
			String pattern = "allow ";	
			pr = Runtime.getRuntime().exec("grep -w "+pattern+"./"+dirName+"/Rules");
			br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            		f = new File("./"+dirName+"/OnlyRules");
			fw = new FileWriter(f.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			temp = br.readLine();
			while(temp != null){
				temp += "\n";
				rules.add(temp);
				bw.write(temp);
				temp = br.readLine();			
			}
			br.close();
			bw.close();
			
			System.out.println("Done Writing the Rules to ./"+dirName+"/OnlyRules");
			/*
			JFrame RuleFrame = new JFrame();
			RuleFrame.setTitle("Select Rules to add");
			RuleFrame.setSize(400,400);
			RuleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			topPanel = new JPanel();
			topPanel.setLayout(new BorderLayout());
			RuleFrame.getContentPane().add(topPanel);

			lst = new JList(rules);
			topPanel.add(lst, BorderLayout.CENTER);
			
			ok = new JButton("Ok");
			ok.setPreferredSize(new Dimension(50, 50));
			topPanel.add(ok, BorderLayout.SOUTH);
			ok.addActionListener(this);
			RuleFrame.setVisible(true);
			
			*/
			RulesFrame obj = new RulesFrame(rules);
			obj.setVisible(true);
			/*
			br = new BufferedReader(new FileReader("./"+dirName+"/OnlyRules"));
			while((temp = br.readLine()) != null){
				//pr = Runtime.getRuntime().exec("sepolicy-inject ");
				System.out.println(temp);
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void afterOkClick(){
		System.out.println("you clicked on OK");
		
	}
	
}
