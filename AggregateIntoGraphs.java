
import java.util.HashMap;

/**
 * Created by SunJc on 18/11/16.
 */
public class AggregateIntoGraphs {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java AggreateIntoGraphs [input] from to #");
            System.exit(0);
        }
        CSVloader reader = new CSVloader(args[0]);
        HashMap<Integer,HashMap<Integer,Double>> edges = new  HashMap<Integer,HashMap<Integer,Double>>();
        int ccount = -1;
        int l = Integer.parseInt(args[1]);
        int h = Integer.parseInt(args[2]);
        for (String[] s:
             reader) {
            ccount++;
            if(ccount<l)
                continue;
            else if(ccount>h)
                break;
            int from = Integer.parseInt(s[1]);
            int to = Integer.parseInt(s[2]);
            double value = Double.parseDouble(s[4]);

            if(!edges.containsKey(from)){
                edges.put(from,new HashMap<Integer,Double>());
            }
            HashMap<Integer,Double> fromEdges = edges.get(from);
            if(!fromEdges.containsKey(to)){
                fromEdges.put(to,value);
            }else{
                fromEdges.put(to,fromEdges.get(to)+value);
            }
        }
        Printer printer = new Printer(util.intermediateName(Integer.parseInt(args[3])));
        printer.handle().println("Source,Target,Type,Id,Label,Weight");
        int count = 0;
        for (int from: edges.keySet()) {
            for (int to: edges.get(from).keySet()) {
                printer.handle().printf("%d,%d,Directed,%d,#,%f\n",from,to,count,edges.get(from).get(to));
                count++;
            }
        }
        printer.close();
    }
}
