EXTERNAL cutMode(value)
EXTERNAL goto(actor, target)
EXTERNAL animation(actor, anim, wait)
EXTERNAL setModelProp(prop, value)
EXTERNAL getModelProp(prop)

-> init

=== function cutMode(value) ===
(CUTMODE {value:true|false})

=== function goto(actor, target) ===
(GOTO {actor} to {target})

=== function animation(actor, anim, wait) ===
(ANIMATION {actor}.{anim} {wait})

=== function setModelProp(prop, value) ===
(SETMODELPROP {prop}={value})

=== function getModelProp(prop) ===
(GETMODELPROP {prop})


=== init ===

~goto("red", "target")
~animation("green", "stand.left", false)

~setModelProp("currentScene.actors[red].state", "RED COLOR")

Hi! This is a graphic adventure written with Ink! #actor:green

Can you drive your game and story throught the Ink language? #actor:green

* Yes, I can do everything with Ink.
* I don't think so.
* I know nothing about Ink

- Well, you did it! #actor:green

The red character state is: {getModelProp("currentScene.actors[red].state")}

-> END



