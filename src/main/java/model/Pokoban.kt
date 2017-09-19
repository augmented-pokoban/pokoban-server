package model

class Pokoban(val id: String) {
    val agents: List<Agent> = ArrayList<Agent>()
    val boxes: List<Box> = ArrayList<Box>()
    val goals: List<Goal> = ArrayList<Goal>()
}

