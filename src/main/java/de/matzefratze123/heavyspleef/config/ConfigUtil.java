/**
 *   HeavySpleef - Advanced spleef plugin for bukkit
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
package de.matzefratze123.heavyspleef.config;

import de.matzefratze123.heavyspleef.HeavySpleef;
import de.matzefratze123.heavyspleef.core.BroadcastType;

public class ConfigUtil {
	
	/**
	 * Gets an broadcast type from the config.
	 * Internal method
	 * 
	 * @param name The config message name
	 */
	public static BroadcastType getBroadcast(String name) {
		String str = HeavySpleef.getSystemConfig().getString("messages." + name);
		if (str == null)
			return BroadcastType.RADIUS;
		
		BroadcastType type = BroadcastType.getBroadcastType(str);
		if (type == null)
			return BroadcastType.RADIUS;
		
		return type;
	}
	
}
