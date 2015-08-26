import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
public class Interpreter {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception 
	{
		// TODO Auto-generated method stub
		BufferedReader bufferedReader = new BufferedReader(new FileReader("pgm1_32.s"));
		BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter("pg64.s"));
		String line;
		String IA_64="";
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            if(line.contains(".cfi_restore 5"))
            {
            	line="";
            }
            if(line.contains("andl	$-16, %esp"))
            {
            	line="";
            }
            if(line.contains("subl	$16, %esp"))
            {
            	line="";
            }
            if(line.contains("$.LC0"))
            {
            	line=line.replace("%esp", "%edi");
            }
            if(line.contains("pushl"))
            {
            	line=line.replace("pushl", "pushq");
            }
            if(line.contains("movl"))
            {
            	line=line.replace("movl", "movq");
            }
            if(line.contains("%ebp"))
            {
            	line=line.replace("%ebp", "%rbp");
            }
            if(line.contains("%esp"))
            {
            	line=line.replace("%esp", "%rsp");
            }
            if(line.contains(".cfi_def_cfa_offset"))
            {
            	line=line.replace("8", "16");
            }
            if(line.contains(".cfi_offset"))
            {
            	line=line.replace("8", "16");
            	line=line.replace("5", "6");
            }
            if(line.contains(".cfi_def_cfa_register"))
            {
            	line=line.replace("5","6");
            }
            if(line.contains("leave"))
            {
            	line=line.replace("leave","popq	%rbp");
            }
            if(line.contains(".cfi_def_cfa"))
            {
            	line=line.replaceFirst("4","7");
            	line=line.replace("4","8");
            }
            bufferedwriter.write(line);
            bufferedwriter.write("\n");
        }
		//reader.readLine()
		bufferedwriter.close();
		bufferedReader.close();
	}

}
