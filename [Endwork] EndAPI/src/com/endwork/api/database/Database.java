package com.endwork.api.database;

import com.endwork.api.APICore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class Database
{

    private Connection connection;
    private String urlbase, host, database, user, pass;

    public Database(String urlbase, String host, String database, String user, String pass)
    {
        this.urlbase = urlbase;
        this.host = host;
        this.database = database;
        this.user = user;
        this.pass = pass;
    }

    public void connection()
    {
        if (!isConnected())
        {
            try
            {
                connection = DriverManager.getConnection(urlbase + host +  "/" + database, user, pass);
                APICore.getInstance().getLogger().info("Database connection succeed");
            }
            catch (SQLException e)
            {
                APICore.getInstance().getLogger().severe("§cDatabase connection failed");
                Bukkit.getPluginManager().disablePlugin(APICore.getInstance());
                e.printStackTrace();
            }
        }
    }

    public void disconnect()
    {
        if (isConnected())
        {
            try
            {
                connection.close();
                APICore.getInstance().getLogger().fine("Database connection closed");
            }
            catch (SQLException e)
            {
                APICore.getInstance().getLogger().severe("§cDatabase disconnection failed");
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected()
    {
        return connection != null;
    }

    public void update(String qry)
    {
        try
        {
            PreparedStatement s = connection.prepareStatement(qry);
            s.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Object query(String qry, Function<ResultSet, Object> consumer)
    {
        try
        {
            PreparedStatement s = connection.prepareStatement(qry);
            ResultSet rs = s.executeQuery();
            return consumer.apply(rs);
        }
        catch (SQLException e)
        {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void query(String qry, Consumer<ResultSet> consumer)
    {
        try
        {
            PreparedStatement s = connection.prepareStatement(qry);
            ResultSet rs = s.executeQuery();
            consumer.accept(rs);
        }
        catch (SQLException e)
        {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    public boolean isRegistered(Player player){
        String uuid = player.getUniqueId().toString().replace("-", "");
        return (boolean) query("SELECT * FROM t_player WHERE uuid=UNHEX('" + uuid + "')", rs -> {
            try {
                if(rs.next()) return true;
            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        });
    }

    public boolean isRegistered(String player){
        return (boolean) query("SELECT * FROM t_player WHERE pseudo='" + player + "'", rs -> {
            try {
                if(rs.next()) return true;
            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        });
    }

    public void register(Player player){
        if(isRegistered(player)) return;
        String uuid = player.getUniqueId().toString().replace("-", "");
        update("INSERT INTO t_player (uuid, pseudo) VALUES (UNHEX('" + uuid + "'), '" + player.getName() + "')");
    }
}

