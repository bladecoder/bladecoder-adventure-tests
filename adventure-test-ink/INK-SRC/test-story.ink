EXTERNAL cutMode(value)
EXTERNAL goto(actor, target)
EXTERNAL animation(actor, anim, wait)

-> init

=== function cutMode(value) ===
(CUTMODE {value:true|false})

=== function goto(actor, target) ===
(GOTO {actor} to {target})

=== function animation(actor, anim, wait) ===
(ANIMATION {actor}.{anim} {wait})

=== init ===

~goto("red", "target")
~animation("green", "stand.left", false)

Hi! This is a graphic adventure written with Ink! #actor:green

Can you drive your game and story throught the Ink language? #actor:green

* Yes, I can do everything with Ink.
* I don't think so.
* I know nothing about Ink

- Well, you did it! #actor:green

-> END



