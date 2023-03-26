package fr.endwork.gotuhc.event.game;

import fr.endwork.gotuhc.game.Game;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;

@Getter
@RequiredArgsConstructor
public abstract class GameEvent extends Event {

  private final Game game;

}
