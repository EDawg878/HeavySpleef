/**
 *   HeavySpleef - The simple spleef plugin for bukkit
 *   
 *   Copyright (C) 2013 matzefratze123
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package me.matzefratze123.heavyspleef.command;

import me.matzefratze123.heavyspleef.core.Game;
import me.matzefratze123.heavyspleef.core.GameManager;
import me.matzefratze123.heavyspleef.utility.Permissions;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStartOnReachMinimum extends HSCommand {

	public CommandStartOnReachMinimum() {
		setMaxArgs(2);
		setMinArgs(1);
		setPermission(Permissions.STARTONMINPLAYERS.getPerm());
		setUsage("/spleef startonminplayers <Name> [true|false]");
		setOnlyIngame(true);
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		if (!GameManager.hasGame(args[0].toLowerCase())) {
			p.sendMessage(_("arenaDoesntExists"));
			return;
		}
		
		Game game = GameManager.getGame(args[0].toLowerCase());
		if (args.length == 1) {
			p.sendMessage(_("startOnMinPlayersInfo", String.valueOf(game.startsOnMinPlayers())));
			return;
		} else if (args.length == 2) {
			boolean start = Boolean.parseBoolean(args[1]);
			game.setStartOnMinPlayers(start);
			
			String message = start ? _("startOnMinPlayersTrue") : _("startOnMinPlayersFalse");
			p.sendMessage(message);
		}
	}

}