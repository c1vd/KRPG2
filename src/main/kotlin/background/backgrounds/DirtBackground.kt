package background.backgrounds

import background.Background
import background.textures.DirtBackgroundTexture
import org.openrndr.draw.ColorBuffer

object DirtBackground : Background {
    override val texture: ColorBuffer = DirtBackgroundTexture
}