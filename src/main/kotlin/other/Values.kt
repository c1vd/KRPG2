package other

import kotlin.math.max

/* значения, нужные для работы программы */

var blockSize = 32.0
    set(value){
        field = org.openrndr.math.clamp(value, 16.0, 48.0)
    }
var heightOfScreen = 1000
var widthOfScreen = 1200
var blocksPerSemiHeight = heightOfScreen / (2 * blockSize)
var blocksPerSemiWidth = widthOfScreen / (2 * blockSize)
var renderDistance = max(blocksPerSemiWidth, blocksPerSemiHeight) + 1

fun updateValues(){
    blocksPerSemiHeight = heightOfScreen / (2 * blockSize)
    blocksPerSemiWidth = widthOfScreen / (2 * blockSize)
    renderDistance = max(blocksPerSemiWidth, blocksPerSemiHeight) + 1
}