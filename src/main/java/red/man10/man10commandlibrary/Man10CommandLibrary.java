package red.man10.man10commandlibrary;

import org.bukkit.plugin.java.JavaPlugin;

public final class Man10CommandLibrary extends JavaPlugin {

//////////////////////////////////////////////////////////
//     Man10CommandLibrary(まんじゅうコマンドライブラリ)
//
//     created by Mr_IK(https://twitter.com/Mr_IK2302)
//
//     ライブラリにmPluginを追加したい？僕に相談してね
//////////////////////////////////////////////////////////

    @Override
    public void onEnable() {
        // Pluginが読み込まれた
        new MCLData(this);
        getCommand("mcl").setExecutor(new MCLMainCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
