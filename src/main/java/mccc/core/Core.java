package mccc.core;

import mccc.core.commands.AdminCommands;
import mccc.core.listeners.GamemodeListener;
import mccc.core.listeners.PlayerListener;
import mccc.core.local.Repository;
import mccc.core.placeholders.CoreExpansion;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Core extends JavaPlugin {

  public Repository repository;
  public ApiManager apiManager;
  public StageManager stageManager;

  public CoreExpansion placeholderManager;
  public PermissionManager permissionManager;
  public BukkitAudiences adventureApi;

  public OfflinePlayerScheduler offlinePlayerScheduler;

  @Override
  public void onEnable() {

    // Plugin startup logic
    getLogger().info("MCCC Core plugin started");

    // Commands registration
    if (getCommand("core") == null)
      getLogger().warning("Unable to register commands");
    else
      Objects.requireNonNull(getCommand("core")).setExecutor(new AdminCommands(this));

    // Default core configuration
    saveDefaultConfig();

    // Repository initialization
    repository = new Repository(this);
    repository.fetch();

    // LuckPerms initialization
    permissionManager = new PermissionManager(this);


    // API initialization
    apiManager = new ApiManager(this);
    apiManager.teamManager.assignColors();
    adventureApi = BukkitAudiences.create(this);
    offlinePlayerScheduler = new OfflinePlayerScheduler(this);


    // Placeholders initialization
    placeholderManager = new CoreExpansion(this);
    placeholderManager.register();

    // Listeners initialization
    getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    getServer().getPluginManager().registerEvents(new GamemodeListener(this), this);

  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
    adventureApi.close();
  }

  public void registerStageManager(StageManager stageManager_) {
    stageManager = stageManager_;
  }
}
