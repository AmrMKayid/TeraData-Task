import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Task1_Read2Lists {


    public static void main(String[] args) throws IOException {

        String path = "resources/task1_lists.csv";

        System.out.println("Method 1: ");
        Method1_UsingStrings(path);

        System.out.println("###################################");

        System.out.println("Method 2: ");
        Method2_UsingHashMap(path);

    }

    private static void Method1_UsingStrings(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line, s1 = "", s2 = "";

        while ((line = br.readLine()) != null) {
            String str[] = line.split(",");
            s1 += str[0] + " ";
            s2 += str[1] + " ";
        }

        System.out.println("L1 = " + Arrays.toString(s1.split(" ")));
        System.out.println("L2 = " + Arrays.toString(s2.split(" ")));
    }

    private static void Method2_UsingHashMap(String path) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        HashMap<String, String> map = new HashMap<>();

        while ((line = br.readLine()) != null) {
            String str[] = line.split(",");
            map.put(str[0], str[1]);
        }

//        System.out.println(map);

        String l1[] = map.keySet().toArray(new String[0]);
        l1 = Arrays.stream(l1)
                .filter(value ->
                        value != null && value.length() > 0
                )
                .toArray(String[]::new);

        System.out.println("L1 = " + Arrays.toString(l1));

        String l2[] = map.values().toArray(new String[0]);
        l2 = Arrays.stream(l2)
                .filter(value ->
                        value != null && value.length() > 0
                )
                .toArray(String[]::new);
        System.out.println("L2 = " + Arrays.toString(l2));
    }


}
