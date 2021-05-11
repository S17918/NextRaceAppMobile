package nextrace.app.transporters

import nextrace.app.models.Race

class RaceTransporter(val race: Race) {
    companion object{
        @JvmStatic lateinit var raceObject: Race
    }

    init {
        raceObject = race
    }
}