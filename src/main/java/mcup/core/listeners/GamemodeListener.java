package mcup.core.listeners;

import mcup.core.Core;
import mcup.core.events.GamemodeStageEndEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GamemodeListener implements Listener {

  @EventHandler
  public void onStageEnd(GamemodeStageEndEvent e) {
    plugin.stageManager.switchToNextStage();
  }
  private final Core plugin;

  public GamemodeListener(Core plugin_) {
    plugin = plugin_;
  }
}
