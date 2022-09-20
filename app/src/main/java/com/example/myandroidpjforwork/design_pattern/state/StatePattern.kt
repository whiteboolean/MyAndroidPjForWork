package com.example.myandroidpjforwork.design_pattern.state


/**
 * 状态模式和策略模式有点相似，都能实现某种算法，业务逻辑的切换
 */
sealed class WaterMachineState(open val machine:WaterMachine){
    fun turnHeating(){
        if(this !is Heating){
            println("turn heating")
            machine.state = machine.heating
        } else {
            println("The state is already heating mode.")
        }
    }
    fun turnCooling(){
        if(this !is Cooling){
            println("turn cooling")
            machine.state = machine.cooling
        } else {
            println("The state is already cooling mode.")
        }
    }
    fun turnOff(){
        if(this !is Off){
            println("turn off")
            machine.state = machine.off
        } else {
            println("The state is already off.")
        }
    }
}

class Off(override val machine: WaterMachine):WaterMachineState(machine)
class Heating(override val machine: WaterMachine):WaterMachineState(machine)
class Cooling(override val machine:WaterMachine):WaterMachineState(machine)

class WaterMachine{
    var state:WaterMachineState
    val off = Off(this)
    val heating = Heating(this)
    val cooling = Cooling(this)
    init {
        this.state = off
    }

    fun turnHeating(){
        this.state.turnHeating()
    }

    fun turnCooling(){
        this.state.turnCooling()
    }

    fun turnOff(){
        this.state.turnOff()
    }
}


enum class Moment {
    EARLY_MORNING,//早上上班
    DRINKING_WATER,//日常饮水
    INSTANCE_NOODLES,//吃泡面
    AFTER_WORK//下班
}

fun waterMachineOps(machine: WaterMachine, moment: Moment) {
    when (moment) {
        Moment.EARLY_MORNING,
        Moment.DRINKING_WATER -> when (machine.state) {
            !is Cooling -> machine.turnCooling()
            else -> {}
        }
        Moment.INSTANCE_NOODLES -> when (machine.state) {
            !is Heating -> machine.turnHeating()
            else -> {}
        }
        Moment.AFTER_WORK -> when (machine.state) {
            !is Off -> machine.turnOff()
            else -> {}
        }
    }
}
fun main() {
    val machine = WaterMachine()
    waterMachineOps(machine,Moment.DRINKING_WATER)
    waterMachineOps(machine,Moment.INSTANCE_NOODLES)
    waterMachineOps(machine,Moment.DRINKING_WATER)
    waterMachineOps(machine,Moment.AFTER_WORK)
}
