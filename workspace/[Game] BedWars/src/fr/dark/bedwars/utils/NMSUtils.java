package fr.dark.bedwars.utils;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;

/**
*
* @author Etrenak
*
*/

public class NMSUtils
{
    private static String version;
    private static Set<Class<?>> classes;;

    static
    {
        version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        classes = new HashSet<Class<?>>();
    }

    public static void register(String clazz) throws ClassNotFoundException
    {
        classes.add(Class.forName(clazz.replaceAll("_version_", version)));
    }

    public static Class<?> getClass(String simpleName) throws ClassNotFoundException
    {
        for(Class<?> c : classes)
            if(c.getName().endsWith(simpleName))
                return c;

        throw new ClassNotFoundException("Cannot found this class : " + simpleName + "(Is it registered ?)");
    }

    public static String getVersion()
    {
        return version;
    }
}