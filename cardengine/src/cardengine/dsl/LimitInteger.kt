package cardengine.dsl

class LimitInteger() {
    var polarity: Polarity? = null;
    var value: Int? = null;

    infix fun above(other: Int): LimitInteger {
        polarity = Polarity.Above;
        value = other;
        return this
    }

    infix fun below(other: Int): LimitInteger {
        polarity = Polarity.Below;
        value = other;
        return this
    }

    infix fun equal(other: Int): LimitInteger {
        polarity = Polarity.Equal;
        value = other;
        return this
    }

    fun test(other: Int): Boolean {
        checkNotNull(value)

        return when (polarity) {
            Polarity.Above -> return value!! > other
            Polarity.Below -> return value!! < other
            Polarity.Equal -> return value!! == other
            else -> error("Invalid polarity")
        }
    }
}
/*
infix fun above(value: Int): LimitInteger =
    LimitInteger().apply {
        polarity = Polarity.Above
        this.value = value
    }

infix fun below(value: Int): LimitInteger =
    LimitInteger().apply {
        polarity = Polarity.Below
        this.value = value
    }

infix fun equal(value: Int): LimitInteger =
    LimitInteger().apply {
        polarity = Polarity.Equal
        this.value = value
    }*/