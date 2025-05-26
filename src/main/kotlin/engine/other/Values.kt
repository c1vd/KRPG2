package engine.other

import org.openrndr.math.clamp
import kotlin.math.max

/* значения, нужные для работы программы */

var blockSize = 32.0
    set(value){
        field = clamp(value, 16.0, 48.0)
    }
const val defaultHeightOfScreen = 1000
const val defaultWidthOfScreen = 1200
var blocksPerSemiHeight = defaultHeightOfScreen / (2 * blockSize)
var blocksPerSemiWidth = defaultWidthOfScreen / (2 * blockSize)
var renderDistance = max(blocksPerSemiWidth, blocksPerSemiHeight) + 1

fun updateValues(width: Int, height: Int){
    blocksPerSemiHeight = height / (2 * blockSize)
    blocksPerSemiWidth = width / (2 * blockSize)
    renderDistance = max(blocksPerSemiWidth, blocksPerSemiHeight) + 1
}