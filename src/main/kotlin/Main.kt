import camera.Camera
import render.Draw
import entities.Player
import extensions.toBlockVector2
import math.*
import org.openrndr.*
import org.openrndr.color.ColorRGBa
import org.openrndr.math.Vector2
import world.World


var frameTime: Double = 0.0


fun main() {
    application {
        configure {
            width = widthOfScreen
            height = heightOfScreen
            windowResizable = true
        }
        program {
            var right = false
            var left = false
            var up = false
            var down = false

            val defaultPlayerX = 0.0
            val defaultPlayerY = 0.0

            val world = World("world1.world")
            val player = Player(Vector2(defaultPlayerX, defaultPlayerY), 100.0, world, Vector2(0.5, 0.5), speed = 5.0)
            val camera = Camera(player)
            val renderer = Draw(drawer, world, camera)

            var begin = 0.0
            keyboard.keyDown.listen {
                if(it.key == KEY_ARROW_RIGHT){
                    right = true
                }
                if(it.key == KEY_ARROW_LEFT){
                    left = true
                }
                if(it.key == KEY_ARROW_UP){
                    up = true
                }
                if(it.key == KEY_ARROW_DOWN){
                    down = true
                }
            }
            keyboard.keyUp.listen {
                if(it.key == KEY_ARROW_RIGHT){
                    right = false
                }
                if(it.key == KEY_ARROW_LEFT){
                    left = false
                }
                if(it.key == KEY_ARROW_UP){
                    up = false
                }
                if(it.key == KEY_ARROW_DOWN){
                    down = false
                }
            }

            extend {
                frameTime = seconds - begin
                begin = seconds

                widthOfScreen = width
                heightOfScreen = height

                updateValues()
                // camera moving
                camera.moveCamera()

                // screen cleaning
                renderer.clearScreen()

                // FPS and FrameTime
                renderer.setFillColor(ColorRGBa.WHITE)
                drawer.text(
                    "FrameTime: ${
                        (frameTime * 10000).toInt().toDouble() / 10
                    } ms; FPS: ${(1 / frameTime).toInt()}; Player X: ${player.position.x}; Player Y: ${player.position.y}", 10.0, 20.0
                )

                drawer.rectangle(Vector2(widthOfScreen / 2.0, heightOfScreen / 2.0), 16.0, 16.0)

                if (right){
                    player.right(frameTime)
                }
                if (left){
                    player.left(frameTime)
                }
                if(up){
                    player.up(frameTime)
                }
                if(down){
                    player.down(frameTime)
                }

                // render
                renderer.drawWorld()
            }
            world.save()
        }
    }
}