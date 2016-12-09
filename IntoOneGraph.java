import java.util.DoubleSummaryStatistics;
import java.util.HashMap;

/**
 * Created by SunJc on 18/11/16.
 */
public class IntoOneGraph {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java IntoOneGraph [input]");
            System.exit(0);
        }
        int n = Integer.parseInt(args[0]);
        HashMap<Integer,HashMap<Integer,Double>> edges = new  HashMap<Integer,HashMap<Integer,Double>>();
        for (int i = 0; i < n; i++) {
            CSVloader reader = new CSVloader(util.intermediateName(i));
            boolean first = true;
            for (String[] s:
                    reader) {
                if (first){
                    first = false;
                    continue;
                }
                int from = Integer.parseInt(s[0]);
                int to = Integer.parseInt(s[1]);
                double value = Double.parseDouble(s[5]);

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
        }

        Printer printer = new Printer("oneGraph.csv");
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
