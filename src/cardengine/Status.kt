package cardengine

// Status effects
data class Status(
    var armor: Int = 0,
    var shield: Int = 0,
    var vitality: Int = 0,
    var bleeding: Int = 0,
    var poison: Int = 0,
    var lock: Boolean = false,
    var doom: Int = 0
)