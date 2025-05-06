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
    private fun drawBlock(block: DefaultBlock?, x: Int, y: Int){
        if (block != null)
            drawer.image(block.texture, getCoordinatesOfBlockOnScreen(x, y, camera), blockSize, blockSize)
    }
    fun drawWorld(){
        for(x in clamp((camera.x - renderDistance).toInt(), 0, worldSizeX-1)..<clamp((camera.x + renderDistance).toInt(), 0, worldSizeX-1)){
            for (y in clamp((camera.y - renderDistance).toInt(), 0, worldSizeY-1)..<clamp((camera.y + renderDistance).toInt(), 0, worldSizeY-1)){
                drawBlock(world.getBlock(x, y), x, y)
            }
        }
    }
}