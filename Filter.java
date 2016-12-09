import org.omg.PortableInterceptor.INACTIVE;

/**
 * Created by SunJc on 18/11/16.
 */
public class Filter {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Filter [file] threshold");
            System.exit(0);
        }
        CSVloader reader = new CSVloader(args[0]);
        Printer p = new Printer("filter_" + args[1] + ".csv");
        p.handle().println("Source,Target,Type,Id,Label,Weight");
        boolean first = true;
        int count = 0;
        for (String[] s :
                reader) {
            if (first) {
                first = false;
                continue;
            }
            int from = Integer.parseInt(s[0]);
            int to = Integer.parseInt(s[1]);
            double value = Double.parseDouble(s[5]);

            if (value > Double.parseDouble(args[1])) {
                p.handle().printf("%d,%d,Directed,%d,#,%f\n", from, to,count, value);
                count++;
            }
        }
        p.close();
    }
}
