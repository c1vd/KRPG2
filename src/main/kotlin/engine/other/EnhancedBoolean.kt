package engine.other

class EnhancedBoolean(private var value: Boolean) {
    fun reverse() {
        value = !value
    }

    fun setFalse(){
        value = false
    }

    fun setTrue(){
        value = true
    }

    fun getValue(): Boolean{
        return value
    }

}