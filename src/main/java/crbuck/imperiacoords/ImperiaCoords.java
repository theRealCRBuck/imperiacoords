package crbuck.imperiacoords;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ImperiaCoords extends JavaPlugin {

  private static double latitudeScaleFactor;
  private static double longitudeScaleFactor; 

  public void onEnable() {
    FileConfiguration config = getConfig();
    config.addDefault("latitudeConversionFactor", -614.35); //-614.350069875
    config.addDefault("longitudeConversionFactor", 563.19); // 563.191611719
    config.options().copyDefaults(true);
    saveConfig();

    setLongitudeConversionFactor();
    setLatitudeConversionFactor();
        
    this.getCommand("real-coords").setExecutor(new RealCoordsExecutor());
    this.getCommand("mc-coords").setExecutor(new McCoordsExecutor());
  }

  public static double getLatitudeConversionFactor() {
    return latitudeScaleFactor;
  }

    public static double getLongitudeConversionFactor() {
    return longitudeScaleFactor;
  }

  private void setLatitudeConversionFactor() {
    latitudeScaleFactor = this.getConfig().getDouble("latitudeConversionFactor");
  }

  private void setLongitudeConversionFactor() {
    longitudeScaleFactor = this.getConfig().getDouble("longitudeConversionFactor");
  }
}