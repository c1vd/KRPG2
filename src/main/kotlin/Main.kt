import camera.Camera
import render.Draw
import entities.Player
import math.*
import org.openrndr.*
import org.openrndr.color.ColorRGBa
import world.World


var frameTime: Double = 0.0

fun main(){
    application {
        configure{
            width = widthOfScreen
            height = heightOfScreen
            windowResizable = true
        }
        program {
            var isMouseButtonPressed = false
            val defaultPlayerX = 0.0
            val defaultPlayerY = 0.0

            val world = World("world1.world")
            val player = Player(defaultPlayerX, defaultPlayerY, 100.0, world, speed = 10.0)
            val camera = Camera(player)
            val renderer = Draw(drawer, world, camera)

            var begin = 0.0
            mouse.buttonDown.listen {
                if(it.button == MouseButton.LEFT)
                    isMouseButtonPressed = true
            }
            mouse.buttonUp.listen {
                if(it.button == MouseButton.LEFT)
                    isMouseButtonPressed = false
            }

            extend{
                frameTime = seconds - begin
                begin = seconds


                updateValues()
                widthOfScreen = width
                heightOfScreen = height
                // camera moving
                camera.moveCamera()

                // screen cleaning
                renderer.clearScreen()

                // FPS and FrameTime
                drawer.fill = ColorRGBa.WHITE
                drawer.text("FrameTime: ${(frameTime*10000).toInt().toDouble()/10} ms; FPS: ${(1/frameTime).toInt()}", 10.0, 20.0)

                drawer.circle(widthOfScreen/2.0, heightOfScreen/2.0, 2.0)

                if (isMouseButtonPressed){
                    player.go(getCoordinatesFromMousePixelPositionAndCamera(mouse.position, camera), frameTime)
                }

                // render
                renderer.drawWorld()
            }
            world.save()
        }
    }
}