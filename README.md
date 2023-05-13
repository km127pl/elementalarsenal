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

### License
This project is licensed under the AGPL-3.0 license - see the [LICENSE](LICENSE) file for details