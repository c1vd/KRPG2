package entities

import org.openrndr.math.Vector2

interface Entity{
    var position: Vector2
    var health: Double
    var maxHealth: Double
    val size: Vector2
}