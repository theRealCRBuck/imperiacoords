package crbuck.imperiacoords;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class McCoordsExecutor implements CommandExecutor {
    String greenColorCode = "\u00A7a";
    String messageFormat = "\n" + "Real Coords: %f, %f\n" + "MC Coords: %f, %f\n" + greenColorCode + "play.imperiamc.net:25668/?worldname=world&mapname=flat&zoom=6&x=%f&y=64&z=%f" + "\n";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 2) {
            double latitude;
            if(args[0].charAt(args[0].length() - 1) == ',') {
                latitude = Double.valueOf(args[0].substring(0,args[0].length() - 1));
            } else {
                latitude = Double.valueOf(args[0]);
            }
            double longitude = Double.valueOf(args[1]);

            Location mcCoords = convertToMcCoords(latitude, longitude);
            double x = mcCoords.getX();
            double z = mcCoords.getZ();

            sender.sendMessage(String.format(messageFormat, x, z, latitude, longitude, x, z));
            return true;
        }
 
        return false;
    }

    public Location convertToMcCoords(double latitude, double longitude) {
        double x = longitude * ImperiaCoords.getLongitudeScaleFactor();
        double z = latitude * ImperiaCoords.getLatitudeScaleFactor();
        return new Location(null, x, 64, z);
    }
}
