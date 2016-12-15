package buildcraft.lib;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.text.TextFormatting;

import buildcraft.lib.misc.ColourUtil;
import buildcraft.lib.misc.LocaleUtil;

/** Configuration file for lib. In order to keep lib as close to being just a library mod as possible, these are not set
 * by a config file, but instead by BC Core. Feel free to set them yourself, from your own configs, if you do not depend
 * on BC COre itself, and it might not be loaded in the mod environment. */
public class BCLibConfig {
    /** If true then items and blocks will display the colour of an item (one of {@link EnumDyeColor}) with the correct
     * {@link TextFormatting} colour value.<br>
     * This changes the behaviour of {@link ColourUtil#convertColourToTextFormat(EnumDyeColor)}. */
    public static boolean useColouredLabels = true;

    /** The lifespan (in seconds) that spawned items will have, when dropped by a quarry or builder (etc) */
    public static int itemLifespan = 60;

    /** If true then fluidstacks will localize with something similar to "4B Water" rather than "4000mB of Water" when
     * calling {@link LocaleUtil#localizeFluidStatic(net.minecraftforge.fluids.FluidStack)} */
    public static boolean useBucketsStatic = true;

    /** If true then fluidstacks will localize with something similar to "4B/s" rather than "4000mB/t" when calling
     * {@link LocaleUtil#localizeFluidStatic(net.minecraftforge.fluids.FluidStack)} */
    public static boolean useBucketsFlow = true;

    /** If true then fluidstacks and Mj will be localized with longer names (for example "1.2 Buckets per second" rather
     * than "60mB/t") */
    public static boolean useLocalizedLongName = false;

    /** If true then ItemRenderUtil.renderItemStack will use the facing parameter to rotate the item */
    public static RenderRotation rotateTravelingItems = RenderRotation.ENABLED;

    /** Resets cached values across various BCLib classes that rely on these config options. */
    public static void refreshConfigs() {
        LocaleUtil.onConfigChanged();
    }

    public enum RenderRotation {
        DISABLED {
            @Override
            public EnumFacing changeFacing(EnumFacing dir) {
                return EnumFacing.EAST;
            }
        },
        HORIZONTALS_ONLY {
            @Override
            public EnumFacing changeFacing(EnumFacing dir) {
                return dir.getAxis() == Axis.Y ? EnumFacing.EAST : dir;
            }
        },
        ENABLED {
            @Override
            public EnumFacing changeFacing(EnumFacing dir) {
                return dir;
            }
        };

        public abstract EnumFacing changeFacing(EnumFacing dir);
    }
}