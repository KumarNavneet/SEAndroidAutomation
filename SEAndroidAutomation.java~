import java.io.*;
public class SEAndroidAutomation{
	public static void main(String args[]){
		String dirName = args[0];
		String mkdirCmd[] = {"mkdir", dirName};
		String cdDirCmd[] = {"cd", dirName};
		String sepolicyPullCmd[] = {"adb", "pull","sepolicy","./"+dirName};
		String bugReportCmd[] = {"adb", "bugReport"};
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

			//pb = new ProcessBuilder(bugReportCmd);
			//pb.redirectErrorStream(true);
			//pr = pb.start();
			//pr.waitFor();

			pr = Runtime.getRuntime().exec("adb bugreport");
			BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			File f = new File("./"+dirName+"/audit.log");
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			//DataOutputStream os = new DataOutputStream(pr.getOutputStream());            
            		//for (String tmpCmd : bugReportCmd) 
            		//      os.writeBytes(tmpCmd+"\n");
			String temp = br.readLine()+"\n";
			while(temp != null){
					bw.write(temp);
					temp = br.readLine()+"\n";
			}
			bw.close();
			System.out.println("Done Writing the Bugreport to ./"+dirName+"audit.log");
            
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
