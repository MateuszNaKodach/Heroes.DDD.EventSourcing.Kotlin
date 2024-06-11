package pl.nakodach.pl.nakodach.shared.buildingblocks.domain

interface IDecider<in Command, State, Event> {
    val decide: (Command, State) -> List<Event>
    val evolve: (State, Event) -> State
    val initialState: State

    fun decide(events: Collection<Event>, command: Command) = decide(command, evolve(events))

    private fun evolve(givenEvents: Collection<Event>): State =
        givenEvents.fold(initialState) { state, event -> evolve(state, event) }
}

data class Decider<in Command, State, Event>(
    override val decide: (Command, State) -> List<Event>,
    override val evolve: (State, Event) -> State,
    override val initialState: State
) : IDecider<Command, State, Event>;