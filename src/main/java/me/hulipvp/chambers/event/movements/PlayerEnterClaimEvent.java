package me.hulipvp.chambers.event.movements;

import org.bukkit.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.hulipvp.chambers.claim.structure.Claim;
import me.hulipvp.chambers.event.ChambersEvent;

/**
 * The Event that is called when a Player enters a new Claim
 */
@AllArgsConstructor
@Getter
public class PlayerEnterClaimEvent extends ChambersEvent {
	
	private Player player;
	private Claim claim;

}
