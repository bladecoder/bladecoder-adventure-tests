-> d1

=== d1 ===

Hi! #actor:player
How are you? #actor:player

Fine, thanks! #actor: green

Are you hungry? #actor:green

* player: No[], I don't.
	Ok, let me know when you want some food. #actor:green
* Yes #actor:player
	Would you like a hamburger? #actor:green
	** Yes #actor:player
		Maybe you are too fat, here you have a banana. #actor:green
	** No #actor:player
		Well, here you have a banana. #actor:green

- Bye   #actor:player

-> END
