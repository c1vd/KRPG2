package render

import blocks.Block
import camera.Camera
import math.*
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.math.Vector2
import world.World

class Draw(private val drawer: Drawer, private val world: World, private val camera: Camera) {
    fun clearScreen(color: ColorRGBa = ColorRGBa.BLACK) {
        drawer.clear(color)
    }

    fun setFillColor(color: ColorRGBa){
        drawer.fill = color
    }

    private fun drawBlock(block: Block, blockPosition: Vector2) {
        drawer.image(block.texture, getCoordinatesOfBlockOnScreen(blockPosition, camera), blockSize, blockSize)
    }

    private fun getXRangeToRender(): IntProgression {
        return clamp(
            (camera.position.x - renderDistance).toInt(),
            0,
            worldSizeX - 1
        )..<clamp((camera.position.x + renderDistance).toInt(), 0, worldSizeX - 1)
    }

    private fun getYRangeToRender(): IntProgression {
        return clamp(
            (camera.position.y - renderDistance).toInt(),
            0,
            worldSizeY - 1
        )..<clamp((camera.position.y + renderDistance).toInt(), 0, worldSizeY - 1)
    }

    private fun drawBackground(xRange: IntProgression, yRange: IntProgression) {}
    private fun drawBlocks(xRange: IntProgression, yRange: IntProgression) {
        for (x in xRange) {
            for (y in yRange) {
                val blockToRender = world.getBlock(x, y)
                if (blockToRender != null) {
                    drawBlock(blockToRender, Vector2(x.toDouble(), y.toDouble()))
                }
            }
        }
    }

    fun drawWorld() {
        val xRange = getXRangeToRender()
        val yRange = getYRangeToRender()
        drawBackground(xRange, yRange)
        drawBlocks(xRange, yRange)
    }
}