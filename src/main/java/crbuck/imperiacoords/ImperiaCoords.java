package crbuck.imperiacoords;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ImperiaCoords extends JavaPlugin {

  private static double latitudeScaleFactor;
  private static double longitudeScaleFactor; 

  public void onEnable() {
    FileConfiguration config = getConfig();
    config.addDefault("northWorldBorder", -26624);
    config.addDefault("eastWorldBorder", 61440);
    config.options().copyDefaults(true);
    saveConfig();

    setLongitudeScaleFactor();
    setLatitudeScaleFactor();
        
    this.getCommand("real-coords").setExecutor(new RealCoordsExecutor());
    this.getCommand("mc-coords").setExecutor(new McCoordsExecutor());
  }

  public static double getLatitudeScaleFactor() {
    return latitudeScaleFactor;
  }

  private void setLatitudeScaleFactor() {
    if(this.getConfig().getInt("northWorldBorder") < 0) {
        latitudeScaleFactor = -longitudeScaleFactor;
    } else {
        latitudeScaleFactor = longitudeScaleFactor;
    }
  }

  public static double getLongitudeScaleFactor() {
    return longitudeScaleFactor;
  }

  private void setLongitudeScaleFactor() {
      longitudeScaleFactor = (double) this.getConfig().getInt("eastWorldBorder") / 180;
  }
}