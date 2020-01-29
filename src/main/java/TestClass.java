import java.util.HashMap;
import java.util.Map;

public class TestClass {

    private String display(String t) {
        return "Hi " + t;
    }

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();

        String temp [] = {""};

        map.put(120, "UP");
        map.put(11, "ND");
        map.put(null, "ABC");
        map.put(null, null);
        System.out.println(map.size())
        ;
        System.out.println(map);

    }
}