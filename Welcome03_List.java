/*
 * Arrays and Lists of Objects
 */

import core.data.*;
import java.util.Scanner;

public class Welcome03_List {
   public static void main(String[] args) {

      DataSource ds = DataSource.connect(
            "http://weather.gov/xml/current_obs/index.xml").load();

      WeatherStation[] allstns = ds.fetchArray(
            "WeatherStation",
            "station/station_name",
            "station/station_id",
            "station/state",
            "station/latitude",
            "station/longitude"
      );

      System.out.println("Total stations: " + allstns.length);

      Scanner sc = new Scanner(System.in);
      System.out.print("Enter a state abbreviation: ");
      String state = sc.next();

      System.out.println("\nStations in " + state + ":");
      for (WeatherStation ws : allstns) {
         if (ws.isLocatedInState(state)) {
            System.out.println("  " + ws.getId() + ": " + ws.getName());
         }
      }

      // Find southernmost station (lowest latitude)
      WeatherStation southernmost = allstns[0];

      for (WeatherStation ws : allstns) {
         if (ws.getLatitude() < southernmost.getLatitude()) {
            southernmost = ws;
         }
      }

      System.out.println("\nSouthernmost weather station:");
      System.out.println("  " + southernmost.getId() + ": " 
                         + southernmost.getName()
                         + " (Latitude: " 
                         + southernmost.getLatitude() + ")");
   }
}
