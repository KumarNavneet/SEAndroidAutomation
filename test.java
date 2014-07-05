import java.io.*;
import java.util.*;
public class test{
	public static void main(String args[]){
		Scanner scanner = null;
		try{
			Process process = Runtime.getRuntime().exec(new String[] {"/usr/bin/adb", "shell", "su", "-c","dmesg"});
			//process.waitFor();
			InputStreamReader reader = new InputStreamReader(process.getInputStream());
			scanner = new Scanner(reader);
			while(scanner.hasNextLine())
				System.out.println("Navneet : "+scanner.nextLine());
			scanner.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
