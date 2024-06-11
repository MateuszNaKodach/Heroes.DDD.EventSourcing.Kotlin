package pl.nakodach.pl.nakodach.shared.buildingblocks.domain

interface IDecider<in C, S, E> {
    val decide: (C, S) -> List<E>
    val evolve: (S, E) -> S
    val initialState: S

    fun decide(events: Collection<E>, command: C) = decide(command, evolve(events))

    private fun evolve(givenEvents: Collection<E>): S =
        givenEvents.fold(initialState) { state, event -> evolve(state, event) }
}

data class Decider<in C, S, E>(
    override val decide: (C, S) -> List<E>,
    override val evolve: (S, E) -> S,
    override val initialState: S
) : IDecider<C, S, E>;