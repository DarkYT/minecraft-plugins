package fr.endwork.gotuhc.event.game;

import fr.endwork.gotuhc.game.Game;
import lombok.Getter;
import org.bukkit.event.HandlerList;

public class GameStartEvent extends GameEvent {

  @Getter private static final HandlerList handlerList = new HandlerList();

  public GameStartEvent(Game game) {
    super(game);
  }

  @Override
  public HandlerList getHandlers() {
    return handlerList;
  }

}
