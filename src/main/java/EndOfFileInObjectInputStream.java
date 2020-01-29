import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EndOfFileInObjectInputStream {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
            out.writeInt(2);
            out.writeObject("Hello World");

            Map<String, String> greetings = new HashMap<>();

            greetings.put("German", "Guten morgen");
            greetings.put("Greek", "Kalimera");
            greetings.put("isiXhosa", "Molo");
            greetings.put("Afrikaans", "Gooie more");
            greetings.put("English", "Good morning");

            out.writeObject(greetings);

        }

        try (ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(bout.toByteArray())
        )) {

            int numberOfObjects = in.readInt();
            for (int i = 0; i < numberOfObjects ; i++) {
                System.out.println(in.readObject());
            }
            System.out.println("in.available() = " + in.available());
        }


    }
}
