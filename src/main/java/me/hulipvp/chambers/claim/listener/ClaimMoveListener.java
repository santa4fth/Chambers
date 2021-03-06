package me.hulipvp.chambers.claim.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.event.movements.PlayerEnterClaimEvent;
import me.hulipvp.chambers.event.movements.PlayerLeaveClaimEvent;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.game.structure.GameStatus;
import me.hulipvp.chambers.team.structure.Team;
import me.hulipvp.chambers.team.structure.TeamType;

public class ClaimMoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (game.getStatus() != GameStatus.INGAME) {
			return;
		}
		Location to = event.getTo();
		Location from = event.getFrom();
		if (to.getBlockX() != from.getBlockX() || to.getBlockZ() != from.getBlockZ()) {
			Player player = event.getPlayer();
			Team toTeam = Chambers.getInstance().getClaimManager().getTeamAt(to);
			Team fromTeam = Chambers.getInstance().getClaimManager().getTeamAt(from);
			if (toTeam != fromTeam) {
				Bukkit.getPluginManager().callEvent(new PlayerEnterClaimEvent(player, toTeam.getClaim()));
				Bukkit.getPluginManager().callEvent(new PlayerLeaveClaimEvent(player, fromTeam.getClaim()));
				if (toTeam.getType() == TeamType.KOTH_CAP || fromTeam.getType() == TeamType.KOTH_CAP) {
					return;
				}
				player.sendMessage(ChatColor.YELLOW + "Now leaving: " + fromTeam.getFormattedName());
				player.sendMessage(ChatColor.YELLOW + "Now entering: " + toTeam.getFormattedName());
			}
		}
	}

}
