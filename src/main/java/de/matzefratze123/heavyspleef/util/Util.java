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
package de.matzefratze123.heavyspleef.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.Material;

import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;

import de.matzefratze123.heavyspleef.objects.SimpleBlockData;

public class Util {

	public static String formatMaterialName(String str) {
		str = str.toLowerCase();
		
		String[] parts = str.split("_");
		String realName = "";
		
		Iterator<String> iter = Arrays.asList(parts).iterator();
		while (iter.hasNext()) {
			String next = iter.next();
			
			char[] chars = next.toCharArray();
			chars[0] = Character.toUpperCase(chars[0]);
			
			next = String.copyValueOf(chars);
			realName += next;
			if (iter.hasNext())
				realName += " ";
		}
		
		return realName;
	}
	
	/**
	 * Converts a string to a material
	 * 
	 * @param s The string
	 * @return The material specified by this string. If there is no Material the return is null
	 */
	private static Material getMaterialFromName(String s) {
		if (s == null)
			return null;
		Material mat;
		
		try {
			int id = Integer.parseInt(s);
			mat = Material.getMaterial(id);
		} catch (Exception e) {
			try {
				s = s.toUpperCase();
				mat = Material.getMaterial(s);
			} catch (Exception e1) {//Catch the exception again
				mat = null;
			}
		}
		
		return mat;
	}
	
	/**
	 * Converts an argument to material and data
	 * 
	 * @param str The string
	 * @return A simpleblockdata objects that contains the material and data
	 */ 
	public static SimpleBlockData parseMaterial(String str, boolean onlySolid) {
		if (str == null)
			return null;
		String[] parts = str.split(":");
		
		if (parts.length < 1)
			return null;
		Material m = getMaterialFromName(parts[0]);
		if (m == null)
			return null;
		if (!SimpleBlockData.isSolid(m.getId()) && onlySolid)
			return null;
		byte data = 0;
		
		if (parts.length > 1) {
			try {
				data = Byte.parseByte(parts[1]);
			} catch (Exception e) {}
		}
		return new SimpleBlockData(m, data);
	}
	
	/**
	 * Gets the transparent materials for the method getTargetBlock(HashSet<Byte>, int) in the type Player
	 * 
	 * @return A HashSet containing air, water and lava
	 */
	public static HashSet<Byte> getTransparentMaterials() {
		HashSet<Byte> set = new HashSet<Byte>();
		
		set.add((byte)0);
		set.add((byte)8);
		set.add((byte)9);
		set.add((byte)10);
		set.add((byte)11);
		
		return set;
	}
	
	public static String toFriendlyString(Iterable<?> iterable, String seperator) {
		Iterator<?> iter = iterable.iterator();
		StringBuilder builder = new StringBuilder();
		
		while(iter.hasNext()) {
			Object next = iter.next();
			
			builder.append(next);
			if (iter.hasNext())
				builder.append(seperator);
		}
		
		return builder.toString();
	}
	
	public static String toFriendlyString(Object[] o, String seperator) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < o.length; i++) {
			Object next = o[i];
			
			builder.append(next);
			if (o.length >= i + 2)
				builder.append(seperator);
		}
		
		return builder.toString();
	}
	
	public static Vector toWorldEditVector(Location location) {
		int x, y, z;
		
		x = location.getBlockX();
		y = location.getBlockY();
		z = location.getBlockZ();
		
		return new Vector(x, y, z);
	}
	
	public static Location toBukkitLocation(LocalWorld world, Vector vector) {
		int x, y, z;
		
		x = vector.getBlockX();
		y = vector.getBlockY();
		z = vector.getBlockZ();
		
		return new Location(BukkitUtil.toWorld(world), x, y, z);
	}
	
	public static Location getMin(Location l1, Location l2) {
		return new Location(
				l1.getWorld(), 
				Math.min(l1.getX(), l2.getX()),
				Math.min(l1.getY(), l2.getY()),
				Math.min(l1.getZ(), l2.getZ())
							);
	}
	
	public static Location getMax(Location l1, Location l2) {
		return new Location(
				l1.getWorld(), 
				Math.max(l1.getX(), l2.getX()),
				Math.max(l1.getY(), l2.getY()),
				Math.max(l1.getZ(), l2.getZ())
							);
	}
	
	/**
	 * Checks if a string can be parsed into a number
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		boolean isNumeric = true;
		
		if (str == null) {
			return false;
		}
		if (str.isEmpty()) {
			return false;
		}
		
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			
			if (c == '-' && i == 0) {
				continue;
			}
			
			isNumeric &= Character.isDigit(c);
		}
		
		return isNumeric;
	}

}
