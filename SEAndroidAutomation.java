import java.io.*;
import javax.swing.*;
public class SEAndroidAutomation{
	public static void main(String args[]){
		String dirName = args[0];
		String mkdirCmd[] = {"mkdir", dirName};
		//String cdDirCmd[] = {"cd", dirName};
		String sepolicyPullCmd[] = {"adb", "pull","sepolicy","./"+dirName};
		String bugReportCmd[] = {"adb", "bugreport"};
		String ruleCmd[] = {"audit2allow", "-i"+"audit.log","-o"+"./"+dirName+"/Rules"};// -p ./"+dirName+"/sepolicy"};//,">","Rules"};
		try{	
			Runtime rt = Runtime.getRuntime();
		
			ProcessBuilder pb = new ProcessBuilder(mkdirCmd);
			pb.redirectErrorStream(true);
			Process pr = pb.start();
			pr.waitFor();		
		
			//pb = new ProcessBuilder(cdDirCmd);
			//pr = pb.start();
			//pr.waitFor();
		
			pb = new ProcessBuilder(sepolicyPullCmd);
			pb.redirectErrorStream(true);
			pr = pb.start();
			pr.waitFor();
			
			String temp = "";
			BufferedReader br = null;
			File f = null;
			FileWriter fw = null;
			BufferedWriter bw = null;
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
			String rules[] = new String[20];
			int i=0;
			while(temp != null){
				temp += "\n";
				rules[i++]=temp;
				bw.write(temp);
				temp = br.readLine();			
			}
			br.close();
			bw.close();
			
			System.out.println("Done Writing the Rules to ./"+dirName+"/OnlyRules");
	
			RulesFrame obj = new RulesFrame(rules);
			obj.setVisible(true);
			br = new BufferedReader(new FileReader("./"+dirName+"/OnlyRules"));
			while((temp = br.readLine()) != null){
				//pr = Runtime.getRuntime().exec("sepolicy-inject ");
				System.out.println(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
