package items

import blocks.DefaultBlock

interface BlockItem : Item {
    val block: DefaultBlock
}