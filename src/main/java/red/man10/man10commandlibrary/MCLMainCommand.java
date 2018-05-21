package red.man10.man10commandlibrary;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MCLMainCommand implements CommandExecutor {
    Man10CommandLibrary mcl;
    public MCLMainCommand(Man10CommandLibrary mcl){
        this.mcl = mcl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            mcl.getLogger().info("このプラグインはコンソールからのコマンドを想定していません");
            return true;
        }
        Player p = (Player)sender;
        if(args.length==1){
            if(args[0].equalsIgnoreCase("plugins")||args[0].equalsIgnoreCase("pl")){
                if(!p.hasPermission("mcl.plugins")){
                    p.sendMessage("§a[MCSPlugins]§r[Plugin,list,how,can,I,show,it,do,not,you,:)]");
                    return true;
                }
                p.sendMessage("§a[MCSPlugins]§r"+ MCLData.getmPluginList());
                return true;
            }
        }
        p.sendMessage("§e§l==============[MCL]==============");
        p.sendMessage("§6§l/mcl plugins/pl §f§l: MCLに入っているmPluginリスト表示");
        p.sendMessage("§e§l=================================");
        return true;
    }
}
