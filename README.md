# TestDropChances

This is a simple Bukkit plugin to demonstrate a bug with Spigot equipment
drop chances.


## Commands

 * `/testdropchances <main> [<off> <boots> <leggings> <chestplate> <helmet>]` - Spawn
   a low-health test mob. All arguments are drop chances in the range [0.0,1.0].
   If only the main hand drop chance is specified, all drop chances are set to
   that. Otherwise, all drop chances must be provided.

Examples:

```
/testdropchances 0.099
/testdropchances 0.1 0.2 0.3 0.4 0.5 0.6
```


## Vanilla Minecraft Commands

To get the drop chances:
```
/data get entity @e[type=minecraft:wither_skeleton,distance=..20,limit=1] HandDropChances
/data get entity @e[type=minecraft:wither_skeleton,distance=..20,limit=1] ArmorDropChances
```

To set the drop chances:
```
/data merge entity @e[type=minecraft:wither_skeleton,distance=..20,limit=1] {HandDropChances: [0.1f,0.2f]}
/data merge entity @e[type=minecraft:wither_skeleton,distance=..20,limit=1] {ArmorDropChances: [0.3f,0.4f,0.5f,0.6f]}
```

The NBT tags are documented [here](https://minecraft.gamepedia.com/Chunk_format/Mob). 
There are examples of using them [here](https://minecraft.gamepedia.com/Tutorials/Summoning_jockeys).


## Permissions

 * `testdropchances.use` - Permission to use `/testdropchances`.
