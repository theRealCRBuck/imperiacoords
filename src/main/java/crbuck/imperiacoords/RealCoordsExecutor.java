package crbuck.imperiacoords;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RealCoordsExecutor implements CommandExecutor {
    String greenColorCode = "\u00A7a";
    String messageFormat = "\n" + "MC Coords: %f, %f\n" + "Real Coords: %f, %f\n" + greenColorCode + "google.com/maps/place/%f,%f" + "\n";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            if(sender instanceof Player == false) {
                return false;
            }

            Player player = (Player) sender;
            Location playerLocation = player.getLocation();
            double x = playerLocation.getX();
            double z = playerLocation.getZ();

            LatLon realCoords = convertToRealCoords(x, z);
            double latitude = realCoords.getLatitude();
            double longitude = realCoords.getLongitude();

            player.sendMessage(String.format(messageFormat, x, z, latitude, longitude, latitude, longitude));
            return true;
        }

        if(args.length == 2) {
            double x;
            if(args[0].charAt(args[0].length() - 1) == ',') {
                x = Double.valueOf(args[0].substring(0,args[0].length() - 1));
            } else {
                x = Double.valueOf(args[0]);
            }
            double z = Double.valueOf(args[1]);

            LatLon realCoords = convertToRealCoords(x, z);
            double latitude = realCoords.getLatitude();
            double longitude = realCoords.getLongitude();

            sender.sendMessage(String.format(messageFormat, x, z, latitude, longitude, latitude, longitude));
            return true;
        }
 
        return false;
    }

    public LatLon convertToRealCoords(double x, double z) {
        double latitude = z / ImperiaCoords.getLatitudeScaleFactor();
        double longitude = x / ImperiaCoords.getLongitudeScaleFactor();
        return new LatLon(latitude, longitude);
    }
}