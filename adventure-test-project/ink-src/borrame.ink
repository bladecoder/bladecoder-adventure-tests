VAR test=1
VAR test2=1

~ temp tmp = "hola"

+ {test} cadena1.
->END
+ {test} [cadena2.]
->END
+ {test}{test2} cadena31. [cadena32.] cadena33.
->END
+  cadena4.
cadena5.
->END

+  [cadena7.]
-> END

+  {tmp == "hola3"} {tmp != "hola4"}cadena8 [cadena9.] cadena10
{tmp == "hola2":
    xxx
}
-> END

cadena6.