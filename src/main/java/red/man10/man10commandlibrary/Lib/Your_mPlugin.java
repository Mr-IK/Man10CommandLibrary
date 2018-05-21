package red.man10.man10commandlibrary.Lib;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import red.man10.man10commandlibrary.MCLData;
import red.man10.man10commandlibrary.Man10CommandLibrary;

public class Your_mPlugin implements CommandExecutor, Listener {

    String myPluginname;
    Man10CommandLibrary mcl;
    public Your_mPlugin(Man10CommandLibrary mcl){
        this.mcl = mcl;

        //ここに自分のmPluginの名前を入力してください。(他の人と被らないように)
        myPluginname = "Your_mPlugin";

        //mPluginリストへのデータ送信。リストに載りたくない場合は削除
        MCLData.mPluginList.add(myPluginname);

        //コマンドが使いたい場合は下の例に沿って入力する。
        //mcl.getCommand("ここに欲しいコマンドを入力").setExecutor(this);
        //なお、コマンドを実装してもしなくてもplugin.ymlをいじること。
        mcl.getCommand("your_mp").setExecutor(this);

        //イベントハンドラが使いたい場合はこの文を残しておく
        mcl.getServer().getPluginManager().registerEvents (this,mcl);

        //コンフィグを読み込むコードです。必要ないなら消してね。
        config = MCLData.getMyConfig(myPluginname);
        MCLData.saveMyConfig(myPluginname,config);
    }

    //コンフィグ
    FileConfiguration config;


    //コマンドの使い方は http://man10.red/2018/05/12/spigotプラグイン講座3-1コマンドを実装しよう/ などで勉強しよう
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            mcl.getLogger().info("["+myPluginname+"]このプラグインはコンソールからのコマンドを想定していません");
            return true;
        }
        Player p = (Player)sender;
        p.sendMessage("§e§l==============[Default]==============");
        p.sendMessage("§6§lいますぐ自分のmPluginを作ってみよう！");
        p.sendMessage("§e§l=====================================");
        return true;
    }

    //イベントハンドラの使い方は http://man10.red/2018/05/20/spigotプラグイン講座3-3イベントをキャンセルしよう/ などで勉強しよう
    @EventHandler
    public void onLogin(PlayerJoinEvent e){
        e.getPlayer().sendMessage("§d§l"+myPluginname+": §f起動しています");
    }
}
