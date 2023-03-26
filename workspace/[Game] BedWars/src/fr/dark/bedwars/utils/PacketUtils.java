package fr.dark.bedwars.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

/**
*
* @author Etrenak
*
*/
public class PacketUtils
{
    static
    {
        try
        {
            NMSUtils.register("org.bukkit.craftbukkit._version_.entity.CraftPlayer");
            NMSUtils.register("net.minecraft.server._version_.PlayerConnection");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void setField(String field, Object value, Object instance) throws Exception
    {
        Class<?> c = instance.getClass();
        Field f = c.getDeclaredField(field);
        f.setAccessible(true);
        f.set(instance, value);
    }

    public static void printFields(Object o, String indent) throws Exception
    {

        if(o == null)
        {
            print(indent + "null");
            return;
        }
        Class<?> c = o.getClass();

        if(indent.length() > 4 * 2)
            return;

        if(o.getClass().isEnum())
        {
            print(indent + "§cenum...");
            return;
        }

        int i = 0;

        for(Field f : c.getDeclaredFields())
        {
            f.setAccessible(true);

            if(f.get(o) == null)
                print(indent + "§cnull");
            else
            {
                Class<?> fType = f.get(o).getClass();

                if(fType.isArray())
                {
                    if(Array.getLength(f.get(o)) > 1000)
                        print("§b" + indent + f.getName() + "=" + f.get(o).getClass().getSimpleName() + ": " + f.get(o) + "§e>1000");

                    else
                        for(int k = 0; k < Array.getLength(f.get(o)); k++)
                        {
                            Object current = Array.get(f.get(o), k);
                            if(current == null)
                                print("§a" + indent + f.getName() + "[" + k + "]=§cnull");

                            else if(isPrimitive(current.getClass()))
                                print("§b" + indent + f.getName() + "[" + k + "]=" + current);

                            else
                            {
                                print("§a" + indent + f.getName() + "[" + k + "]=" + current.getClass().getSimpleName());
                                printFields(current, indent + "    ");
                            }
                        }
                }

                else if(isPrimitive(fType))
                    print("§b" + indent + f.getName() + "=" + f.get(o).getClass().getSimpleName() + ": " + f.get(o));

                else if(Iterable.class.isAssignableFrom(fType))
                {
                    for(Object it : (Iterable<?>) f.get(o))
                    {
                        if(it == null)
                            print("§a" + indent + f.getName() + "[" + i++ + "]=§cnull");

                        else if(isPrimitive(it.getClass()))
                            print("§b" + indent + f.getName() + "[" + i++ + "]=" + it);

                        else
                        {
                            print("§a" + indent + f.getName() + "[" + i++ + "]=" + it.getClass().getSimpleName());
                            printFields(it, indent + "    ");
                        }
                    }
                }
                else
                {
                    print("§a" + indent + f.getName() + "=" + f.get(o).getClass().getSimpleName());
                    printFields(f.get(o), indent + "    ");
                }
            }
        }
    }

    public static void print(Object o)
    {
        System.out.println(o.toString());
    }

    public static boolean isPrimitive(Class<?> c)
    {
        try
        {
            return c.isPrimitive() || Class.forName(c.getSimpleName().toLowerCase()).isPrimitive();
        }catch(ClassNotFoundException ignore)
        {}
        return false;
    }

    public static Object getPlayerConnection(Player p) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException
    {
        Object craftPlayer = NMSUtils.getClass("CraftPlayer").cast(p);
        Object entityPlayer = NMSUtils.getClass("CraftPlayer").getDeclaredMethod("getHandle").invoke(craftPlayer);
        Object playerConnection = NMSUtils.getClass("PlayerConnection").cast(entityPlayer.getClass().getField("playerConnection").get(entityPlayer));
        return playerConnection;
    }

    public static void sendPacket(Object playerConnection, Object packet) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException
    {
        NMSUtils.getClass("PlayerConnection").getDeclaredMethod("sendPacket", NMSUtils.getClass("Packet")).invoke(playerConnection, packet);
    }

    public static void sendPacket(Player p, Object packet) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, NoSuchFieldException
    {
        sendPacket(getPlayerConnection(p), packet);
    }
}
