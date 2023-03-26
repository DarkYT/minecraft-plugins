package com.endwork.api;

import com.endwork.api.database.Database;
import com.endwork.api.managers.FileManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class APICore extends JavaPlugin {

    private static APICore instance;
    private Database database;

    @Override
    public void onEnable() {
        //Instantiate global variables
        instance = this;

        for(FileManager file : FileManager.values()) file.create();
        EnableDatabase();
    }

    //Getters
    public static APICore getInstance() {
        return instance;
    }

    public Database getDatabase() {
        return database;
    }

    //Enabling functions
    private void EnableDatabase(){
        FileConfiguration databaseFile = FileManager.getConfig(FileManager.DATABASE);

        String protocol = databaseFile.getString("Database.Protocol");
        String host = databaseFile.getString("Database.Host");
        String databaseName = databaseFile.getString("Database.DBName");
        String user = databaseFile.getString("Database.Username");
        String pass = databaseFile.getString("Database.Password");

        database = new Database(protocol, host, databaseName, user, pass);
        database.connection();
    }
}
