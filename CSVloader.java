import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by SunJc on 18/11/16.
 */
public class CSVloader implements Iterable<String[]>{
    String line = "";
    String cvsSplitBy = ",";
    BufferedReader br = null;

    public CSVloader(String fileName) {
        try {
            br = new BufferedReader(new FileReader(fileName));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterator<String[]> iterator() {
        return new Iterator<String[]>() {
            public boolean hasNext() {
                boolean has = false;
                try {
                    has = (line = br.readLine()) != null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!has) {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return has;
            }

            public String[] next() {
                return line.split(cvsSplitBy);
            }
        };
    }
}
