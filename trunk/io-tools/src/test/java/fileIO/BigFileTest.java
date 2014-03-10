package fileIO;

import java.io.File;
import java.util.Properties;

import org.testng.annotations.Test;

public class BigFileTest {
	@Test
	public void OutputLineTest() throws Exception {
		BigFile file = new BigFile(getClass().getResourceAsStream("BigFileTest.txt"));
		for (String line : file){
			System.out.println(line);
		}
	}
	
	public static void main(String[] args) throws Exception{
		Properties props=System.getProperties();
		props.list(System.out);
		String usrDir = System.getProperty("user.dir");
		System.out.println(usrDir);
		BigFile file = new BigFile(usrDir + File.separator+"target/test-classes/fileIO/BigFileTest.txt");
		for (String line : file){
			System.out.println(line);
		}
	}
}
