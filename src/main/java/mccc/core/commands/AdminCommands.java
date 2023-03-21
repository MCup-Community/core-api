package mccc.core.commands;

import mccc.core.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AdminCommands implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

    if (args.length == 0) {
      // TODO
      return false;
    }

    if (args[0].equals("score")) {
      if (args.length == 1) {
        // TODO
        return false;
      }

      if (args[1].equals("add")) {
        if (args.length == 2) {

          return false;
        }

        if (args[2].equals("player") || args[2].equals("team")) {
          if (args.length < 5) {

            return false;
          }

          String entity_name = args[3];
          int amount = Integer.parseInt(args[4]);
          String message = null;

          if (args.length == 6)
            message = args[5];

          if (args[2].equals("player"))
            plugin.apiManager.scoreManager.add_score(plugin.apiManager.playerManager.get_player_object(entity_name), amount, message);
          else
            plugin.apiManager.scoreManager.add_score(plugin.apiManager.teamManager.get_team(entity_name), amount, message, null);
        }
      }
    }

    if (args[0].equals("permissions")) {
      if (args.length == 1) {
        // TODO
        return true;
      }

      if (args[1].equals("clear"))
        plugin.permissionManager.clear_groups();

      if (args[1].equals("init"))
        plugin.permissionManager.initialize_team_groups();

      if (args[1].equals("fill"))
        plugin.permissionManager.fill_team_groups();

      if (args[1].equals("give")) {
        if (args.length == 2) {
          // TODO
          return true;
        }

        plugin.permissionManager.assign_player_to_team(args[2]);
      }
    }

    if (args[0].equals("database")) {
      if (args.length == 1) {
        // TODO
        return false;
      }

      if (args[1].equals("fetch")) {
        plugin.repository.fetch();
      }

      if (args[1].equals("write")) {
        plugin.repository.write();
      }
    }

    return true;
  }

  private final Core plugin;
  public AdminCommands(Core plugin_) {
    plugin = plugin_;
  }
}
