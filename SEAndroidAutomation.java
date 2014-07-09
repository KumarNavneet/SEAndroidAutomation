import java.io.*;
public class SEAndroidAutomation{
	public static void main(String args[]){
		String dirName = args[0];
		String mkdirCmd[] = {"mkdir", dirName};
		//String cdDirCmd[] = {"cd", dirName};
		String sepolicyPullCmd[] = {"adb", "pull","sepolicy","./"+dirName};
		String bugReportCmd[] = {"adb", "bugreport"};
		String ruleCmd[] = {"audit2allow", "./"+dirName+"/audit.log"};//,">","Rules"};
		String ruleCmd2[] = {"cat", "Rules", "|", "grep ","allow"};//,">","OnlyRules"};
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
			
			pr = Runtime.getRuntime().exec("adb bugreport");
			br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			f = new File("./"+dirName+"/audit.log");
			fw = new FileWriter(f.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			//DataOutputStream os = new DataOutputStream(pr.getOutputStream());            
            		//for (String tmpCmd : bugReportCmd) 
            		//      os.writeBytes(tmpCmd+"\n");
			String temp = br.readLine();
			//int c = 0;
			while(temp != null){
					temp += "\n";
					bw.write(temp);
					temp = br.readLine();
					//c++;
			}
			bw.close();
			System.out.println("Done Writing the Bugreport to ./"+dirName+"audit.log");

			pr = Runtime.getRuntime().exec("audit2allow ./"+dirName+"/audit.log");
			br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            		f = new File("./"+dirName+"/Rules");
			fw = new FileWriter(f.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			temp = br.readLine();
			while(temp != null){
				temp += "\n";
				bw.write(temp);
				temp = br.readLine();			
			}
			
			pr = Runtime.getRuntime().exec("cat ./"+dirName+"/Rules"+" | grep allow");
			br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            		f = new File("./"+dirName+"/OnlyRules");
			fw = new FileWriter(f.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			temp = br.readLine();
			while(temp != null){
				temp += "\n";
				bw.write(temp);
				temp = br.readLine();		
			}
			System.out.println("Done Writing the Rules to ./"+dirName+"OnlyRules");
			
			br = new BufferedReader(new FileReader("./"+dirName+"/OnlyRules"));
			while((temp = br.readLine()) != null){
				pr = Runtime.getRuntime().exec("sepolicy-inject ");
				System.out.println(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
