MiniDBMS
========

Mini Database Management System

CS448 project.

---------------
Project Detail:
---------------
See doc/handout.pdf for details


------
Usage:
------

Info
~~~~
The main function is in main.Console

Compile
~~~~~~~
It's already compiled. All the class files are in ./bin/
Or you can import project into eclipse workplace, and compile from there.

Run
~~~

- From Terminal
Run `java -cp ./bin/ main.Console username (< example.sql > output)`
Or `java -cp ./bin/ main.Console`

- By using Eclipse
Simply import the project and run it.


------------
Other files:
------------

- `*.ser`
Storage of the database content.
    - users.ser
    Store all users' info
    - schemas.ser
    Store all schemas' info
    - tables.ser
    Store all tables and their tuples

- `output[1-5].ser`
The output of the example input.
*Notice: The output1 contains all the output from output1 to output5*

- `*.sql`
The example sql file containing all the sql commands.
