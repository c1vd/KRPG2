package math

import kotlin.math.max

/* константы, часть из которых планируется превратить в изменяемые(они указаны to var) */

var blockSize = 32.0
    set(value){
        field = org.openrndr.math.clamp(value, 16.0, 48.0)
    }
const val heightOfScreen = 1000 // to var
const val widthOfScreen = 1200 // to var
var blocksPerSemiHeight = heightOfScreen / (2 * blockSize)
var blocksPerSemiWidth = widthOfScreen / (2 * blockSize)
var worldSizeX = 100
var worldSizeY = 100
var renderDistance = max(blocksPerSemiWidth, blocksPerSemiHeight) + 1

fun updateValues(){
    blocksPerSemiHeight = heightOfScreen / (2 * blockSize)
    blocksPerSemiWidth = widthOfScreen / (2 * blockSize)
    renderDistance = max(blocksPerSemiWidth, blocksPerSemiHeight) + 1
}