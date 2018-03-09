import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Task1_Read2Lists {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("resources/task1_lists.csv"));
        String line, s1 = "", s2 = "";

        while((line = br.readLine())!=null){
            String str[] = line.split(",");
            s1 += str[0] + " ";
            s2 += str[1] + " ";
        }

        System.out.println("L1 = " + Arrays.toString(s1.split(" ")));
        System.out.println("L2 = " + Arrays.toString(s2.split(" ")));

    }


}
