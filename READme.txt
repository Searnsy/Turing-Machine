This program is designed to mimic the functionality of a Turing Machine. The TuringMachine class acts as the machine,
and it holds tapes from the Tape class. The machine processes the tapes using rules from the Rule class.

The Main class creates a Turing Machine based upon two input files specified by the user.
The formatting of the input files has changed a bit as greater functionality was required.
To see the most up to date formatting structure, run the program with:

Rules/Algebra.txt
Tapes/AlgebraSample.txt



To create your own set of rules, format the file as follows:

/A comment only requires a single slash at the start of the line, but
any improperly formatted line will be skipped over.

//Each rule has 5 parts: preState, preCondition, postCondition, shift, postState
//If the current state is equal to the preState, and the cells at the current spot on the tapes match preCondition,
//Then the rule is run as follows: change tapes to postCondition, shift each tape in the direction noted, change to postState.
//Details:
//The first valid rule that fits the requirements is always run.
//All tape cells hold java Strings. Technically, this could be used to simplify operations
//preState: if the state doesn't match, it will skip onto the next state without checking the condition
//preCondition: if a * is used, it will assume that any string is valid here
//postCondition: if a * is used, it will write to the tape whatever was already there
//shift: any character other than R,L will cause the tape to stay in its current location
//postState: the program finishes running when -1 is found as the postState

//Here are some examples of valid rules
(0,{1,a},{2,b},{L,R},1)
(0,{2,b},{1,b},{L,R},1)
(0,{ , },{-,-},{R,L},0)
(1,{*,*},{*,*},{S,S},-1)



To create your own tape, format the file as follows:
Each line represents a different tape.
The first segment in a line tells the machine where on the tape to start as the current block.
if -1 is found, it will start at the last cell.
The comma is reserved for formatting of this file. Do not use it as a character within a tape unless it was added by rule execution.
The Turing Machine will default add extra tapes up to the number of tapes within the preCondition of the first rule.
Good practice has all rules written for the same number of tapes.

Sample input tapes are as follows:
0,((4x(((15/4)+19)%5))-1)
-1,Hello
3,T35t1ng



After the most recent revisions, the only set of rules and tapes properly formatted here is:
Rules/Algebra.txt
Tapes/AlgebraSample.txt

The Algebra Rule Set has the functionality to interpret any operations if they are defined properly.
The rules assume that everything is fully parenthesized (i.e. nothing is left to orders of operation)
The current implementations handle only natural numbers, so it will run infinitely in the case of negative numbers or 0 as an input.

Right now, there are implementations for the following operations (a,b != 0):
a+b
a-b (a > b)
axb
a/b (integer division truncates answer)
a%b

The sample in AlgebraSample reduces as follows:
((4x(((15/4)+19)%5))-1)
((4x((3+19)%5))-1)
((4x(22%5))-1)
((4x2)-1)
(8-1)
7