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

public class Example_mPlugin implements CommandExecutor, Listener {

    //////////////////////////////////////////////
    //    Example_mPlugin
    //   created by Mr_IK(https://twitter.com/Mr_IK2302)
    //
    //   mPlugin list view mPlugin.
    //////////////////////////////////////////////


    String myPluginname;
    Man10CommandLibrary mcl;
    public Example_mPlugin(Man10CommandLibrary mcl){
        this.mcl = mcl;

        //ここに自分のプラグインの名前を入力してください。(他の人と被らないように)
        myPluginname = "Example_mPlugin";

        //プラグインリストへのデータ送信。リストに載りたくない場合は削除
        MCLData.mPluginList.add(myPluginname);

        //コマンドが使いたい場合は下の例に沿って入力する。
        //mcl.getCommand("ここに欲しいコマンドを入力").setExecutor(this);
        mcl.getCommand("mcl").setExecutor(this);

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
        if(args.length==1){
            if(args[0].equalsIgnoreCase("plugins")||args[0].equalsIgnoreCase("pl")){
                if(!p.hasPermission("mcl.plugins")){
                    p.sendMessage("§a[MCSPlugins]§rプラグインリスト,なんて,見せるわけ,ないだろ");
                    return true;
                }
                p.sendMessage("§a[MCSPlugins]§r"+ MCLData.getmPluginList());
                return true;
            }
        }
        p.sendMessage("§e§l==============[MCL]==============");
        p.sendMessage("§6§l/mcl plugins/pl §f§l: MCLに入っているプラグインリスト表示");
        p.sendMessage("§e§l=================================");
        return true;
    }

    //イベントハンドラの使い方は http://man10.red/2018/05/20/spigotプラグイン講座3-3イベントをキャンセルしよう/ などで勉強しよう
    @EventHandler
    public void onLogin(PlayerJoinEvent e){
        e.getPlayer().sendMessage("§d§lM§f§la§a§ln§f§l10サーバーへようこそ！");
        e.getPlayer().sendMessage("§e§lHPやWIKIは最新のものをチェックしてね！->§f§l man10.red");
    }
}
