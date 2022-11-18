import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MyTable {
    public MyTable(String filename) {
        students_ = new HashMap<>();
        names_ = new ArrayList<>();
        this.fill(filename);
    }
    public MyTable() {
        students_ = new HashMap<>();
        names_ = new ArrayList<>();
    }
    public void insert(String name, int isHere, int mark) {
        Pair<Integer, Integer> data = new Pair<>(isHere, mark);
        students_.put(name, data);
    }
    public ArrayList<Pair<String, Integer>> getStudents() {
        ArrayList<Pair<String, Integer>> list = new ArrayList<>();
        for (String x : names_) {
            list.add(new Pair<>(x, students_.get(x).second));
        }
        return list;
    }
    public String chooseStudent() {
        Random r = new Random();
        return names_.get(r.nextInt(names_.size()));
    }

    public Boolean isInTable(String name) {
        return students_.containsKey(name);
    }

    public void write(String filename) {
        try (FileWriter writer = new FileWriter("students.txt", false)) {
            for (HashMap.Entry<String, Pair<Integer, Integer>> x : students_.entrySet()) {
                String text = x.getKey() + " "
                        + x.getValue().first + " "
                        + x.getValue().second + "\n";
                writer.write(text);
            }
            writer.flush();
        } catch(IOException ex) {
            System.out.println("Не удалось заполнить файл!");
        }
    }
    public void fill(String filename) {
        try {
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] str = line.split(" ");
                String name = str[0] + " " + str[1];
                names_.add(name);
                Pair<Integer, Integer> data = new Pair<>(
                        Integer.parseInt(str[2]), Integer.parseInt(str[3]));
                students_.put(name, data);
                line = reader.readLine();
            }
        } catch(IOException ex) {
            System.out.println("Не удалось считать файл!");
        }
    }
    private HashMap<String, Pair<Integer, Integer>> students_;
    private ArrayList<String> names_;
}
