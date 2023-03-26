package com.endwork.api.managers;

import com.endwork.api.APICore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public enum FileManager
{

    DATABASE(APICore.getInstance().getDataFolder(), "database.yml");

    private File dataFolder;
    private String fileName;

    FileManager(File dataFolder, String fileName)
    {
        this.dataFolder = dataFolder;
        this.fileName = fileName;
    }

    public static File getFile(FileManager files)
    {
        return new File(files.getDataFolder(), files.getFileName());
    }

    public static FileConfiguration getConfig(FileManager files)
    {
        return YamlConfiguration.loadConfiguration(getFile(files));
    }

    public static void save(FileConfiguration config, FileManager files)
    {
        try
        {
            config.save(getFile(files));
        }
        catch (IOException e)
        {
            APICore.getInstance().getLogger().severe("Can't save " + files.getFileName());
            e.printStackTrace();
        }
    }

    public void create()
    {
        if(!getDataFolder().exists())
            getDataFolder().mkdir();


        if(!getFile(this).exists())
        {
            try
            {
                getFile(this).createNewFile();

                if(this == DATABASE)
                {
                    FileConfiguration config = getConfig(this);

                    config.set("Database.Protocol", "jdbc:mysql://");
                    config.set("Database.Host", "localhost");
                    config.set("Database.DBName", "blunder");
                    config.set("Database.Username", "dark");
                    config.set("Database.Password", "root");

                    save(config, this);
                }

            }
            catch (IOException e)
            {
                APICore.getInstance().getLogger().severe("Can't create "+fileName);
                e.printStackTrace();
            }
        }
    }

    public File getDataFolder()
    {
        return dataFolder;
    }

    public String getFileName()
    {
        return fileName;
    }

}
