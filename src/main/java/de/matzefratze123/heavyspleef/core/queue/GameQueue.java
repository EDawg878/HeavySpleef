package de.matzefratze123.heavyspleef.core.queue;

import de.matzefratze123.heavyspleef.core.Game;
import de.matzefratze123.heavyspleef.objects.SpleefPlayer;

public class GameQueue {
	
	private Queue<SpleefPlayer> queue;
	private Game game;
	
	public GameQueue(Game game) {
		this.game = game;
		this.queue = new ArrayQueue<SpleefPlayer>();
	}
	
	public int addPlayer(SpleefPlayer player) {
		if (queue.contains(player))
			return -1;
		
		return queue.add(player);
	}
	
	public void removePlayer(SpleefPlayer player) {
		if (!queue.contains(player))
			return;
		
		queue.remove(player);
	}
	
	public boolean contains(SpleefPlayer player) {
		return queue.contains(player);
	}
	
	public void processQueue() {
		SpleefPlayer currentItem = null;
		
		do {
			currentItem = queue.remove();
			
			if (currentItem != null && currentItem.isOnline()) {		
				game.join(currentItem);
			}
		} while (currentItem != null);
	}
	
	public int size() {
		return queue.size();
	}

	public void clear() {
		
	}
	
}