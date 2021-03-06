package red.man10.man10commandlibrary;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import red.man10.man10commandlibrary.Lib.Example_mPlugin;
import red.man10.man10commandlibrary.Lib.IK_randomChoice;
import red.man10.man10commandlibrary.Lib.Your_mPlugin;

import java.io.File;
import java.util.ArrayList;

public class MCLData{
    static Man10CommandLibrary mcl;
    public static VaultManager vault;
    public MCLData(Man10CommandLibrary mcl){
        this.mcl = mcl;
        LibLoad();
        vault = new VaultManager(mcl);
    }

    //mPluginリスト
    public static ArrayList<String> mPluginList = new ArrayList<>();

    public void LibLoad(){
        /////////////////////////////////////////////////
        //※必須！！ ここに new ○○○(mcl); と自分のクラスを追加する。
        /////////////////////////////////////////////////
        new Example_mPlugin(mcl);
        new Your_mPlugin(mcl);
        new IK_randomChoice(mcl);
    }

    //mPluginリスト取得
    public static ArrayList<String> getmPluginList(){
        return mPluginList;
    }

    //自分のmPluginのファイルをゲットする
    public static FileConfiguration getMyConfig(String pluginName){
        String path = mcl.getDataFolder()+"/"+pluginName+".yml";
        return YamlConfiguration.loadConfiguration(new File(path));
    }

    //自分のmPluginのファイルをセーブする
    public static boolean saveMyConfig(String pluginName,FileConfiguration config){
        String path = mcl.getDataFolder()+"/"+pluginName+".yml";
        try{
            config.save(path);
        }catch (Exception e){
            return false;
        }

        return true;
    }
}
