package fr.endwork.gotuhc.game.exception;

import fr.endwork.gotuhc.game.GameState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class InvalidGameStateException extends RuntimeException {

  private final GameState current;
  private final GameState expected;

}
