package uniexp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;

public class DataBase {

   List<Planete> planeteList;

   public DataBase()
   {
      this.planeteList = new ArrayList<>();
   }
   public List<Planete> getPlaneteList()
   {
      loadData("src/main/resources/PlanetsName.txt");
       return planeteList;
   }
   private void loadData(String path)
   {
      BufferedReader reader;
      try {
         reader = new BufferedReader(new FileReader(path));
         String line = reader.readLine();

         while (line != null) {
            String[] values = line.split(" ");
            planeteList.add(new Planete(values[0], parseDouble(values[1]), parseBoolean(values[2]), values[3], parseDouble(values[4])));
            line = reader.readLine();
         }

         reader.close();
      } catch (IOException e) {
         System.out.println("Wrong input format :\n");
         e.printStackTrace();
      }
   }
}
