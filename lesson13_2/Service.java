package lesson13_2;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class Service {
    private String pathToFile = "C:\\Users\\Bulbazaur1\\IdeaProjects\\TrainingProject\\src\\lesson13_2\\Presidents";
    private List<President> presidentList = new ArrayList<>();

    public List<President> getPresidentList() {
        return presidentList;
    }

    public List<President> inPresidentList() {
        try (FileReader fr = new FileReader(pathToFile);
             BufferedReader reader = new BufferedReader(fr)) {
            while (reader.ready()) {
                String temp = reader.readLine();
                if (temp == null) {
                    break;
                } else if (temp != null) {
                    String[] arr = temp.split(";");
                    presidentList.add(new President(arr[0], LocalDate.parse(arr[1], DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                            , LocalDate.parse(arr[2], DateTimeFormatter.ofPattern("dd.MM.yyyy")), arr[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return presidentList;
    }

    public void writeNewPresident(String nameOfPresident, LocalDate beginningOfRein, LocalDate endOfRein, String consignment) {
        try {
            Path path = Path.of(pathToFile);
            Files.write(Path.of(path.toUri()), ("\n" + nameOfPresident + ";" + beginningOfRein + ";" + endOfRein + ";" + consignment).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String whoReins(LocalDate localDate) {
        for (President president : presidentList) {
            if (localDate.isAfter(president.getBeginningOfReign()) && localDate.isBefore(president.getEndOfReign())) {
                return president.getNameOfPresident();
            }
        }
        return null;
    }

    public String getPathToFile() {
        return pathToFile;
    }

}