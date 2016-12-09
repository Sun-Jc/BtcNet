import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by SunJc on 18/11/16.
 */
public class Printer {
    FileOutputStream file = null;
    PrintStream printer = null;
    public Printer(String filename){
        try {
            file = new FileOutputStream(filename);
            printer = new PrintStream(file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public PrintStream handle(){
        return printer;
    }
    public void close(){
        try {
            if(null!=file){
                file.close();
                printer.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
