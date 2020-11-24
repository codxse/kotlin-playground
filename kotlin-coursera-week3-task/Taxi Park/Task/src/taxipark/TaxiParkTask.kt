package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
        this.allDrivers
                .filter { it !in this.trips.map { it.driver } }
                .toSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
        this.allPassengers.filter {
            this.trips
                    .filter { trip -> it in trip.passengers }
                    .count() >= minTrips
        } .toSet()



/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        this.allPassengers
                .filter { p: Passenger ->
                    this.trips
                            .filter { trip: Trip -> p in trip.passengers }
                            .filter { it.driver == driver }
                            .count() > 1

                }
                .toSet()




/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
        this.allPassengers
                .filter { ps ->
                    val passengers = this.trips.filter { trip -> ps in trip.passengers }
                    val ps1 = passengers.filter { it.discount != null }
                    val ps2 = passengers.filter { it.discount == null }
                    ps1.count() > ps2.count()
                }
                .toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return if (this.trips.isEmpty()) {
        return null
    } else {
        val maxDuration = this.trips.map{ it.duration } .max() ?: 0;
        val rgs = (0..maxDuration).chunked(10).map {
            if (it.count() == 10) it else it.first()..it.first()+9
        }
        val _tripInRange = rgs.map trip@{ rg ->
            val n = this.trips.filter { it.duration in rg } .count()
            return@trip Pair(n, rg.first()..rg.last())
        }
        val (_, value) = _tripInRange.sortedBy { it.first }.last()
        value
    }
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    return if (this.trips.isEmpty()) {
        false
    } else {
        val totalCost = this.trips.map { it.cost } .sum()
        val cosByDriver = this.trips
                .groupBy { it.driver }
                .mapValues { (_, v) -> v.sumByDouble { it.cost } }
                .toList()
                .sortedByDescending { (_, v) -> v }
                .toMap()
        val (nDriver, _) = cosByDriver.values.fold(Pair(0,0.0)) fold@{
            (nDriver, total), value ->
            if (total >= (totalCost * 0.8)) {
                return@fold Pair(nDriver, total)
            } else {
                return@fold Pair(nDriver + 1, total + value)
            }
        }
        return nDriver <= (this.allDrivers.size * 0.2)
    }
}