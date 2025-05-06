package render

import blocks.DefaultBlock
import camera.Camera
import math.*
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import world.World
import kotlin.math.max
import kotlin.math.min

class Draw(private val drawer: Drawer, private val world: World, private val camera: Camera) {
    fun clearScreen(color: ColorRGBa = ColorRGBa.BLACK) {
        drawer.clear(color)
    }

    private fun drawBlock(block: DefaultBlock, x: Int, y: Int) {
        drawer.image(block.texture, getCoordinatesOfBlockOnScreen(x, y, camera), blockSize, blockSize)
    }

    private fun getXRangeToRender(): IntProgression {
        return clamp(
            (camera.x - renderDistance).toInt(),
            0,
            worldSizeX - 1
        )..<clamp((camera.x + renderDistance).toInt(), 0, worldSizeX - 1)
    }

    private fun getYRangeToRender(): IntProgression {
        return clamp(
            (camera.y - renderDistance).toInt(),
            0,
            worldSizeY - 1
        )..<clamp((camera.y + renderDistance).toInt(), 0, worldSizeY - 1)
    }

    fun drawWorld() {
        for (x in getXRangeToRender()) {
            for (y in getYRangeToRender()) {
                val blockToRender = world.getBlock(x, y)
                if (blockToRender != null){
                    drawBlock(blockToRender, x, y)
                }
            }
        }
    }
}