package cardengine.test.dsl

import cardengine.dsl.*
import javax.xml.transform.Templates

class TestLimitInteger {
    fun test() {
        CardCollection(GameTemplates.cards) power above 2
    }
}