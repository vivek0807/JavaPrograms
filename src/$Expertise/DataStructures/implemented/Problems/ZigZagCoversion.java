package $Expertise.DataStructures.implemented.Problems;

/**
 * <h1>Zing- zag Conversion</h1>
 * <h3>Problem</h3>
 * <h3></h3>
 */
public class ZigZagCoversion {
    public static void main(String[] args) {
            String s= "PAYPALISHIRING";
            int rows=3;

            StringBuilder stringBuilder= new StringBuilder();
            int increment=0;
        for (int i = 0; i <rows ; i++) {
            increment=2*(rows-1);

            for (int j = i; j <s.length() ; j+=increment) {
                stringBuilder=stringBuilder.append(s.charAt(j));

                if (i>0 && i<rows-1 && j+increment-2*i <s.length())
                    stringBuilder.append(s.charAt(j+increment-2*i));
            }

        }

        System.out.println(stringBuilder.toString());

    }
}
