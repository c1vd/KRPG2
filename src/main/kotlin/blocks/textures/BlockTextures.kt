package blocks.textures

import org.openrndr.draw.ColorBuffer
import org.openrndr.draw.loadImage


/* файл, содержащий предзагруженные текстуры для блоков */

val dirtTexture: ColorBuffer = loadImage("data/images/dirt.jpg")
val bedrockTexture: ColorBuffer = loadImage("data/images/bedrock.jpg")
val mudTexture: ColorBuffer = loadImage("data/images/mud.jpg")
// val unknownTexture: ColorBuffer = loadImage("data/images/unknown.jpg")