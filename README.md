## Elemental Arsenal

Elemental Arsenal is Minecraft plugin that allows you to create custom items.

### Features

* Create custom items, armor pieces *duh*
* Add abilities to items
* Add custom damage to items

### Commands

| Command                    | Permission             | Description                                |
|----------------------------|------------------------|--------------------------------------------|
| `/ea`                      | elementalarsenal.admin | Opens the Elemental Arsenal admin item gui |
| `/ea give [item] <player>` | elementalarsenal.admin | Gives an item to a player                  |
|                            |                        |                                            |

### Permissions

| Permission             | Description                                                                            |
|------------------------|----------------------------------------------------------------------------------------|
| elementalarsenal.*     | Allows access to all features<br>**Note:** Give this permission only to trusted people |
| elementalarsenal.admin | Allows access to all commands                                                          |
| elementalarsenal.give  | Allows access to the `/ea give` command                                                |

### Item Configuration

```yaml
# Path: plugins/ElementalArsenal/config.yml
settings:
  damage-indicators: true # Whether or not to show damage indicators
  blood-particles: true # Whether or not to show blood particles
```

### Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

**Tip:** You should use the [IntelliJ IDEA](https://www.jetbrains.com/idea/) IDE to develop this plugin.
Otherwise you will have to manually add the [Paper](https://github.com/PaperMC/Paper) repository to your project.

Once you setup the project, edit `build.gradle` and change the `destinationDirectory` in `shadowJar` to your dev server's plugin
folder.

### Thanks to
* stefvanschie for the [InventoryFramework](https://github.com/stefvanschie/IF/wiki/GUI)
* [Paper](https://papermc.io) for the, well, Paper server software
* [Bukkit](https://bukkit.org) for actually making Minecraft Server plugins possible

### License

This project is licensed under the AGPL-3.0 license - see the [LICENSE](LICENSE) file for details