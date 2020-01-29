import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8Group {

    public static void main(String[] args) {
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");

        System.out.println(items
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }
}
