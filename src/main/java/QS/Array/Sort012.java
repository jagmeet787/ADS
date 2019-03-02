package QS.Array;

public class Sort012 {
    public static void main (String[] args) throws java.io.IOException{
        java.io.BufferedReader br =
                new java.io.BufferedReader(
                        new java.io.InputStreamReader(System.in)
                );
        int tc = Integer.parseInt(br.readLine());
        while( tc-- > 0 ) {
            int n = Integer.parseInt(br.readLine());
            int n0 = 0, n1 = 0, n2 = 0;
            String[] toks = br.readLine().trim().split("\\s+");
            for ( int i = 0; i < n; i++ ) {
                int val = Integer.parseInt(toks[i]);
                if ( val == 0 ) n0++;
                else if ( val == 1 ) n1++;
                else n2++;
            }
            StringBuilder sb = new StringBuilder();
            while ( n0-- > 0 ) sb.append("0 ");
            while ( n1-- > 0 ) sb.append("1 ");
            while ( n2-- > 0 ) sb.append("2 ");
            System.out.println(sb);
        }
    }
}
