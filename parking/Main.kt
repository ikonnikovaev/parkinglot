package parking

import java.util.*

class Car {
    val registrationNumber:String
    val color: String

    constructor(registrationNumber: String, color: String) {
        this.registrationNumber = registrationNumber
        this.color = color
    }
}

class ParkingLot {
    val size: Int
    val spots: Array<Car?>

    constructor(size: Int) {
        this.size = size
        this.spots = arrayOfNulls<Car>(size)
    }

    fun park(car: Car): Int {
        for (s in spots.indices) {
            if (spots[s] == null) {
                spots[s] = car
                println("${car.color} car parked on the spot ${s + 1}.")
                return s
            }
        }
        println("Sorry, parking lot is full.")
        return -1
    }

    fun leave(s: Int): Boolean {
        if (spots[s] != null) {
            spots[s] = null
            println("Spot ${s + 1} is free.")
            return true
        }
        else {
            println("There is no car in the spot ${s + 1}.")
            return false
        }


    }

    fun printStatus() {
        var isEmpty = true
        for (s in spots.indices) {
            if (spots[s] != null) {
                println("${s + 1} ${spots[s]?.registrationNumber} ${spots[s]?.color}")
                isEmpty = false
            }
        }
        if (isEmpty) {
            println("Parking lot is empty.")
        }
    }

    fun regByColor(color: String): List<String?> {
        val carsOfColor = spots.filter {
            it != null && it.color.toLowerCase().equals(color.toLowerCase())
        }
        val registrationNumbersOfColor = carsOfColor.map{
            it?.registrationNumber
        }
        if (registrationNumbersOfColor.isEmpty()) {
            println("No cars with color $color were found.")
        }
        else {
            println(registrationNumbersOfColor.joinToString())
        }
        return registrationNumbersOfColor
    }

    fun spotByColor(color: String): List<Int> {
        var spotsOfColor = spots.indices.filter {
            spots[it] != null && spots[it]?.color?.toLowerCase().equals(color.toLowerCase())
        }
        if (spotsOfColor.isEmpty()) {
            println("No cars with color $color were found.")
        }
        else {
            println(spotsOfColor.map{it + 1}.joinToString())
        }
        return spotsOfColor
    }

    fun spotByRegistrationNumber(registrationNumber: String): List<Int> {
        var spotsOfReg = spots.indices.filter {
            spots[it] != null && spots[it]?.registrationNumber.equals(registrationNumber)
        }
        if (spotsOfReg.isEmpty()) {
            println("No cars with registration number $registrationNumber were found.")
        }
        else {
            println(spotsOfReg.map{it + 1}.joinToString())
        }
        return spotsOfReg
    }


}




fun main() {

    var parkingLot: ParkingLot? = null

    val sc = Scanner(System.`in`)

    while (true) {
        val input = sc.nextLine().split(" ")
        val command = input[0]
        if ("exit".equals(command)) {
            break
        }
        else if ("create".equals(command)) {
            val size = input[1].toInt()
            parkingLot = ParkingLot(size)
            println("Created a parking lot with $size spots.")
        }
        else if (parkingLot == null) {
            println("Sorry, parking lot is not created.")

        }
        else if ("park".equals(command)) {
            val regNumber = input[1]
            val color = input[2]
            val car = Car(regNumber, color)
            parkingLot.park(car)

        }
        else if ("leave".equals(command)) {
            val spot = input[1].toInt() - 1
            parkingLot.leave(spot)
        }
        else if ("status".equals(command)) {
            parkingLot.printStatus()
        }
        else if ("spot_by_color".equals(command)) {
            val color = input[1]
            parkingLot.spotByColor(color)
        }
        else if ("reg_by_color".equals(command)) {
            val color = input[1]
            parkingLot.regByColor(color)
        }
        else if ("spot_by_reg".equals(command)) {
            val registrationNumber = input[1]
            parkingLot.spotByRegistrationNumber(registrationNumber)
        }

    }

}
