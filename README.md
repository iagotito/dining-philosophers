
# Dining Philosophers problem with Semaphores and with Monitors

This repository contains the solutions to the Dining Philosophers 
problem with both strategies: using Semaphores and using Monitors 
to syncronize the philosophers process.

> You can read more about this problem in it's
> [Wikipedia Page](https://en.wikipedia.org/wiki/Dining_philosophers_problem).

## How to run

Execute the main method in `MainPhisolophersDinner.java` and 
choose which solution do you want to run: `sem` to the solution 
using semaphores or `mon` to the solution using monitors. Type 
anything else to cancel.

> I made this on VS Code so it don't have the folders structure 
created by an IntelliJ or Eclipse, then you may have some trouble 
executing this on your favorite IDE.

## The execution

When the program starts it will create N philosophers processes 
running at the same time. When a philosopher change it states 
it will print the states array of all philosophers. Then will be 
easy to see that a philosopher never eats when a neighbor is 
eating.

> If you wish to see each philosopher log like "pholosopher X is 
trying to take the cutlely" just remove the comments ih the code.
