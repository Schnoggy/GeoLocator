package de.schnoggy.geolocator.command;

import de.schnoggy.geolocator.GeoLocator;
import de.schnoggy.geolocator.util.*;
import net.dv8tion.jda.api.entities.TextChannel;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;

/***********************************
 * User: Malvin H.
 * Date: 09.04.2022
 * Project: GeoLocator
 * Product: IntelliJ IDEA
 * Copyright: Bunsy.net | Developer
 ***********************************/

@CommandSpecification(
        command = "regionname",
        permission = "",
        aliases = {""}
)

public class RegionNameCommand extends Command {

    @Override
    public void onCommand(User user, TextChannel channel, String[] args) throws IOException {
        switch (args.length) {
            case 0: {
                sendEmbed(Embed.getEmbed("**Regionname**", "Please specify a ip", Color.RED));
                break;
            }
            case 1: {
                LocateService locateService = GeoLocator.getInstance().getLocateService();
                JSONObject jsonObject = locateService.queryIP(args[0]);

                try {
                    sendEmbed(Embed.getEmbed("**Regionname**", "Regionname: " + jsonObject.getString("regionName"), Color.GREEN));
                } catch (Exception e) {
                    if (jsonObject.getString("status").equals("fail")) {
                        sendEmbed(Embed.getEmbed("**Regionname**", "IP not found!",
                                Color.RED));
                    }
                }

                break;
            }
            default: {
                sendEmbed(Embed.getEmbed("**Regionname**", "Too many arguments", Color.RED));
            }
        }
    }
}
