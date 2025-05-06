import camera.Camera
import render.Draw
import entities.Player
import math.*
import org.openrndr.*
import org.openrndr.color.ColorRGBa
import world.World

fun main(){
    application {
        configure{
            width = widthOfScreen
            height = heightOfScreen
        }
        program {
            val defaultPlayerX = 0.0
            val defaultPlayerY = 0.0

            val world = World("world1.world")
            val player = Player(defaultPlayerX, defaultPlayerY, 100.0, world)
            val camera = Camera(player)
            val renderer = Draw(drawer, world, camera)

            var begin = 0.0
            var frameTime: Double
            mouse.buttonDown.listen {
                player.goTo(getCoordinatesFromMousePixelPositionAndCamera(mouse.position, camera))
            }
            extend{
                frameTime = seconds - begin
                begin = seconds

                updateValues()
                // camera moving
                camera.moveCamera()

                // screen cleaning
                renderer.clearScreen()

                // FPS and FrameTime
                drawer.fill = ColorRGBa.WHITE
                drawer.text("FrameTime: ${(frameTime*10000).toInt().toDouble()/10} ms; FPS: ${(1/frameTime).toInt()}", 10.0, 20.0)

                drawer.circle(widthOfScreen/2.0, heightOfScreen/2.0, 2.0)

                // TODO: make physics

                // render
                renderer.drawWorld()
            }
            world.save()
        }
    }
}