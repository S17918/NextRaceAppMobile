package nextrace.app.models

class Race(val id: Int, val category: Category, val track: Track, val laps: Int, val raceName: String, val tookPlace: Boolean, val eventList: EventList) {
}