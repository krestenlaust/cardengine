package cardengine

object GameTemplates {
    val cards: List<Card> = listOf(
        Card(null,
            "Martin (Ham de trælse)",
            10,
            Faction.CAS,
            false,
            listOf(CardTag.G4S).toMutableList(),
            7,
            2),
        Card(null,
            "Annette fra AAU Booking",
            10,
            Faction.CAS,
            false,
            listOf(CardTag.AAUBooking, CardTag.Secretary).toMutableList(),
            3,
            0)
    )
}
