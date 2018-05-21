package red.man10.man10commandlibrary.Lib;

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

public class Example_mPlugin implements CommandExecutor, Listener {

    //////////////////////////////////////////////
    //    Example_mPlugin
    //   created by Mr_IK(https://twitter.com/Mr_IK2302)
    //
    //   mPlugin list view mPlugin.
    //////////////////////////////////////////////


    String myPluginname;
    Man10CommandLibrary mcl;
    //mPluginロード
    public Example_mPlugin(Man10CommandLibrary mcl){
        this.mcl = mcl;

        //ここに自分のプラグインの名前を入力してください。(他の人と被らないように)
        myPluginname = "Example_mPlugin";

        //プラグインリストへのデータ送信。リストに載りたくない場合は削除
        MCLData.mPluginList.add(myPluginname);

        //コマンドが使いたい場合は下の例に沿って入力する。
        //mcl.getCommand("ここに欲しいコマンドを入力").setExecutor(this);
        mcl.getCommand("mbal").setExecutor(this);

        //イベントハンドラが使いたい場合はこの文を残しておく
        mcl.getServer().getPluginManager().registerEvents (this,mcl);

        //コンフィグを読み込むコードです。必要ないなら消してね。
        config = MCLData.getMyConfig(myPluginname);
        configLoad();
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
        Double bal = MCLData.vault.getBalance(p.getUniqueId());
        String str = JPmoneytrace(bal);
        p.sendMessage("§a§l[Mbal]現在の所持金は §9§l"+str+" §a§lです");
        return true;
    }

    //呼び出すと、config内の"test"が存在するかifをかけ、存在しない場合"test"を作成しセーブする
    public void configLoad(){
        if(!config.contains("test")){
            config.set("test","コンフィグテスト");
            MCLData.saveMyConfig(myPluginname,config);
        }
    }

    public String JPmoneytrace(double bal){
        int balance = (int)bal;
        int oku = 0;
        int man = 0;
        if(bal >= 100000000){
            oku = balance/100000000;
            balance = balance%100000000;
        }
        if(bal >= 10000){
            man = balance/10000;
            balance = balance%10000;
        }
        return oku+"億"+man+"万"+balance+"円";
    }

    //イベントハンドラの使い方は http://man10.red/2018/05/20/spigotプラグイン講座3-3イベントをキャンセルしよう/ などで勉強しよう
    @EventHandler
    public void onLogin(PlayerJoinEvent e){
        //config内の"test"を呼び出すテスト。
        e.getPlayer().sendMessage("§d§lM§f§la§a§ln§f§l10サーバーへようこそ！: "+config.getString("test"));
    }
}
