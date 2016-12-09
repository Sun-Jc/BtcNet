/**
 * Created by SunJc on 18/11/16.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("usage: java Main filename numOfThread");
        int n = Integer.parseInt(args[1]);
        int NUM =  37450461;
        int len = NUM/n;
        for (int i = 0; i < n; i++) {
            int l = len * i;
            int h = len * (i+1) - 1;
            if(i==n-1){
                h = NUM-1;
            }
            String[] as = new String[4];
            as[0] = args[0];
            as[1] = Integer.toString(l);
            as[2] = Integer.toString(h);
            as[3] = Integer.toString(i);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.printf("%s, %s to %s started\n",as[3],as[1],as[2]);
                    AggregateIntoGraphs.main(as);
                }
            }).start();
        }
    }
}
