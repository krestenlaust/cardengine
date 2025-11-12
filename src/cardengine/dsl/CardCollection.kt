package cardengine.dsl

import cardengine.Card

class CardCollection constructor(
    val collection: List<Card>
) {
    private val transformations = mutableListOf<(MutableList<Card>) -> List<Card>>()

    infix fun power(other: LimitInteger): CardCollection {
        transformations += { list -> list
            .filter { card -> card.power != null }
            .filter { card -> other.test(card.power!!) }}
        return this
    }

    fun toList(): List<Card> {
        return transformations.fold(collection) { acc, transform -> transform(acc as MutableList<Card>) }
    }
}

/*
infix fun and(other: CardCollection): CardCollection {
    transformations += { list -> list.addAll(other.) }
    return this
}

infix fun or(other: CardCollection): CardCollection {
    transformations += { list -> list.(predicate) }
    return this
}*/
