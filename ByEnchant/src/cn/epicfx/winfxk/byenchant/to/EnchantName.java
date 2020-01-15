package cn.epicfx.winfxk.byenchant.to;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Winfxk
 */
public enum EnchantName {
	/**
	 * 保护
	 */
	PROTECTION("保护", 0),
	/**
	 * 火焰保护
	 */
	PROTECTION_FIRE("火焰保护", 1),
	/**
	 * 摔落保护
	 */
	PROTECTION_FALL("摔落保护", 2),
	/**
	 * 爆炸保护
	 */
	BLAST_PROTECTION("爆炸保护", 3),
	/**
	 * 弹射物保护
	 */
	PROJECTILE_PROTECTION("弹射物保护", 4),
	/**
	 * 荆棘
	 */
	THORNS("荆棘", 5),
	/**
	 * 水下呼吸
	 */
	RESPIRATION("水下呼吸", 6, 3),
	/**
	 * 深海探索者
	 */
	DEPTH_STRIDER("深海探索者", 7, 3),
	/**
	 * 水下速掘
	 */
	AQUA_AFFINITY("水下速掘", 8, 1),
	/**
	 * 锋利
	 */
	SHARPNESS("锋利", 9),
	/**
	 * 亡灵杀手
	 */
	SMITE("亡灵杀手", 10),
	/**
	 * 节肢杀手
	 */
	BANE_OF_ARTHROPODS("节肢杀手", 11),
	/**
	 * 击退
	 */
	KNOCKBACK("击退", 12, 2),
	/**
	 * 火焰附加
	 */
	FIRE_ASPECT("火焰附加", 13, 2),
	/**
	 * 抢夺
	 */
	LOOTING("抢夺", 14, 3),
	/**
	 * 效率
	 */
	EFFICIENCY("效率", 15),
	/**
	 * 精准采集
	 */
	SILK_TOUCH("精准采集", 16, 1),
	/**
	 * 耐久
	 */
	DURABILITY("耐久", 17),
	/**
	 * 时运
	 */
	FORTUNE("时运", 18, 3),
	/**
	 * 力量
	 */
	POWER("力量", 19),
	/**
	 * 冲击
	 */
	PUNCH("冲击", 20, 2),
	/**
	 * 火矢
	 */
	FLAME("火矢", 21, 1),
	/**
	 * 无限
	 */
	INFINITY("无限", 22, 1),
	/**
	 * 海之眷顾
	 */
	LUCK_OF_THE_SEA("海之眷顾", 23, 3),
	/**
	 * 饵钓
	 */
	LURE("饵钓", 24, 3),
	/**
	 * 冰霜行者
	 */
	FROST_WALKER("冰霜行者", 25, 2),
	/**
	 * 经验修补
	 */
	MENDING("经验修补", 26, 1),
	/**
	 * 绑定诅咒
	 */
	BINDING_CURSE("绑定诅咒", 27, 1),
	/**
	 * 消失诅咒
	 */
	VANISHING_CURSE("消失诅咒", 28, 1),
	/**
	 * 穿刺
	 */
	IMPALING("穿刺", 29),
	/**
	 * 激流
	 */
	RIPTIDE("激流", 30, 3),
	/**
	 * 忠诚
	 */
	LOYALTY("忠诚", 31, 3),
	/**
	 * 引雷
	 */
	CHANNELING("引雷", 32, 1),
	/**
	 * 多重射击
	 */
	MULTISHOT("多重射击", 33),
	/**
	 * 穿透
	 */
	PIERCING("穿透", 34),
	/**
	 * 快速装填
	 */
	QUICK_CHARGE("快速装填", 35);

	private String Name;
	private int ID, maxLevel;
	private static final LinkedHashMap<Integer, EnchantName> ItemKey = new LinkedHashMap<>();
	static {
		for (EnchantName item : EnchantName.values())
			ItemKey.put(item.getID(), item);
	}

	public static Map<Integer, EnchantName> getAll() {
		return ItemKey;
	}

	private EnchantName(String Name, int ID) {
		this.Name = Name;
		this.ID = ID;
		this.maxLevel = 5;
	}

	private EnchantName(String Name, int ID, int maxLevel) {
		this.Name = Name;
		this.ID = ID;
		this.maxLevel = maxLevel;
	}

	/**
	 * 取得当前附魔的ID
	 * 
	 * @return
	 */
	public Integer getID() {
		return ID;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	/**
	 * 取得当前附魔的名称
	 * 
	 * @return
	 */
	public String getName() {
		return Name;
	}

}
