import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Task1_Read2Lists {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("resources/task1_lists.csv"));
        String line =  null;
        HashMap<String, String> map = new HashMap<String, String>();

        while((line=br.readLine())!=null){
            String str[] = line.split(",");
            map.put(str[0], str[1]);
        }

//        System.out.println(map);

        String l1[] = map.keySet().toArray(new String[0]);
        l1 =  Arrays.stream(l1)
                .filter(value ->
                        value != null && value.length() > 0
                )
                .toArray(size -> new String[size]);

        System.out.println("L1 = " + Arrays.toString(l1));

        String l2[] = map.values().toArray(new String[0]);
        l2 =  Arrays.stream(l2)
                .filter(value ->
                        value != null && value.length() > 0
                )
                .toArray(size -> new String[size]);
        System.out.println("L2 = " + Arrays.toString(l2));
    }


}
