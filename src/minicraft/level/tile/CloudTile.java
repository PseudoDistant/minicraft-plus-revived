package minicraft.level.tile;

import minicraft.entity.Entity;
import minicraft.entity.ItemEntity;
import minicraft.entity.Player;
import minicraft.gfx.Color;
import minicraft.gfx.Screen;
import minicraft.gfx.Sprite;
import minicraft.gfx.ConnectorSprite;
import minicraft.gfx.ConnectorSprite;
import minicraft.item.Item;
import minicraft.item.StackableItem;
import minicraft.item.ToolItem;
import minicraft.item.ToolType;
import minicraft.item.Items;
import minicraft.level.Level;

public class CloudTile extends Tile {
	private static ConnectorSprite sprite = new ConnectorSprite(CloudTile.class, new Sprite(4, 0, 3, 3, Color.get(333, 444, 555, -1), 0), new Sprite(7, 0, 2, 2, Color.get(333, 444, 555, -1), 0), ConnectorSprite.makeSprite(2, 2, Color.get(444, 444, 555, 444), 0, false, 19, 18, 20, 19))
	{
		public boolean connectsTo(Tile tile, boolean isSide) {
			//System.out.println("using overridden");
			return tile != Tiles.get("Infinite Fall");
		}
	};
	
	protected static void addInstances() {
		Tiles.add(new CloudTile("Cloud"));
	}
	
	private CloudTile(String name) {
		super(name, sprite);
	}

	public boolean mayPass(Level level, int x, int y, Entity e) {
		return true;
	}

	public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
		if (item instanceof ToolItem) {
			ToolItem tool = (ToolItem) item;
			if (tool.type == ToolType.Shovel && player.payStamina(5)) {
				// level.setTile(xt, yt, Tiles.get("Infinite Fall"), 0); // would allow you to shovel cloud, I think.
				level.dropItem(xt<<4, yt<<4, 1, 3, Items.get("cloud"));
				return true;
			}
		}
		return false;
	}
}
