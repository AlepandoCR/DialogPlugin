# 📜 DialogPlugin – Custom Dialog Wrapper (Minecraft 1.21+)

**DialogPlugin** is a developer-focused plugin for easily testing and extending Minecraft's new native dialogs via the `ServerboundCustomClickActionPacket`.  
It offers a full Kotlin-based wrapper for creating rich, interactive dialogs with buttons, inputs, and custom actions.

> ❗ **Note:** This plugin is intended as a dev utility – maintenance is not guaranteed.  
> Feel free to fork, adapt, and build your own features on top of it.

---

## 🚀 Features

- ✅ Kotlin-first builder pattern
- ✅ Support for all Vanilla dialog types (MultiAction, List, Links, Notice)
- ✅ Custom actions via Mojang’s native packet system
- ✅ Input reading (text, number, multiline)
- ✅ Item & message-based dialog bodies
- ✅ Easy integration with Bukkit events

---

## 📘 How to Use

### 🧱 Building a Simple Dialog

```kotlin
val dialogData = DialogDataBuilder()
    .title(Component.literal("Test Menu"))
    .externalTitle(Component.literal("Menu Test"))
    .canCloseWithEscape(true)
    .addBody(PlainMessageDialogBody(100, Component.literal("Hello from Dialog!")))
    .build() 
   ```
   
### 🎯 Adding Buttons with Actions

 ```kotlin
 val testButton = Button(
    ButtonDataBuilder()
        .label(Component.literal("Kill Me"))
        .width(100)
        .build(),
    Optional.of(CustomAll(Keys.KILL_PLAYER.key, Optional.empty()))
) 
```
### 🔀 MultiAction Dialog
 ```koltlin
val dialog = MultiActionDialogBuilder()
    .data(dialogData)
    .columns(1)
    .exitButton(exitButton)
    .addButton(testButton)
    .build() 
   ```
### 🧪 Opening the Dialog
 ```kotlin
val holder = Holder.Direct(dialog.toNMS())
(craftPlayer.handle as ServerPlayer).openDialog(holder) 
```

### ⚙️ Creating Custom Actions
#### 🧨 Registering an Action
 ```kotlin
enum class Keys(val key: ResourceLocation, val action: CustomAction, val reader: InputReader) {
    KILL_PLAYER(
        ResourceLocation.fromNamespaceAndPath("fuchibol", "kill_player"),
        KillPlayerAction,
        PlayerReturnValueReader
    );
} 
```
#### 🔥 Action Implementation
 ```kotlin
object KillPlayerAction: CustomAction() {
    override fun task(player: Player, plugin: DialogPlugin) {
        dynamicListener?.start()
        player.damage(5.0)

        object : BukkitRunnable() {
            override fun run() {
                dynamicListener?.stop()
            }
        }.runTaskLater(plugin, 20L)
    }

    override fun listener(): Listener {
        return object : Listener {
            @EventHandler
            fun onPlayerDeath(event: PlayerDeathEvent) {
                event.player.sendMessage("You died during a dialog!")
            }
        }
    }
} 
```
#### 🔍 Input Readers

```kotlin
object PlayerReturnValueReader : InputReader {
    override fun task(player: Player, packet: ServerboundCustomClickActionPacket, value: Any?) {
        player.sendMessage("Received input: $value")
    }
}
```
#### ⌨️ Creating Input Fields (_There are more than shown_)
```kotlin
val stringInput = TextInputBuilder()
    .label(Component.literal("Your name"))
    .initial("Player")
    .maxLength(300)
    .multiline(MultilineOptions(5, 10))
    .build()

val numberInput = NumberRangeInputBuilder()
    .label(Component.literal("Pick a number"))
    .rangeInfo(RangeInfo(1.0f, 10.0f))
    .labelFormat("Value: %s")
    .build()
```


🧠 Extending the Plugin
You can add more actions, readers, and even UI components by following the builder and registry patterns shown above.

🤝 Contributions
Pull requests are welcome.
Feel free to adapt the system or use it as the base for your own dialog-based plugin!

📝 License
This plugin is open and unlicensed. You are free to do anything with it.