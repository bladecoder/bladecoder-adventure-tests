=== multiflow ===

= flow1
> Goto: actor=GREEN, target=GREEN, pos=-800_0
> Animation: animation=GREEN.stand.right, wait=false

GREEN> I am the green character.

> Goto: actor=GREEN, target=GREEN, pos=800_0
> Animation: animation=GREEN.stand.left, wait=false

GREEN> This is the flow 1.

-> flow1

= flow2
> Goto: actor=PLAYER, target=PLAYER, pos=800_0
> Animation: animation=PLAYER.stand.left, wait=false

PLAYER> I am the white character.

> Goto: actor=PLAYER, target=PLAYER, pos=-800_0
> Animation: animation=PLAYER.stand.right, wait=false

PLAYER> This is the flow 2.

-> flow2
