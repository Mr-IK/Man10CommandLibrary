package red.man10.man10commandlibrary.Lib;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import red.man10.man10commandlibrary.MCLData;
import red.man10.man10commandlibrary.Man10CommandLibrary;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class IK_randomChoice implements CommandExecutor, Listener {

    String myPluginname;
    Man10CommandLibrary mcl;
    public IK_randomChoice(Man10CommandLibrary mcl){
        this.mcl = mcl;

        //ここに自分のプラグインの名前を入力してください。(他の人と被らないように)
        myPluginname = "IK_randomChoice";

        //プラグインリストへのデータ送信。リストに載りたくない場合は削除
        MCLData.mPluginList.add(myPluginname);

        //コマンドが使いたい場合は下の例に沿って入力する。
        //mcl.getCommand("ここに欲しいコマンドを入力").setExecutor(this);
        //なお、コマンドを実装してもしなくてもplugin.ymlをいじること。
        mcl.getCommand("irc").setExecutor(this);


    }



    //コマンドの使い方は http://man10.red/2018/05/12/spigotプラグイン講座3-1コマンドを実装しよう/ などで勉強しよう
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            mcl.getLogger().info("["+myPluginname+"]このプラグインはコンソールからのコマンドを想定していません");
            return true;
        }
        Player p = (Player)sender;
        Player randomPlayer = Bukkit.getOnlinePlayers().stream().findAny().get();
        p.sendMessage("§a§lIRC§: §f§l"+randomPlayer.getDisplayName()+"§a§lがセレクトされました");
        return true;
    }

}
