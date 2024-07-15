# Filled Map Coordinates
A small mod that records where an empty map is filled and uses that to show coordinates of the area a filled map is covering.

## Screenshots

With [advanced tooltips](https://minecraft.wiki/w/Tooltip) disabled:  
![Screenshot showcasing a map with coordinates in the tooltip, advanced tooltips disabled](https://cdn.modrinth.com/data/2SRiBKfr/images/43bc84630e585da79bbd10d14105aade35945760.png)

With [advanced tooltips](https://minecraft.wiki/w/Tooltip) enabled:  
![Screenshot showcasing a map with coordinates in the tooltip, advanced tooltips enabled](https://cdn.modrinth.com/data/2SRiBKfr/images/169fe60dc28af5d2bf13324e55b203ce869425da.png)

## Configuration
There's just a single config value, and… you could just _not_ install the mod if you're inclined to change it:
```yaml
# If enabled, the mod stores coordinate data on the map item when creating a new filled map
enabled: true
```
You can manage config in-game with the command `/mapcoordinates <config-value> true/false`.  
Alternatively, you can edit it manually in `config/FilledMapCoordinates/config.yml`.

## Usage

When enabled, simply use maps like you always would.

Whenever you create a new filled map, Minecraft will now store the exact coordinates where
you did so in custom component data on the map item itself. The mod then uses that data to
calculate the coordinates of the top-left and bottom-right corners of the area that this map
covers.  
It will also record the scaling level of a map, and update it if you zoom the map out
on a cartography table. You can read up [on the Wiki](https://minecraft.wiki/w/Map#Usage)
zoom level / scale works with regard to the coordinates a map covers.

The mod needs to be present both on the server (for storing the data) and the client
(for showing the data). **Note** that already existing maps will not show the new tooltip
information as they do not have the required coordinates metadata present on the item.
If uninstalling the mod later, the metadata stored by the mod should not have any impact.

---
I was only able to get this mod done by building on [somyk](https://modrinth.com/user/somyk)'s mod [Map Art Copyright](https://modrinth.com/mod/mapartcopyright). Thank you!