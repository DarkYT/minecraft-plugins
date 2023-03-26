package fr.endwork.gotuhc.module;


import com.google.common.collect.ImmutableMap;
import fr.endwork.gotuhc.GameCore;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import fr.endwork.gotuhc.util.Types;
import lombok.Data;

@Data
public class ModuleData {

  private final Class<? extends IModule> clazz;
  private final LifeCycle lifeCycle;
  private final boolean enabledOnStart;
  private final ImmutableMap<String, Class> settings;

  /**
   * Creates data for a scenario, which encompasses {@link Scenario}.
   *
   * @param clazz The module class type to set
   * @param setting The string value for the setting
   * @param value The string representation to attempt to set
   */
  public static void setSetting(Class<? extends IModule> clazz, String setting, String value) {
    for (ModuleData data : ModuleRegistry.getAllModules()) {
      if (data.getClazz() == clazz) {
        for (Field field : clazz.getFields()) {
          Annotation annotation = field.getAnnotation(Setting.class);
          if (annotation != null && ((Setting) annotation).value().equals(setting)) {
            ModuleHandler handler = data.getLifeCycle() == LifeCycle.SERVER
                ? GameCore.getInstance().getModuleHandler() : GameCore.getCurrentGame().getModuleHandler();
            IModule module = handler.getModule(clazz);
            field.setAccessible(true);
            try {
              field.set(module, Types.parseObject(field.getType(), value));
            } catch (IllegalAccessException e) {
              GameCore.getInstance().getLogger().warning("Unable to set setting \"" + setting + "\" to value \""
                  + value + "\" in " + clazz.getName());
              e.printStackTrace();
            }
          }
        }
      }
    }
  }

}
